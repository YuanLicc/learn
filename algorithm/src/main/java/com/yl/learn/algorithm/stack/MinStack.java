package com.yl.learn.algorithm.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 1. pop、push、getMin 操作的时间复杂度都是 O(1)。
 * @param <T>
 */
public class MinStack<T extends Comparable> {

    private Stack<T> dataStack;

    private Stack<T> min;

    public MinStack() {
        dataStack = new Stack<>();
        min = new Stack<>();
    }

    public T pop() {
        T value = dataStack.pop();
        if(value == getMin()) {
            min.pop();
        }
        return dataStack.pop();
    }

    public void push(T t) {
        dataStack.push(t);

        if(min.size() == 0) {
            min.push(t);
        }
        else {
            if(min.peek().compareTo(t) > 0) {
                min.push(t);
            }
            //
        }
    }

    public T getMin() {
        return min.peek();
    }

}
