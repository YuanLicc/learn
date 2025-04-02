package com.yl.learn.interview.algorithm.list;

import com.yl.learn.common.util.PrintUtil;
import junit.framework.TestCase;

public class NodeToListTest extends TestCase {

    public void testNodeToList() {

        NodeToList.ListNode listNode = new NodeToList.ListNode(0);
        listNode.next = new NodeToList.ListNode(1);

        PrintUtil.print(NodeToList.listFromTailToHead(listNode));
    }
}
