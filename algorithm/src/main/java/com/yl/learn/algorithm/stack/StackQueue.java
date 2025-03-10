package com.yl.learn.algorithm.stack;

import java.util.Stack;

/**
 * 编写一个 ，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。
 * @param <T>
 */
public class StackQueue<T> {

    Stack<T> pop;
    Stack<T> push;


    public StackQueue() {
        pop = new Stack<>();
        push = new Stack<>();
    }

    public void add(T item) {

        if(!pop.isEmpty()) {
            while (!pop.isEmpty()) {
                push.push(pop.pop());
            }
        }

        push.push(item);
    }

    public T poll() {
        if(push.isEmpty()) {
            throw new RuntimeException();
        }

        while (!push.isEmpty()) {
            pop.push(push.pop());
        }

        return pop.pop();
    }

    public T peek() {
        if(push.isEmpty()) {
            if(pop.isEmpty()) {
                throw new RuntimeException();
            }
            else {
                return pop.peek();
            }
        }
        else {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
            return pop.peek();
        }
    }

}
