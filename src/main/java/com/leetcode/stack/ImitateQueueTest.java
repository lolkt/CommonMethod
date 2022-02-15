package com.leetcode.stack;

import org.junit.Test;

public class ImitateQueueTest {

    @Test
    public void test(){
        ImitateQueue imitateQueue = new ImitateQueue();
        imitateQueue.push(1);
        imitateQueue.push(2);
        System.out.print(imitateQueue.pop());//期望结果：1
        imitateQueue.push(3);
        System.out.print(imitateQueue.pop());//期望结果：2
        imitateQueue.push(4);
        System.out.print(imitateQueue.pop());//期望结果：3
        System.out.print(imitateQueue.pop());//期望结果：4
    }

}
