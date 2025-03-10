package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * leetcode 98
 */
public class BSTCheck {
    
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
    
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        
        Integer pre = null;
        while (node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                TreeNode pop = stack.pop();
                if(pre == null) pre = pop.val;
                else if(pre >= pop.val) return false;
                else {
                    pre = pop.val;
                }
                node = pop.right;
            }
        }
        
        return true;
    }
}
