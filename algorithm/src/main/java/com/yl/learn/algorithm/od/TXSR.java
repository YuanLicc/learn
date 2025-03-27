package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 商人经营一家店铺，有number种商品，由于仓库限制每件商品的最大持有数量是item[index]，每种商品的价格是item-price[item_index][day]
 *
 * 通过对商品的买进和卖出获取利润，请给出商人在days天内能获取的最大的利润
 * 注：同一件商品可以反复买进和卖出
 *
 * 输入描述
 * 第一行输入商品的数量number，比如3
 * 第二行输入商品售货天数 days，比如3
 * 第三行输入仓库限制每件商品的最大持有数量是item[index]，比如4 5 6
 * 后面继续输入number行days列，含义如下：
 * 第一件商品每天的价格，比如1 2 3
 * 第二件商品每天的价格，比如4 3 2
 * 第三件商品每天的价格，比如1 5 3
 * 输出描述
 * 输出商人在这段时间内的最大利润。
 * 示例1
 * 输入
 * 3
 * 3
 * 4 5 6
 * 1 2 3
 * 4 3 2
 * 1 5 2
 * 输出
 * 32
 */
public class TXSR {

    private static int max(int[][] spDaysPrice, int[] spNumLimit) {
        int sum = 0;
        for (int i = 0; i < spDaysPrice.length; i++) {
            int min = Integer.MAX_VALUE, maxMinus = 0;
            for (int j = 0;  j < spDaysPrice[i].length; j++) {
                min = Math.min(min, spDaysPrice[i][j]);
                maxMinus = Math.max(spDaysPrice[i][j] - min, maxMinus);
            }
            sum += spNumLimit[i] * maxMinus;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int saleDays = in.nextInt();
        in.nextLine();
        int[] spNumLimit = new int[number];
        for (int i = 0; i < number; i++) {
            spNumLimit[i] = in.nextInt();
        }
        in.nextLine();
        int[][] spDaysPrice = new int[number][saleDays];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < saleDays; j++) {
                spDaysPrice[i][j] = in.nextInt();
            }
        }
        System.out.println(max(spDaysPrice, spNumLimit));
    }

}
