package com.zh.learn.interview.tree.treeUtil;

/**
 * Created by hong on 2018/8/1.
 * 示例树工厂（得到一棵示例树）
 */
public class BinaryTreeFactory {
    /**
     * 构造示例树
     * @return 根节点A
     * 树的形状如下：
     *               A
     *         B           C
     *           D      E
     */
    public static BinaryTree createTree() {
        BinaryTree E = new BinaryTree(null, null, "E");
        BinaryTree C = new BinaryTree(E, null, "C");

        BinaryTree D = new BinaryTree(null, null, "D");
        BinaryTree B = new BinaryTree(null, D, "B");

        BinaryTree A = new BinaryTree(B, C, "A");

        return A;
    }

}
