本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 策略模式（Strategy Pattern）

### 定义

定义一系列算法，每一个算法封装起来，并让他们可以相互替换。策略模式让算法独立于使用它的客户而变化，是一种对象行为型模式。

### 模式结构

```java
public interface Strategy {
    void algorithm();
}

public class StrategyA implements Strategy {
    public void algorithm() {
        //算法之一
    }
}

public class StrategyB implements Strategy {
    public void algorithm() {
    	//算法之二
	}
}

public class Context {
    private Strategy strategy;
    
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void algorithm() {
        strategy.algorithm();
    }
}
```

- Context  

  环境类，使用算法的角色，它在解决某个问题（即实现某个方法）时可以采用多种策略，在环境类中维护一个对象策略类的引用实例，用于定义所采用的策略。

- Strategy  

  抽象策略类，为所支持的算法声明了抽象方法，是所有策略的父类，它可以是抽象类也可以是接口。环境类使用在其中声明的方法调用在具体策略类中实现的算法。

- StrategyA  

  具体策略类，具体策略类实现了抽象策略类中定义的算法。

### 优点

策略模式的优点：

- 开闭原则的完美支持，可在不修改原有系统的基础上选择算法或行为，也可以灵活的增加新的算法和行为。
- 提供了管理相关算法族的办法。
- 提供了可以替换继承关系的办法。
- 避免使用多重条件转移语句。

### 缺点

策略模式的缺点：

- 客户端 必须知道所有策略类，并自行决定使用哪一个策略类。
- 策略模式造成产生很多策略类和对象，可以通过使用享元模式在一定程度上减少对象的数量。

### 适用环境

策略模式的适用环境：

- 系统中含许多类，他们之间的区别在于他们的行为，那么使用策略模式可以动态的让一个对象在许多行为中选择一种行为。
- 几种算法中选择一种。
