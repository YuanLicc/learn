package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class SwapPairs {

    // 将相邻成对的节点进行交换
    // 将相邻成对的节点进行交换
    public ListNode swapPairs(ListNode head) { // 时间复杂度 N / 2 空间复杂度 N / 2 + 1
        if(head == null || head.next == null) {
            return head;
        }
        ListNode rs = head.next;
        swap(head);
        return rs;
    }

    private ListNode swap(ListNode head) { // 空间复杂度 N / 2
        if(head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        head.next = swap(second.next);
        second.next = head;
        return second;
    }

}
