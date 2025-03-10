package com.yl.learn.algorithm.zuo;

public class NumOfCoins {

    public static int coins(int[] moneys, int aim) {
        if(moneys == null || moneys.length == 0 || aim < 0) {
            return -1;
        }

        if(aim == 0) {
            return 1;
        }

        int[][] cell = new int[moneys.length][aim + 1];


        for(int i = 0; i < aim; i++) {
            cell[0][i] = (i + 1) % moneys[0] == 0 ? 1 : 0;
        }

        for(int i = 1; i < moneys.length; i++) {
            cell[i][0] = 1;
        }

        for(int i = 1; i < moneys.length; i++) {


            for(int j = 1; j < aim; j++) {

                int count = 0;

                for(int k = 0;j - k * moneys[i] >= 0; k++) {
                    count += get(cell, i - 1, j - k * moneys[i]);
                }

                cell[i][j] = count;
            }
        }

        return cell[moneys.length - 1][aim - 1];

    }

    private static int get(int[][] cell, int i, int j) {
        if(i < 0 || j < 0) {
            return 0;
        }
        else {
            return cell[i][j];
        }
    }

}
