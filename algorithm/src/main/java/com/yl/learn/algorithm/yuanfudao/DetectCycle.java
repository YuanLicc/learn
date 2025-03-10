package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 来源：力扣（LeetCode） 142
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 */
public class DetectCycle {
    
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode quick = head;
        
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
            
            if(slow == quick) {
                slow = head;
                while (slow != quick) {
                    slow = slow.next;
                    quick = quick.next;
                }
                return slow;
            }
        }
        
        return null;
    }
}
