package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class BracketGenerator extends TestCase {
    
    public List<String> generateParenthesis(int n) {
        
        if(n <= 0) return new ArrayList<>(1);
        
        List<String> rs = new ArrayList<>();
        
        dfs(n, 0, 0, rs, "");
        
        return rs;
    }
    
    private void dfs(int cnt, int leftCnt, int rightCnt, List<String> rs, String s) {
        
        if(rightCnt > leftCnt || leftCnt > cnt) return;
        
        if(leftCnt == rightCnt && leftCnt == cnt) {
            rs.add(s);
        }
        
        dfs(cnt, leftCnt + 1, rightCnt, rs, s + "(");
        dfs(cnt, leftCnt, rightCnt + 1, rs, s + ")");
    }
}
