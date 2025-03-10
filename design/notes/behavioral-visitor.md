本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 访问者模式（Visitor Pattern）

### 定义

表示一个作用域某对象结构中的各元素的操作，它使我们可以在不改变各元素的类的前提下定义作用域这些元素的新操作，是一种对象行为型模式。

### 模式结构

抽象访问者，为对象结构类中每一个具体元素类声明一个访问操作，从这个操作的名称或参数类型可以清楚知道需要访问的具体元素的类型，具体访问者需要实现这些操作方法，定义对这些元素访问操作。这里声明的是商品访问者抽象类：

```java
public abstract class ProductVisitor {
    public abstract void visit(FruitProduct product);
    
    public abstract void visit(VegetableProduct product);
}
```

具体访问者，买家：

```java
public class CustomerVisitor extends ProductVisitor {
    public void visit(FruitProduct product) {
        //展示水果价格、图片、品种
    }
    
    public void visit(VegetableProduct product) {
        //展示蔬菜价格、图片、产地
    }
}
```

具体访问者，卖家：

```java
public class SalerVisitor extends ProductVisitor {
    public void visit(FruitProduct product) {
        //展示水果价格、图片、品种、进价
    }
    
    public void visit(VegetableProduct product) {
        //展示蔬菜价格、图片、产地、进价
    }
}
```

抽象元素，一般是抽象类或接口，定义了一个accept方法，该方法以一个抽象访问者作为你参数，下面是商品抽象类：

```java
public abstract class Product {
    //属性、getter、setter
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}
```

具体元素，水果：

```java
public class FruitProduct extends Product {
    //....
}
```

...

### 优点

访问者模式的优点：

- 增加新的访问操作变得很容易，直接新增一个访问者类即可，符合开闭原则。
- 将有关对象的访问行为集中到一个访问者对象中，而不是分散到一个个的元素类中。累的职责更加清晰。
- 能够在不修改现有类的层次结构的情况下，定义该类层次结构的新操作。

### 缺点

访问者模式的缺点：

- 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类意味着要在抽象访问角色中增加一个新的抽象操作，并在每一个具体访问者类中增加相应的具体操作。
- 破坏封装。访问者模式要求访问者对象访问并调用每一个元素对象的操作，这意味着元素对象有时候必须暴露一些自己的内部操作和内部状态，否则无法供访问者访问。

### 适用环境

访问者模式的适用环境：

- 一个对象结构包含很多类型的对象，希望对这些对象实施一些依赖其具体类型的操作。
- 需要对一个对象结构中的对象进行很多不同并且不相关的操作，而需要避免让这些操作污染这些对象的类，也不希望在增加新的操作时修改这些类。