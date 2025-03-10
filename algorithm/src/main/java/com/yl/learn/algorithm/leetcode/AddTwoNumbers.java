package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照 逆序 的方式存储的，
 * 并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class AddTwoNumbers {

    // 理解题目：将两个数字相加即可，
    // 需要考虑进位情况
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // 时间复杂度 MAX(M, N)
        return add(l1, l2, false);
    }

    private ListNode add(ListNode l1, ListNode l2, boolean isPlus) { // 时间复杂度 MAX(M, N)
        if(l1 == null && l2 == null) {
            return isPlus ? new ListNode(1) : null;
        }
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0)  + (isPlus ? 1 : 0);
        ListNode current = l1 == null ? l2 : l1;
        if(sum >= 10) {
            current.val = sum % 10;
            isPlus = true;
        }
        else {
            current.val = sum;
            isPlus = false;
        }
        current.next = add(l1 == null ? null : l1.next, l2 == null ? null : l2.next, isPlus);
        return current;
    }

}
