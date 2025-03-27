package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 一个局域网内有很多台电脑，分别标注为 0 ~ N-1 的数字。相连接的电脑距离不一样，所以感染时间不一样，感染时间用 t 表示。
 * 其中网络内一台电脑被病毒感染，求其感染网络内所有的电脑最少需要多长时间。如果最后有电脑不会感染，则返回-1。
 * 给定一个数组 times 表示一台电脑把相邻电脑感染所用的时间。
 * 如图：path[i] = {i, j, t} 表示：电脑 i->j，电脑 i 上的病毒感染 j，需要时间 t。
 * 输入描述
 * 第一行输入一个整数N ，表示局域网内电脑个数 N ，1 ≤ N ≤ 200 ;
 * 第二行输入一个整数M ,表示有 M 条网络连接；
 * 接下来M行 ,每行输入为 i , j , t 。表示电脑 i 感染电脑j 需要时间 t 。（1 ≤ i , j ≤ N）
 * 最后一行为病毒所在的电脑编号。
 *
 * 输出描述
 * 输出最少需要多少时间才能感染全部电脑，如果不存在输出 -1
 *
 * 用例
 * 输入
 * 4
 * 3
 * 2 1 1
 * 2 3 1
 * 3 4 1
 * 2
 * 输出	2
 * 说明	第一个参数：局域网内电脑个数N，1 ≤ N ≤ 200；
 * 第二个参数：总共多少条网络连接
 * 第三个 2 1 1 表示2->1时间为1
 * 第六行：表示病毒最开始所在电脑号2
 */
public class NetLink {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int comNum = in.nextInt(); // 电脑台数
        int links = in.nextInt();  // 表示链接关系个数
        in.nextLine();
        Map<Integer, List<int[]>> linkInfos = new HashMap<>();// 表示链接关系 电脑1 电脑2 感染时间
        for (int i = 0; i < links; i++) {
            int linkKey = in.nextInt();
            List<int[]> list = linkInfos.getOrDefault(linkKey, new ArrayList<>());
            list.add(new int[]{in.nextInt(), in.nextInt()});
            linkInfos.put(linkKey, list);
        }
        int startCom = in.nextInt(); // 表示病毒开始的电脑ID
        int[] comTimes = new int[comNum];
        Arrays.fill(comTimes, Integer.MAX_VALUE);
        comTimes[startCom - 1] = 0;
        bfs(linkInfos, comTimes, startCom, 0);
        int max = Arrays.stream(comTimes).max().getAsInt();
        System.out.println(max == Integer.MAX_VALUE ? -1 : max);
    }

    private static void bfs(Map<Integer, List<int[]>> linkInfos, int[] comTimes, int startCom, int startTime) {
        List<int[]> level = linkInfos.get(startCom);
        if(level == null) return;
        for (int[] ints : level) {
            comTimes[ints[0] - 1] = Math.min(startTime + ints[1], comTimes[ints[0] - 1]);
            bfs(linkInfos, comTimes, ints[0], comTimes[ints[0] - 1]);
        }
    }
}
