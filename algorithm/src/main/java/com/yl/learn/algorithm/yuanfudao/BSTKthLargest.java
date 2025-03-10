package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 剑指 offer 54
 */
public class BSTKthLargest {
    
    public int kthLargest(TreeNode root, int k) {
        if(root == null || k <= 0) return -1;
        List<Integer> list = new ArrayList<>();
        inTravel(root, list);
        
        if(k > list.size()) return -1;
        return list.get(list.size() - k);
    }
    
    private int inTravel(TreeNode root, int k) {
    
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        
        while ((node != null || !stack.isEmpty()) && k >= 0) {
            if(node != null) {
                stack.push(node);
                node = node.right;
            }
            else {
                TreeNode cur = stack.pop();
                k--;
                if (k == 0) return cur.val;
                node = cur.left;
            }
        }
        
        return -1;
    }
    
    private void inTravel(TreeNode root, List<Integer> list) {
        
        if(root == null) return;
        
        inTravel(root.left, list);
        list.add(root.val);
        inTravel(root.right, list);
    }
    
}
