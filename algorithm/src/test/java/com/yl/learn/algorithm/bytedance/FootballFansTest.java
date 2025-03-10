package com.yl.learn.algorithm.bytedance;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class FootballFansTest extends TestSuper {


    int m = 10;
    int n = 10;
    byte[][] cell = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,1,0,0,0},
            {0,1,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,1},
            {0,0,0,1,1,1,0,0,0,1},
            {0,0,0,0,0,0,1,0,1,1},
            {0,1,1,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,0,0,0,0},
            {0,0,1,0,0,1,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0}};

    @Test
    public void test() {

        PrintUtil.template("Football Fans, how much the group, how much the max fans of group? ", () -> {
            Arrays.asList(cell).stream().forEach( (row) ->
                    PrintUtil.println(Arrays.toString(row))
            );
            PrintUtil.println(Arrays.toString(FootballFans.football(m, n, cell)), "Result: ", "");
        });
    }
}
