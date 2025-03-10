## HashMap - 源码解析

#### 基本属性

```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```

Hash 表默认容量 1 << 4，1 向左移动4位 0b10000=16。

```java
static final int MAXIMUM_CAPACITY = 1 << 30;
```

Hash 表最大容量 $2^{30}$，`int` 四个字节，最大正数为 $2^{31} - 1$。

```java
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

默认负载因子或则说扩容因子，Hash 表元素个数达到 ${容量} * {负载因子}$ 时，Hash 表需要进行扩容。

```java
static final int TREEIFY_THRESHOLD = 8;
```

链表转化为树的阈值，当某链表元素个数达到此阈值时，将转化为红黑树。

```java
static final int UNTREEIFY_THRESHOLD = 6;
```

树转化为链表阈值，当树元素个数达到此阈值时，树将转化为链表。

```java
static final int MIN_TREEIFY_CAPACITY = 64;
```

当 hash 表内元素达到此阈值时，链表需要转化为红黑树。

```java
transient Node<K,V>[] table;
```

Hash 表的存储结构（数组）。

```java
transient int size;
```

Hash 表内键值对个数。

```java
transient int modCount;
```

Hash 表结构修改次数。

```java
int threshold;
```

阈值，它的下一次的值将在扩容时被赋值  $={容量}*{负载因子}$

```java
final float loadFactor;
```

负载因子，默认 0.75

#### 构造方法

```java
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    // 计算获得一个和指定容量相近的2的幂的数作为哈希表的容量
    this.threshold = tableSizeFor(initialCapacity);
}

public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
}

public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    // 初始化哈希表并遍历m调用核心添加方法进行键值对的添加
    putMapEntries(m, false);
}
```

初始化中可以看出，只修改了两个内部属性：容量，阈值。这里阈值的初始化和负载因子不是${容量} * {负载因子}$的关系，而是 >= 容量的最小的2的幂，如容量为 10，阈值就是 16.

#### 核心-内部类

##### 链表

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
```

##### 红黑树

```java
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
    TreeNode(int hash, K key, V val, Node<K,V> next) {
        super(hash, key, val, next);
    }
    //........
    //........
}
```

红黑树理解为平衡二叉搜索树即可，可以在 $O(logn)$ 的复杂度快速查找到想要的元素，相对于普通的平衡二叉搜索树而言，红黑树引入的红黑节点规则允许任意节点的子树的高度差大于1，但是要满足红黑节点个数的规则，这样可以减少添加节点使得树不平衡时维护平衡操作的次数（详见[百度百科](https://baike.baidu.com/item/%E7%BA%A2%E9%BB%91%E6%A0%91/2413209?fr=aladdin)）。

#### 核心方法

##### hash

```java
static final int hash(Object key) {
    int h;
    // key 取 hashCode, 高于 16位的与低位进行异或运算
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

##### put - 插入

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 若 table 为空，则调用 resize() 进行初始化。
    if ((tab = table) == null || (n = tab.length) == 0)
        // 初始化后得到 table 数组的长度
        n = (tab = resize()).length;
    // (n - 1) & hash 得到对应的下标，若对应下标为空，则直接创建链表，并赋值给对应下标。
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    // 下面是对应下标不为空，说明存在 hash 冲突，同一个桶里至少已存在一个键值对。
    // 注意：由于存在 hash 冲突，所以接下来的判断不能单凭 hash 值进行判断，必须确保 key 值相等才能找到正确的键值对。
    else {
        Node<K,V> e; K k;
        // 判断桶的首个节点是否和插入的 key 相等，如果是，则进行值的覆盖。
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 如果哈希桶是红黑树，那么调用红黑树的插入方法进行插入，同样的，若存在则直接返回节点，不存在则进行插入操作并返回 null
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 最后就是哈希桶为链表，且首节点 key 和插入的 key 不相等，需要对链表进行遍历。
        else {
            for (int binCount = 0; ; ++binCount) {
                // 遍历完链表后判定不存在相等的 key，尾部新增节点
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 插入后判断链表长度是否满足转化为红黑树的阈值，满足则将链表转化为红黑树。
                    if (binCount >= TREEIFY_THRESHOLD - 1) 
                        // 将链表转化为红黑树
                        treeifyBin(tab, hash);
                    break;
                }
                // 如果查找到相等的 key，则退出循环，后续将进行值的覆盖。
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { 
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    // 元素个数+1，并判断元素个数是否超过阈值，超过则进行扩容
    if (++size > threshold)
        resize();
    // 空钩，LinkedHashMap 中有实现
    afterNodeInsertion(evict);
    return null;
}
```

##### get - 查找

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    // 非空情况下通过 (n - 1) & hash 计算得到哈希表内的下标。
    if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
        // 首先判断桶首节点是否为需要查找的目标，是则返回
        if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            // 如果桶是红黑树则调用对应方法查找
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            // 桶为链表则遍历查找
            do {
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

##### remove - 删除

```java
public V remove(Object key) {
    Node<K,V> e;
    return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
}

final Node<K,V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
    Node<K,V>[] tab; Node<K,V> p; int n, index;
    // (n - 1) & hash 找到 hash 值对应桶并判断桶是否为空
    if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
        Node<K,V> node = null, e; K k; V v;
        // 判断桶首节点是否为要删除的元素
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            node = p;
        else if ((e = p.next) != null) {
            // 若桶为红黑树则调用进行查找
            if (p instanceof TreeNode)
                node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
            // 桶为链表遍历查找
            else {
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
        }
        if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
            // 红黑树，则调用删除方法进行删除
            if (node instanceof TreeNode)
                ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
            // 链表且为首节点
            else if (node == p)
                tab[index] = node.next;
            else
                p.next = node.next;
            ++modCount;
            --size;
            // 空钩
            afterNodeRemoval(node);
            return node;
        }
    }
    return null;
}
```

##### resize - 扩容或初始化

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    // 初始化后 oldCap 才会有值，所以大于 0 的情况下判断边界，确定容量和阈值，排除边界情况，通常是扩容为以前的两倍
    if (oldCap > 0) {
        // 若容量大于等于最大容量限制，则将阈值调整至 int 的最大值
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 容量和阈值均扩大一倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; 
    }
    // 以下是初始化的判断
    else if (oldThr > 0)
        // 容量 = 阈值
        newCap = oldThr;
    // 设置默认值
    else {
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
    }
    // 更新阈值
    threshold = newThr;
    // 初始化新容量的桶
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                // 遍历链表并筛选出两条链表:
                // 1) 一条仍然适合放在当前桶内(hash & oldCap == 0)
                // 2) 一条适合放在下标为 j + oldCap 的桶内
                // 因为容量扩展到了原始容量的一倍，可以简单做一个推理：
                // 假设原始容量为 8，现在有一个元素的 key 的 hash 为 30
                // 根据映射算法 (n - 1) & hash = 7 & 30 = 6，所以原哈希表内当前元素放在下标为 6 的桶内，现在扩容至两倍。
                // 容量为 16，计算 (n - 1) & hash = 15 & 30 = 14
                // 同理计算 hash 为 40 的前后下标：7 & 40 = 0；15 & 40 = 8
                // 可见前后的关系为 j + oldCap，当然其实这种关系和扩展两倍并无关系。实则这类关系和 hash 到 下标的映射有关，
                // 可以观察这些计算规则来进行分析。
                else { 
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

所以，扩容大致可分为两个部分：

1）容量，阈值的扩展

这部分逻辑也适用或者说包含初始化的逻辑，前面 putVal 方法内判断哈希表为空时，就调用了此方法进行初始化。

2）原哈希表内数据迁移

原哈希表数据应该重新定位到正确的桶内。这里链表和红黑树都需要切分为两个部分，一部分仍然可以放在当前桶内，一部分需要放在 j + oldCap 桶内。相对于链表需要注意的是，红黑树被切分后，若红黑树的元素个数小于等于转化的阈值（6），需要将红黑树转化为链表结构。

#### 面试问题

##### 1 扩容因子为什么是 0.75？

前面讲到初始化对象的时候咱们的容量，阈值和扩展因子没有满足 ${阈值} = {容量} * {扩容因子}$，所以再分析分析初始化的代码可知，初始化的时候，若在初始化对象时传入了容量参数，那么相应的阈值就有值，那么初始化将会把阈值赋给容量，此时$阈值=容量$；若初始化对象时未传入容量参数，那么将设置默认的容量 16 及相应的阈值=${容量} * {扩容因子}$。另外，抛开初始化情况，其余情况下大致都满足 ${阈值} = {容量} * {扩容因子}$，所以扩容因子关系到哈希表的扩容，所以，扩容因子越小，则触发扩容越频繁，占用更多的空间；相反则扩容频率越小，容器内 hash 冲突几率更高，进行扩容的代价就越大。所以选择一个适当的因子来平衡偏小和偏大的优缺点。

##### 2 jdk 1.8 中扩容的优化？

不再对每个元素重新计算 hash，而是通过逻辑与的运算进行判断（j + oldCap）。

##### 3 hash 冲突时如何查找元素？

判断 hash 相等再判断 key 相等。











