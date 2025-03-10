package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 */
public class IsSymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if(root == null) return false;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) {
            return false;
        }
        if(left.val != right.val) {
            return false;
        }
        return (isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left));
    }

}
