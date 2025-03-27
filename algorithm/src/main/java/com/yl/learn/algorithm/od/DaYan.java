package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * quacqkuquacqkacuqkackuack
 */
public class DaYan {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] talk = in.nextLine().toCharArray();
        System.out.println(nums(talk));
    }

    private static int nums(char[] talks) {
        String quack = "quack";
        List<Character> memory = new ArrayList<>(); // 用于记录鸟叫声的位置
        HashSet<Integer> hashSet = new HashSet<>();
        // 开始遍历
        for (char talk : talks) {
            int index = quack.indexOf(talk);
            if(index == -1) { // 检查字符是否为叫声的组成部分，如果不是的话，则不符合规范，可以跳过检查
                continue;
            }
            // 如果还没保存鸟叫位置，那么集合为空，且当前字符为Q开始字符，那么可以直接添加
            if(memory.isEmpty() && talk == quack.charAt(0)) {
                memory.add(talk);
            }
            else if (!memory.isEmpty()) { // 现在处理不为空的情况
                // 如果当前为Q，表示新的叫声，咱们看看鸟叫状态中，是否有已经叫完的鸟，如果有，可以让这个鸟叫
                // 如果当前不为Q，表示需要寻找一个状态，是当前叫声的前一个字符，比如K，需要寻找处于C的鸟
                boolean isContains = false; // 标识一下是否在鸟叫状态中找到对应的状态
                char aim = talk == quack.charAt(0) ? ' ' : quack.charAt(index - 1);
                for (int i = 0; i < memory.size(); i++) {
                    char currentL = memory.get(i);
                    if(currentL == aim) { // 找到了目标鸟
                        if(talk == quack.charAt(quack.length() - 1)) { // 如果当前状态为K，表示完整的叫声
                            memory.set(i, ' ');
                            hashSet.add(i); // 完整叫声说明是一只鸟，但是这只鸟可能后面还会叫，所以用 hashset 存储
                        }
                        else {
                            memory.set(i, talk);
                        }
                        isContains = true;
                        break;
                    }
                }
                // 如果状态中没找到，那么要么不合法，要么就是Q开头的新叫声，且不能是已经出现的鸟叫的，所以是新鸟
                if(!isContains && talk == quack.charAt(0)) {
                    memory.add(quack.charAt(0));
                }
            }
            // 鸟叫状态为空且当前字符不为Q，表示不合法
        }

        return hashSet.size();
    }

    static char[] completeTalk = new char[]{'q', 'u', 'a', 'c', 'k'};

    // quacqkuquacqkacuqkackuack
    //

    // Scanner in = new Scanner(System.in);
    //        char[] talk = in.nextLine().toCharArray();
    //        int[] index = new int[talk.length];
    //        int max = 0;
    //        for (char cur : completeTalk) {
    //            int num = 1;
    //            for (int j = 0; j < talk.length; j++) {
    //                if (cur == talk[j]) {
    //                    index[j] = num;
    //                    max = Math.max(num, max);
    //                    num++;
    //                }
    //            }
    //        }
    //        Map<Integer, Integer> indexCNT = new HashMap<>();
    //        for (int i = 0; i <= index.length; i++) {
    //            int cnt = indexCNT.getOrDefault(index[i], 0) + 1;
    //            indexCNT.put(index[i], cnt);
    //        }
    //
    //        for (int i = 1; i <= max; i++) {
    //            if(indexCNT.get(i) != 5) continue;
    //            int cnt = 0;
    //            for (int j = 0; j < index.length; j++) {
    //                if(index[j] == i) cnt++;
    //            }
    //        }
}
