package com.designpattern.proxy;

public class Hello implements Ihello {
    @Override
    public void sayHello() {
        System.out.println( "Hello");
    }
}
