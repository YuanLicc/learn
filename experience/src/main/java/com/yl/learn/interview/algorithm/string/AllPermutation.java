package com.yl.learn.interview.algorithm.string;

import com.yl.learn.common.util.CharUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串全排
 * @author YuanLi
 */
public class AllPermutation {

    public static List allPermutations(String source) {

        if(source == null || source.length() == 0) return null;

        return allPermutations(source.toCharArray(), 0, source.length() - 1, new ArrayList());
    }

    private static List allPermutations(char[] source, int start, int end, List<String> permutations) {
        if(start == end) {
            permutations.add(String.valueOf(source));
            return permutations;
        }

        for(int i = start; i <= end; i++) {
            source = CharUtil.swap(source, i, start);

            allPermutations(source, start + 1, source.length - 1, permutations);

            source = CharUtil.swap(source, i, start);
        }

        return permutations;
    }

}
