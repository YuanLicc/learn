本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 状态模式（State Pattern）

### 定义

允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类。是一种对象行为型模式。

### 模式结构

```java
public interface State {
    void on();
    void off();
    void handle();
}

public class OnState implements State {
    private String light;
    
    public void On() {
        //
    }
    
    public void Off() {
        light = "Off";
    }
    
    public void handle() {
        off();
    }
}

public class OffState implements State {
    private String light;
    
    public void On() {
        light = "On";
    }
    
    public void Off() {
        //
    }  
    
    public void handle() {
        on();
    }
}

public class Context {
    private State state;
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void handle() {
        state.handle();
    }
}
```

- Context  

  环境类，又称为上下文类，拥有状态的对象，但是由于其状态存在多样性且在不同状态下对象的行为有所不同，因此将状态独立出去形成单独的状态类。在环境类中维护一个抽象状态类State的实例，这个实例定义当前状态，在具体实现时，它是一个State子类的对象。

- State  

  抽象状态类，定义一个接口以封装与环境类的一个特定状态相关的行为，在抽象状态类中生命了各种不同对应的方法，而在其子类中实现了这些方法，由于不同状态下对象的行为可能不同，因此在不同子类中方法的实现可能存在不同，相同的方法可以写在抽象状态类中。

- StateA  

  具体状态类，是抽象状态类的子类，每一个子类实现一个与环境类的一个状态相关的行为，每一个具体状态类对应换一个具体状态，不同的具体状态行为有所不同。

### 优点

状态模式的优点：

- 封装了转换规则。在状态模式中无需使用冗长的条件语句来进行状态的判断和转移，将不同状态间的转换封装在状态类中，提高了代码的可维护性。
- 枚举可能的状态，在枚举状态之前需要确定状态种类。
- 将所有与某个状态修昂管的行为放在一个类中，并且可以方便的增加新的状态，只需要改变对象状态即可改变对象的行为。
- 允许状态转换逻辑与状态对象合成一体，而不是一个巨大的条件语句块。
- 可以让多个环境对象共享一个状态对象，从而减少系统的对象个数。

### 缺点

状态模式的缺点：

- 状态模式的使用必然会增加系统类和对象的个数。
- 状态模式的结构与实现都较为复杂。
- 对开闭原则的支持并不好，对于切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需要修改对应类的源代码。

### 适用环境

状态模式的适用环境：

- 对象的行为依赖它的状态（属性），并且可以根据它的状态改变而改变它的系那个管行为。
- 代码中包含大量与对象状态相关的条件语句，这些条件语句的出现，会倒追代码的可维护性和灵活性变差，不能方便的增加和删除状态，使客户端类与类库之间耦合增加。