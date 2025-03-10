package com.yl.learn.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 */
public class IsValidBracket {
    public boolean isValid(String s) {
        if(s == null || s.equals("")) return true;
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int index = 0;
        while(index < chars.length) {
            char current = chars[index];
            switch(current) {
                case '{' :
                    stack.addLast('{');
                    break;
                case '[' :
                    stack.addLast('[');
                    break;
                case '(' :
                    stack.addLast('(');
                    break;
                case '}' :
                    if(stack.size() == 0 || stack.removeLast() != '{') {
                        return false;
                    }
                    break;
                case ']' :
                    if(stack.size() == 0 || stack.removeLast() != '[') {
                        return false;
                    }
                    break;
                case ')' :
                    if(stack.size() == 0 || stack.removeLast() != '(') {
                        return false;
                    }
                    break;
                default :
                    break;
            }
            index++;
        }
        return stack.size() == 0;
    }

}
