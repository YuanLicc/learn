本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 工厂方法模式（Factory Method Pattern）

### 定义

​又称为工厂模式、虚拟构造器模式、多态工厂模式，是类创建型模式。工厂父类负责定义创建产品对象的公共接口，而工厂子类负责生成具体的产品对象，目的是将产品类的实例化推迟到子类中完成，即通过子类来确定究竟该实例化哪个产品类。

### 模式结构

``` java
public interface Product {...}

public class ProductA implements Product {...}

public class ProductB implements Product {...}

public interface Factory {
    Product factory();
}

public class AFactory implements Factory {
    public Product factory() {
        return new ProductA();
    }
}

public class BFactory implements Factory {
    public Product factory() {
        return new ProductB();
    }
}
```

- Product  

  抽象产品，是工厂方法模式创建对象的超类型。也就是产品对象的父类或接口。

- ProductA  

  具体产品，实现了抽象产品接口。

- Factory  

  抽象工厂，声明了工厂方法，用于返回一个产品。抽象工厂时工厂方法模式的核心。

- AFactory  

  具体工厂，实现抽象工厂的方法，返回一个具体的产品类的实例。

### 优点

- 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还想客户隐藏了哪种具体产品类将被实例化这一细节，用户只关心所需产品对应的工厂，无需关心创建细节。
- 基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够使工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。
- 加入新产品时，无须修改抽象工厂和抽象产品提供的接口。符合开闭原则。

### 缺点

- 添加新产品时，需要添加新的产品类和对应的具体工厂类，系统中类成对增加。
- 使用抽象层定义，增加了系统的抽象性和理解难度。

### 使用环境

- 不关心类的实例化细节，只需要一个对象的情况下，可以使用工厂方法模式。

### 扩展

- 抽象工厂可以定义多个工厂方法，这些工厂方法可以对应不同的业务逻辑，构造出满足需求的对象。
- 产品对象的重复使用，在工厂类中创建一个保存对象的集合，根据客户需求，查询集合返回对象，如果没有再进行创建并存入集合。此时模式转化为享元模式。