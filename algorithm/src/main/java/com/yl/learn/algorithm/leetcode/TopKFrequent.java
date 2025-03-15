package com.yl.learn.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            Integer get = map.getOrDefault(nums[i], 0);
            map.put(nums[i], get + 1);
        }

        Integer[] keys = new Integer[map.size()];
        map.keySet().toArray(keys);
        int[] rs = new int[k];
        heap(keys, map, keys.length);

        for(int i = keys.length - 1; i >= keys.length - k; i--) {
            rs[keys.length - i - 1] = keys[0];
            swap(keys, 0, i);
            build(keys, 0, i, map);
        }
        return rs;
    }

    private void heap(Integer[] keys, Map<Integer, Integer> map, int heapSize) {
        for(int i = heapSize / 2 - 1; i >= 0; i--) {
            build(keys, i, heapSize, map);
        }
    }

    private void build(Integer[] keys, int node, int heapSize, Map<Integer, Integer> map) {
        int leftNode = node * 2 + 1, rightNode = node * 2 + 2;
        int maxNode = node;
        if(leftNode < heapSize && map.get(keys[leftNode]) > map.get(keys[maxNode])) {
            maxNode = leftNode;
        }
        if(rightNode < heapSize && map.get(keys[rightNode]) > map.get(keys[maxNode])) {
            maxNode = rightNode;
        }

        if(maxNode != node) {
            swap(keys, node, maxNode);
            build(keys, maxNode, heapSize, map);
        }
    }

    private void swap(Integer[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
