package com.yl.learn.algorithm.offer;

public class Solution {

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public static void main(String[] args) {
        RandomListNode a1 = new RandomListNode(1);
        RandomListNode a2 = new RandomListNode(2);
        RandomListNode a3 = new RandomListNode(3);
        RandomListNode a4 = new RandomListNode(4);
        RandomListNode a5 = new RandomListNode(5);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        a3.random = a1;
        a5.random = a4;

        Clone(a1);

    }

    public static RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null) {
            return null;
        }
        // 1. 链表元素后复制一个备份
        RandomListNode temp = pHead;
        while(temp != null) {
            RandomListNode next = temp.next;
            RandomListNode copy = new RandomListNode(temp.label);
            temp.next = copy;
            copy.next = next;
            temp = next;
        }
        
        // 2. 指向 randome
        temp = pHead;
        while(temp != null) {
            if(temp.random != null) {
                temp.next.random = temp.random.next;
                temp = temp.next;
            }
            temp = temp.next;
        }
        
        // 3. 拆分
        RandomListNode root = pHead.next;
        RandomListNode head = root;
        temp = pHead;
        while(temp != null) {
            temp.next = temp.next.next;
            if(head.next!=null)
                head.next = head.next.next;
            head = head.next;
            temp = temp.next;
        }
        return root;
    }
}