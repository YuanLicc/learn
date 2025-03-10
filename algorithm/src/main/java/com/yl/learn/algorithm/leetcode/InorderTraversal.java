package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rs = new ArrayList<>();
        inorder(root, rs);
        return rs;
    }

    // 递归实现，时间复杂度 N
    private void inorder(TreeNode root, List<Integer> rs) {
        if(root == null) {
            return;
        }
        inorder(root.left, rs);
        rs.add(root.val);
        inorder(root.right, rs);
    }

    // 非递归循环，时间复杂度 N、空间复杂度 N 结果集N
    private List<Integer> inorder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        List<Integer> rs = new ArrayList<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) { // 深度获取左节点
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); // 左节点为空时，开始加入结果集
            rs.add(root.val);
            root = root.right;
        }
        return rs;
    }
}
