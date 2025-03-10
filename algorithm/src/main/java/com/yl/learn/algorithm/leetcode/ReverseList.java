package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 */
public class ReverseList {
    // 理解题目：也就是将链表的指针逆转
    // 思路：遍历链表做指针翻转，但是需要注意 next 指针需要先保存，不然指针反转后，next 拿不到了
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode rs = null;
        reverse(head, rs);
        return rs;
    }

    private static ListNode reverse(ListNode head, ListNode rs) {
        if(head.next == null) {
            rs = head;
            return head;
        }
        ListNode pre = reverse(head.next, rs);
        head.next = null;
        pre.next = head;
        return head;
    }

    private static ListNode reverse(ListNode head) {
        if(head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        reverseList(ListNode.Builder.build(new int[]{1,2,3,4,5}));
    }
}
