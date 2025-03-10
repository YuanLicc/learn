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
