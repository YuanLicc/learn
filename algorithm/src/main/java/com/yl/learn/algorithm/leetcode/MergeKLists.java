package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode min = null;
        int minVal = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < lists.length; i++) {
            ListNode current = lists[i];
            if(current != null && current.val < minVal) {
                min = current;
                minVal = current.val;
                minIndex = i;
            }
        }
        if(min == null) {
            return null;
        }
        lists[minIndex] = min.next;
        min.next = mergeKLists(lists);
        return min;
    }



}
