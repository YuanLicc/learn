package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。
 *
 * 如果上报太频繁，会对服务端造成压力;
 * 如果上报太晚，会降低用户的体验；
 * 如果一次上报的条数太多，会导致超时失败。
 * 为此，项目组设计了如下的上报策略：
 *
 * 每成功上报一条日志，奖励1分
 * 每条日志每延迟上报1秒，扣1分
 * 积累日志达到100条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
 *
 * 输入描述
 * 按时序产生的日志条数 T1,T2…Tn，其中 1<=n<=1000，0<=Ti<=100
 *
 * 输出描述
 * 首次上报最多能获得的积分数
 *
 * 示例1
 * 输入
 *
 * 1 98 1
 * 1
 * 输出
 *
 * 98
 * 1
 * 说明
 *
 * T1 时刻上报得 1 分
 * T2 时刻上报得98分，最大
 * T3 时刻上报得 0 分
 *
 * 示例2
 * 输入
 *
 * 50 60 1
 * 1
 * 输出
 *
 * 50
 * 1
 * 说明
 *
 * 如果第1个时刻上报，获得积分50。如果第2个时刻上报，最多上报100条，前50条延迟上报1s，每条扣除1分，共获得积分为 100-50=50
 *
 * 示例3
 * 输入
 *
 * 3 7 40 10 60
 * 输出
 *
 * 37
 * 说明
 *
 * T1时刻上报得3分
 * T2时刻上报得7分
 * T3时刻上报得37分，最大
 * T4时刻上报得-3分
 * T5时刻上报，因为已经超了100条限制，所以只能上报100条，得-23分
 */
public class SBLog {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] logs = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] sumOver = new int[logs.length];
        int[] minus = new int[logs.length];
        int sum = 0;
        for (int i = 0; i < logs.length; i++) {
            sum += logs[i];
            sumOver[i] = Math.min(sum, 100);
            for (int j = i + 1; j < logs.length; j++) {
                minus[j] += logs[i] * (j - i);
            }
        }

        int max = 0;
        for (int i = 0; i < logs.length; i++) {
            max = Math.max(sumOver[i] - minus[i], max);
        }
        System.out.println(max);
    }

}
