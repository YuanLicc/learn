package com.yl.learn.algorithm;

/**
 * 最大公约数
 */
public class MaxCommonDivisor {


    /**
     * 欧几里德算法 gcd(a, b) = gcd(a, a % b)
     * @param num1
     * @param num2
     * @return
     */
    public static int greatest(int num1, int num2) {

        if(num1 == 0) {
            return num2;
        }

        return greatest(num2 % num1, num1);
    }

    public static void main(String[] args) {

        System.out.println(greatest(4, 9));
    }

}
