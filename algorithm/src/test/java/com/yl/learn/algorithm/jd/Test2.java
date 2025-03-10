package com.yl.learn.algorithm.jd;

import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int line = in.nextInt();

        long[][] cell = new long[line][3];

        for(int i = 0; i < line; i++) {
            cell[i][0] = in.nextLong();
            cell[i][1] = in.nextLong();
            cell[i][2] = in.nextLong();
        }

        System.out.println(get(cell));
    }

    public static int get(long[][] cell) {

        int count = 0;

        for(int i = 0; i < cell.length; i++) {

            for(int j = 0; j < cell.length; j++) {
                if(i == j) {
                    //
                }
                else {
                    if(cell[i][0] < cell[j][0] && cell[i][1] < cell[j][1] && cell[i][2] < cell[j][2]) {
                        count++;
                        break;
                    }
                }
            }

        }
        return count;
    }

}
