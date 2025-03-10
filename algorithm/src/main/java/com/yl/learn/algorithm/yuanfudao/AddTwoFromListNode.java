package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * leetcode 2
 */
public class AddTwoFromListNode extends TestCase {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        if((l1 == null && l2 == null) || l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = l1;
        ListNode l1Tail = l1;
        boolean isUp = false;
        
        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if(isUp) sum += 1;
            isUp = sum >= 10;
            l1.val = sum % 10;
            l1Tail = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        ListNode notNull = l1 == null ? l2 : l1;
        
        while (notNull != null) {
            int sum = isUp ? notNull.val + 1 : notNull.val;
            isUp = sum >= 10;
            l1Tail.next = new ListNode(sum % 10);
            l1Tail = l1Tail.next;
            notNull = notNull.next;
        }
        
        if (isUp) l1Tail.next = new ListNode(1);
        return head;
    }
    
    public void test() {
        PrintUtil.println(addTwoNumbers(new ListNode(5), new ListNode(5)));
    }
}
