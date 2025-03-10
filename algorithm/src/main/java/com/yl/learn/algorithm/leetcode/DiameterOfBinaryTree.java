package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

/**
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 */
public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) { // 时间复杂度 N
        depth(root);
        return max;
    }

    int max = 0;
    private int depth(TreeNode root) {
        if(root == null) return 0;
        int left = depth(root.left) + 1;
        int right = depth(root.right) + 1;
        max = Math.max(left + right - 2, max);
        return Math.max(left, right);
    }
}
