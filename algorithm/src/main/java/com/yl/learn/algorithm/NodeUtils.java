package com.yl.learn.algorithm;

public class NodeUtils {

    public static <T> Node<T> toNode(T ... arr) {
        Node<T> root = new Node<>(arr[0]);
        Node<T> node = root;
        int index = 1;

        while (index < arr.length) {
            node.next = new Node<>(arr[index++]);
            node = node.next;
        }

        return root;
    }

    public static <T> String toString(Node<T> node) {

        if(node == null) {
            return "null";
        }

        StringBuilder builder = new StringBuilder();

        Node<T> temp = node;

        while (temp != null) {
            builder.append(temp.val.toString() + " -> ");
            temp = temp.next;
        }

        return builder.substring(0, builder.length() - 4);
    }

}
