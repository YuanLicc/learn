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
