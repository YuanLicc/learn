package com.yl.learn.algorithm.fuxi;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class InTravel {
    
    public List<Integer> inTravel(TreeNode node) {
        
        if(node == null) return null;
        
        List<Integer> rs = new ArrayList<>();
        
        inTravel(node, rs);
        
        return rs;
    }
    
    private void inTravel(TreeNode node, List<Integer> rs) {
    
        Stack<TreeNode> stack = new Stack<>();
        
        while (node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                TreeNode cur = stack.pop();
                rs.add(cur.val);
                node = cur.right;
            }
        }
        
    }
    
}
