package com.yl.learn.dp.strategy;

import com.yl.learn.dp.strategy.imp.ArraySortStrategy;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class StrategyTest extends TestCase {
    public void testStrategy() {
        Integer[] arr = new Integer[] {1, 2, 3, 4};
        ArraySortAlgorithm<Integer> algorithm1 = ArraySortStrategy.SELECT_SORT.algorithm(arr);
        ArraySortAlgorithm<Integer> algorithm2 = ArraySortStrategy.SELECT_SORT.algorithm(arr);
        System.out.println(algorithm1 == algorithm2);
    }
}
