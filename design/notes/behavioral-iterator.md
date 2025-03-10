本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 迭代器模式（Iterator Pattern）

### 定义

提供一种方法来访问聚合对象，而不用暴露这个对象的内部表示，其别名为游标。迭代器是一种行为型模式。

### 模式结构

```java
public interface Iterator {
    void first();
    void last();
    boolean isFirst();
    boolean isLast();
}

public class InteratorA implements Iterator {
    private Object[] objs;
    //实现
}
```

Java中集合类型都有实现，这里就不再对此模式进行赘述，大体就是，对集合中元素的遍历操作，贴上`java.util.Iterator`接口的定义：

```java
package java.util;

import java.util.function.Consumer;

public interface Iterator<E> {
    
    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * 剩余元素遍历执行action.accept()方法 
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

java中集合接口与此相关的方法定义：

```java
Iterator<E> iterator();
```

集合一般会在内部实现`Iterator`接口，作为实现上面方法的返回具体迭代器，下面是AbstractList中的实现：

```java
private class Itr implements Iterator<E> {
    /**
    * Index of element to be returned by subsequent call to next.
    */
    int cursor = 0;

    /**
    * Index of element returned by most recent call to next or
    * previous.  Reset to -1 if this element is deleted by a call
    * to remove.
    */
    int lastRet = -1;

    /**
    * The modCount value that the iterator believes that the backing
    * List should have.  If this expectation is violated, the iterator
    * has detected concurrent modification.
    */
    int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor != size();
    }

    public E next() {
        checkForComodification();
        try {
            int i = cursor;
            E next = get(i);
            lastRet = i;
            cursor = i + 1;
            return next;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            AbstractList.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
```

