package com.yl.learn.algorithm.od;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 现在有多组整数数组，需要将它们合并成一个新的数组。
 *
 * 合并规则，从每个数组里按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，
 *
 * 如果该行不足固定长度或者已经为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
 *
 * 输入描述
 * 第一行是每次读取的固定长度，0<长度<10
 *
 * 第二行是整数数组的数目，0<数目<1000
 *
 * 第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
 *
 * 输出描述
 * 输出一个新的数组，用逗号分隔。
 *
 * 示例1
 * 输入
 *
 * 3
 * 2
 * 2,5,6,7,9,5,7
 * 1,7,4,3,4
 * 4
 * 输出
 *
 * 2,5,6,1,7,4,7,9,5,3,4,7
 * 说明
 *
 * 1、获得长度3和数组数目2
 * 2、先遍历第一行，获得2,5,6
 * 3、再遍历第二行，获得1,7,4
 * 4、再循环回到第一行，获得7,9,5
 * 5、再遍历第二行，获得3,4
 * 6、再回到第一行，获得7，按顺序拼接成最终结果
 */
public class CombineList {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         int number = in.nextInt();
         int lineNumber = in.nextInt();
         in.nextLine();

        ArrayList<LinkedList<Integer>> queueList = new ArrayList<>();
         for (int i = 0; i < lineNumber; i++) {
             String[] lineNums = in.nextLine().split(",");
             LinkedList<Integer> queue = new LinkedList<>();
             for (int j = 0; j < lineNums.length; j++) {
                 queue.addLast(Integer.parseInt(lineNums[j]));
             }
             queueList.add(queue);
         }
         StringBuilder sb = new StringBuilder();
        HashSet<Integer> empty = new HashSet<>();
         while (true) {
             for (int i = 0; i < queueList.size(); i++) {
                 boolean isC = dfs(queueList, empty, number, 0, i,  sb);
                 if(!isC) {
                     System.out.println(sb.toString());
                     return;
                 }
             }
         }
    }

    private static boolean dfs(ArrayList<LinkedList<Integer>> queueList, HashSet<Integer> empty,
                               int k, int num, int start, StringBuilder sb) {
        if(k <= num) return true;
        if(queueList.size() <= empty.size()) return false;
        LinkedList<Integer> queue = queueList.get(start);
        while (!queue.isEmpty() && k != num) {
            sb.append(queue.removeFirst()).append(",");
            num++;
        }
        if(queue.isEmpty()) empty.add(start);
        return dfs(queueList, empty, k, num, start == queueList.size() - 1 ? 0 : start + 1, sb);
    }
}
