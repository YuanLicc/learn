package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 */
public class OddEven {

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;

        ListNode jiNode = head;
        ListNode ouNode = head.next;
        ListNode ouFirst = ouNode;

        while (ouNode != null && ouNode.next != null) {

            jiNode.next = ouNode.next;
            jiNode = jiNode.next;

            ouNode.next = jiNode.next;
            ouNode = ouNode.next;

        }

        jiNode.next = ouFirst;

        return head;
    }

}
