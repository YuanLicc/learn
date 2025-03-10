package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        List<Integer> collect = new ArrayList<>();
        inOrder(root, collect);
        if(collect.size() <= 1) return true;

        int preIndex = 0;
        for(int i = 1; i < collect.size(); i++) {
            if(collect.get(preIndex) > collect.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root, List<Integer> collect) {
        if(root == null) {
            return;
        }
        inOrder(root.left, collect);
        collect.add(root.val);
        inOrder(root.right, collect);
    }
}
