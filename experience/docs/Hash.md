## Hash 相关

### 1. 有没有可能两个不相等的对象有有相同的 hashcode？

有可能，两个不相等的对象可能会有相同的 hashcode 值，这就是为什么在 hashmap 中会有冲突。相等 hashcode 值的规定只是说如果两个对象相等，必须有相同的hashcode 值，但是没有关于不相等对象的任何规定。



### 2. 两个相同的对象会有不同的的 hash code 吗？

不能，根据 hash code 的规定，这是不可能的。



### 3. 我们可以在 hashcode() 中使用随机数字吗？

不行，因为对象的 hashcode 值必须是相同的。参见答案获取更多关于 Java 中重写 hashCode() 方法的知识。 



### 4. 为什么在重写 equals 方法的时候需要重写 hashCode 方法？

因为有强制的规范指定需要同时重写 hashcode 与 equal 是方法，许多容器类，如 HashMap、HashSet 都依赖于 hashcode 与 equals 的规定。 