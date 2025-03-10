package com.yl.learn.algorithm.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了提高文章质量，每一篇文章（假设全部都是英文）都会有m民编辑进行审核，每个编辑独立工作，
 * 会把觉得有问题的句子通过下表记录下来，比如[1,10]，1表示病句的第一个字符，10表示病句的最后一个字符。
 * 也就是从1到10着10个字符组成的句子，是有问题的。
 *
 * 现在需要把多名编辑有问题的句子合并起来，送个总编辑进行最终的审核。比如编辑A指出的病句是[1,10]，[32,45];
 * 编辑B指出的病句是[5,16]，[78,94]那么[1,10]和[5,16]是有交叉的，可以合并成[1,16][32,45][78,94]
 *
 * 输入描述：
 * 编辑数量m，之后每行是每个编辑的标记的下表组合，第一个和最后一个下标用英文逗号分隔，每组下标之间用分号分隔
 *
 * 输出描述：
 * 合并后的下标集合，第一个和最后一个下标用英文逗号分隔，每组下标之间用分号分隔。返回结果是从小到大递增排列
 *
 * 例：输入
 * 3
 * 1,10;32,45
 * 78,94;5,16
 * 80,100;200,220;16,32
 *
 * 输出： 1,45;78,100;200,220
 */
public class SickSentence {

    public static class Wrong {
        public int left;
        public int right;

        public Wrong(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return left + " -> " + right;
        }
    }

    public static List<Wrong> sickSentence(List<Wrong> input) {
        sort(input);
        List<Wrong> wrongs = new ArrayList<>();
        int count = 0;
        while (input.size() != 0) {
            if(input.size() > 1) {
                Wrong first = input.get(0);
                Wrong second = input.get(1);
                if(first.right >= second.left) {
                    if(first.right > second.right) {
                        input.set(0, new Wrong(first.left, first.right));
                    }
                    else {
                        input.set(0, new Wrong(first.left, second.right));
                    }
                    input.remove(1);
                }
                else {
                    wrongs.add(first);
                    input.remove(0);
                }
            }
           else {
                wrongs.add(input.get(0));
                input.remove(0);
            }
        }
        return wrongs;
    }

    public static void sort(List<Wrong> input) {
        for(int i = 0; i < input.size() - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < input.size(); j++) {
                if(input.get(i).left > input.get(j).left) {
                    minIndex = j;
                }
            }
            swap(input, i, minIndex);
        }
    }

    private static void swap(List<Wrong> input, int i, int j) {
        Wrong temp = input.get(i);
        input.set(i, input.get(j));
        input.set(j, temp);
    }

}
