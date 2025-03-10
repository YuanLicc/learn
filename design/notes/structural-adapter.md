本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 结构型 - 适配器模式（Adapter Pattern）

### 定义

将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作。

### 模式结构

类适配器  

```java
public class Adaptee {
    public void method1 () {
        //逻辑
    }
}

public interface Target {
    void method2();
}

public class Adapter extends Adaptee implements Target {
    public void method2() {
        method1();
    }
}
```

对象适配器  

```java
public class Adaptee {
    void method1 () {
        //逻辑
    }
}

public interface Target {
    void method2();
}

public class Adapter implements Target {
    private Adaptee adaptee;
    
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    public void method2() {
        adaptee.method1();
    }
}
```

- Target  

  目标抽象类，定义了客户要用的特定领域的接口，可以是抽象类或接口，也可以是具体类，在类适配器中，由于Java不支持多重继承，所以只能是接口。

- Adapter  

  适配器类，作为一个转换器，适配Target与Adaptee。是适配器模式的核心。

- Adaptee  

  适配者类，被适配的角色，定义了一个已经存在的接口，这个接口需要适配，适配者类一般是一个具体类，包含了客户希望使用的业务方法。

### 优点

- 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，无须修改代码。
- 增加了类的透明性和复用性，将具体的实现封装在适配者类中，对于客户端类来说是透明的，而且提高了适配者的复用性。
- 灵活性和扩展性非常好，通过使用配置文件，可以很方便的替换适配器，也可以在不修改源有代码的基础上增加新的适配器类，符合开闭原则。
- 类适配器 - 适配器类是适配者的子类，因此可以在适配器类中替换或重载方法，使适配器更加灵活。
- 对象适配器 - 可以把多个适配者适配到同一个目标抽象类。

### 缺点

- 类适配器 - Java不支持多重继承，好一次最多能适配一个适配者类，而且目标抽象类必须为接口。
- 对象适配器 - 与类适配器相比，要置换适配者的方法比较困难。

### 例子

JDBC驱动，我们在连接数据库并进行操作时，无论是什么数据库，我们使用的都是同一套接口，这一套接口就是适配器模式中的目标抽象类的具体实现，而针对Oracle、MySQL等数据库的具体实现就是适配者类，适配器类负责针对不同的数据库选择不同的实现方法。