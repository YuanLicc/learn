package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 定义构造三叉搜索树规则如下：
 *
 * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
 *
 * 如果数小于节点的数减去500，则将数插入节点的左子树
 *
 * 如果数大于节点的数加上500，则将数插入节点的右子树
 *
 * 否则，将数插入节点的中子树
 *
 * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
 *
 * 输入描述
 * 第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
 *
 * 第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
 *
 * 输出描述
 * 输出树的高度（根节点的高度为1）
 *
 * 示例1
 * 输入
 *
 * 5
 * 5000 2000 5000 8000 1800
 * 输出
 *
 * 3
 */
public class ThreeNode {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        int[] nums = toArray(in.nextLine());
        Node head = new Node(nums[0]);
        int max = 0;
        for (int i = 1; i < num; i++) {
            int high = dfs(head, nums[i], 1);
            max = Math.max(max, high);
        }
        System.out.println(max);
    }

    private static int[] toArray(String str) {
        String[] numStr = str.split(" ");
        int[] rs = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++) rs[i] = Integer.parseInt(numStr[i]);
        return rs;
    }

    private static int dfs(Node node, int aimValue, int high) {
        if(aimValue < node.value - 500) {
            if(node.left == null) {
                node.left = new Node(aimValue);
                return high + 1;
            }
            return dfs(node.left, aimValue, high + 1);
        }
        else if(aimValue > node.value + 500) {
            if(node.right == null) {
                node.right = new Node(aimValue);
                return high + 1;
            }
            return dfs(node.right, aimValue, high + 1);
        }
        else {
            if(node.middle == null) {
                node.middle = new Node(aimValue);
                return high + 1;
            }
            return dfs(node.middle, aimValue, high + 1);
        }
    }

    static class Node {
        Node left, middle, right;
        int value;
        Node(int value) {
            this.value = value;
        }
    }
}
