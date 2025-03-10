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
        System.out.println(proxy.getClass());
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
