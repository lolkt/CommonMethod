package com.designpattern.proxy.static1;

import com.designpattern.proxy.Hello;
import com.designpattern.proxy.Ihello;

public class HelloProxy implements Ihello {

    Ihello ihello;

    public HelloProxy(Hello hello) {
        ihello=hello;
    }

    @Override
    public void sayHello() {
        // 调用真实对象的方法
        System.out.println("Hello, World!");
        ihello.sayHello();
    }
}
