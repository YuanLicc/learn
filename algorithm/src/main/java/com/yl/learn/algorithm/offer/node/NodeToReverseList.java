package com.yl.learn.algorithm.offer.node;

import com.yl.learn.algorithm.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class NodeToReverseList {

    public static <T> List<T> toListRecursion(Node<T> node) {
        List<T> list = new ArrayList<>();

        toList(node, list);

        return list;
    }

    /**
     * 递归实现
     */
    private static <T> void toList(Node<T> node, List<T> list) {
        Node<T> m = node;

        if (node == null) {
            return;
        }

        if (node.next != null) {
            toList(node.next, list);
        }

        list.add(m.val);
    }

    // 栈实现
    public static <T> List<T> toListStack(Node<T> node) {

        if(node == null) {
            return null;
        }

        Stack<T> stack = new Stack<>();

        Node<T> temp = node;

        while (temp != null) {
            stack.push(temp.val);

            temp = temp.next;
        }

        List<T> list = new ArrayList<>(stack.size());

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list;
    }

}
