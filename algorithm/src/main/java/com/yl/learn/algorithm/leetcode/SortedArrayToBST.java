package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 *
 */
public class SortedArrayToBST {

    // 首先二叉搜索树中，中序遍历即为递增序列，所以数组传入的是二叉搜索数的中序遍历
    // 那么咱们直接尾部为头，一直向前遍历得到一个只有左子树的二叉树
    // 另外需要保证平衡（任意节点的左右子树高度差异不超过1），所以需要进行处理；
    // 如果以数组中间位置为根节点，分别处理左右节点即可
    public TreeNode sortedArrayToBST(int[] nums) { // 时间复杂度 N
        return bst(nums, 0, nums.length - 1);
    }

    private TreeNode bst(int[] nums, int left, int right) {
        if(left == right) {
            return new TreeNode(nums[left]);
        }
        if(left > right) return null;
        int middle = (left + right) / 2;
        TreeNode cNode = new TreeNode(nums[middle]);
        cNode.left = bst(nums, left, middle - 1);
        cNode.right = bst(nums, middle + 1, right);
        return cNode;
    }

}
