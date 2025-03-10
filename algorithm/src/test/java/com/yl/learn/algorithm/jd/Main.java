package com.yl.learn.algorithm.jd;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int T = input.nextInt();
        input.nextLine();

        while (T > 0) {
            T--;
            int N = input.nextInt();
            int M = input.nextInt();
            input.nextLine();

            int[][] m = new int[M][2];
            for (int i = 0; i < M; i++) {
                m[i][0] = input.nextInt();
                m[i][1] = input.nextInt();
                input.nextLine();
            }

            System.out.println(check(m, M, N));
        }

    }

    // 深度优先遍历，遍历的点都不能出现在一个集合中
    public static boolean check(int[][] cell, int m, int n) {


        for(int i = 0; i < m; i++) {

        }

        return false;
    }

}
