package com.yl.learn.algorithm.iqiyi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String inp = in.next();

        if(inp.length() != 6) {
            return;
        }

        int[] cell = new int[6];

        int pre = 0;
        int post = 0;

        for(int i = 0; i< 6; i++) {
            cell[i] = Integer.parseInt(inp.charAt(i) + "");
            if(i < 3) {
                pre += cell[i];
            }
            else {
                post += cell[i];
            }
        }

        if(pre == post) {
            System.out.println(0);
        }
        else if(pre > post) {
            System.out.println(Math.min(num(cell, 3, 5, pre - post), numMinus(cell, 0, 2, pre - post)));
        }
        else {
            System.out.println(Math.min(num(cell, 0, 2, post - pre),numMinus(cell, 3, 5, post - pre)));
        }
    }

    private static int numMinus(int[] cell, int start, int end, int aim) {
        if (aim > 18) {
            return 3;
        }

        for(int i = start; i <= end; i++) {
            if((cell[i] - aim) >= 0) {
                return 1;
            }
        }

        return 2;

    }

    private static int num(int[] cell, int start, int end, int aim) {

        if (aim > 18) {
            return 3;
        }

        for(int i = start; i <= end; i++) {
            if((cell[i] + aim) <= 9) {
                return 1;
            }
        }


        return 2;

    }
}
