本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 建造者模式（Abstract Factory Pattern）

### 定义

将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。建造者模式一步一步创建一个的复杂对象，它允许用户通过制定复杂对象的类型和内容就可以构建他们，而不需要了解具体的构建细节。

### 模式结构

```java
public class Computer {
    private CPU cpu;
    private Memory memory;
    //setter getter
}

public abstract ComputerBuilder {
    private Computer computer;
    
    public abstract void buildCPU();
    public abstract void buildMemory();
    
    public Computer getComputer() {
        return computer;
    }
} 

public class ComputerBuilderA extends ComputerBuilder {
    public void buildCPU() {
        //build CPU
    }
    
    public void buildMemory() {
        //build Memory
    }
} 

public class Director {
    private ComputerBuilder builder;
    
    public void setComputerBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }
    
    public Computer construct() {
        builder.buildCPU();
        builder.buildMemory();
        return builder.getComputer();
    }
}
```

- ComputerBuilder  

  抽象建造者，为创建一个产品对象（Computer）的各个部件指定抽象接口，在该接口中一般声明两种方法：build-方法、get-方法，前者构建复杂对象的各个部件，后者返回复杂对象。可以定义为接口或者抽象类。

- ComputerBuilderA  

  具体建造者，实现了产品对象各个部件的构造和装配。

- Computer  

  产品，被构建的复杂对象，包含多个组成部件。

- Director  

  指挥者，负责阿奶复杂对象的建造次序，指挥者与抽象见照着之间存在关联关系。客户端只需要与指挥者进行交互，客户端需要确定具体的建造者并实例化具体建造者对象。

### 优点

- 不关系产品内部组成细节，将产品本身与产品的创建过程解耦，使得相同创建过程可以创建不同的产品对象。
- 每一个具体建造者相对独立，与其他的具体建造者无关，因此可以很方便的替换具体建造者或新增具体建造者类。
- 更加精细的控制产品的创建过程。
- 增加新的具体建造者类，无需修改原有代码，符合开闭原则。

### 缺点

- 建造者创建的产品一般具有较多的共同点，其组成部分相似。如果产品间差异太大，则适合建造者模式。
- 若产品内部变化复杂，可能会导致创建很多具体建造者类来实现这种变化。

### 适用环境

- 对象有复杂的内部结构。
- 对象的属性相互依赖，需要指定生成顺序。
- 隔离复杂对象的创建和使用。

### 与抽象工厂模式的比较

- 抽象工厂返回一系列相关的产品，而建造者返回一个组装好的完整产品。