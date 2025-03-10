package com.yl.learn.algorithm.sangtang;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int numCount = in.nextInt();
        int k = in.nextInt();

        int[] num = new int[numCount];

        for(int i = 0; i < numCount; i++) {
            num[i] = in.nextInt();
        }

        if(num == null || num.length < 2) {
            return;
        }
        System.out.print(min(num, k));
    }

    private static int min(int[] num, int k) {

        change(num, k);
        int min = num[0];
        int max = num[1];

        for(int i = 1; i < num.length; i++) {
            if(num[i] > max) {
                max = num[i];
            }

            if(num[i] < min) {
                min = num[i];
            }
        }

        return max - min;
    }

    private static void change(int[] num, int k) {

        int average = average(num);

        for(int i = 0; i < num.length; i++) {
            if(num[i] <= average) {
                num[i] = num[i] + k;
            }
            else {
                num[i] = num[i] - k;
            }
        }
    }

    private static int average(int[] num) {
        int plus = 0;
        for(int i = 0; i < num.length; i++) {
            plus += num[i];
        }

        return plus / num.length;
    }

}
