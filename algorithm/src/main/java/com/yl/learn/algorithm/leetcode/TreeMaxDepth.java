package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 */
public class TreeMaxDepth {

    public int maxDepth(TreeNode root) {
        return maxDep(root, 0);
    }

    private int maxDep(TreeNode root, int dep) {
        if(root == null) return dep;
        return Math.max(maxDep(root.left, dep + 1), maxDep(root.right, dep + 1));
    }
}
