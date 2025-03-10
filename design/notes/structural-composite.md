本文摘自书籍[《设计模式》](https://www.amazon.cn/dp/B005XUK0DE/ref=sr_1_1?s=books&ie=UTF8&qid=1525848051&sr=1-1&keywords=%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+%E5%88%98%E4%BC%9F)

## 结构型 - 组合模式（Composite Pattern）

### 定义

组合多个对象形成属性结构以表示“整体与部分”的结构层次。组合模式对单个对象（叶子对象）和组合对象（容器对象）的使用具有一致性。属于对象结构模式。想象成树型结构即可，包含叶子节点（叶子对象）与非叶子节点（容器对象）。

### 模式结构

```java
public interface Component {
    void operation();
    void add(Component c);
    void remove(Component c);
    Component getChild(int i);
}

public class Leaf implements Component {
    public void add(Component c) {
        //异常处理或错误提示
    }
    public void remove(Component c) {
        //异常处理或错误提示
    }
    public Component getChild(int i) {
        //异常处理或错误提示
    }
    public void operation () {
        //叶子节点处理
    }
}

public class Composite implements Component {
    private ArrayList<Component> list = new ArrayList<>();
    
    public void add(Component c) {
        list.add(c);
    }
    public void remove(Component c) {
        list.remove(c);
    }
    public Component getChild(int i) {
        return list.get(i);
    }
    public void operation() {
        for(Component c : list) {
            c.operation();
        }
    }
}
```

- Component  

  抽象构件，可以使接口或抽象类，为叶子构件和容器构件对象声明接口，在该角色中可以包含所有子类共有的行为的声明和实现。定义了访问及管理它的子构件的方法，如增加子构件、删除、获取等。

- Leaf  

  叶子构件，表示叶子节点对象，叶子节点没有子节点，他事先了在抽象构件中定义的行为。对于那些访问及管理子构件的方法，可以通过异常或报错等方式进行处理。

- Composite  

  容器构件，表示容器节点对象，容器节点对象包含子节点，其子节点可以是叶子节点，也可以是容器节点，提供了一个集合来存储子节点，实现了抽象构件中定义的行为，包括哪些访问及管理子构件的方法。

### 两种组合模式

- 透明组合模式 - 上面的例子就是透明组合模式，在抽象构件中定义了容器构件独有的子节点操作、管理方法，这使得叶子构件实现这些方法没有任何意义。
- 安全组合模式 - 与透明组合模式相反的是，安全组合模式只在容器构件中声明并实现子节点的操作、管理方法。而叶子节点中只实现抽象构件中定义的业务方法。由于叶子构件与容器构件存在不同的方法，造成客户端不能完全针对抽象编程。并一致使用叶子构件和容器构件。

### 优点

组合模式的优点：

- 可以清楚的定义分层次的复杂对象，表示对象的全部或部分层次，使得增加新构件更加容易，由于它让客户忽略了层次的差异，而它的结构又是动态的，提供了对象管理灵活的接口，因此，组合模式可以方便的对层次结构进行控制。
- 调用简单，客户端可以一致的使用组合结构或单个对象。
- 可以形成复杂的树形结构。

### 缺点

组合模式的缺点：

- 是设计变得更加抽象，对象的业务规则如果很复杂，则实现组合模式具有很大挑战性。

### 适用环境

组合模式适用环境：

- 需要表示一个对象整体或部分层次，并希望通过一种方式忽略整体与部分的差异，可以一致的对待他们。

### 例子

 文件系统，文件夹对应容器构件，文件对应叶子构件。

