package com.yl.learn.algorithm.iqiyi;

import java.util.Scanner;

public class MainMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int types = in.nextInt();
        int days = in.nextInt();
        int p = in.nextInt();

        int[] bases = new int[types];

        for(int i = 0; i < types; i++) {
            bases[i] = in.nextInt();
        }

        char chars[] = new char[days];
        int[] cell = new int[days];
        for(int i = 0; i < days; i++) {
            chars[i] = in.next().charAt(0);
            cell[i] = in.nextInt();
        }


        System.out.println(max(bases, chars, cell, p));
    }

    private static int max(int[] bases, char[] chars, int[] cell, int p) {

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == 'A') {
                bases[cell[i] - 1] += 1;
            }
            else if(chars[i] == 'B') {
                bases[cell[i] - 1] -= 1;
            }
        }

        int index = p - 1;
        int count = 0;
        for(int i = 0; i < bases.length; i++) {
            if(i != index) {
                if(bases[index] < bases[i]) {
                    count++;
                }
            }
        }

        return count + 1;
    }

}
