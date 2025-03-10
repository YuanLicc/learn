package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class YHTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<Integer> top = new ArrayList<>();
        top.add(1);
        List<List<Integer>> rs = new ArrayList<>();
        rs.add(top);
        int levelNum = 2;
        while(levelNum <= numRows) {
            List<Integer> upLevel = rs.get(rs.size() - 1);
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < levelNum; i++) {
                if(i == 0 || i == levelNum - 1) {
                    level.add(1);
                }
                else {
                    level.add(upLevel.get(i - 1) + upLevel.get(i));
                }
            }
            rs.add(level);
            levelNum++;
        }
        return rs;
    }

}
