package com.designpattern.singleton;

import org.junit.Test;

public class TestSingleton {

    @Test
    public void test1() {
        // 访问枚举单例
        SingletonEnum singleton = SingletonEnum.instance;

        // 调用单例的方法
        singleton.whateverMethod();

        // 注意：枚举实例是唯一的，因此下面的代码和上面的代码访问的是同一个实例
        SingletonEnum anotherSingleton = SingletonEnum.instance;
        System.out.println(singleton == anotherSingleton); // 输出 true，因为它们是同一个实例
    }

}
