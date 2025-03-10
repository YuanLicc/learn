package com.yl.learn.algorithm.stack;

import java.util.Stack;


/**
 * 仅使用递归实现栈的逆序。
 */
public class RecursionReverseStack {


    public <T> void reverse(Stack<T> stack) {

        if(stack.isEmpty()) {
            return;
        }

        T last = getLastAndRemove(stack);
        reverse(stack);
        stack.push(last);
    }

    private <T> T getLastAndRemove(Stack<T> stack) {

        T t = stack.pop();

        if(stack.isEmpty()) {
            return t;
        }
        else {
            T last = getLastAndRemove(stack);
            stack.push(t);

            return last;
        }

    }

}
