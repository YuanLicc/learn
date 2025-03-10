package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 输入：head = [1,2,2,1]
 * 输出：true
 */
public class IsPalindromeList {

    // 题目理解：链表回文，正读反读一样即可
    // 思路：考虑递归进行逆向对比
    // 另外可以考虑 非递归版本，考虑使用栈进行存储，然后出栈与原链表进行对比
    public boolean isPalindrome(ListNode head) { // 空间复杂度 N、时间复杂度 N
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode p = head;
        while (p != null) {
            stack.addFirst(p);
            p = p.next;
        }
        p = head;
        while (!stack.isEmpty()) {
            if(p.val != stack.pop().val) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

    boolean rs = true;
    private ListNode isPalindrome(ListNode head, ListNode back) { // 空间复杂度 1，时间复杂度 N
        if(head.next == null) {
            if(head.val != back.val) {
                rs = false;
            }
            return back.next;
        }
        ListNode nix = isPalindrome(head.next, back);
        if(nix.val != head.val) {
            rs = false;
        }
        return nix.next;
    }
}
