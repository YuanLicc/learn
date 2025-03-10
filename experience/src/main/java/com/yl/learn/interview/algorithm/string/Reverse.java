package com.yl.learn.interview.algorithm.string;


import com.yl.learn.common.util.CharUtil;

/**
 * 字符串反转
 * @author YuanLi
 */
public class Reverse {

    public static String reverse(String src) {
        if(src == null) return null;

        int length = src.length();

        if(length == 0) return src;

        char[] chars = src.toCharArray();

        for (int i = ((length - 1) >> 1); i >= 0; i--) {
            CharUtil.swap(chars, i, length - 1 - i);
        }

        return String.valueOf(chars);
    }

}
