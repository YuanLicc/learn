package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 程序员小明打了一辆出租车去上班。出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。
 * 出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。
 *
 * 比如：
 * 23再多一块钱就变为25；
 * 39再多一块钱变为50；
 * 399再多一块钱变为500；
 * 小明识破了司机的伎俩，准备利用自己的学识打败司机的阴谋。
 * 给出计费表的表面读数，返回实际产生的费用。
 * 输入描述
 * 只有一行，数字N，表示里程表的读数。
 *
 * (1<=N<=888888888)。
 *
 * 输出描述
 * 一个数字，表示实际产生的费用。以回车结束。
 *
 * 示例1
 * 输入
 * 5
 * 输出
 * 4
 * 说明
 * 5表示计费表的表面读数。4表示实际产生的费用其实只有4块钱。
 *
 * 示例2
 * 输入
 * 17
 * 输出
 *
 * 15
 * 说明
 *
 * 17表示计费表的表面读数。15表示实际产生的费用其实只有15块钱。
 *
 * 示例3
 * 输入
 *
 * 100 遇到1个十位 10；遇到 9个 个位4 19
 * 输出
 *
 * 81
 */
public class Taxi {

    // 跳表转化为9进制计算
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        int rs = 0;
        for (int i = 0; i < num.length(); i++) {
            int charNum = Character.getNumericValue(num.charAt(i));
            if(charNum > 4) charNum--;
            rs = rs * 9 + charNum;
        }
        System.out.println(rs);
    }

}
