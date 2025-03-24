package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给定一组不等式，判断是否成立并输出不等式的最大差(输出浮点数的整数部分)
 *
 * 要求:
 *
 * 不等式系数为 double类型，是一个二维数组
 *
 * 不等式的变量为 int类型，是一维数组;
 *
 * 不等式的目标值为 double类型，是一维数组
 *
 * 不等式约束为字符串数组，只能是:“>”,“>=”,“<”,“<=”,“=”，
 *
 * 例如，不等式组:
 *
 *  a11x1 + a12x2 + a13x3 + a14x4 + a15x5 <= b1;
 *  a21x1 + a22x2 + a23x3 + a24x4 + a25x5 <= b2;
 *  a31x1 + a32x2 + a33x3 + a34x4 + a35x5 <= b3;
 * 最大差 = max{(a11x1+a12x2+a13x3+a14x4+a15x5-b1),(a21x1+a22x2+a23x3+a24x4+ a25x5-b2),(a31x1+a32x2+a33x3+a34x4+a35x5-b3)},
 *
 * 类型为整数(输出浮点数的整数部分)
 *
 * 输入描述
 * a11,a12,a13,a14,a15,a21,a22,a23,a24,a25, a31,a32,a33,a34,a35,x1,x2,x3,x4,x5,b1,b2,b3,<=,<=,<=
 *
 * 不等式组系数(double类型):
 * a11,a12,a13,a14,a15
 * a21,a22,a23,a24,a25
 * a31,a32,a33,a34,a35
 * 不等式变量(int类型):x1,x2,x3,x4,x5
 * 不等式目标值(double类型):b1,b2,b3
 * 不等式约束(字符串类型):<=,<=,<=
 *
 * 输出描述
 * true或者 false，最大差
 *
 * 示例1
 * 输入
 *
 * 2.3,3,5.6,7.6;11,3,8.6,25,1;0.3,9,5.3,66,7.8;
 * 1,3,2,7,5;340,670,80.6;<=,<=,<=
 * 输出
 *
 * false 458
 */
public class NotEquals {

    static String COMMA = ",";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numS = in.nextLine().split(";");
        int[] plu = toInt(numS[numS.length - 3], COMMA);
        double[] right = toDouble(numS[numS.length - 2], COMMA);
        String[] rel = numS[numS.length - 1].split(COMMA);

        int maxMinus = Integer.MIN_VALUE;
        boolean rs = true;
        for (int i = 0; i < numS.length - 3; i++) {
            double num1 = 0;
            double[] current = toDouble(numS[i], COMMA);
            for (int j = 0; j < current.length; j++) {
                num1 += current[j] * plu[j];
            }
            rs = rs && judge(num1, right[i], rel[i]);
            maxMinus = (int) Math.max(num1 - right[i], maxMinus);
        }
        System.out.println(rs + "," + maxMinus);
    }

    private static boolean judge(double num1, double num2, String rel) {
        switch (rel) {
            case ">" : {
                return num1 > num2;
            }
            case ">=" : {
                return num1 >= num2;
            }
            case "<" : {
                return num1 < num2;
            }
            case "<=" : {
                return num1 <= num2;
            }
            case "=" : {
                return num1 == num2;
            }
            default: return false;
        }
    }

    private static double[] toDouble(String str, String deli) {
        String[] nums = str.split(deli);
        double[] rs = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rs[i] = Double.parseDouble(nums[i]);
        }
        return rs;
    }

    private static int[] toInt(String str, String deli) {
        String[] nums = str.split(deli);
        int[] rs = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rs[i] = Integer.parseInt(nums[i]);
        }
        return rs;
    }
}
