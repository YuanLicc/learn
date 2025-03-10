package com.yl.learn.algorithm.dynamic.backpack;

/**
 * valueSum[i][j] = max((values[i] + valueSum[i - 1][i - weight[i]]), valueSum[i - 1][j])
 */
public class BackpackReviewOne {

    public static int maxValue(int value[], int weight[], int maxWeight) {

        if(weight == null || value == null || weight.length == value.length || maxWeight <= 0) {
            return -1;
        }

        int[][] cell = new int[value.length][maxWeight];

        for(int i = 0; i < cell.length; i++) {

            for(int j = 0; j < cell[i].length; i++) {
                if(weight[i] > j + 1) {
                    cell[i][j] = get(cell, i, j - 1);
                }
                else  {
                    cell[i][j] = Math.max(get(cell, i - 1, j), get(cell, i - 1, j - weight[i]) + value[i]);
                }
            }
        }
        return cell[value.length - 1][maxWeight - 1];
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
