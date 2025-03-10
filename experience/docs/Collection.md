## 集合公共相关

### 1. List、Set、Map 和 Queue 之间的区别？

List 是一个有序集合，允许元素重复。它的某些实现可以提供基于下标值的常量访问时间，但是这不是 List 接口保证的。Set 是一个无序集合。 



### 2. 用哪两种方式来实现集合的排序？

你可以使用有序集合，如 TreeSet 或 TreeMap，你也可以使用有顺序的的集合，如 list，然后通过 Collections.sort() 来排序。 



### 3. 我们能自己写一个容器类，然后使用 for-each 循环吗？

 可以，你可以写一个自己的容器类。如果你想使用 Java 中增强的循环来遍历，你只需要实现 Iterable 接口。如果你实现 Collection 接口，默认就具有该属性。 



### 4. 说出几点 Java 中使用 Collections 的最佳实践

这是我在使用 Java 中 Collection 类的一些最佳实践： 

a）使用正确的集合类，例如，如果不需要同步列表，使用 ArrayList 而不是 Vector。 

b）优先使用并发集合，而不是对集合进行同步。并发集合提供更好的可扩展性。

c）使用接口代表和访问集合，如使用List存储 ArrayList，使用 Map 存储 HashMap 等等。 

d）使用迭代器来循环集合。 

e）使用集合的时候使用泛型。 



## 概念

### 集合

类图如下

![Collection 类图](./images/collection.svg)

