## List 相关

### 1. ArrayList 和 LinkedList 有什么区别？

`ArrayList` 和 `LinkedList` 都实现了List接口，有以下的不同点：

- `ArrayList` 是基于索引的数据接口。它的底层是数组。它可以以 `O(1)` 时间复杂度对元素进行随机访问。以此对应，`LinkedList` 是以元素列表的形式存储的数据，每一个元素都和它的前一个后一个元素链接在一起，在这种情况下，查找某个元素的时间复杂度是`O(n)`。 
- 相对于 `ArrayList`，`LinkedList` 的插入，添加，删除操作速度更快，因为当元素被添加到集合任意位置的时候，不需要像数组那样重新计算大小或者是更新索引。
- `LinkedList` 比 `ArrayList` 更占内存，因为`LinkedList` 为每一个节点存储了两个引用，一个指向前一个元素，一个指向下一个元素。



### 2. ArrayList 与 LinkedList 的不区别？

最明显的区别是 ArrrayList 底层的数据结构是数组，支持随机访问，而 LinkedList 的底层数据结构书链表，不支持随机访问。使用下标访问一个元素，ArrayList 的时间复杂度是 O(1)，而 LinkedList 是 O(n) 。



### 3. Java 中的 LinkedList 是单向链表还是双向链表？

是双向链表。 



### 4. 写一段代码在遍历 ArrayList 时移除一个元素？

该问题的关键在于面试者使用的是 ArrayList 的 remove() 还是 Iterator 的 remove()方法。这有一段示例代码，是使用正确的方式来实现在遍历的过程中移除元素，而不会出现 ConcurrentModificationException 异常的示例代码。 



### 5. ArrayList 和 HashMap 的默认大小是多数？

在 Java 7 中，ArrayList 的默认大小是 10 个元素，HashMap 的默认大小是16个元素（必须是2的幂）。这就是 Java 7 中 ArrayList 和 HashMap 类的代码片段： 

```java
// from ArrayList.java JDK 1.7 
private static final int DEFAULT_CAPACITY = 10; //from HashMap.java JDK 7 
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```



