package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
 * 则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class HasCycle {

    // 题目理解：判断链表是否有环形，环形链表说明遍历时无法终止
    // 思路，采用快慢指针来实现，当快指针为空时表示无环
    // 当快指针越圈追上慢指针时，表示有环
    public boolean hasCycle(ListNode head) { // 时间复杂度 N，空间复杂度 2
        if(head == null) return false;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

}
