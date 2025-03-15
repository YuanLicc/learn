package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.TreeNode;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 */
public class TreeNodeFlatten {

    // 可以利用递归实现，也可以利用栈保存右节点
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode right = root.right; // 保存右节点备份

        root.right = root.left; // 将右节点设置为左节点
        flatten(root.left);
        root.left = null; // 将左节点设置为空

        flatten(right); // 处理右节点
        if(right != null) {
            right.left = null; // 将左节点设置为空
        }
        while(root.right != null) { // 将右节点添加到先序遍历的末尾
            root = root.right;
        }
        root.right = right;
    }

}
