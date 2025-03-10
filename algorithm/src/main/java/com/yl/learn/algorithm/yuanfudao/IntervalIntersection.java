package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * 输入：
 * A = [[0,2],[5,10],[13,23],[24,25]]
 * B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * 来源：力扣（LeetCode） 986
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntervalIntersection extends TestCase {

    public int[][] intervalIntersection(int[][] A, int[][] B) {

        ArrayList<int[]> rs = new ArrayList<>();

        int index= 0;
        for(int i = 0; i < B.length; i++) {
            int mergeIndex = 0;
            for(int j = index; j < A.length; j++) {
                int[] merge = checkAndMerge(B[i], A[j]);
                if(merge != null) {
                    rs.add(merge);
                    mergeIndex++;
                }
                else if(mergeIndex > 0) {
                    index = j - 1;
                    break;
                }
            }

        }


        return rs.toArray(new int[rs.size()][]);
    }

    // 1, 3; 2, 4
    // 1, 5; 2, 3
    private int[] checkAndMerge(int[] a, int[] b) {
        int min = Math.max(a[0], b[0]);
        int max = Math.min(a[1], b[1]);

        if(min <= max) {
            return new int[]{min, max};
        }
        return null;
    }

    public void test() {
        intervalIntersection(new int[][]{{14, 16}}, new int[][]{{7, 13}, {16, 20}});
    }
}
