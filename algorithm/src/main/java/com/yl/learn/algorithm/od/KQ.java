package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 公司用一个字符串来表示员工的出勤信息
 *
 * absent：缺勤
 * late：迟到
 * leaveearly：早退
 * present：正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 *
 * 缺勤不超过一次；
 * 没有连续的迟到/早退；
 * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
 * 输入描述
 * 用户的考勤数据字符串
 *
 * 记录条数 >= 1；
 * 输入字符串长度 < 10000；
 * 不存在非法输入；
 * 如：
 *
 * 2
 * present
 * present absent present present leaveearly present absent
 *
 * 输出描述
 * 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”，
 * 对于输入示例的结果应为：
 *
 * true false
 */
public class KQ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            String[] kqs = in.nextLine().split(" ");
            sb.append(find(kqs) + (i == num - 1 ? "" : " "));
        }
        System.out.println(sb.toString());
    }

    private static boolean find(String[] kqs) {
        int left = 0, right = 0, ab = 0, ll = 0;
        boolean pre = false;
        while(right < kqs.length) {
            String current = kqs[right];
            if(current.equals("absent")) {
                pre = false;
                ab++;
            }
            else if(current.equals("late") || current.equals("leaveearly")) {
                if(pre) left = right;
                else {
                    pre = true;
                    ll++;
                }
            }
            if(ab > 1) {
                while (left <= right && ab > 1) {
                    if(kqs[left].equals("absent")) {
                        ab--;
                    }
                    left++;
                }
            }
            else if(ll + ab > 3) {
                while (left <= right && ll + ab > 3) {
                    if(kqs[left].equals("absent")) {
                        ab--;
                    }
                    else if(kqs[left].equals("late") || kqs[left].equals("leaveearly")) {
                        ll--;
                    }
                    left++;
                }
            }
            if(right - left == 6) {
                return true;
            }
            right++;
        }
        return kqs.length < 7 && left == 0;
    }
}
