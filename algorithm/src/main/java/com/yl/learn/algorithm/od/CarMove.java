package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 有一辆汽车需要从 m * n 的地图左上角（起点）开往地图的右下角（终点），去往每一个地区都需要消耗一定的油量，加油站可进行加油。
 *
 * 请你计算汽车确保从从起点到达终点时所需的最少初始油量。
 *
 * 说明：
 *
 * 智能汽车可以上下左右四个方向移动
 * 地图上的数字取值是 0 或 -1 或 正整数：
 * -1 ：表示加油站，可以加满油，汽车的油箱容量最大为100；
 * 0 ：表示这个地区是障碍物，汽车不能通过
 * 正整数：表示汽车走过这个地区的耗油量
 * 如果汽车无论如何都无法到达终点，则返回 -1
 * 输入描述
 * 第一行为两个数字，M，N，表示地图的大小为 M * N
 *
 * 0 < M,N ≤ 200
 * 后面一个 M * N 的矩阵，其中的值是 0 或 -1 或正整数，加油站的总数不超过 200 个
 *
 * 输出描述
 * 如果汽车无论如何都无法到达终点，则返回 -1
 *
 * 如果汽车可以到达终点，则返回最少的初始油量
 *
 * 示例1
 * 输入
 *
 * 2,2
 * 10,20
 * 30,40
 * 1
 * 2
 * 3
 * 输出
 *
 * 70
 * 1
 * 说明
 *
 * 行走的路线为：右→下
 *
 * 示例2
 * 输入
 *
 * 4,4
 * 10,30,30,20
 * 30,30,-1,10
 * 0,20,20,40
 * 10,-1,30,40
 * 1
 * 2
 * 3
 * 4
 * 5
 * 输出
 *
 * 70
 * 1
 * 说明
 *
 * 行走的路线为：右→右→下→下→下→右
 *
 * 示例3
 * 输入
 *
 * 4,5
 * 10,0,30,-1,10
 * 30,0,20,0,20
 * 10,0,10,0,30
 * 10,-1,30,0,10
 * 输出
 *
 * 60
 * 1
 * 说明
 *
 * 行走的路线为：下→下→下→右→右→上→上→上→右→右→下→下→下
 */
public class CarMove {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] ceilInfo = in.nextLine().split(",");
        int row = Integer.parseInt(ceilInfo[0]);
        int column = Integer.parseInt(ceilInfo[1]);

        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }
        dfs(matrix, new boolean[row][column], 0, 0, 100, 100, false);
        System.out.println(minOil == 101 ? -1 : minOil);
    }

    static int minOil = 101;

    static void dfs(int[][] matrix, boolean[][] visited, int i, int j, int startOil, int currentOil, boolean isVisit) {
        if(i < 0 || i >= visited.length
                || j < 0 || j >= visited[0].length
                || visited[i][j] || currentOil < matrix[i][j] || matrix[i][j] == 0) return;
        if(i == matrix.length - 1 && j == matrix[0].length - 1) { // 表示到达了终点
            if(isVisit) minOil = Math.min(startOil, minOil);
            else minOil = Math.min(100 - currentOil + matrix[i][j], minOil);
            return;
        }

        if(matrix[i][j] == -1) { // 遇到加油站了
            startOil = isVisit ? startOil : 100 - currentOil;
            currentOil = 100;
            isVisit = true;
        }
        else {
            currentOil -= matrix[i][j];
        }
        visited[i][j] = true;
        for (int[] direction : directions) {
            dfs(matrix, visited, i + direction[0], j + direction[1], startOil, currentOil, isVisit);
        }
        visited[i][j] = false;
        isVisit = !(matrix[i][j] == -1);
    }

    static int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

}
