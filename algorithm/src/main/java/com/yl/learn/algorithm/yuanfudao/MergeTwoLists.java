package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import junit.framework.TestCase;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）21
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class MergeTwoLists extends TestCase {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode min = l1.val < l2.val ? l1 : l2;
        ListNode max = l1.val >= l2.val ? l1 : l2;

        ListNode minNext = mergeTwoLists(min.next, max);

        min.next = minNext;
        return min;
    }
}
