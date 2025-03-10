package com.yl.learn.algorithm.sangtang;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int plaintNum = in.nextInt();

        char[][][] plaints = new char[plaintNum][][];

        for(int i = 0; i < plaintNum; i++) {
            int m = in.nextInt();
            int n = in.nextInt();
            char[][] plaint = new char[m][n];
            for(int k = 0; k < m; k++) {
                for(int kk = 0; kk < n; kk++) {
                    plaint[k][kk] = (char)in.nextInt();
                }
            }
            plaints[i] = plaint;
        }

        check(plaints);
    }

    private static void check(char[][][] plaints) {

        for(int i = 0; i < plaints.length; i++) {
            System.out.println(check(plaints[i]));
        }

    }

    private static boolean check(char[][] plaint) {

        return false;
    }

}
