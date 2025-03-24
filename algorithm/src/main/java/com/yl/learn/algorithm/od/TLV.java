package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * TLV编码是按[Tag Length Value]格式进行编码的，一段码流中的信元用Tag标识，
 * Tag在码流中唯一不重复，Length表示信元Value的长度，Value表示信元的值。
 * 码流以某信元的Tag开头，Tag固定占一个字节，Length固定占两个字节，字节序为小端序。
 * 现给定TLV格式编码的码流，以及需要解码的信元Tag，请输出该信元的Value。
 *
 * 输入码流的16进制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；
 * 码流字符串的最大长度不超过50000个字节。
 *
 * 输入描述
 * 输入的第一行为一个字符串，表示待解码信元的Tag；
 * 输入的第二行为一个字符串，表示待解码的16进制码流，字节之间用空格分隔。
 *
 * 输出描述
 * 输出一个字符串，表示待解码信元以16进制表示的Value。
 *
 * 示例1
 * 输入
 * 31
 * 32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
 * 输出
 * 32 33
 */
public class TLV {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String aimTag = in.nextLine();
        String[] stream = in.nextLine().split(" ");
        int start = 0;
        while (start < stream.length) {
            String tag = stream[start];
            int length = Integer.parseInt(stream[start + 2] + stream[start + 1], 16);
            if(tag.equals(aimTag)) {
                print(stream, start + 3, length);
                return;
            }
            else {
                start = start + 2 + length + 1;
            }
        }
    }

    private static void print(String[] stream, int start, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + len; i++) {
            sb.append(stream[i]).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
