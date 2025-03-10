package com.yl.learn.algorithm.zuo;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 矩阵最小路径和，如下矩阵：
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * 从左上角开始，只能往右边走或者往下走，直到右下角，在这条路径中，每个点对应数字的和。
 */
public class MatrixMinSumOfPathTest extends TestSuper {

    int[][] cell = {
        {1,3,5,9},
        {8,1,3,4},
        {5,0,6,1},
        {8,8,4,0}
    };

    @Test
    public void test() {
        PrintUtil.template("Min sum of path for Matrix: ", () -> {

            Arrays.asList(cell).stream().forEach((arr) -> {PrintUtil.println(Arrays.toString(arr));});

            PrintUtil.println(MatrixMinSumOfPath.minSumOfPath(cell));
        });
    }

}
