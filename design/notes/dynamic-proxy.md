## 代理模式 - 动态代理机制

动态代理与普通的代理从结构上来讲并没有任何区别，代理类也是实现了抽象主题类的定义，而被代理的类也是实现了抽象主题类的具体主题类，前面一章我们已经讲过了 ---> [链接](https://github.com/YuanLicc/design-pattern-notes/blob/master/notes/structural-proxy.md)，而具体的动态也就是我们不再具体实现代理类，而是由jdk的类库生成代理类，并实例化返回一个代理对象。就像上一章表现出来的一样，代理类实现了抽象主题接口，并关联了被代理的对象，此时脑海中能出现一个大致的类图及关系即可，后面再讲解细节。在此之前，我们将代理简单的理解为，定义了一个方法，但是希望在调用这个方法之前或者之后我能进行某些操作，但这些操作时代理类执行实现的，并且代理类还负责调用这个方法。

### 涉及类

```java
package java.lang.reflect;

public interface InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
```

`invoke`方法参数说明：

- proxy  代理类
- method 被代理方法
- args 被代理方法的参数列表

```java
public class Proxy implements java.io.Serializable {
	/** 
	 * 返回代理对象
     * @param   loader the class loader to define the proxy class
     * @param   interfaces the list of interfaces for the proxy class
     *          to implement
     * @param   h the invocation handler to dispatch method invocations to
     */
    public static Object newProxyInstance(
        ClassLoader loader,
        Class<?>[] interfaces,
        InvocationHandler h) 
        throws IllegalArgumentException {
        ...
    }
    ...
}
```

`newProxyInstance`方法参数说明：

- loader 被代理类的类加载器（一般我们使用抽象主题类的类加载器：`Subject.class.getClassLoader()`）
- interfaces 被代理类实现的接口列表 （一般就是我们的抽象主题类：`new Class[] {Subject.class}`）
- h 调用处理对象（实现了`java.lang.reflect.InvocationHandler`接口的类实例）

### 例子

此例子的[源码链接](../src/main/java/com/yl/learn/dp/proxy/DynamicProxy.java)（本项目是maven项目，若链接下不存在对应代码，请到测试目录下寻找），抽象主题类：

```java
package com.yl.learn.dp.proxy;

public interface Person {
    void trimName();
}
```

具体抽象主题类：

```java
package com.yl.learn.dp.proxy.entity;

import com.yl.learn.dp.proxy.Person;

public class PersonA implements Person {
    private String name;

    public PersonA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void trimName() {
        name = name.trim();
    }

    public String toString() {
        String str = "name:" + name;
        return str;
    }
}
```

前面提到了代理的简单理解，所以我们考虑了调用前及调用后的操作，调用前由于不考虑方法调用返回值的处理问题，所以将在后面的`InvocationHandler`的实现抽象类`DynamicProxy`中定义，而考虑到方法调用后可能对方法返回对象进行处理，由于对象类型的不同，实现肯定参差不一，所以定义为一个接口，这个接口的实现类实例将作为参数传入`DynamicProxy`被调用，如下：

```java
package com.yl.learn.dp.proxy;

/**
 * 代理模式中，方法调用后处理器
 * @author YuanLi
 */
public interface AfterInvokeHandler {

    /**
     * 代理模式中，方法调用后处理方法定义
     * @param proxied 被代理对象
     * @param methodReturnObject 目标方法调用后的返回值
     */
    void afterInvokeHandle(Object proxied, Object methodReturnObject);
}
```

将实现接口`java.lang.reflect.InvocationHandler`类包装成了一个抽象类，具体方法交给其子类实现：

```java
package com.yl.learn.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理抽象类，所有的动态代理都应该继承自此抽象类
 * @author YuanLi
 */
public abstract class DynamicProxy implements InvocationHandler {

    /**
     * 被代理实例
     */
    protected Object proxied;

    /**
     * 调用目标方法后的处理类对象
     */
    private AfterInvokeHandler afterInvokeHandler;

    public DynamicProxy(Object proxied, AfterInvokeHandler afterInvokeHandler) {
        this.afterInvokeHandler = afterInvokeHandler;
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preInvoke();//调用目标方法前操作。
        Object returnObject = method.invoke(proxied, args);//调用目标方法
        afterInvokeHandler.afterInvokeHandle(proxied, returnObject);//调用目标方法后的处理
        return returnObject;//返回目标方法的返回值
    }

    /**
     * 调用目标方法前操作，子类实现
     */
    public abstract void preInvoke();
}
```

引入一个实现了抽象类`DynamicProxy`的类，其目的是在目标方法调用前打印对象：

```java
package com.yl.learn.dp.proxy.impl;

import com.yl.learn.dp.proxy.AfterInvokeHandler;
import com.yl.learn.dp.proxy.DynamicProxy;

/**
 * 打印对象代理
 * @author YuanLi 
 */
public class PrintObjectChangeProxy extends DynamicProxy {

    public PrintObjectChangeProxy(Object proxied, AfterInvokeHandler afterInvokeHandler) {
        super(proxied, afterInvokeHandler);
    }

    @Override
    public void preInvoke() {
        System.out.println(proxied);
    }
}
```

测试代码：

```java
package com.yl.learn.dp.proxy;

import com.yl.learn.dp.proxy.entity.PersonA;
import com.yl.learn.dp.proxy.impl.PrintObjectChangeProxy;
import junit.framework.TestCase;

import java.lang.reflect.Proxy;

/**
 * 代理测试
 * @author YuanLi 
 */
public class ProxyTest extends TestCase {

    public void testProxy() {
        PersonA personA = new PersonA(" A _ a");
        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class},
        new PrintObjectChangeProxy(personA, new AfterInvokeHandler() {
            @Override
            public void afterInvokeHandle(Object proxied, Object methodReturnObject) {
                System.out.println(proxied.toString());
            }
        }));
        person.trimName();
    }
}
```

其中`new AfterInvokeHandler...`是对接口`AfterInvokeHandler`的实现，被称为匿名类。结果如下：

```java
name: A _ a
name:A _ a
```

### 解析

- 声明了被代理对象`personA`。
- 然后调用`newProxyInstance`方法生成代理对象person，在此过程中，生成了一个代理类，这个代理类实现了我们传入的参数`Class[]{Person.class}`所指定的接口列表，在这个类中，相比与被代理的类，它没有任何与PersonA中定义的属性相关的属性，但是它有一个类型为`InvocationHandler`的属性，也就是我们传入的参数`InvocationHandler h`，而在它实现的接口方法`trimName()`中，会调用`h.invoke`方法，所以在调用`person.trimName();`时，实际上执行的是`h.invoke`方法。

到此，已经很清晰的描述了动态代理的实现机制，完毕。