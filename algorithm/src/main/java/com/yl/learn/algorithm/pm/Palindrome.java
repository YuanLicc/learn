package com.yl.learn.algorithm.pm;

import com.yl.learn.algorithm.Node;

public class Palindrome {

    /**
     * 判断一个字串是否是回文？
     * @param source
     * @return
     */
    public static boolean palindrome(String source) {
        if(source == null) {
            return false;
        }

        if(source.length() == 1) {
            return true;
        }

        int lastIndex = source.length() - 1;
        int middleIndex = lastIndex >> 1;

        for(int i = 0; i <= middleIndex; i++) {
            char left = source.charAt(i);
            char right = source.charAt(lastIndex - i);

            if(left != right) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断一条单向链表是不是“回文”
     * @param node
     * @return
     */
    public static boolean palindrome(Node<Integer> node) {
        if(node == null) {
            return false;
        }

        Node<Integer> temp = node;
        int count = 0;

        while (temp != null) {
            temp = temp.next;
            count++;
        }

        Node<Integer> middle = node;
        int middleCount = count >> 1;

        while (middleCount != 0) {
            middle = middle.next;
            middleCount--;
        }

        Node<Integer> pre = middle;
        Node<Integer> curr = middle.next;
        pre.next = null;

        while (curr != null) {
            Node<Integer> next = curr.next;

            curr.next = pre;
            pre = curr;
            curr = next;
        }

        count = 0;
        while (pre != null && count <= middleCount) {
            if(pre.val != node.val) {
                return false;
            }
            pre = pre.next;
            count++;
        }

        return true;
    }

}
