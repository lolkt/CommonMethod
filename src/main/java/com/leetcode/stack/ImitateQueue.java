package com.leetcode.stack;

import java.util.Stack;

/**
 * 用两个栈模拟实现队列的入队出队
 */
public class ImitateQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    /**
     * 入队:栈1入栈
     */
    public void push(int data){
        // 栈1入栈
        stack1.push(data);
    }
    /**
     * 出队
     */
    public Integer pop(){
        // 栈2为空的时候
        if (stack2.isEmpty()){
            // 将栈1中的所有元素放进栈2中 并且栈2出栈
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        // 栈2不为空直接出栈 避免因进栈顺序导致出栈出错
        return stack2.pop();
    }

}
