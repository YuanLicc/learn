package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.tree.TreeNode;
import junit.framework.TestCase;

import java.util.Stack;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * leetcode 530
 */
public class BSTMinDiff extends TestCase {
    
    public int getMinimumDifference(TreeNode<Integer> root) {
    
        Stack<TreeNode<Integer>> stack = new Stack<>();
        
        TreeNode<Integer> pre = null;
        int min = Integer.MAX_VALUE;
        TreeNode<Integer> node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                TreeNode<Integer> ca = stack.pop();
                if(pre == null) {
                    pre = ca;
                }
                else {
                    min = Math.min(ca.value - pre.value, min);
                    pre = ca;
                }
                node = ca.right;
            }
        }
        
        recursion(root, null);
        
        return min;
    }
    
    int min1 = Integer.MAX_VALUE;
    
    private void recursion(TreeNode<Integer> root, TreeNode<Integer> pre) {
        
        if(root == null) return;
        
        recursion(root.left, pre);
        if(pre == null) {
            pre = root;
        }
        else {
            min1 = Math.min(root.value - pre.value, min1);
            pre = root;
        }
        recursion(root.right, pre);
    }
    
    public void test() {
        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Integer> ri = new TreeNode<>(3);
        ri.left = new TreeNode<>(2);
        root.right = ri;
        getMinimumDifference(root);
    }
}
