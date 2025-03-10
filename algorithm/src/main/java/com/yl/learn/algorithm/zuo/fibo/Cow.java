package com.yl.learn.algorithm.zuo.fibo;

/**
 * 一开始有 n 头成熟母牛，成熟母牛每年都会生一头小母牛，小母牛 3 年后成熟之后也可以生小母牛，
 * 问：m 年后总共多少牛？（不考虑牛死亡）
 */
public class Cow {

    public static int cowsRecursion(int startCount, int years) {
        if(years == 1 || years == 2 || years == 3) {

            return years * startCount;
        }

        return cowsRecursion(startCount, years - 1) + cowsRecursion(startCount, years - 3);

    }

    public static long cows(int startCount, int years) {

        long prepre = startCount;
        long pre = 2 * startCount;
        long re = 3 * startCount;

        for(int i = 4; i <= years; i++) {
            long temp1 = re;
            long temp2 = pre;

            re = re + prepre;

            prepre = temp2;
            pre = temp1;

        }

        return re;

    }

}
