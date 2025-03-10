package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import junit.framework.TestCase;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * leetcode 23
 */
public class LNKMerge extends TestCase {
    
    public ListNode mergeKLists(ListNode[] lists) {
        
        ListNode listNode = null;
        ListNode listTemp = null;
        
        while(true) {
            int minIndex = -1;
    
            for(int i = 0; i < lists.length; i++) {
                if(lists[i] == null) continue;
                minIndex = minIndex == -1 ? i : (lists[minIndex].val > lists[i].val ? i : minIndex);
            }
    
            if(minIndex == -1) {
                return listNode;
            }
            
            if(listNode == null) {
                listNode = new ListNode(lists[minIndex].val);
                listTemp = listNode;
            }
            else {
                listTemp.next = new ListNode(lists[minIndex].val);
                listTemp = listTemp.next;
            }
    
            lists[minIndex] = lists[minIndex].next;
        }
    }
}
