package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 给定长度为 n nn 的无序的数字数组，每个数字代表二叉树的叶子节点的权值，数字数组的值均大于等于 1 11 。
 * 请完成一个函数，根据输入的数字数组，生成哈夫曼树，并将哈夫曼树按照中序遍历输出。
 * 为了保证输出的二叉树中序遍历结果统一，增加以下限制:又树节点中，
 * 左节点权值小于等于右节点权值，根节点权值为左右节点权值之和。当左右节点权值相同时，左子树高度高度小于等于右子树。
 * 注意: 所有用例保证有效，并能生成哈夫曼树提醒:哈夫曼树又称最优二叉树，是一种带权路径长度最短的一叉树。
 * 所谓树的带权路径长度，就是树中所有的叶结点的权值乘上其到根结点的路径长度(若根结点为 0 00 层，叶结点到根结点的路径长度为叶结点的层数)
 *
 * 输入描述
 * 例如：由叶子节点 5 15 40 30 10 生成的最优二叉树如下图所示，该树的最短带权路径长度为 40 * 1 + 30 * 2 +5 * 4 + 10 * 4 = 205 。
 *
 * 输出描述
 * 输出一个哈夫曼的中序遍历数组，数值间以空格分隔
 *
 * 示例1
 * 输入
 *
 * 5
 * 5 15 40 30 10
 * 输出
 *
 * 40 100 30 60 15 30 5 15 10
 */
public class HfmTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        Node[] nums = new Node[num];
        for (int i = 0; i < num; i++) nums[i] = new Node(in.nextInt());
        heap(nums, nums.length);
        while (true) {
            Node first = popHeap(nums, num);
            Node second = popHeap(nums, num - 1);
            num -= 2;
            Node newNode = new Node(first.value + second.value);
            newNode.left = first;
            newNode.right = second;
            nums[num] = newNode;
            heap(nums, num + 1);
            num++;
            if(num == 1) break;
        }
        StringBuilder sb = new StringBuilder();
        dfs(nums[0], sb);
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static void dfs(Node node, StringBuilder sb) {
        if(node == null) return;
        dfs(node.left, sb);
        sb.append(node.value).append(" ");
        dfs(node.right, sb);
    }

    private static Node popHeap(Node[] nums, int length) {
        Node head = nums[0], trail = nums[length - 1];
        nums[length - 1] = null;
        nums[0] = trail;
        build(nums, length - 1, 0);
        return head;
    }

    static class Node {
        Node left, right;
        int value;
        Node(int value) {
            this.value = value;
        }
    }

    private static void heap(Node[] nums, int length) {
        for (int i = length / 2 - 1; i >= 0; i--) {
            build(nums, length, i);
        }
    }

    private static void build(Node[] nums, int length, int i) {
        if(i >= length) return;
        int left = i * 2 + 1, right = i * 2 + 2;
        int min = i;
        if(left < length && nums[left].value < nums[min].value) min = left;
        if(right < length && nums[right].value < nums[min].value) min = right;
        if(min != i) {
            swap(nums, i, min);
            build(nums, length, min);
        }
    }

    private static void swap(Node[] nums, int i, int min) {
        Node tmp = nums[i];
        nums[i] = nums[min];
        nums[min] = tmp;
    }

}
