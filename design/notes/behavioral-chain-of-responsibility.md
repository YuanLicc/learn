本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 职责链模式（Chain Of Responsibility Pattern）

### 定义

避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象链接成一条链，并且沿着这条链传递请求，知道有对象处理它为止。是对象行为型模式。

### 模式结构

```java
public abstract Handler {
    protected Handler nextHandler;
    
    public void setHandler(Handler handler) {
        this.nextHandler = handler;
    }
    
    void handle(String request);
}

public class HandlerA extends Handler {
    public void handle(String request) {
        if(request.equals("条件")){
            //处理
        }
        else {
            nextHandler.handle();
        }
    }
}

public class HandlerB extends Handler {
    public void handle(String request) {
        if(request.equals("条件")){
            //处理
        }
        else {
            nextHandler.handle();
        }
    }
}
```

- Handler  

  抽象处理类，定义了一个处理请求的接口，它一般被设计为抽象类，由于不同的具体矗立着处理请求的方式不同，因此在其中定义了抽象请求的处理方法。因为每一个处理者的下家还是一个处理者，所以在抽象处理者中定义了一个相同类型的属性，作为下家的引用。

- HandlerA  

  具体处理类，是抽象处理类的子类，它可以处理用户请求，在具体处理者类中实现了抽象处理者中定义的抽象请求处理方法，在处理请求之前需要进行判断，看是否有处理权限，如果没有，则交给下家处理。

### 优点

职责链模式的优点：

- 降低耦合度，职责链模式使得一个对象无需知道是其他哪一个对象处理请求。对象仅需知道该请求会被处理即可，接受者和发送者都没有对方的明确信息，且链中的对象不需要知道链的结构，由客户端负责链的创建。
- 可简化对象的相互连接：请求处理对象仅需维持一个纸箱其后继者的引用，而不需要维持它对所有候选处理者的引用。
- 增强给对象指派职责的灵活性：在给对象分派职责时，职责链可以给我们带来更多的灵活性。
- 增加新的请求处理类很方便。

### 缺点

职责链模式的缺点：

- 不能保证请求一定会被接收
- 对于比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响，而且在进行代码调试时不太方便，如果建链不当，可能造成循环调用。

### 适用环境

职责链模式的适用环境：

- 多个对象可以处理一个请求，具体是那个对象处理，由运行时自动确定，客户端只需提交请求即可。
- 在不明确指定接受者的情况下，向多个对象中的一个提交一个请求，请求的发送者与请求的处理者解耦。