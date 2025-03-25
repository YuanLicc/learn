package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局。
 *
 * 现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有 N（元）进行投资，能接收的总风险值为X。
 *
 * 你要在可接受范围内选择最优的投资方式获得最大回报。
 *
 * 备注：
 *
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回报
 * 输入描述
 * 第一行：
 *
 * 产品数（取值范围[1,20]）
 * 总投资额（整数，取值范围[1, 10000]）
 * 可接受的总风险（整数，取值范围[1,200]）
 * 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
 * 第三行：产品风险值序列，输入为整数，取值范围[1, 100]
 * 第四行：最大投资额度序列，输入为整数，取值范围[1, 10000]
 *
 * 输出描述
 * 每个产品的投资额序列
 *
 * 示例1
 * 输入
 *
 * 5 100 10
 * 回报率 10 20 30 40 50
 * 风险值 3  4  5  6  10
 * 最大额 20 30 20 40 30
 * 输出
 * 0 30 0 40 0
 * 说明  投资第二项30个单位，第四项40个单位，总的投资风险为两项相加为4+6=10
 */
public class TZRecover {

    // 审题，最多选两个，那么计算选一个和选两个的值比较即可
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int productNum = in.nextInt();
        int amount = in.nextInt();
        int fxAccept = in.nextInt();
        in.nextLine();

        int[][] productInfo = new int[3][productNum];
        for (int i =  0; i < productInfo.length; i++) {
            for (int j = 0; j < productInfo[i].length; j++) {
                productInfo[i][j] = in.nextInt();
            }
        }
        int oneMaxIndex = findMax(productInfo, amount, fxAccept);
        int oneR = recover(productInfo, oneMaxIndex);
        int[] twoMaxIndex = findMaxTwo(productInfo, amount, fxAccept);
        int twoR = recover(productInfo, twoMaxIndex[0], twoMaxIndex[1]);
        if(oneR < 0 && twoR < 0) {
            print(productInfo, -1, -1);
        }
        else if(oneR > twoR) {
            print(productInfo, oneMaxIndex, -1);
        }
        else {
            print(productInfo, twoMaxIndex[0], twoMaxIndex[1]);
        }
    }

    private static void print(int[][] productInfo, int i, int j) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < productInfo[0].length; index++) {
            if(index == i) {
                sb.append(productInfo[2][i]).append(index == productInfo[0].length - 1 ? "" : " ");
            }
            else if(index == j) {
                sb.append(productInfo[2][j]).append(index == productInfo[0].length - 1 ? "" : " ");
            }
            else {
                sb.append(0).append(index == productInfo[0].length - 1 ? "" : " ");
            }
        }
        System.out.println(sb.toString());
    }

    private static int recover(int[][] productInfo, int i, int j) {
        return recover(productInfo, i) + recover(productInfo, j);
    }

    private static int recover(int[][] productInfo, int i) {
        if(i < 0) return -1;
        return productInfo[0][i] * productInfo[2][i];
    }


    private static int[] findMaxTwo(int[][] productInfo, int amount, int fxAccept) {
        int maxR = 0;
        int[] rs = new int[2];
        rs[0] = -1;
        rs[1] = -1;
        for (int i = 0; i < productInfo[0].length; i++) {
            int oneAmount = productInfo[2][i];
            int oneFxAccept = productInfo[1][i];
            int oneR = productInfo[0][i] * oneAmount;
            for (int j = i + 1; j < productInfo[0].length; j++) {
                int twoAmount = productInfo[2][j];
                int twoFxAccept = productInfo[1][j];
                int twoR = productInfo[0][j] * twoAmount;
                if(oneAmount + twoAmount <= amount && oneFxAccept + twoFxAccept <= fxAccept) {
                    if(oneR + twoR > maxR) {
                        rs[0] = i;
                        rs[1] = j;
                    }
                }
            }
        }
        return rs;
    }

    private static int findMax(int[][] productInfo, int amount, int fxAccept) {
        int maxR = 0, maxIndex = -1;
        for (int i = 0; i < productInfo[0].length; i++) {
            if(productInfo[2][i] <= amount && productInfo[1][i] <= fxAccept) {
                if(productInfo[0][i] * productInfo[2][i] < maxR) {
                    maxR = productInfo[0][i] * productInfo[2][i];
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }
}
