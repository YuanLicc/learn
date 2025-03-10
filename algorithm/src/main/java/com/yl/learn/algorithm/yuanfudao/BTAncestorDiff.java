package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 * 来源：力扣（LeetCode） 1026
 * 链接：https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor
 */
public class BTAncestorDiff extends TestCase {

    public int maxAncestorDiff(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;

        List<TreeNode> inTravels = new ArrayList<>();
        inTravel(root, inTravels);

        return max(root, inTravels, 0, inTravels.size() - 1);
    }

    public int maxAncestorDiff1(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;

        return maxAncestorDiff(root, root.val, root.val);
    }

    private int maxAncestorDiff(TreeNode root, int max, int min) {
        if(root == null) return 0;

        int maxMinus = Math.max(Math.abs(max - root.val), Math.abs(min - root.val));
        
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);

        int left = maxAncestorDiff(root.left, max, min);
        int right = maxAncestorDiff(root.right, max, min);

        return Math.max(Math.max(left, right), maxMinus);
    }

    private int max(TreeNode node, List<TreeNode> travels, int start, int end) {
        if(node == null) return 0;

        int max = 0;
        int index = travels.indexOf(node);
        int leftMax = max(node.left, travels, start, index - 1);
        int rightMax = max(node.right, travels, index + 1, end);
        max = max > leftMax ? max : leftMax;
        max = max > rightMax ? max : rightMax;

        for(int i = start; i <= end; i++) {
            TreeNode sun = travels.get(i);
            if(sun != node) {
                int minus = Math.abs(node.val - sun.val);
                max = max > minus ? max : minus;
            }
        }

        return max;
    }

    private void inTravel(TreeNode node, List<TreeNode> list) {
        if(node == null) return;

        inTravel(node.left, list);
        list.add(node);
        inTravel(node.right, list);
    }
}
