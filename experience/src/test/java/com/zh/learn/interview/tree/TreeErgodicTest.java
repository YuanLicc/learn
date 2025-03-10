package com.zh.learn.interview.tree;

import com.zh.learn.interview.tree.treeCode.TreeErgodic;
import com.zh.learn.interview.tree.treeUtil.BinaryTree;
import com.zh.learn.interview.tree.treeUtil.BinaryTreeFactory;
import org.junit.Test;

/**
 * Created by hong on 2018/8/1.
 */
public class TreeErgodicTest {
    /**
     * 对前序递归遍历进行测试
     */
    @Test
    public void preRecursionTest() {
        BinaryTree tree =  BinaryTreeFactory.createTree();
        TreeErgodic.preRecursion(tree);
    }

    /**
     * 先序遍历非递归算法测试
     */
    @Test
    public void preNonRecursionTest() {
        BinaryTree tree = BinaryTreeFactory.createTree();
        TreeErgodic.preNonRecursion(tree);
    }

    /**
     * 中序遍历递归算法测试
     */
    @Test
    public void midRecursionTest() {
        BinaryTree tree = BinaryTreeFactory.createTree();
        TreeErgodic.midRecursion(tree);
    }

    /**
     * 后序非递归遍历算法测试
     */
    @Test
    public void backNonRecursionTest() {
        BinaryTree tree = BinaryTreeFactory.createTree();
        TreeErgodic.backRecursion(tree);
    }

    /**
     * 层次遍历算法测试
     */
    @Test
    public void levelTraversalTest() {
        BinaryTree tree = BinaryTreeFactory.createTree();
        TreeErgodic.levelTraversal(tree);
    }
}
