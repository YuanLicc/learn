本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 中介者模式（Mediator Pattern）

### 定义

用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显示的相互引用，从而使其耦合松散，而且可以独立的改变他们之间的交互。是一种对象行为型模式。中介者对象简单而言就是，当两个类A、B同为一个接口的实现类，这里称这两个类为同事类，当A中的方法需要调用B的方法时，一种方法就是直接在A中存储B的引用，在方法内直接调用即可，为了降低这两个类的耦合度，引入了一个中间类，使得A不存储B的引用，而是存储中间类的引用，而中间类不出意料，肯定是存储了B的引用，此时我们称这个中间类为中介者，它使得这两个同事类耦合度降低，而且当我们依赖变更时，直接替换中介者类中的引用即可。当然可能出现关联多个同事类的情况，此时中介者中维护一个列表即可。

### 模式结构

```java
public abstract class Mediator {
    protected List<Colleagues> colleagues;
    
    public void register(Colleague c) {
        colleagues.add(c);
    }
    
    public void operation();
}

public class MediatorA extends Mediator {
    public void operation() {
        for(Colleague c : colleagues) {
            c.operate();
        }
    }
}

public abstract class Colleague {
    protected Mediator mediator;
    
    public Colleague(Mediator m) {
        this.mediator = m;
    }
    
    abstract void operate()；
}

public class ColleagueA extends Colleague {
    public void operate() {
        mediator.operation();
    }
}
```

- Mediator  

  抽象中介者，定义一个接口，用于调用执行具体同事类的方法。

- MediatorA  

  具体中介者类，抽象中介者的子类，维护具体同事类的对象引用，并在实现抽象中介者的方法中调用这些对象引用的方法。

- Colleague  

  抽象同事类，定义了业务方法。

- ColleagueA  

  具体同事类，抽象同事类的子类，存储了一个中介者对象的引用。

### 优点

中介者模式的优点：

- 简化了对象之间的交互。
- 将各同事类解耦。
- 减少子类生成。

### 缺点

中介者模式的缺点：

- 包含了同事之间的交互细节，可能导致具体中介者类非常复杂，难以维护。

### 适用环境

中介者模式的适用环境：

- 对象间存在复杂的引用关系。
- 一个对象引用了其他很多对象，这个类将很难被复用。

### 应用

- MVC 中controller就作为一个中介者。