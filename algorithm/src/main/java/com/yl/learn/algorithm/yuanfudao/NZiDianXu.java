package com.yl.learn.algorithm.yuanfudao;

import java.util.ArrayList;
import java.util.List;

public class NZiDianXu {
    
    public List<Integer> lexicalOrder(int n) {
        List<Integer> rs = new ArrayList<>();
        if(n <= 0) return rs;
        
        for(int i = 1; i < 10; i++) {
            dfs(i, n, rs);
        }
        
        return rs;
    }
    
    private void dfs(int node, int max, List<Integer> rs) {
        
        if(node > max) return;
        
        rs.add(node);
        
        for(int i = 0; i < 10; i++) {
            dfs(node * 10 + i, max, rs);
        }
        
    }
}
