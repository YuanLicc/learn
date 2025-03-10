package com.yl.learn.algorithm.baidu;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInOrder {
    
    public List<Integer> inOrderTraversal(TreeNode root) {
        
        List<Integer> rs = new ArrayList<>();
        inOrderTraversal(root, rs);
        return rs;
    }
    
    private void inOrderTraversal(TreeNode root, List<Integer> rs) {
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left, rs);
        rs.add(root.val);
        inOrderTraversal(root.right, rs);
    }
    
}
