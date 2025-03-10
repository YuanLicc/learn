本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 单例模式（Singleton Pattern）

### 定义

单例模式确保一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。

### 模式结构

饿汉式  

```java
public class Singleton {
    private static final Singleton instance = new Singleton();
    
    private Singleton() {...}
    
    public static Singleton getInstance() {
        return instance;
    }
}
```

懒汉式  

```java
public class Singleton {
    private static Singleton instance = null;
    
    private Singleton() {...}
    
    public static getInstance () {
        if(instance == null) {
            instance = new Singleton();
        }
        return insatnce;
    }
}
```

### 优点

- 唯一实例的受控访问。
- 仅存在一个实例，节约系统资源。

### 缺点

- 无抽象层，扩展困难。
- 单例类的职责过重，将产品的创建和产品本身的功能融合在一起，违背了单一职责原则。

### 使用环境

- 系统只需要一个实例。

### 注意

- 不要使用单例模式存取全局变量。
- 不要讲数据库连接做成单例，会造成数据库连接得不到释放。