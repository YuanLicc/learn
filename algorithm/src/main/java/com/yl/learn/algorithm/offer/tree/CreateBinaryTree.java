package com.yl.learn.algorithm.offer.tree;


import com.yl.learn.algorithm.TreeNode;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入
 * 前序遍历序列 {1,2,4,7,3,5,6,8}
 * 和
 * 中序遍历序列 {4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class CreateBinaryTree {

    public static TreeNode create(int[] pre, int[] in) {
        return create(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode create(int[] pre, int i, int i1, int[] in, int i2, int i3) {

        if(i > i1) {
            return null;
        }

        int value = pre[i];

        TreeNode root = new TreeNode();
        root.val = value;

        int count = 0;
        for(int j = i2; j <= i3; j++) {
            count++;

            if(value == in[j]) {
                root.left = create(pre, i + 1, i + count - 1, in, i2, i2 + count - 1);

                root.right = create(pre, i + count, i1, in, i2 + count, i3);

                break;
            }

        }
        return root;
    }

}
