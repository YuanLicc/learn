package com.yl.learn.interview.algorithm.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * @author YuanLi
 */
public class StackToQueue {

    Stack<Integer> stack1 = new Stack<>();

    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
