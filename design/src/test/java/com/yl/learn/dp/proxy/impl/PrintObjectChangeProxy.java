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
