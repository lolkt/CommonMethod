package com.designpattern.singleton;

public class SingletonLanHan {
    //volatile 防止指令重排
    private volatile static SingletonLanHan instance;

    public static SingletonLanHan getInstance() {
//        这个判断是在没有同步块（synchronized块）的情况下进行的。它的目的是检查单例实例是否已经被创建。
//        如果instance不是null，说明单例实例已经被创建，那么就没有必要进入同步块，直接返回已存在的实例即可。这可以避免不必要的同步开销，从而提高性能。
//        如果instance是null，说明单例实例还没有被创建，那么需要进一步处理。
        if (instance == null) {
            synchronized (SingletonLanHan.class) {
//                第二个if判断在同步块内。这是因为多线程环境下，即使第一个if判断后，某个线程决定进入同步块去创建实例，但在此期间，其他线程可能也通过了第一个if判断，并尝试进入同步块。
//                因此，第二个if判断的作用是确保只有一个线程能实际创建单例实例。即使多个线程同时通过了第一个if判断，但由于同步块的存在，它们会排队等待。
//                当第一个线程进入同步块并创建实例后，其他线程会发现instance已经不再是null，因此它们会直接返回已存在的实例，而不会再次创建。
                if (instance == null) {
                    instance = new SingletonLanHan();
                }
            }
        }
        return instance;
    }
}
