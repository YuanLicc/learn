package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 来源：力扣（LeetCode）93
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class RestoreIpAddresses extends TestCase {
    
    // 4 个整数
    // 0 - 255
    // 不能以 0 开头，可以是 0
    public List<String> restoreIpAddresses(String s) {
        
        if(s == null || s.length() < 4) return new ArrayList<>(1);
        
        List<String> rs = new ArrayList<>();
        
        dfs(rs, "", s, 0, 4);
        
        return rs;
    }
    
    private void dfs(List<String> rs, String curNum, String right, int leftNum, int rightNum) {
        if(right == null && leftNum == 4) {
            rs.add(curNum.substring(1, curNum.length()));
            return;
        }
        
        // 不能以 0 开头，可以是 0
        if(right == null || right.length() > rightNum * 3) return;
        
        for(int i = 0; i < 3 && i < right.length(); i++) {
            String subStr = right.substring(0, i + 1);
            String rightLeft = i == right.length() - 1 ? null : right.substring(i + 1, right.length());
    
            if(subStr.charAt(0) == '0' && subStr.length() > 1) return;
            if(isUp(subStr, "255")) return;
            
            dfs(rs, curNum + "." + subStr, rightLeft, leftNum + 1, rightNum - 1);
        }
    }
    
    private boolean isUp(String aim, String cmp) {
        return Integer.valueOf(aim) > Integer.valueOf(cmp);
    }
    
    public void test() {
        restoreIpAddresses("25525511135");
    }
}
