本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 行为型 - 解释器模式（Interpreter Pattern）

### 定义

定义语言的文法，并且建立一个解释器来解释该语言中的句子，这里语言指的是使用规定格式和语法的代码，解释模式是一种类行为型模式。

### 模式结构

```java
public class Context {...}

public abstract AbstractExpression {
    void interpret(Context ctx);
}

public class TerminalExpression extends AbstractExpression {
    public void interpret(Context ctx) {
        //终结符解释操作
    }
}

public class NonterminalExpression extends AbstractExpression {
    public void interpret(Cpntext ctx) {
        //非终结符解释操作
    }
}
```

- Context  

  环境类，又称为上下文类，用于存储解释器之外的一些全局信息，通常它临时存储了需要解释的语句。

- AbstractExpression  

  抽象表达式，声明了抽象的解释操作，它是所有的终结符表达式和非终结符表达式的公共父类。

- TerminalExpression  

  终结符表达式，是抽象表达式的子类，实现了与文法中的终结符相关联的解释操作。在句子中的每一个终结符都是该类的一个实例。

- NonterminalExpression  

  非终结符表达式，是抽象表达式的子类，实现了文法中非终结符的解释操作，由于其中一半包含终结符表达式，也可以继续包含非终结符表达式，因此其解释操作一般通过递归的方式来完成。

### 优点

解释器模式的优点：

- 易于改变和扩展文法。
- 易于实现文法。
- 增加了新的解释表达式的方式。

### 缺点

解释器模式的缺点：

- 对于复杂文法难以维护，在解释器模式中，每一条规则至少需要定义一个类。
- 执行效率低。
- 应用场景有限。

### 应用

- Smalltalk语言的编译器。
- 使用解释器你模式，可以开发简单的编译器，如数学表达式解析器、正则表达式解析器。