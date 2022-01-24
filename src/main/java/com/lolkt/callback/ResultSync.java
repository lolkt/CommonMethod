package com.lolkt.callback;

public class ResultSync {
    /** 请求超时时间*/
    private static final long timeout = 2000;
    private Object data;
    public Object get() {
        try {
            synchronized (this) {
                if (null == data) {
                    this.wait(timeout);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void set(Object data) {
        synchronized (this) {
            this.data = data;
            this.notify();
        }
    }
}