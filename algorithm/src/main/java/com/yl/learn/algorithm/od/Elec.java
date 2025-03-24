package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区;
 * 整体上常年光照良好，但是也有一些地区光照不太好。
 *
 * 某电力公司希望在这里建设多个光伏电站，生产清洁能源对每平方公里的土地进行了发电评估，
 * 其中不能建设的区域发电量为0kw，可以发电的区域根据光照，地形等给出了每平方公里年发电量x千瓦。
 * 我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
 *
 * 输入描述
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长最低要求的发电量
 * 之后每行为调研区域每平方公里的发电量
 *
 * 输出描述
 * 输出为这样的区域有多少个
 *
 * 示例1
 * 输入
 *
 * 2 5 2 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 输出
 * 4
 */
public class Elec {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt(), j = in.nextInt(), width = in.nextInt(), aim = in.nextInt();
        if(width > i || width > j) return;
        int[][] nums = new int[i + 1][j + 1];
        for (int index = 1; index <= i; index++) {
            for (int index1 = 1; index1 <= j; index1++) {
                nums[index][index1] = in.nextInt();
            }
        }
        init(nums);
        System.out.println(find(nums, width, aim));
    }

    private static int find(int[][] nums, int width, int aim) {
        int cnt = 0;
        for (int i = width; i < nums.length; i++) {
            for (int j = width; j < nums[i].length; j++) {
                int sum = nums[i][j] - nums[i - width][j] - nums[i][j - width] + nums[i - width][j - width];
                if(sum >= aim) cnt++;
            }
        }
        return cnt;
    }

    private static void init(int[][] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums[i].length; j++) {
                nums[i][j] = nums[i][j] + nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
            }
        }
    }

}
