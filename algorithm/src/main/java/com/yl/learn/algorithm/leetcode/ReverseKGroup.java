package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
 * 那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 */
public class ReverseKGroup {

    // 同翻转相邻两个元素，此题翻转连续的K个元素
    public ListNode reverseKGroup(ListNode head, int k) { // 时间复杂度 N + N
        if(head.next == null) return head;
        if(k == 1) return head;

        ListNode p = head;
        ListNode rs = null;
        ListNode preTail = null;
        while(p != null) {
            int num = k;
            ListNode first = p; // 表示长度为K的子链表头
            while(p != null && num > 1) { // 筛选出长度为K的链表
                p = p.next;
                num--;
            }
            if(p != null) { // 对长度为K的链表进行翻转操作
                ListNode tmp = p.next; // 记录长度为K的链表的下一个节点，作为下个循环的 first
                p.next = null;
                ListNode subHead = reverse(first); // 翻转链表返回链表头
                if(preTail != null) { // 如果前面存在翻转过的链表
                    preTail.next = subHead; // 将前面翻转过的链表尾部与当前翻转过的链表头进行连接
                }
                preTail = first;
                rs = rs == null ? subHead : rs;
                p = tmp;
            }
            else { // 如果长度为K的链表尾指针为空，表示不足K个元素
                if(preTail != null) {
                    preTail.next = first;
                }
                else {
                    rs = first;
                }
            }
        }
        return rs;
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode hh = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return hh;
    }

}
