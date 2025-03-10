package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import junit.framework.TestCase;

/**
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 * 来源：力扣（LeetCode）814
 */
public class BTPrune extends TestCase {
    
    public TreeNode pruneTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null && root.val == 0)) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return (root.left == null && root.right == null && root.val == 0) ? null : root;
    }
}
