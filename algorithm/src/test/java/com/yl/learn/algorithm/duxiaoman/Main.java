package com.yl.learn.algorithm.duxiaoman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] cell = new int[n];

        for(int i = 0; i < n; i++) {
            cell[i] = in.nextInt();
        }
    }

    /*private static long max(int[] cell) {

        int max[][] = new int[cell.length][2];

        for(int i = cell.length - 1; i >= 1; i--) {
            int cMax = Integer.MIN_VALUE;

            for(int j = i - 1; j >= 0; j++) {
                int xx = cell[i] - cell[j];
                if(xx > cMax) {
                    max[i][0] = xx;
                    max[i][1] = j;
                }
            }
        }

    }

    private static long max(int max[][], int i) {

    }*/

}
