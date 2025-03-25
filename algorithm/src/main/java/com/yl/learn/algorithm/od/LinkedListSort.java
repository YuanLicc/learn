package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 有一个特异性的双端队列，该队列可以从头部或尾部添加数据，但是只能从头部移出数据。
 *
 * 小A依次执行2n个指令往队列中添加数据和移出数据。其中n个指令是添加数据（可能从头部添加、也可能从尾部添加），依次添加1到n；n个指令是移出数据。
 * 现在要求移除数据的顺序为1到n。
 * 为了满足最后输出的要求，小A可以在任何时候调整队列中数据的顺序。
 * 请问 小A 最少需要调整几次才能够满足移除数据的顺序正好是1到n。
 *
 * 输入描述
 * 第一行一个数据n，表示数据的范围。
 * 接下来的2n行，其中有n行为添加数据，指令为：
 * head add x表示从头部添加数据 x，
 * tail add x 表示从尾部添加数据x，
 * 另外 n 行为移出数据指令，指令为：remove 的形式，表示移出1个数据；
 * 1 ≤ n ≤ 3 * 10^5。
 * 所有的数据均合法。
 *
 * 输出描述
 * 一个整数，表示 小A 要调整的最小次数。
 *
 * 示例1
 * 输入
 *
 * 5
 * head add 1
 * tail add 2
 * remove
 * head add 3
 * tail add 4
 * head add 5
 * remove
 * remove
 * remove
 * remove
 * 输出
 *
 * 1
 */
public class LinkedListSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        int index = 0;
        boolean isSorted = true;
        int size = 0, count = 0;
        while (index < num * 2) {
            String command = in.nextLine();
            if(command.startsWith("head")) {
                // 如果不为空的情况，使用头插法，破坏了顺序性
                isSorted = size == 0;
                size++;
            }
            else if(command.startsWith("tail")) {
                if(size == 0) {
                    isSorted = true;
                }
                // 如果不为空，有序的情况下尾插法，还是有序；非有序的情况下，还是非有序
                size++;
            }
            else { // 从头部移除
                if(!isSorted) { // 如果不是有序的，那么排序
                    count++;
                    isSorted = true;
                }
                size--;
            }
            index++;
        }
        System.out.println(count);
    }

}
