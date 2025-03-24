package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 有一个简易内存池，内存按照大小粒度分类，每个粒度有若干个可用内存资源，用户会进行一系列内存申请，需要按需分配内存池中的资源返回申请结果成功失败列表。
 *
 * 分配规则如下：
 *
 * 分配的内存要大于等于内存的申请量，存在满足需求的内存就必须分配，优先分配粒度小的，但内存不能拆分使用；
 * 需要按申请顺序分配，先申请的先分配，有可用内存分配则申请结果为true；
 * 没有可用则返回false。
 * 注意：不考虑内存释放
 *
 * 输入描述
 * 输入为两行字符串
 *
 * 第一行为内存池资源列表，包含内存粒度数据信息，粒度数据间用逗号分割
 *
 * 一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量
 * 资源列表不大于1024
 * 每个粒度的数量不大于4096
 * 第二行为申请列表，申请的内存大小间用逗号分割
 *
 * 申请列表不大于100000
 * 如:
 * 64:2,128:1,32:4,1:128
 * 50,36,64,128,127
 *
 * 输出描述
 * 输出为内存池分配结果
 * 如true,true,true,false,false
 *
 * 示例1
 * 输入
 * 64:2,128:1,32:4,1:128
 * 50,36,64,128,127
 * 输出
 *
 * true,true,true,false,false
 */
public class MemoryPool {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] memoryStr = in.nextLine().split(",");
        int[] memories = new int[memoryStr.length];
        Map<Integer, Integer> memoryMap = new HashMap<>();

        for (int i = 0; i < memoryStr.length; i++) {
            String[] kv = memoryStr[i].split(":");
            int memory = Integer.parseInt(kv[0]);
            int num = Integer.parseInt(kv[1]);
            memories[i] = memory;
            memoryMap.put(memory, num);
        }
        Arrays.sort(memories);
        StringBuilder sb = new StringBuilder();
        String[] apply = in.nextLine().split(",");
        for (int i = 0; i < apply.length; i++) {
            sb.append(find(memories, memoryMap, Integer.parseInt(apply[i]))).append(i == apply.length - 1 ? "" : ",");
        }
        System.out.println(sb.toString());
    }

    private static boolean find(int[] memories, Map<Integer, Integer> memoryMap, int aim) {
        for (int i = 0; i < memories.length; i++) {
            Integer num = memoryMap.get(memories[i]);
            if(num == null) continue;
            if(memories[i] >= aim) {
                if(num == 1) memoryMap.remove(memories[i]);
                else {
                    memoryMap.put(memories[i], num - 1);
                }
                return true;
            }
        }
        return false;
    }
}
