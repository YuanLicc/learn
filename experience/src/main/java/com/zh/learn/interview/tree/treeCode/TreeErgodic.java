package com.zh.learn.interview.tree.treeCode;

import com.zh.learn.interview.tree.treeUtil.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by hong on 2018/8/1.
 * 关于二叉树的遍历方法
 */
public class TreeErgodic {

    /**
     * 先序遍历递归算法
     */
    public static void preRecursion(BinaryTree root) {
        if(root == null) {
            return;
        }
        root.visit();
        preRecursion(root.lChild);
        preRecursion(root.rChild);
    }

    /**
     * 先序遍历非递归算法
     * @param root
     */
    public static void preNonRecursion(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(root);
        BinaryTree node;

        while(!stack.isEmpty()) {
            node = stack.pop();
            node.visit();

            // 由于是先序遍历，所以是先访问左节点再访问右节点，此处数据结构为栈要想先访问左节点就需要先对右节点进行进栈判断，这样才能满足先进后出之后首先遍历左节点
            if(node.rChild != null) {
                stack.push(node.rChild);
            }

            if(node.lChild != null) {
                stack.push(node.lChild);
            }
        }
    }

    /**
     * 中序遍历递归算法
     */
    public static void midRecursion(BinaryTree root) {
        if(root == null) {
            return;
        }

        if(root.lChild != null) {
            midRecursion(root.lChild);
        }

        root.visit();

        if(root.rChild != null) {
            midRecursion(root.rChild);
        }
    }

    /**
     * 中序遍历非递归算法
     * @param root
     */
    public static void midNonRecursion(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(root);
        BinaryTree node = root;

        while(!stack.isEmpty() || node != null) {
            if(node != null) { //如果该节点不为null则一直判断该节点的左孩子节点，如果左孩子节点也不为空则入栈
                stack.push(node);
                node = node.lChild;
            } else { // 出栈，进行节点访问，并把右节点进行传递
                node = stack.pop();
                node.visit();
                node = node.rChild;
            }
        }
    }

    /**
     * 后序遍历递归算法(先左后右最后中)
     * @param root
     */
    public static void backRecursion(BinaryTree root) {
        if(root == null) {
            return;
        }
        if(root.lChild != null) {
            backRecursion(root.lChild);
        }

        if(root.rChild != null) {
            backRecursion(root.rChild);
        }

        root.visit();
    }

    /**
     * 后序遍历非递归算法
     * 后序遍历非递归由于是先遍历左子节点再遍历右子节点最后再遍历当前节点，在进行出栈时不知道
     * 是当前的左节点或者右节点，所以需要一个另外的栈来存储信息，以进行判断。
     * @param root
     */
    public static void backNonRecursion(BinaryTree root) {
        Stack<BinaryTree> leftStack = new Stack<>();
        Stack<BinaryTree> rightStack = new Stack<>();

        leftStack.push(root);

        // 当两个栈的其中一个有数据就继续遍历
        while(!leftStack.empty() || !rightStack.empty()) {

            // 如果leftStackPeekNode中最上面的节点为叶子节点则直接打印
            if(leftStack.peek().lChild == null && leftStack.peek().rChild == null) {
                BinaryTree popNode = leftStack.pop(); // 出队该节点
                popNode.visit();

                // 如果出队的是右侧队列的左节点或者右侧队列的第一个节点只有一个节点则把右侧节点也出队
                if(popNode == rightStack.peek().lChild || (rightStack.peek().lChild == null && rightStack.peek().rChild == popNode)) {
                    rightStack.pop().visit();
                }
            } else { // 最上面的节点不为叶子节点则把该节点的子节点放入该队列并把这个节点放到右侧的栈中
                // 左侧队列不为空
                if(!leftStack.empty()) {
                    if(leftStack.peek().rChild != null) {
                        leftStack.push(leftStack.peek().rChild);
                    }
                    if(leftStack.peek().lChild != null) {
                        leftStack.push(leftStack.peek().lChild);
                    }
                    rightStack.push(leftStack.pop());
                } else { // 左侧队列为空则对右侧队列进行出队
                    rightStack.pop().visit();
                }
            }
        }
    }

    /**
     * 对二叉树进行层次遍历
     * 首先利用一个队列的数据结构进行对二叉树层次遍历的实现
     * @param root
     */
    public static void levelTraversal(BinaryTree root) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0) {
            BinaryTree node = queue.poll();
            node.visit();

            if(node.lChild != null) {
                queue.add(node.lChild);
            }

            if(node.rChild != null) {
                queue.add(node.rChild);
            }
        }
    }





}
