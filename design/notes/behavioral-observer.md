本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 观察者模式（Observer Pattern）

### 定义

定义对象间的一种一对多的依赖关系，使得每当一个对象状态发生改变时，其相关依赖对象都得到通知并自动更新。是一种对象行为型模式。

### 模式结构

Subject - 目标，又称为被观察的对象。在目标中定义了一个观察者集合，它可以存储任意数量的观察者对象，它提供一个接口来增加和删除观察者对象，同时它定义了通知方法`notify`。可以是接口、抽象类、实现类。

```java
public abstract class Subject {
    protected List<Observer> observers;
    
    public abstract void attach(Observer o);
    
    public abstract void detach(Observer o);
    
    public abstract void notifyObservers();
}
```

SubjectA - 具体目标，是目标类的子类，通常它包含经常发生改变的数据，当它的状态发何时能改变时，向它的各个观察者发出通知。

```java
public class SubjectA extends Subject {
    public void attach(Observer o) {
        observers.add(o);
    }
    
    public void detach(Observer o) {
        observers.remove(o);
    }
    
    public void notify() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
```

Observer - 观察者抽象类，一般定义为接口，声明了更新数据的方法。

```java
public interface Observer {
    void update();
}
```

ObserverA - 具体观察者，实现了观察者抽象类。

```java
public class ObserverA implements Observer {
    public void update() {
        //更新操作
    }
}
```

总之，观察者模式就是将操作存储到目标对象中，需要执行时，才调用执行。

### 优点

观察者模式的优点：

- 可以实现表示层数据逻辑层的分离，并定义了稳定的消息更新传递机制，抽象了更新接口，使得可以有各种各样的不同的表示层作为具体观察者角色。
- 观察者模式在观察目标和观察者之间建立一个抽象的耦合。
- 观察者模式支持广播通信，观察目标会向所有注册的观察者发出通知，简化了一堆多系统设计的难度。
- 符合开闭原则的要求，增加新的具体观察者无须修改原有系统代码。

### 缺点

观察者模式的缺点：

- 若观察目标对象有很多直接或间接的观察者的话，通知所有观察者消耗时间。
- 观察者没有相应的机制让观察者知道所观察目标对象是怎么发生变化的。

### 适用环境

观察者模式的适用环境：

- 抽象模型有两个方面，其中一个方面依赖另一个方面。
- 一个对象的改变将导致其他对象发生变化。

### 应用

- AWT中事件处理。