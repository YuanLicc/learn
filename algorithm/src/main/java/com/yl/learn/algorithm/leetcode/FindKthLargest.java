package com.yl.learn.algorithm.leetcode;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        buildMaxHeap(nums, nums.length);
        for(int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heap(nums, 0, i);
        }
        return nums[0];
    }

    // 堆排思路：
    // 首先需要获取到最后一个子树结构父节点 => 总节点数 / 2 - 1
    // 然后进行逐级向上构建局部堆 => 将父节点及左右子节点进行比较：大的节点和父节点进行交换，若发生了交换，那么还需要维护交换的子节点的堆
    private void buildMaxHeap(int[] nums, int heapSize) {
        for(int i = heapSize / 2 - 1; i >= 0; i--) {
            heap(nums, i, heapSize);
        }
    }

    private void heap(int[] nums, int node, int heapSize) {
        int leftNode = node * 2 + 1;
        int rightNode = node * 2 + 2;
        int maxNode = node;
        if(leftNode < heapSize && nums[leftNode] > nums[maxNode]) {
            maxNode = leftNode;
        }
        if(rightNode < heapSize && nums[rightNode] > nums[maxNode]) {
            maxNode = rightNode;
        }

        if(maxNode != node) {
            swap(nums, node, maxNode);
            heap(nums, maxNode, heapSize); // 交换后平衡后续节点
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
