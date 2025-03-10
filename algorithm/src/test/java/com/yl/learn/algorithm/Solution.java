package com.yl.learn.algorithm;

public class Solution {

    public static void main(String[] args) {

        ListNode node = new ListNode(0);
        ListNode head = node;
        for(int i = 0; i < 3; i++) {
            node.next = new ListNode(i + 1);
            node = node.next;
        }

        Merge(head, head);
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode Merge(ListNode list1,ListNode list2) {
        
        if(list1 == null) {
            return list2;
        }
        else if(list2 == null) {
            return list1;
        }
        
        ListNode head = new ListNode(0);
        ListNode root = head;
        while(list1 != null && list2 != null) {
            if(list1.val < list2.val){
                head.next = list1;
                head = list1;
                list1 = list1.next;
            }
            else{
                head.next = list2;
                head = list2;
                list2 = list2.next;
            }
        }
        
        if(list1 != null) {
            head.next = list1;
        }
        
        if(list2 != null) {
            head.next = list2;
        }
        
        return root.next;
    }
}