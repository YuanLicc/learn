package com.yl.learn.algorithm.od;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 题目描述
 * 向一个空栈压入正整数，每当压入一个整数时，执行以下规则（设: 栈顶至栈底整数依次编号为 n1, n2, …, nx，其中n1 为最新压入的整数)
 *
 * 如果 n1 = n2，则 n1、n2全部出栈，压入新数据 m (m = 2*n1)
 *
 * 如果 n1 = n2 + … + ny( y的范围为[3,x]) ，则 n1, n2, …, ny 全部出栈，压入新数据 m (m = 2*n1)。
 *
 * 如果上述规则都不满足，则不做操作。
 *
 * 如：依次向栈压入 6、1、2、3，
 *
 * 当压入 2 时，栈顶至栈底依次为 [2,1,6]；
 * 当压入 3 时，3 = 2 + 1，3、2、1 全部出栈，重新入栈整数6，此时栈顶至栈底依次为 [6,6]；6 = 6，两个 6 全部出栈，压入 12，最终栈中只剩个元素 12。 向栈中输入一串数字，请输出应用此规则后栈中最终存留的数字。
 * 输入
 * 使用单个空格隔开的正整数的字符串，如 “5 6 7 8”，左边的数字先入栈。
 *
 * 正整数大小为 [1, 2^31−1]。
 * 正整数个数为 [1,1000]。
 * 输出
 * 最终栈中存留的元素值，元素值使用单个空格隔开，如 "8 7 6 5"，从左至右依次为栈顶至栈底的数字。
 *
 * 示例1
 * 输入
 *
 * 10 20 50 80 1 1
 * 1
 * 输出
 *
 * 2 160
 */
public class ToStack {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] nums = in.nextLine().split(" ");
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(sum);
        for (String num : nums) {
            int currentInt = Integer.parseInt(num);
            if(stack.peekLast() == currentInt) {
                dfs(stack, currentInt);
                sum += currentInt;
            }
            else if(sum == currentInt) {
                stack.clear();
                stack.addLast(currentInt * 2);
                sum = currentInt * 2;
            }
            else {
                stack.addLast(currentInt);
                sum += currentInt;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast()).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static void dfs(Deque<Integer> stack, int current) {
        if(stack.isEmpty()) {
            stack.addLast(current);
        }
        else if(stack.peekLast() == current) {
            stack.removeLast();
            dfs(stack, current * 2);
        }
        else {
            stack.addLast(current);
        }
    }
}
