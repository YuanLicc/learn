本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 结构型 - 享元模式（Flyweight Pattern）

### 定义

运用共享技术有效的支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。由于享元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，是一种对象结构型模式。

### 模式结构

```java
public interface Flyweight {
    void operation();
}

public class FlyweightA implements Flyweight {
    //属性（内部状态）
    public void operation() {
        ...
    }
}

public class FlyweightFactory {
    private HashMap<String, Flyweight> flyweights;
    ...
    public Flyweight getFlyweight(String key) {
        //flyweights中存在则直接返回，不存在则创建、加入flyweights、返回
    }
}
```

- Flyweight  

  抽象享元类，声明一个接口，通过它可以接受并作用于外部状态。在抽象享元类中定义了具体享元类公共的方法，这些方法可以向外界提供享元对象的内部数据（内部状态），同时也可以通过这些方法来设置外部数据（外部状态）。

- FlyweightA  

  具体享元类，实现了抽象享元接口，其实例称为享元对象。由于享元模式的共享特性，共享类可以结合单例模式设计。也可以根据需要设计非共享享元类。

- FlyweightFactory  

  享元工厂类，创建并管理享元对象；针对抽象享元类编程，将各种类型的具体享元对象存储在一个集合中。当客户请求一个对象时，返回对应的对象。

### 内部状态

书上把这个状态搞得玄乎得不得了（恼怒），享元模式中的内部状态直接理解为属性即可，比如笔的种类：

```java
public interface Flyweight {
    void drawLine(Color color);
}

public class Pen implements Flyweight {
    private PenType type;
    public void drawLine (Color color) {
        //画线，但是要利用外部传入的数据color
    }
}
```

### 外部状态

形如内部状态，首先把状态理解为数据即可，享元抽象类定义了一系列可以设置外部状态的方法，形如上面的`drawLine`方法，而此方法将被客户调用，客户传入的参数color就被称为外部状态。

### 优点

享元模式的优点：

- 可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份。
- 享元模式的外部状态相对独立，而且不会影响内部状态，从而使得享元对象可以在不同环境下被共享。

### 缺点

享元模式的缺点：

- 享元模式使得系统更加复杂，需要分离出内部状态和外部状态。

### 适用环境

享元模式的适用环境：

- 存在大量相同或相似的对象，由于这类对象的大量使用，造成内存的大量耗费。
- 对象的大部分状态都可以外部化，可以将这些外部状态传入对象。
- 享元模式维护一个对象集合，因此对象应该会被多次使用，才值得使用享元模式。