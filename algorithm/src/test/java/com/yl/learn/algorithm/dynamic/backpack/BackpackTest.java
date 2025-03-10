package com.yl.learn.algorithm.dynamic.backpack;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class BackpackTest extends TestSuper {

    int[] tonesM = {2, 5, 7, 6, 2, 6};
    String tonesMS = "Stones value: " + Arrays.toString(tonesM) + "\n";

    int[] tonesV = {2, 3, 4, 3, 1, 4};
    String tonesVS = "Stones volume: " + Arrays.toString(tonesV) + "\n";

    int backpackV = 10;
    String backpackVS = "Backpack volume: " + backpackV + "\n";

    int[] tonesM1 = {6,3,5,4,6};
    String tonesMS1 = "Stones value: " + Arrays.toString(tonesM1) + "\n";

    int[] tonesV1 = {2,2,6,5,4};
    String tonesVS1 = "Stones volume: " + Arrays.toString(tonesV1) + "\n";

    int backpackV1 = 10;
    String backpackVS1 = "Backpack volume: " + backpackV1 + "\n";


    @Test
    public void test() {
        PrintUtil.template(tonesMS + tonesVS + backpackVS + "Dynamic plain, select stones :", () -> {
            Arrays.asList(Backpack.backpack(tonesV, tonesM, backpackV)).stream()
                    .forEach((row) -> {
                        PrintUtil.println(Arrays.toString(row));
                    });
        });
    }

    @Test
    public void test1() {
        PrintUtil.template(tonesMS1 + tonesVS1 + backpackVS1 + "Dynamic plain, select stones :", () -> {
            Arrays.asList(Backpack.backpack(tonesV1, tonesM1, backpackV1)).stream()
                    .forEach((row) -> {
                        PrintUtil.println(Arrays.toString(row));
                    });
        });
    }

}
