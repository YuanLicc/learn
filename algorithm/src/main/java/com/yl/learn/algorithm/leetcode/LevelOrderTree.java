package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 */
public class LevelOrderTree {

    public List<List<Integer>> levelOrder(TreeNode root) { // 时间复杂度 N、空间复杂度=结果集N
        List<List<Integer>> rs = new ArrayList<>();
        levelOrder(root, 1, rs);
        return rs;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> rs) {
        if(root == null) return;
        if(rs.size() >= level) {
            List<Integer> currentLevel = rs.get(level - 1);
            currentLevel.add(root.val);
        }
        else {
            List<Integer> currentLevel = new ArrayList<>();
            currentLevel.add(root.val);
            rs.add(currentLevel);
        }
        levelOrder(root.left, level + 1, rs);
        levelOrder(root.right, level + 1, rs);
    }

}
