package com.wangyang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBoss {
    public static  IBoss  getProxy(IBoss boss){

        IBoss proxy = (IBoss)Proxy.newProxyInstance(boss.getClass().getClassLoader(), boss.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            //代理对象方法执行一次，这里就执行一次
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object invoke = method.invoke(boss, args);
                System.out.println("after");
                return invoke;
            }
        });


        return proxy;
    }
}
