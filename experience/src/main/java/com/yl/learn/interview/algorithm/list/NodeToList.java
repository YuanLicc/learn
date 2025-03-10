package com.yl.learn.interview.algorithm.list;

import java.util.ArrayList;

public class NodeToList {

    public static ArrayList<Integer> listFromTailToHead(ListNode listNode) {
        ArrayList<Integer> re = new ArrayList<>();

        ListNode temp = listNode;
        while(temp != null) {
            re.add(temp.val);
            temp = temp.next;
        }

        for(int i = 0; i < (re.size()) / 2; i++) {
            Integer tempIn = re.get(re.size() - 1 - i);
            re.set(re.size() - 1 - i, re.get(i));
            re.set(i, tempIn);
        }
        return re;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
