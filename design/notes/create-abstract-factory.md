本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 抽象工厂模式（Abstract Factory Pattern）

### 定义

​提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。

### 模式结构

```java
public interface ProductPhone {...}

public class ProductHuaWeiPhone implements ProductPhone {...}

public class ProductXiaoMiPhone implements ProductPhone {...}

public interface ProductTV {...}

public class ProductHuaWeiTV implements ProductTV {...}

public class ProductXiaoMiTV implements ProductTV {...}

public interface Factory {
    ProductPhone factoryPhone();
    ProductTV factoryTV();
}

public class FactoryHuaWei implements Factory {
    public ProductPhone factoryPhone() {
        return new ProductHuaWeiPhone();
    }
    
    public ProductTV factoryTV() {
        return new ProductHuaWeiTV();
    }
}

public class FactoryXiaoMi implements Factory {
    public ProductPhone factoryPhone() {
        return new ProductXiaoMiPhone();
    }
    
    public ProductTV factoryTV() {
        return new ProductXiaoMiTV();
    }   
}
```

- Factory  

  抽象工厂，声明生成抽象产品的方法，在一个抽象工厂中定义一组方法，每一个方法对应一个产品等级结构（例子中`phone`与`TV`代表不同的产品等级结构）。

- FactoryHuaWei  

  具体工厂，实现抽象工厂声明的方法，生成一组产品，这些产品构成一个产品族（例子中`HuaWei`与`XiaoMi`代表不同的产品族）。

关于产品族与产品等级结构可以通过下面的表格理解：

| 产品族\产品等级结构 |    Phone    |    TV    |    Watch    |
| :-----------------: | :---------: | :------: | :---------: |
|     **HuaWei**      | HuaWeiPhone | HuaWeiTV | HuaWeiWatch |
|     **XiaoMi**      | XiaoMiPhone | XiaoMiTV | XiaoMiWtach |

### 优点

- 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂变得相对容易。抽象工厂模式可以实现高内聚低耦合的设计目的，一次被广泛的应用。
- 当一个产品族的多个对象被设计成一起工作时，它保证了客户端始终只使用同一个产品族的对象。
- 增加新的具体工厂和产品族很方便。

### 缺点

- 难以扩展新的产品等级结构。

### 适用环境

- 不关心产品如何创建。

- 系统含多个产品族。
- 属于同一个产品族的产品将在一起使用。