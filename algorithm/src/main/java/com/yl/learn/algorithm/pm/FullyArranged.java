package com.yl.learn.algorithm.pm;

import com.yl.learn.util.util.PrintUtil;

import java.util.Arrays;

public class FullyArranged {

    public static void fullyArranged(char[] chars, int start) {

        if(chars.length - 1 == start) {
            PrintUtil.println(Arrays.toString(chars));
        }


        for(int i = start; i < chars.length; i++) {
            char tt = chars[i];
            chars[i] = chars[start];
            chars[start] = tt;

            fullyArranged(chars, start + 1);

            tt = chars[i];
            chars[i] = chars[start];
            chars[start] = tt;
        }

    }

}
