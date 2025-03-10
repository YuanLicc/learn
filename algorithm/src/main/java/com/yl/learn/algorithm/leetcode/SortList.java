package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 */
public class SortList {

    // 理解：对链表进行排序
    // 思路1 可以用快排进行排序后重新连接成链表
    // 也可以使用冒泡排序来实现
    public ListNode sortList(ListNode head) {
        if(head == null) return null;
        List<ListNode> list = new ArrayList<ListNode>();
        while(head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort((item1, item2) -> item1.val - item2.val);
        ListNode pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            ListNode current = list.get(i);
            current.next = null;
            pre.next = current;
            pre = pre.next;
        }
        return list.get(0);
    }

    // 使用归并排序
    public ListNode sortList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if(left == null || right == null) {
            return left == null ? right : left;
        }
        if(left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        }
        else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    public static void main(String[] args) {
        new  SortList().sortList1(ListNode.Builder.build(new int[]{4,2,1,3}));
    }

}
