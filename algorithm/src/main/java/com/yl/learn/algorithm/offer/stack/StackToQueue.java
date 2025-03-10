package com.yl.learn.algorithm.offer.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class StackToQueue<T> {

    Stack<T> stack1 = null;

    Stack<T> stack2 = null;


    public void push(T item) {

        if(stack1 == null) {
            stack1 = new Stack<>();
            stack1.push(item);
        }
        else {
            if(!stack2.isEmpty()) {
                while (!stack2.isEmpty()) {
                    stack1.push(item);
                }
            }
            stack1.push(item);
        }

    }

    public T pop() {

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }

}
