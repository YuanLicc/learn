package com.yl.learn.algorithm.zuo;

/**
 * 商店补钱，输入纸币面额：{10, 5, 2, 1}，目标补钱额度：20
 * 输出补钱张数最少的数目，如上面栗子中，仅需两张 10 元即可满足 20 额度，即返回 2。
 * f[i][j]=min(f[i－1][j-k*value[i]+k)，其中0<=k<=j/value[i]
 */
public class MinNumMoney {

    public static int moneyNum(int[] moneys, int aim) {

        if(moneys == null || moneys.length == 0 || aim < 0) {
            return 0;
        }
        else if(aim == 0){
            return 0;
        }

        int[][] cell = new int[moneys.length][aim];

        for(int i = 0; i < aim; i++) {
            if(moneys[0] <= (i + 1) && (i + 1) % moneys[0] == 0) {
                cell[0][i] = (i + 1) / moneys[0];
            }
            else {
                cell[0][i] = Integer.MAX_VALUE;
            }
        }

        for(int i = 1; i < cell.length; i++) {
            if(moneys[i] == 1) {
                cell[i][0] = 1;
            }
            else {
                cell[i][0] = Integer.MAX_VALUE;
            }
        }


        for(int i = 1; i < cell.length; i++) {

            for(int j = 1; j < cell[i].length; j++) {

                if((j + 1) < moneys[i]) {
                    cell[i][j] = Integer.MAX_VALUE;
                }
                else {
                    int k = (j + 1) / moneys[i];

                    int min = Integer.MAX_VALUE;

                    for(int kk = 0; kk <= k; kk++) {

                        int c = Integer.MAX_VALUE;

                        if(j + 1 - kk * moneys[i] == 0) {
                            c = 0;
                        }
                        else {
                            c = get(cell, i - 1,j - kk * moneys[i]);
                        }

                        if(c != Integer.MAX_VALUE && (c + kk) < min) {
                            min = c + kk;
                        }
                    }

                    cell[i][j] = min;
                }
            }
        }

        return cell[moneys.length - 1][aim - 1];
    }


    private static int get(int[][] cell, int i, int j) {
        if(i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        else {
            return cell[i][j];
        }
    }

}
