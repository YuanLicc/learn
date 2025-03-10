package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 */
public class RemoveNthFromEnd {

    // 考虑双指针来实现
    public ListNode removeNthFromEnd(ListNode head, int n) { // 时间复杂度 N、空间复杂度常数
        if(head == null) {
            return null;
        }
        ListNode p = head;
        while (p != null && n > 1) { // 将 P 指针指向第 N 位
            p = p.next;
            n--;
        }
        if(p == null) { // 此类情况表示链表长度不足N的情况，直接返回 head
            return head;
        }
        if(p.next == null) { // 表示N为链表长度，那么直接返回 head.next
            return head.next;
        }
        p = p.next;
        ListNode pre = head, slow = head.next;
        while(p.next != null) {
            // 双指针后移，指针间包含N个元素，若右指针到达链表末，
            // 左指针就是倒数第N个元素了
            p = p.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = slow.next;
        return head;
    }
}
