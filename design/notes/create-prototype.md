本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 创建型 - 原型模式（Prototype Pattern）

### 定义

通过复制原型创建新的对象，是创建型模式。

### 模式结构

```java
public interface Prototype etxends Serializable{
    public Object deepClone();
}

public class PrototypeA {
    private Attribute a1;
    private Attribute a2;
    
    public Object deepClone() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toArrayByte());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        }
        catch(Exception e) {//IOException, ClassNotFoundException, OptionalDataException
            // deal exception
        }
    }
}
```

```java
public class Prototype implements Clonable {
    private int num;
    private char ch;
    
    public Object clone() {
        try{
            return super.clone();
        }
        catch (CloneNotSupportException e) {
            // deal exception
        }
    }
}

public class Client {
    private Clonable prototype;
    ....
    //由客户端调用进行对象的复制，返回给用户。
}
```

- 抽象原型类  

  例一中为Prototype，例二中为Cloneable，抽象原型类定义剧透克隆自己的方法的接口，是所有具体原型类的公共父类，可以是抽象类或接口。

- 具体原型类  

  例一中为PrototypeA，例二中为Prototype，具体原型类实现了具体的克隆方法，在克隆方法中返回自己的一个克隆对象。

### 优点

- 当创建对象实例较为复杂时，使用原型模式可以简化对象的创建过程，提高新实例的创建效率。
- 可动态增加或较少产品类。增加新产品只需实现原型抽象类即可，客户端只需要针对抽象类编程就可确保增加与减少产品类对系统无影响。
- 可使用深度克隆保存对象状态。

### 缺点

- 每个类需要配备克隆方法。
- 实现深度克隆时，需要复杂的代码编写。

### 适用环境

- 创建新对象成本较大。
- 系统需要保存对象状态，若对象状态变化小，或者对象本身占内存不大的情况下。