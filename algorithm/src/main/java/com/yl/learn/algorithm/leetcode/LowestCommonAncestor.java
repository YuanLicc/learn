package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

public class LowestCommonAncestor {

    // 思路，想要获取到最小公共父节点，那么需要遍历二叉树节点
    // 当前节点与其中一个目标节点相同时，需要判断下游子节点是否包含另一个节点，如果不包含，需要回溯到父节点进一步进行分析
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return rs;
    }

    TreeNode rs = null;

    private int dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return 0;
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        if (root.equals(p) || root.equals(q)) {
            if(left + right == 1) {
                rs = root;
                return 2;
            }
            return left + right + 1;
        }
        else if(left + right == 2 && rs == null) {
            rs = root;
        }
        return left + right;
    }
}
