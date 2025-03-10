package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * leetcode 148
 */
public class SortListNode extends TestCase {
    
    public ListNode sortList(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode leftNode = head;
        ListNode rightNode = slow.next;
        slow.next = null;
        
        ListNode left = sortList(leftNode);
        ListNode right = sortList(rightNode);
        
        ListNode node = null;
        ListNode rHead = null;
        
        while(left != null && right != null) {
            if(left.val < right.val) {
                if(node == null) {
                    node = left;
                    rHead = node;
                }
                else {
                    node.next = left;
                    node = node.next;
                }
                left = left.next;
            }
            else {
                if(node == null) {
                    node = right;
                    rHead = node;
                }
                else {
                    node.next = right;
                    node = node.next;
                }
                right = right.next;
            }
        }
        
        ListNode notNull = left == null ? right : left;
        node.next = notNull;
        
        return rHead;
    }
    
    
    private int len(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
    
    public void test() {
        ListNode node = ListNode.Builder.build(new int[]{3, 4, 1});
        ListNode node1 = sortList(node);
        PrintUtil.println(node1.toString1());
    }

}
