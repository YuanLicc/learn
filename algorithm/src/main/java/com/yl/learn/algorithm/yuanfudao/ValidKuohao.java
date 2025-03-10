package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）20
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class ValidKuohao extends TestCase {
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
    
        for(int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
        
            if(cur == ']' || cur == ')' || cur == '}') {
            
                if(stack.isEmpty()) return false;
            
                char stackC = stack.pop();
            
                if((cur == ']' && stackC != '[') || (cur == ')' && stackC != '(') || (cur == '}' && stackC != '{')) {
                    return false;
                }
            }
            else {
                stack.push(s.charAt(i));
            }
        }
    
        return stack.isEmpty();
    }
}
