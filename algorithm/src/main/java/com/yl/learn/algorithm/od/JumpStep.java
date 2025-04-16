package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
 * 跳房子的过程中，可以向前跳，也可以向后跳。
 * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
 * 如果有，请输出索引和最小的步数组合。
 *
 * 注意：
 * 数组中的步数可以重复，但数组中的元素不能重复使用。
 * 提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 * 输入描述
 * 第一行输入为每回合可能连续跳的步数，它是int整数数组类型。
 * 第二行输入为房子总格数count，它是int整数类型。
 *
 * 备注
 * count ≤ 1000
 * 0 ≤ steps.length ≤ 5000
 * -100000000 ≤ steps ≤ 100000000
 * 输出描述
 * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）
 *
 * 示例1
 * 输入
 * [1,4,5,2,2]
 * 7
 * 输出
 *
 * [5, 2]
 */
public class JumpStep {
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int[] steps = Arrays.stream(
//                in.nextLine().replaceAll("[^\\-0-9,]", "").split(",")
//        ).mapToInt(Integer::parseInt).toArray();
//        int aimStep = in.nextInt();
//        // 维护一个最小索引和用作判断
//        int minIndexSum = steps.length * 2;
//        // 维护输出结果
//        int firstNum = 0, secondNum = 0;
//        // 存储数字与索引
//        Map<Integer, Integer> numIndexMap = new HashMap<>();
//        for (int i = 0; i < steps.length; i++) {
//            Integer otherIndex = numIndexMap.get(aimStep - steps[i]);
//            // 如果在map中找到了目标值（aimStep - steps[i]），说明当前的 steps[i] + steps[otherIndex] = aimStep
//            if(null != otherIndex) {
//                // 如果索引和小于前面符合条件的索引和，则更新结果
//                if(i + otherIndex < minIndexSum) {
//                    minIndexSum = i + otherIndex;
//                    firstNum = steps[otherIndex];
//                    secondNum = steps[i];
//                }
//            }
//            // 此处需要考虑情况，数组中存在重复数值，比如说 2, 3, 2, 4, 3 aim=6
//            // 咱们不能在遍历时直接 put，这样相同数值的下标将会被更新
//            // 咱们需要计算最小索引和，那么对于重复数值，只需要维护最小的索引即可
//            if(!numIndexMap.containsKey(steps[i])) {
//                numIndexMap.put(steps[i], i);
//            }
//        }
//        System.out.println(minIndexSum == steps.length * 2 ? "" : ("[" + firstNum + "," + secondNum + "]"));
//    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] steps = Arrays.stream(
                in.nextLine().replaceAll("[^\\-0-9,]", "").split(",")
        ).mapToInt(Integer::parseInt).toArray();
        int aimStep = in.nextInt();
        // 维护一个最小索引和用作判断
        int minIndexSum = steps.length * 2;
        // 维护输出结果
        int firstNum = 0, secondNum = 0;
        // 暴力遍历所有组合
        for (int i = 0; i < steps.length; i++) {
            for (int j = i + 1; j < steps.length; j++) {
                // 满足两数之和等于目标值
                if(steps[i] + steps[j] == aimStep) {
                    if(i + j < minIndexSum) {
                        minIndexSum = i + j;
                        firstNum = steps[i];
                        secondNum = steps[j];
                    }
                }
            }
        }
        System.out.println(
                minIndexSum == steps.length * 2 ? "" : ("[" + firstNum + "," + secondNum + "]")
        );
    }

    // int[][] dp = new int[steps.length + 1][aimStep + 1];
    //        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], aimStep);
    //
    //        for (int i = 1; i < dp.length; i++) {
    //            for (int j = 1; j < dp[i].length; j++) {
    //                if(j == steps[i - 1]) dp[i][j] = 1;
    //                else if(j > steps[i - 1]) {
    //                    dp[i][j] = Math.min(dp[i - 1][j], dp[i  - 1][j - steps[i - 1]] + 1);
    //                }
    //                else dp[i][j] = dp[i - 1][j];
    //            }
    //        }

}
