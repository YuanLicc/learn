## Map 相关

### 1. HashMap 是线程安全的吗?

[重新认识 HashMap](https://zhuanlan.zhihu.com/p/21673805)



### 2. ConcurrentHashMap 在 Java 8 中为什么放弃了分段锁？

[ConcurrentHashMap 源码分析](https://yq.aliyun.com/articles/36781)



### 3. 有顺序的 Map 有哪些？

TreeMap、LinkedHashMap

[LinkedHashMap](http://uule.iteye.com/blog/1522291)



### 4. WeakHashMap 是怎么工作的？

WeakHashMap 的工作与正常的 HashMap 类似，但是使用弱引用作为 key，意思就是当 key 对象没有任何引用时，key/value 将会被回收。 



### 5. Java 中 LinkedHashMap 和 PriorityQueue 的区别是什么？

PriorityQueue 保证最高或者最低优先级的的元素总是在队列头部，但是 LinkedHashMap 维持的顺序是元素插入的顺序。当遍历一个 PriorityQueue 时，没有任何顺序保证，但是 LinkedHashMap 课保证遍历顺序是元素插入的顺序。 



### 6. Java 中的 TreeMap 是采用什么树实现的？

Java 中的 TreeMap 是使用红黑树实现的。 



### 7. Hashtable 与 HashMap 有什么不同之处？

这两个类有许多不同的地方，下面列出了一部分： a) Hashtable 是 JDK 1 遗留下来的类，而 HashMap 是后来增加的。 b）Hashtable 是同步的，比较慢，但 HashMap 没有同步策略，所以会更快。 c）Hashtable 不允许有个空的 key，但是 HashMap 允许出现一个 null key。 



### 8. HashMap源码，实现原理，JDK8以后对HashMap做了怎样的优化？

HashMap是基于哈希表的Map接口的非同步实现，提供所有可选的映射操作，并允许使用null值和null键，不保证映射的顺序；HashMap是一个“链表散列”的数据结构，即数组和链表的结合体；它的底层就是一个数组结构，数组中的每一项又是一个链表，每当新建一个HashMap时，就会初始化一个数组。

[彻底搞懂 HashMap...](https://www.cnblogs.com/wang-meng/p/5808006.html)

而在JDK8中引入了红黑树的部分，当存入到数组中的链表长度大于（默认）8时，即转为红黑树；利用红黑树快速增删改查的特点提高HashMap的性能，其中会用到红黑树的插入、删除、查找等算法。 

[教你初步了解红黑树](https://blog.csdn.net/v_july_v/article/details/6105630)



### 9. HashMap的扩容是怎样扩容的，为什么都是2的N次幂的大小？

当hashmap中的元素越来越多的时候，碰撞的几率也就越来越高（因为数组的长度是固定的），所以为了提高查询的效率，就要对hashmap的数组进行扩容，数组扩容这个操作也会出现在ArrayList中，所以这是一个通用的操作，很多人对它的性能表示过怀疑，不过想想我们的“均摊”原理，就释然了，而在hashmap数组扩容之后，最消耗性能的点就出现了：原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是resize。

那么hashmap什么时候进行扩容呢？当hashmap中的元素个数超过数组大小*loadFactor时，就会进行数组扩容，loadFactor的默认值为0.75，也就是说，默认情况下，数组大小为16，那么当hashmap中元素个数超过16*0.75=12的时候，就把数组的大小扩展为2*16=32，即扩大一倍，然后重新计算每个元素在数组中的位置，而这是一个非常消耗性能的操作，所以如果我们已经预知hashmap中元素的个数，那么预设元素的个数能够有效的提高hashmap的性能。 
比如说，我们有1000个元素new HashMap(1000), 但是理论上来讲new HashMap(1024)更合适，不过，即使是1000，hashmap也自动会将其设置为1024。 但是new HashMap(1024)还不是更合适的，因为0.75*1000 < 1000, 也就是说为了让0.75 * size > 1000, 我们必须这样new HashMap(2048)才最合适，既考虑了&的问题，也避免了resize的问题。

接下来来解释一下为什么数组的大小要为2的幂：

(https://github.com/yuanliccc/interview/tree/master/docs/images/HashMapResize.png)

左边两组是数组长度为16（2的4次方），右边两组是数组长度为15。两组的hashcode均为8和9，但是很明显，当它们和1110“与”的时候，产生了相同的结果，也就是说它们会定位到数组中的同一个位置上去，这就产生了碰撞，8和9会被放到同一个链表上，那么查询的时候就需要遍历这个链表，得到8或者9，这样就降低了查询的效率。同时，我们也可以发现，当数组长度为15的时候，hashcode的值会与14（1110）进行“与”，那么最后一位永远是0，而0001，0011，0101，1001，1011，0111，1101这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！  

所以说，当数组长度为2的n次幂的时候，不同的key算得得index相同的几率较小，那么数据在数组上分布就比较均匀，也就是说碰撞的几率小，相对的，查询的时候就不用遍历某个位置上的链表，这样查询效率也就较高了。  说到这里，我们再回头看一下hashmap中默认的数组大小是多少，查看源代码可以得知是16，为什么是16，而不是15，也不是20呢，看到上面annegu的解释之后我们就清楚了吧，显然是因为16是2的整数次幂的原因，在小数据量的情况下16比15和20更能减少key之间的碰撞，而加快查询的效率。  

所以，在存储大容量数据的时候，最好预先指定hashmap的size为2的整数次幂次方。就算不指定的话，也会以大于且最接近指定值大小的2次幂来初始化的 

HashMap的大小为2的N次幂还有另外一种解释：

因为put方法的实现是根据key的hashCode进行hash运算，得到值hash；根据hash值去确定数组的位置，hash& (table,length.-1)的效率要高于hash%(table.length)，但结果是相同的。

### 10. HashMap，HashTable，ConcurrentHashMap的区别？　　

a、HashMap是非线程安全的，HashTable是线程安全的。

b、HashMap的键和值都允许有null值存在，而HashTable则不行。

c、因为线程安全的问题，HashMap效率比HashTable的要高。

HashMap：

它根据键的hashCode值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，但遍历顺序却是不确定的。 HashMap最多只允许一条记录的键为null，允许多条记录的值为null。HashMap非线程安全，即任一时刻可以有多个线程同时写HashMap，可能会导致数据的不一致。如果需要满足线程安全，可以用 Collections的synchronizedMap方法使HashMap具有线程安全的能力，或者使用ConcurrentHashMap。

Hashtable：

Hashtable是遗留类，很多映射的常用功能与HashMap类似，不同的是它承自Dictionary类，并且是线程安全的，任一时间只有一个线程能写Hashtable，并发性不如ConcurrentHashMap，因为ConcurrentHashMap引入了分段锁。



### 11. 极高并发下HashTable和ConcurrentHashMap哪个性能更好，为什么，如何实现的？

当然是ConcurrentHashMap，因为ConcurrentHashMap引入了分段锁，而HashTable则使用的是方法级别的锁；因此在新版本中一般不建议使用HashTable，不需要线程安全的场合可以使用HashMap，而需要线程安全的场合可以使用ConcurrentHashMap；

 

### 12. HashMap在高并发下如果没有处理线程安全会有怎样的隐患，具体表现是什么？

可能造成死循环，具体表现链表的循环指向；

---

## 概念

---

### HashMap

#### HashMap源码，实现原理，JDK8以后对HashMap做了怎样的优化？

HashMap是基于哈希表的Map接口的非同步实现，提供所有可选的映射操作，并允许使用null值和null键，不保证映射的顺序；HashMap是一个“链表散列”的数据结构，即数组和链表的结合体；它的底层就是一个数组结构，数组中的每一项又是一个链表，每当新建一个HashMap时，就会初始化一个数组； 

而在JDK8中引入了红黑树的部分，当存入到数组中的链表长度大于（默认）8时，即转为红黑树；利用红黑树快速增删改查的特点提高HashMap的性能，其中会用到红黑树的插入、删除、查找等算法。 

---

#### 针对HashMap中某个Entry链太长，查找的时间复杂度可能达到O(n)，怎么优化？

目前在jdk1.8中，采用了新的红黑树的结构来实现，当链表的数量大于8的时，就会将冲突的节点保存在红黑树里。

---

#### 怎样设计实现一个高效的线程安全的HashMap

方法一：通过Collections.synchronizedMap()返回一个新的Map，这个新的map就是线程安全的。 这个要求大家习惯基于接口编程，因为返回的并不是HashMap，而是一个Map的实现。 

方法二：重新改写了HashMap，具体的可以查看java.util.concurrent.ConcurrentHashMap。这个方法比方法一有了很大的改进。

方法一使用的是的synchronized方法，是一种悲观锁。在进入之前需要获得锁，确保独享当前对象。然后做相应的修改/读取。

方法二使用的是乐观锁，只有在需要修改对象时，比较和之前的值是否被人修改了，如果被其他线程修改了，那么就会返回失败。锁的实现，使用的是NonfairSync.。这个特性要确保修改的原子性，互斥性，无法在JDK这个级别得到解决，JDK在此此需要调用JNI方法，而JNI则调用CAS指令来确保原子性与互斥性。

---

#### `public V put(K key, V value);`

该方法调用了putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)方法，其中第一个参数则调用了hash(Object key)方法，利用hash算法求得某个值以此来确定该key对应的元素存储在哈希桶数组中的位置。

----

#### `static final int hash(Object key);`

如果key为null则直接返回0，否者用(key.hashCode())^(h>>>16)。

----

#### `final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict);`

首先判断哈希桶数组是否为空或者长度为0，如果是，则调用resize()方法进行扩容。

然后使用从参数传递过来的hash值与哈希桶数组的长度进行取模运算，得到对应的存储下标（实际上其计算表达式为(n - 1) & hash，其中n为数组长度，因此在HashMap中哈希桶数组的长度必须为2的n次方，因此前面的表达式就等价于用hash与数组长度进行取模运算，并且这种方式的效率要更高），如果在数组中该下标处为null，则创建一个新的结点放置到此处。

如果此处不为null，首先判断第一个元素的hash和key是否与要添加元素的key值和其hash相同，如果相同则用新值覆盖旧的值，并返回旧值。否则，如果该数组处存储的元素形式为红黑树，则调用红黑树的插入方法。不然，则开始遍历链表，如果链表中不存在该key，则新创建一个结点加入到链表中（如果链表长度超过8，将会调用构建称为红黑树），如果链表中存在该key，则用新的值覆盖旧的值，并返回旧值。

元素添加完毕后，检查HashMap中存储的元素个数是否超出了所能存储的最大个数，如果超出，则调用resize()方法进行扩容。

---

#### `final Node<K, V>[] resize();`

初始化或者扩容方法。

首先判断旧table的容量是否大于0，如果大于0则再次判断该容量是否大于等于最大容量MAXIMUM_CAPACITY，如果大于，则设置阈值threshold为int的最大值，返回旧table，这次过后Map中的哈希桶数组的容量和阈值都达到了最大值，将不能继续扩容。否则，如果当前容量小于最大容量，并且扩容过或准备扩容，则将新的哈希桶数组的容量和阈值翻一倍。

否则，如果旧table的阈值大于0，就将新的哈希桶数组的容量设置为旧阈值大小。

否则，如果旧table的容量和阈值都为0，就在这里初始化，将新table的容量和阈值设为默认值，容量为16，阈值为12

经过上述判断后，再次判断新table的阈值是否为0，如果为0，则需要重新计算阈值，使用新table的容量乘加载因子，如果新的容量和计算出来的阈值都小于它们各自约定的最大值，则就使用该值作为新阈值，否则使用int的最大值作为阈值。

根据上面计算出来的新的桶容量创建哈希桶数组，并将table的引用指向该数组。

判断旧数组是否为空，如果不为空，则将旧table的结点rehash到新的table中,如果结点是单个结点，便直接在新的table中进行重定位，如果存储的链表形式已经被转换为了红黑树形式，便调用红黑树的rehash操作,如果是链表形式，则遍历它们，将它们移动到新的bucket或者不变，当节点的hash值与旧table的长度做与操作，为0旧保持原始位置，为1则位置在原始位置的基础上增加旧table容量。

