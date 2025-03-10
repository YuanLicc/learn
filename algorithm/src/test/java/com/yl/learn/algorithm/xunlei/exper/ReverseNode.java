package com.yl.learn.algorithm.xunlei.exper;

public class ReverseNode {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        Node node = new Node(0);

        Node curr = node;
        for(int i = 1; i < 10; i++) {
            Node newNode = new Node(i);
            curr.next = newNode;
            curr = newNode;
        }

        Node reverseNode = reverse(node);

        while (reverseNode != null) {
            System.out.print(reverseNode.value + " ");
            reverseNode = reverseNode.next;
        }

    }

    public static Node reverse(Node root) {

        if(root == null) {
            return null;
        }

        Node pre = root;
        Node curr = root.next;
        pre.next = null;

        while(curr != null) {
            Node next = curr.next;

            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }

}
