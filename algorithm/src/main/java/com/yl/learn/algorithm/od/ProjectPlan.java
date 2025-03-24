package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 项目组共有N个开发人员，项目经理接到了M个独立的需求，每个需求的工作量不同，且每个需求只能由一个开发人员独立完成，不能多人合作。假定各个需求直接无任何先后依赖关系，请设计算法帮助项目经理进行工作安排，使整个项目能用最少的时间交付。
 *
 * 输入描述
 * 第一行输入为M个需求的工作量，单位为天，用逗号隔开。
 *
 * 例如：X1 X2 X3 … Xm 。表示共有M个需求，每个需求的工作量分别为X1天，X2天…Xm天。
 *
 * 其中0<M<30；0<Xm<200
 *
 * 第二行输入为项目组人员数量N
 *
 * 输出描述
 * 最快完成所有工作的天数
 *
 * 用例
 * 输入：
 *
 * 6 2 7 7 9 3 2 1 3 11 4
 * 2
 * 输出：
 *
 * 28
 * 1
 * 说明：
 *
 * 共有两位员工，其中一位分配需求 6 2 7 7 3 2 1共需要28天完成，另一位分配需求 9 3 11 4 共需要27天完成，故完成所有工作至少需要28天。
 */
public class ProjectPlan {

    // 理解题目：将数组中数组分为N组，使得，这N组分别之和差值最小，也就是尽量均分，则满足题意
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numsStr = in.nextLine().split(" ");
        int[] nums = new int[numsStr.length];
        int sum = 0;
        for (int i = 0; i < numsStr.length; i++) {
            int num = Integer.parseInt(numsStr[i]);
            sum += num;
            nums[i] = num;
        }
        int person = in.nextInt();
        int left = 1, right = sum;
        while (left < right) {
            int middle = (left + right) / person;
        }
    }
}
