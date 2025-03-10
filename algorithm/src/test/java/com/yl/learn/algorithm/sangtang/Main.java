package com.yl.learn.algorithm.sangtang;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int fang = in.nextInt();

        int[][] cell = new int[fang][2];

        for(int i = 0; i < fang; i++) {
            cell[i][0] = in.nextInt();
            cell[i][1] = in.nextInt();
        }

        System.out.print(max(cell));

    }

    private static int max(int[][] cell) {

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < cell.length; i++) {
            int c = maxMax(cell, i);

            if(c > max) {
                max = c;
            }
        }

        return max;
    }

    private static int maxMax(int[][] cell, int index) {
        int count = 0;
        int currIndex = index;
        int max = cell[index][0];
        int curr = max;
        count++;

        while (count > cell.length) {
            curr = curr + cell[cell[currIndex][1]][0];

            if(curr > max) {
                max = curr;
            }

            currIndex = cell[currIndex][1];
            count++;
        }

        return max;
    }

}
