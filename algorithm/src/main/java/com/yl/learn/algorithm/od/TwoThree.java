package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给出一个二叉树如下图所示：
 * 请由该二叉树生成一个新的二叉树，它满足其树中的每个节点将包含原始树中的左子树和右子树的和。
 * 左子树表示该节点左侧叶子节点为根节点的一颗新树；右子树表示该节点右侧叶子节点为根节点的一颗新树。
 *
 * 输入描述
 * 2行整数，第1行表示二叉树的中序遍历，第2行表示二叉树的前序遍历，以空格分割。
 *
 * 输出描述
 * 1行整数，表示求和树的中序遍历，以空格分割
 *
 * 示例1
 * 输入
 *
 * -3 12 6 8 9 -10 -7
 * 8 12 -3 6 -10 9 -7
 * 输出
 *
 * 0 3 0 7 0 2 0
 */
public class TwoThree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] inOrder = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] preOrder = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node head = build(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
        dfs(head);
        StringBuilder sb = new StringBuilder();
        inOrder(head, sb);
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static void inOrder(Node head, StringBuilder sb) {
        if(head == null) return;
        inOrder(head.left, sb);
        sb.append(head.value).append(" ");
        inOrder(head.right, sb);
    }

    private static int dfs(Node node) {
        if(node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int nodeValue = node.value;
        node.value = left + right;
        return nodeValue + left + right;
    }

    private static Node build(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if(preStart > preEnd) return null;
        Node head = new Node(preOrder[preStart]);
        int middle = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if(head.value == inOrder[i]) {
                middle = i;
                break;
            }
        }
        head.left = build(preOrder, inOrder, preStart + 1, preStart + middle -  inStart, inStart, middle - 1);
        head.right = build(preOrder, inOrder, preStart + middle -  inStart + 1, preEnd, middle + 1, inEnd);
        return head;
    }

    static class Node {
        Node left, right;
        int value;
        Node(int value) {
            this.value = value;
        }
    }

}
