package com.yl.learn.algorithm.od;


import java.util.*;

public class JumpHorse {

    // 一个马
    // 如果想要移动到上下左右位置，都需要三步
    // 如果想要移动到对角线位置，需要移动到相邻位置再移动两步也就是四步
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        int[][] qiPan = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] lines = in.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                if(lines[j].equals(".")) {
                    qiPan[i][j] = 0;
                }
                else {
                    qiPan[i][j] = Integer.parseInt(lines[j]);
                }
            }
        }

        List<Map<String, Integer>> steps = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(qiPan[i][j] > 0) {
                    Map<String, Integer> thr = new HashMap<>();
                    dfs(qiPan, i, j, thr, qiPan[i][j], 0);
                    steps.add(thr);
                }
            }
        }
        int rs = Integer.MAX_VALUE;
        Map<String, Integer> one = steps.get(0);
        for (String key : one.keySet()) {
            int sum = one.get(key);
            boolean is = true;
            for (int j = 1; j < steps.size(); j++) {
                Integer rr = steps.get(j).get(key);
                if(rr == null) {
                    is = false;
                    break;
                }
                else sum += rr;
            }
            if(is) {
                rs = Math.min(rs, sum);
            }
        }
        System.out.println(rs);
    }

    private static void dfs(int[][] qiPan, int i, int j, Map<String, Integer> thr, int stepAll, int step) {
        if(step > stepAll) return;
        if(i < 0 || i >= qiPan.length || j < 0 || j >= qiPan[0].length) return;
        String key = i + "," + j;
        Integer value = thr.get(key);
        if(value == null) thr.put(key, step);
        else thr.put(key, value < step ? value : step);
        dfs(qiPan, i + 1, j + 2, thr, stepAll, step + 1);
        dfs(qiPan, i + 1, j - 2, thr, stepAll, step + 1);
        dfs(qiPan, i - 1, j + 2, thr, stepAll, step + 1);
        dfs(qiPan, i - 1, j - 2, thr, stepAll, step + 1);
        dfs(qiPan, i + 2, j - 1, thr, stepAll, step + 1);
        dfs(qiPan, i + 2, j + 1, thr, stepAll, step + 1);
        dfs(qiPan, i - 2, j - 1, thr, stepAll, step + 1);
        dfs(qiPan, i - 2, j + 1, thr, stepAll, step + 1);
    }
}
