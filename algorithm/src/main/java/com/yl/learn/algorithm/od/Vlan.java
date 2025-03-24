package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * VLAN是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入VLAN ID(1-4094之间的整数)的概念。
 * 定义一个VLAN ID的资源池(下称VLAN资源池)，资源池中连续的VLAN用开始VLAN-结束VLAN表示，不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。
 * 现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池。
 *
 * 输入描述
 * 第一行为字符串格式的VLAN资源池，第二行为业务要申请的VLAN，VLAN的取值范围为[1,4094]之间的整数。
 *
 * 输出描述
 * 从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并且按照VLAN从小到大升序输出。
 * 如果申请的VLAN不在原VLAN资源池内，输出原VLAN资源池升序排序后的字符串即可。
 *
 * 示例1
 * 输入
 *
 * 1-5
 * 2
 * 输出
 * 1,3-5
 *
 * 输入
 * 20-21,15,18,30,5-10
 * 15
 * 输出
 * 5-10,18,20-21,30
 */
public class Vlan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numArea = in.nextLine().split(",");
        int aim = in.nextInt();
        boolean[] nums = new boolean[4094];
        for (int i = 0; i < numArea.length; i++) {
            parse(nums, numArea[i]);
        }
        nums[aim - 1] = false;
        print(nums);
    }

    private static void print(boolean[] nums) {
        StringBuilder sb = new StringBuilder();
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i++) {
            if(start != -1 && end != -1) {
                append(sb, start, end);
                start = -1;
                end = -1;
            }
            if(nums[i]) {
                start = start == -1 ? i : start;
            }
            else end = start == -1 ? -1 : i - 1;
        }
        if(start != -1) {
            end = end == -1 ? nums.length - 1 : end;
            append(sb, start, end);
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static void append(StringBuilder sb, int start, int end) {
        if(start == end) {
            sb.append(start + 1).append(",");
        }
        else {
            sb.append(start + 1).append("-").append(end + 1).append(",");
        }
    }

    private static void parse(boolean[] nums, String numArea) {
        if(numArea.contains("-")) {
            String[] two = numArea.split("-");
            for (int i = Integer.parseInt(two[0]); i <= Integer.parseInt(two[1]); i++) {
                nums[i - 1] = true;
            }
        }
        else nums[Integer.parseInt(numArea) - 1] = true;
    }
}
