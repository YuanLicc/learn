本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 备忘录模式（Memento Pattern）

### 定义

在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态，是一种对象行为型模式。备忘录模式，顾名思义，此模式是一种历史的备份，如IDE内的撤销操作就是一种备忘的体现。

### 模式结构

```java
class Memento {
    private String state;
    public Memento(Originator o) {
        state = o.getState();
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
}

public class Caretaker {
    private Memento memento;
    
    public Memento getMemento() {
        return memento;
    }
    
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}

public class Originator {
    private String state;
    
    public Memento createMemento() {
        return new Memento(this);
    }
    
    public void restoreMemento(Memento m) {
        this.state = m.getState();
    }
    
    //state  gettter\setter
}
```

- Originator  

  原发器，可以创建一个备忘录，并存储它的当前内部状态，也可以使用备忘录恢复期内部状态。一般将需要保存内部状态的类设计为原发器，如一个存储用户信息或商品信息的对象。

- Memento  

  备忘录，存储原发器的内部状态，根据原发器来决定保存那些内部状态。备忘录的设计一般可以参考原发器的设计，根据实际需要确定备忘录类中的属性。需要注意的是，备忘录对象不能直接提供给其他类使用，除了原发器本身与负责人类之外。

- Caretaker  

  负责人，又称为管理者，负责保存备忘录，但是不能对备忘录的内容进行操作或检查，负责人类中可以存储一个或多个备忘录对象，它只负责存储对象，而不能修改对象，也无须知道对象的实现细节。

由于备忘录模式不能把备忘录对象提供给其他类直接使用，因此上诉三个角色对应的实现都要在通一个包下，而备忘录类应该为包内可见。

### 优点

备忘录模式的优点：

- 提供了一种状态恢复的实现机制。
- 实现了信息的封装，一个备忘录对象是一种原发器对象的表示，不会被其它代码改动。

### 缺点

备忘录模式的缺点：

- 资源消耗过大，如果类的成员变量太多，就不可避免占用大量的内存，而且每保存一次对象的状态都需要消耗内存资源。                                                                                              