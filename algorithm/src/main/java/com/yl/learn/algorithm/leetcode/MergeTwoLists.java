package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
public class MergeTwoLists {

    // 题目理解：两个有序链表，合并为一个有序链表即可
    // 思考：多个有序集合中找Top问题，所比较链表头大小即可确定
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // 时间复杂度 N、空间复杂度 0
        if(list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

}
