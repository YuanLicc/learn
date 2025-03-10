package com.yl.learn.algorithm.baidu;

import com.yl.learn.algorithm.ListNode;

// 1-2-3-4
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode pre = head;
        ListNode next = head.next;
        pre.next = null; // 注意
        
        while(next != null) {
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }
        
        return pre;
    }
}
