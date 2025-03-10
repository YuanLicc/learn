package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.ListNode;
import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ReverseListBetween extends TestCase {
    
    public ListNode reverseBetween(ListNode node, int start, int end) {
        assert end >= start && node != null;
        if (start == end) return node;
        
        ListNode prePre = null;
        ListNode pre = null;
        ListNode tmp = node;
        int index = 1;
        
        while (tmp != null) {
            ListNode nodeStart = pre;
            ListNode nodePre = nodeStart;
            while(index > start && index <= end) {
                ListNode cur = tmp;
                tmp = tmp.next;
                cur.next = nodePre;
                nodePre = cur;
                index++;
            }
            
            if(nodePre != nodeStart) {
                if(prePre != null) {
                    prePre.next = nodePre;
                }
                else {
                    node = nodePre;
                }
                nodeStart.next = tmp;
                break;
            }
            
            prePre = pre;
            pre = tmp;
            tmp = tmp.next;
            index++;
        }
        
        return node;
    }
    
    public void test() {
        int[] a = new int[]{3,5};
        PrintUtil.println(reverseBetween(ListNode.Builder.build(a), 1, 2).toString1());
    
        int[] b = new int[]{1,2,3,4,5,6};
        PrintUtil.println(reverseBetween(ListNode.Builder.build(b), 1, 6).toString1());
        PrintUtil.println(reverseBetween(ListNode.Builder.build(b), 1, 3).toString1());
    }
}
