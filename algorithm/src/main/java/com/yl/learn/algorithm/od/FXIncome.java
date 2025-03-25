package com.yl.learn.algorithm.od;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目描述
 * 一个XX产品行销总公司，只有一个boss，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销。
 * 规定，每个月，下级分销需要将自己的总收入（自己的+下级上交的）每满100元上交15元给自己的上级。
 * 现给出一组分销的关系，和每个分销的收入，请找出boss并计算出这个boss的收入。
 * 比如：
 *
 * 收入100元，上交15元；
 * 收入199元（99元不够100），上交15元；
 * 收入200元，上交30元。
 * 输入：
 *
 * 分销关系和收入：[[分销id 上级分销id 收入], [分销id 上级分销id 收入], [分销id 上级分销id 收入]]
 *
 * 分销ID范围： 0…65535
 * 收入范围：0…65535，单位元
 * 提示： 输入的数据只存在1个boss，不存在环路
 * 输出：
 * [boss的ID, 总收入]
 * 输入描述
 * 第一行输入关系的总数量 N
 * 第二行开始，输入关系信息，格式：
 * 分销ID 上级分销ID 收入
 * 比如：
 *
 * 5
 * 1 0 100
 * 2 0 199
 * 3 0 200
 * 4 0 200
 * 5 0 200
 * 输出描述
 * 输出：
 * boss的ID 总收入
 * 比如：
 * 0 120
 */
public class FXIncome {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        HashSet<Integer> ids = new HashSet<>();
        HashSet<Integer> upIds = new HashSet<>();
        int amount = 0;
        for (int i = 0; i < num; i++) {
            ids.add(in.nextInt());
            upIds.add(in.nextInt());
            amount += in.nextInt() / 100 * 15;
        }

        for (Integer upId : upIds) {
            if(!ids.contains(upId)) {
                System.out.println(upId + " " + amount);
            }
        }
    }

}
