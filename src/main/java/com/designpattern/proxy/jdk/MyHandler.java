package com.designpattern.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler<T> implements InvocationHandler {
    T target;

    public MyHandler(T target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello handler");
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
