package com.yl.learn.algorithm;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> rs = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];

            int lIndex = i + 1;
            int rIndex = nums.length - 1;

            while(lIndex < rIndex) {
                int sum = num + nums[lIndex] + nums[rIndex];
                if(sum == 0) {
                    rs.add(Arrays.asList(new Integer[]{num, nums[lIndex], nums[rIndex]}));
                    to(nums, lIndex, true);
                    to(nums, rIndex, false);
                }
                else if(sum > 0) {
                    to(nums, rIndex, false);
                }
                else {
                    to(nums, lIndex, true);
                }
            }
        }
        return rs;
    }

    private static int to(int[] nums, int index, boolean add) {
        int num = nums[index];

        if(add) {
            int i = index;
            while(i < nums.length && nums[i] == num) {
                i++;
            }
            return i;
        }
        else {
            int i = index;
            while(i >= 0 && nums[i] == num) {
                i--;
            }
            return i;
        }
    }
}
