package com.designpattern.proxy;

import com.designpattern.proxy.jdk.MyHandler;
import com.designpattern.proxy.static1.HelloProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class TestPrioxy {
    @Test
    public void test() {

        Ihello h = new HelloProxy(new Hello());
        h.sayHello();
    }

    @Test
    public void test2() {
        MyHandler<Ihello> handler = new MyHandler<>(new Hello());
//参数：动态类的加载器，动态类实现的接口(们)，方法执行前的handler
// 第二个参数必须是接口，如果是类会报错
        Ihello h = (Ihello) Proxy.newProxyInstance(Ihello.class.getClassLoader(), new Class[]{Ihello.class}, handler);
        h.sayHello();

//        hello handler
//        Hello
    }
}
