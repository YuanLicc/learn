package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 来源：力扣（LeetCode）146
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
public class LRUCache extends TestCase {
    
    private int capacity = 0;
    private int size = 0;
    private Map<Integer, LRUNode> cache;
    private LRUNode head, tail;
    
    public LRUCache(int capacity) {
        capacity = capacity <= 0 ? 10 : capacity;
        
        this.capacity = capacity;
        cache = new HashMap<>(this.capacity);
    }
    
    public int get(int key) {
        LRUNode node = cache.get(key);
        if(node == null) return -1;
        toHead(node);
        return node.val;
    }
    
    private void toHead(LRUNode node) {
        if(head == node) return;
        
        LRUNode next = node.next;
        LRUNode pre = node.pre;
        
        LRUNode copy = new LRUNode(null, null, node.val, node.key);
        cache.remove(node);
        
        if(pre != null) pre.next = next;
        if(next != null) next.pre = pre;
        
        if(node == tail) tail = pre;
        
        head.pre = copy;
        copy.next = head;
        head = copy;
        cache.put(copy.key, copy);
        node = null;
    }
    
    public void put(int key, int value) {
        if(size == 0) {
            LRUNode node = new LRUNode(null, null, value, key);
            head = node;
            tail = node;
            cache.put(key, node);
            size++;
            return;
        }
        else if(size == capacity && cache.get(key) == null) {
            removeTail();
        }
        else if(cache.get(key) != null) {
            LRUNode cacheNode = cache.get(key);
            cacheNode.val = value;
            toHead(cacheNode);
            return;
        }
        LRUNode node = new LRUNode(null, null, value, key);
        node.next = head;
        head.pre = node;
        head = node;
        cache.put(key, node);
        size++;
    }
    
    private void removeTail() {
        int key = tail.key;
        cache.remove(key);
        LRUNode pre = tail.pre;
        
        if(pre != null) pre.next = null;
        tail = pre;
        size--;
    }
    
    class LRUNode {
        LRUNode pre, next;
        int val, key;
        
        LRUNode(LRUNode pre, LRUNode next, int val, int key) {
            this.pre = pre;
            this.next = next;
            this.val = val;
            this.key = key;
        }
    }
    
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1); // ,"put","get","get","get","get","get"]
                            // ,[5,5],[1],[2],[3],[4],[5]]
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        lruCache.get(4);
        lruCache.get(3);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(5, 5);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(3);
        lruCache.get(4);
        lruCache.get(5);
    }
}
