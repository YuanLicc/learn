package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

public class LowestCommonAncestor {

    // 思路，想要获取到最小公共父节点，那么需要遍历二叉树节点
    // 当前节点与其中一个目标节点相同时，需要判断下游子节点是否包含另一个节点，如果不包含，需要回溯到父节点进一步进行分析
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    }

    TreeNode rs = null;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;
        if (root.equals(p) || root.equals(q)) {

        }
        dfs(root.left, p, q);
        dfs(root.right, p, q);
    }
}
