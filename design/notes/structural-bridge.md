本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 结构型 - 桥接模式（Bridge Pattern）

### 定义

将抽象本部分与它的实现部分分离，是他们都可以独立的变化。是一种对象结构性模式。

### 模式结构

```java
public interface Implementor {...}

public class ImplementorA implements Implementor {...}

public class ImplementorB implements Implementor {...}

public abstract class Abstraction {
    protected Implementor impl;
    
    public void setImplementor(Implementor impl) {
        this.impl = impl;
    }
    
    abstract void operation();
}

public class RefinedAbstraction extends Abstraction {
    public void operation() {
        //...
    }
}
```

- Abstraction  

  抽象类，定义抽象类的接口，一般为抽象类而不是接口，其中定义了一个Implementor类型的对象并可以维护该对象。可以包含抽象的业务方法，还可以包含具体的业务方法。

- RefinedAbstraction  

  扩充抽象类，扩充Abstraction的接口，通常情况下它不在是抽象类而是具体类，它实现了在Abstraction中定义的抽象业务方法。可以调用在Implementor中定义的业务方法。

- Implementor  

  实现类接口，定义实现类的接口，这个接口不预定要与Abstraction的接口完全一致，事实上这两个接口可以完全不同，一般来讲，Implementor提供基本操作，而Abstraction中定义的接口可能会做更多更复杂的操作。而Abstraction中通过关联关系，可以调用Implementor中定义的方法，替代了继承关系。

### 优点

- 分离抽象接口及实现部分。使用关联关系，解耦了抽象和实现之间固有的绑定关系，使得抽象和实现可以沿着各自的维度变化。
- 桥接模式有时类似于多继承方案，但是多继承违背了单一职责原则，复用性较差。
- 桥接模式提高了系统的可扩充性。

### 缺点

- 增加对系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。
- 要求正确识别系统中两个独立变化的维度，因此其使用范围具有一定的局限性。

### 例子

```java
public abstract Pen {
    protected Color color;
    ....
    public abstarct draw();
}

public class GelPen extends Pen {...}

public class FountainPen extends Pen {...}

public interface Color {...}

public class Red implements Color {...}

public class Blue implements Color {...}
```

### 适用环境

- 如果系统中需要在构建的抽象化角色和具体化角色之间增加更多的灵活性，避免在两个层次之间建立静态的继承联系，通过桥接模式可以使它们在抽象层建立一个关联关系。
- 抽象化角色和实现化角色可以以继承的方式独立扩展而互不影响，在程序运行时可以动态的将一个抽象化子类的对象和一个实现化子类的对象进行组合，即系统需要对抽象化角色和实现化角色进行动态解耦。
- 一个类存在两个独立变化的维度（例子中的笔的类型、笔的颜色），且这两个维度都需要进行扩展。