package com.zh.learn.interview.tree.treeUtil;

import com.yl.learn.common.util.PrintUtil;

/**
 * Created by hong on 2018/8/1.
 * 这是一个二叉树节点类
 */
public class BinaryTree {
    public BinaryTree lChild;
    public BinaryTree rChild;
    public String data;

    public BinaryTree(BinaryTree lChild, BinaryTree rChild, String data) {
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    /**
     * 访问方法
     */
    public void visit() {
        PrintUtil.println(this.toString());
    }

    @Override
    public String toString() {
        return "【"+this.data+"】  ";
    }
}
