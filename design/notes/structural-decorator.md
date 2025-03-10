本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 结构型 - 装饰模式（Decorator Pattern）

### 定义

动态的给对象增加一些额外的职责，就增加功能来说，装饰模式比生成子类更加灵活，是一种对象结构型模式。

### 模式结构

```java
public interface Component {
    void operation();
}

public class ComponentA implements Component {
    
    public void operation() {
        //逻辑
    }
}

public class Decorator implements Component {
    
    private Componenet component;
    
    public Decorator(Component c) {
        component = c;
    }
    
    public void operation() {
        component.operation();
    }
}

public class DecoratorA extends Decorator {
    
    public DecoratorA (Component c) {
        super(c);
    }
    
    public void operation() {
        super.operation();
        addBehavior();
    }
    
    public void addBehavior() {
        //添加的功能
    }
}
```

- Component  

  抽象构件，定义了对象的接口，可以给那些对象动态的增加职责。抽象构件具体构件和抽象装饰类的共同父类，它声明了在具体构件中实现的业务方法，它的引入可以使客户端以一致的方式处理未被装饰的对象及装饰之后的对象，实现客户端的透明操作。

- ComponentA  

  具体构件，定义了具体的构建对象，实现了在抽象构件中声明的方法，装饰器可以给它增加额外的职责。

- Decorator  

  抽象装饰类，是抽象构件的子类，用于给具体的构件新增职责，但是具体职责在其子类实现。它维护一个具体构件对象的引用，通过该引用调用具体构件实现的方法，并通过其子类进行扩展。

- DecoratorA  

  具体装饰类，新增职责。

### 优点

装饰模式的优点：

- 可提供比继承更加灵活的扩展。
- 具体构件类与具体装饰类独立变化，用户可根据需求增加新的构建类和装饰类。

### 缺点

装饰模式的缺点：

- 会产生很多小对象，增加对系统的理解难度。

### 适用环境

装饰模式适用环境：

- 动态透明的方式给单个对象添加职责。
- 当不能采用继承的方式对系统进行扩充或者采用继承不利于系统扩展和维护时。

### 实例

`jdk`中包`java.io`装饰模式的应用：

```java
public abstract class InputStream implements Closeable {...}

public class FilterInputStream extends InputStream {
    protected volatile InputStream in;
    
    protected FilterInputStream(InputStream in) {
        this.in = in;
    }
    
    public int read() throws IOException {
        return in.read();
    }
    ...
}

public class BufferedInputStream extends FilterInputStream {...}

public class DataInputStream extends FilterInputStream implements DataInput {...}

public class PushbackInputStream extends FilterInputStream {...}
```