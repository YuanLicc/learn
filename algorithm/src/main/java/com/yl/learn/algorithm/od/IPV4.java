package com.yl.learn.algorithm.od;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 存在一种虚拟IPv4地址，由4小节组成，每节的范围为0~255，以#号间隔，虚拟IPv4地址可以转换为一个32位的整数，例如：
 * 128#0#255#255，转换为32位整数的结果为2147549183（0x8000FFFF）
 * 1#0#0#0，转换为32位整数的结果为16777216（0x01000000）
 *
 * 现以字符串形式给出一个虚拟IPv4地址，限制第1小节的范围为1~128，即每一节范围分别为(1~128)#(0~255)#(0~255)#(0~255)，
 * 要求每个IPv4地址只能对应到唯一的整数上。如果是非法IPv4，返回invalid IP
 * 输入描述
 * 输入一行，虚拟IPv4地址格式字符串
 *
 * 输出描述
 * 输出一行，按照要求输出整型或者特定字符
 *
 * 示例1
 * 输入
 *
 * 100#101#1#5
 * 输出
 * 1684340997
 *
 * 示例2
 * 输入
 * 1#2#3
 * 输出
 * invalid IP
 */
public class IPV4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] ips = in.nextLine().split("#");
        if(ips.length != 4) {
            printValid();
            return;
        }
        int rs = 0;
        for (int i = 0; i < ips.length; i++) {
            if(!isValidNumber(ips[i], i != 0)) {
                printValid();
                return;
            }
            int number = Integer.parseInt(ips[i]);
            if(i == 0) {
                if(!(number >= 1 && number <= 128)) {
                    printValid();
                    return;
                }
            }
            else {
                if(!(number >= 0 && number <= 255)) {
                    printValid();
                    return;
                }
            }
            rs = rs * 256 + number;
        }
        System.out.println(rs);
    }

    private static boolean isValidNumber(String number, boolean canStartZero) {
        if(number == null || number.isEmpty()) return false;
        if(!canStartZero && number.charAt(0) == '0') return false;
        for (int i = 0; i < number.length(); i++) {
            if(!Character.isDigit(number.charAt(i))) return false;
        }
        return true;
    }

    private static void printValid() {
        System.out.println("invalid IP");
    }

}
