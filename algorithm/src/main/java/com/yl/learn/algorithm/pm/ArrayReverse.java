package com.yl.learn.algorithm.pm;

import com.yl.learn.algorithm.Node;

public class ArrayReverse {

    public static void reverse(char[] str) {

        if(str == null || str.length == 1 || str.length == 0) {
            return;
        }

        reverse(str, 0, str.length - 1);
    }


    /**
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
     * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，
     * 使得原字符串变成字符串“cdefab”。请写一个函数完成此功能，
     * 要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。
     * @param chars
     * @param k
     */
    public static void reverse(char[] chars, int k) {

        reverse(chars, 0, k - 1);
        reverse(chars, k, chars.length - 1);
        reverse(chars, 0, chars.length - 1);

    }


    /**
     * 编写程序，在原字符串中把字符串尾部的m个字符移动到字符串的头部，
     * 要求：长度为n的字符串操作时间复杂度为O(n)，空间复杂度为O(1)。
     * 例如，原字符串为”Ilovebaofeng”，m=7，输出结果为：”baofengIlove”。
     * @param chars
     * @param lastK
     */
    public static void reverseLastK(char[] chars, int lastK) {

        int lastIndex = chars.length - 1;

        reverse(chars, 0, lastIndex - lastK);
        reverse(chars, lastIndex - lastK + 1, lastIndex);
        reverse(chars, 0, chars.length - 1);
    }

    private static void reverse(char[] chars, int from, int to) {

        while (from < to) {

            char temp = chars[to];
            chars[to--] = chars[from];
            chars[from++] = temp;
        }
    }

    /**
     * 链表翻转。给出一个链表和一个数k，比如，链表为1→2→3→4→5→6，k=2，
     * 则翻转后2→1→6→5→4→3，若k=3，翻转后3→2→1→6→5→4，若k=4，
     * 翻转后4→3→2→1→6→5，用程序实现。
     * @param node
     * @param k
     * @return
     */
    public static Node<Integer> reverse(Node<Integer> node, int k) {

        if(node == null) {
            return null;
        }

        Node<Integer> pre = node;

        Node<Integer> middle = pre;

        Node<Integer> curr = node.next;
        pre.next = null;

        int count = 1;
        while (count < k && curr != null) {

            Node<Integer> next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
            count++;
        }

        middle.next = curr;

        return pre;
    }

}
