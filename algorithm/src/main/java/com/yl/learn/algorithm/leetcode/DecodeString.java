package com.yl.learn.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 */
public class DecodeString {

    // 本来就是通过栈实现，但是由于需要存储数值及字符串，单一栈并不好进行操作
    public String decodeString(String s) {
        Deque<String> sStack = new LinkedList<>(); // 存放字符串
        Deque<Integer> nStack = new LinkedList<>(); // 存放数字
        StringBuilder sb = new StringBuilder();
        int index = 0, num = 0;
        while(index < s.length()) {
            char currentC = s.charAt(index);
            if(currentC == '[') {
                nStack.addFirst(num);
                sStack.addFirst(sb.toString());
                sb = new StringBuilder();
                num = 0;
            }
            else if(currentC == ']') {
                StringBuilder tmp = new StringBuilder();
                int cNum = nStack.removeFirst();
                for(int i = 0; i < cNum; i++) tmp.append(sb);
                sb = new StringBuilder(sStack.removeFirst() + tmp);
            }
            else if(Character.isDigit(currentC)) {
                num = num * 10 + (currentC - '0');
            }
            else {
                sb.append(currentC);
            }
            index++;
        }
        return sb.toString();
    }


}
