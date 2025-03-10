本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 简单工厂模式（Simple Factory Pattern）

### 定义	

​	简单工厂模式又称为静态工厂方法模式，它属于类创建型模式。在简单工厂模式中，可以根据参数不同返回不同类的实例。

### 模式结构

```java
public interface Product {...}

public class ProductA implements Product {...}

public class ProductB implements Product {...}

public class Factory {
    public static Product factory(String type) {
        if(type.equals("A")) {
            return new ProductA();
        }
        else if(type.equals("B")) {
            return new ProductB();
        }
        ...
    }
}
```

- Factory

  工厂类，负责实现创建所有实例的内部逻辑；可以被外界直接调用，创建所需的产品对象；

- Product

  抽象产品，是简单工厂模式所创建的所有对象的父类，负责秒速所有实例所共有的公共接口，它的引入将提高系统的灵活性。

### 优点

- 免除客户端直接创建产品对象的责任，而仅仅是消费产品，实现了对责任的分割。
- 通过引入配置文件，可以不修改任何代码的情况下更换和新增具体产品类，提高灵活性。

### 缺点

- 工厂类集中了所有产品的创建逻辑，一旦不能工作，整个系统都将受到影响。
- 系统扩展难，一旦添加新产品不得不修改逻辑，在产品过多的情况下，会造成逻辑过于复杂，不利于扩展和维护。
- 由于静态工厂方法的原因，将无法被继承。

### 适用环境

- 负责创建对象比较少
- 不关心对象的创建细节