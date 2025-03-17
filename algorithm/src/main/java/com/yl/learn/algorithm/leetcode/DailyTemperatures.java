package com.yl.learn.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        // 73,74,75,71,69,72,76,73
        // 74,75,76,72,72,76,0,0
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(0);
        int[] rs = new int[temperatures.length];
        for(int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.removeFirst();
                rs[index] = i - index;
            }
            stack.addFirst(i);
        }
        return rs;
    }

    // 简单栈实现，可优化
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] rs = new int[temperatures.length];
        Deque<Node> stack = new LinkedList<>();
        for(int i = temperatures.length - 1; i >= 0; i--) {
            int maxIndex = stackIndex(stack, temperatures[i]);
            rs[i] = maxIndex == -1 ? 0 : maxIndex - i;
            stack.addFirst(new Node(i, temperatures[i]));
        }
        return rs;
    }

    private int stackIndex(Deque<Node> stack, int value) {
        Deque<Node> tmpStack = new LinkedList<>();
        int index = -1;
        while(!stack.isEmpty()) {
            Node top = stack.peek();
            if(top.value > value) {
                index = top.index;
                break;
            }
            else {
                tmpStack.addFirst(stack.removeFirst());
            }
        }
        while(!tmpStack.isEmpty()) {
            stack.addFirst(tmpStack.removeFirst());
        }
        return index;
    }

    class Node {
        int value;
        int index;
        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
