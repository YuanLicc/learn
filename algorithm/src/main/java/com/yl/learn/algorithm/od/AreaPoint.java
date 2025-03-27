package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
 * 1. 尝试沿着横线坐标正向绘制直线直到给定的终点E
 * 2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏
 * 给定的横坐标终点值E 以及若干条绘制指令，
 * 请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
 *
 * 输入描述
 * 首行为两个整数 N 和 E
 * 表示有N条指令,机器运行的横坐标终点值E
 * 接下来N行 每行两个整数表示一条绘制指令x offsetY
 * 用例保证横坐标x以递增排序的方式出现
 * 且不会出现相同横坐标x
 * 取值范围
 *
 * 0<N<=10000
 * 0<=x<=E<=20000
 * -10000<=offsetY<=10000
 * 输出描述
 * 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
 *
 * 示例1
 * 输入
 *
 * 4 10
 * 1 1
 * 2 1
 * 3 1
 * 4 -2
 * 输出
 *
 * 12
 * 说明
 *
 * 示例2
 * 输入
 *
 * 2 4
 * 0 1
 * 2 -2
 * 输出
 *
 * 4
 */
public class AreaPoint {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lineNum = in.nextInt(), endX = in.nextInt();
        int[][] commands = new int[lineNum][2];
        for (int i = 0; i < lineNum; i++) {
            commands[i][0] = in.nextInt();
            commands[i][1] = in.nextInt();
        }

        // 开始遍历
        int preY = commands[0][1], area = 0, preX = commands[0][0];;
        for (int i = 1; i < commands.length; i++) {
            int currentX = commands[i][0];
            area += (currentX - preX) * Math.abs(preY);
            preY = preY + commands[i][1];
            preX = currentX;
        }

        if(preY != 0) {
            area += (endX - preX) * Math.abs(preY);
        }
        System.out.println(area);
    }

}
