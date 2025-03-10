package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.ListNode;

import java.util.List;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 *
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 自定义评测：
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 *
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 */
public class GetIntersectionNode {

    // 题目理解，给定两个相交链表，返回相交的节点
    // 思路，暴力解法，遍历A 链表时，内循环遍历B链表，判断节点是否相等
    // 另外 A + B 的长度等于 B + A 的长度，可以考虑同时遍历A、B链表，当节点为空时，从另一个链表头开始遍历
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aPointer = headA;
        ListNode bPointer = headB;

        while(aPointer != bPointer) {
            aPointer = aPointer == null ? headB : aPointer.next;
            bPointer = bPointer == null ? headA : bPointer.next;
        }
        return aPointer;
    }
}
