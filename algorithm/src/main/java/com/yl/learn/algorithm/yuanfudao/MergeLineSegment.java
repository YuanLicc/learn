package com.yl.learn.algorithm.yuanfudao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 来源：力扣（LeetCode）56
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeLineSegment {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(item -> item[0]));
        // [1,3],[2,6],[8,10],[15,18]

        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 1; i < intervals.length; i++) {
            if(checkAndMerge(intervals, i - 1, i)) {
                if(i == intervals.length - 1) {
                    list.add(intervals[i]);
                }
            }
            else { // 1, 2  3, 5,
                list.add(intervals[i - 1]);
                if(i == intervals.length - 1) list.add(intervals[i]);
            }
        }

        return list.toArray(new int[list.size()][2]);
    }

    private boolean checkAndMerge(int[][] intervals, int start, int end) {
        int min = Math.max(intervals[start][0], intervals[end][0]);
        int max = Math.min(intervals[start][1], intervals[end][1]);

        if(min <= max) {
            intervals[end][0] = Math.min(intervals[start][0], intervals[end][0]);;
            intervals[end][1] = Math.max(intervals[start][1], intervals[end][1]);;

            return true;
        }
        return false;
    }
}
