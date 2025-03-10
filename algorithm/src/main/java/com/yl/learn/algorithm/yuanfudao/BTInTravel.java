package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.tree.TreeNode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BTInTravel extends TestCase {
    
    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        
        List<Integer> rs = new ArrayList<>();
        Stack<TreeNode<Integer>> stack = new Stack<>();
        
        while(root != null || !stack.isEmpty()) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode<Integer> node = stack.pop();
                rs.add(node.value);
                root = node.right;
            }
        }
        return rs;
    }
    
    private void inTravel(TreeNode<Integer> root, List<Integer> list) {
        if(root == null) return;
        
        inTravel(root.left, list);
        list.add(root.value);
        inTravel(root.right, list);
    }
}
