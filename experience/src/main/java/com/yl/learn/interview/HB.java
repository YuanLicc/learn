package com.yl.learn.interview;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double price = in.nextDouble();
        int count = in.nextInt();
        Arrays.stream(find(price, count)).forEach(System.out::println);
    }

    private static double[] find(double price, int count) {
        DecimalFormat format = new DecimalFormat("0.00");
        double[] rs = new double[count];
        double remain = price;
        for (int i = 1; i <= count; i++) {
            rs[i - 1] = Double.parseDouble(format.format(find(remain, count, i)));
            remain -= rs[i - 1];
        }
        return rs;
    }

    private static double find(double remain, int count, int i) {
        if(count == i) return remain;
        return rand(remain);
    }

    private static double rand(double remain) {
        Random random = new Random();
        double rs = random.nextDouble() * remain;
        if(rs == 0) return rand(remain);
        return rs;
    }

}
