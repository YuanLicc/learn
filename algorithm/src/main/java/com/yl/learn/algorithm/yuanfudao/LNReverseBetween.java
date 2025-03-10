package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import junit.framework.TestCase;

public class LNReverseBetween extends TestCase {
    
    // 1 ≤ m ≤ n ≤ 链表长度。
    public ListNode reverseBetween(ListNode head, int m, int n) {
    
        int index = 1;
        ListNode leftEnd = head;
        ListNode next = head.next;
        
        if(m != 1) {
            index = 2;
            while(index != m) {
                leftEnd = leftEnd.next;
                next = next.next;
                index++;
            }
        }
        else {
            next = head;
        }
        
        if(m != n) {
            // 翻转
            ListNode pre = next;
            next = next.next;
            pre.next = null;
            ListNode rightEnd = pre;
            ListNode nextNext = null;
            while(index < n) {
                nextNext = next.next;
                next.next = pre;
                pre = next;
                next = nextNext;
                index++;
            }
            rightEnd.next = nextNext;
    
            if(m == 1) {
                return pre;
            }
            else {
                leftEnd.next = pre;
            }
        }
        
        return head;
    }
    
    public void test() {
        reverseBetween(ListNode.Builder.build(new int[]{3, 5}), 1, 2);
    }

}
