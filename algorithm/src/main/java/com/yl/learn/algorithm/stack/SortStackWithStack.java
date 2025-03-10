package com.yl.learn.algorithm.stack;

import java.util.Stack;

public class SortStackWithStack {

    public static void sort(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }

        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();

            while (!help.isEmpty() && curr < help.peek()) {
                stack.push(help.pop());
            }
            help.push(curr);

        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

}
