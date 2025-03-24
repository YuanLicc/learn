package com.yl.learn.algorithm.od;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个单链表 L，请编写程序输出 L 中间结点保存的数据。
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。
 *
 * 例如：
 * 给定 L 为 1→7→5，则输出应该为 7；
 * 给定 L 为 1→2→3→4，则输出应该为 3。
 *
 * 输入描述
 * 每个输入包含 1 个测试用例。每个测试用例:
 * 第 1 行给出链表首结点的地址、结点总个数正整数 N (≤105)。
 * 结点的地址是 5 位非负整数，NULL 地址用 −1 表示。
 *
 * 接下来有 N 行，每行格式为：
 * Address Data Next
 * 其中 Address 是结点地址，Data 是该结点保存的整数数据(0 ≤ Data ≤ 108)，Next 是下一结点的地址。
 *
 * 输出描述
 * 对每个测试用例，在一行中输出 L 中间结点保存的数据。
 *
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。
 *
 * ( 如果奇数个节点取中间，偶数个取偏右边的那个值)
 *
 * 示例1
 *
 * 输入
 * 00010 4
 * 00000 3 -1
 * 00010 5 12309
 * 11451 6 00000
 * 12309 7 11451
 *
 * 输出
 * 6
 */
public class ListMiddle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] firstWords = in.nextLine().split( " ");
        int nodeNum = Integer.parseInt(firstWords[1]);
        HashMap<String, String> nodes = new HashMap<>();
        HashMap<String, Integer> nodeValues = new HashMap<>();
        for (int i = 0; i < nodeNum; i++) {
            String[] words = in.nextLine().split(" ");
            nodes.put(words[0], words[2]);
            nodeValues.put(words[0], Integer.parseInt(words[1]));
        }
        System.out.println(dfs(nodes, nodeValues, firstWords[0], 0, nodeNum / 2));
    }

    private static Integer dfs(HashMap<String, String> nodes, HashMap<String, Integer> nodeValues, String word, int index, int aimIndex) {
        if(index == aimIndex) return nodeValues.get(word);
        return dfs(nodes, nodeValues, nodes.get(word), index + 1, aimIndex);
    }
}
