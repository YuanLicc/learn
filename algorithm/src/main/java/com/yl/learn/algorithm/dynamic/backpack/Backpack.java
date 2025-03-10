package com.yl.learn.algorithm.dynamic.backpack;

/**
 * 石头、大小、价格，背包大小，求价值最大的背包策略
 * @author YuanLi
 */
public class Backpack {

    public static int[][] backpack(int[] stonesV, int[] stonesM, int backpackV) {
        if(stonesM == null || stonesV == null || stonesM.length != stonesV.length) {
            return null;
        }

        if(stonesM.length == 0 || backpackV <= 0) {
            return null;
        }

        int[][] cell = new int[stonesM.length][backpackV];

        for(int i = 0; i < stonesM.length; i++) {

            for(int j = 0; j < backpackV; j++) {
                if(stonesV[i] > (j + 1)) {
                    cell[i][j] = get(cell, i - 1, j);
                }
                else {
                    cell[i][j] = Math.max(get(cell, i - 1, j)
                            , get(cell, i - 1, j - stonesV[i]) + stonesM[i]);
                }
            }

        }

        return cell;
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
