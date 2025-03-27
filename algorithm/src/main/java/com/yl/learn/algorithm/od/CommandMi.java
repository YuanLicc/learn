package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给定一个由多个命令字组成的命令字符串：
 *
 * 1、字符串长度小于等于127字节，只包含大小写字母，数字，下划线和偶数个双引号；
 * 2、命令字之间以一个或多个下划线_进行分割；
 * 3、可以通过两个双引号””来标识包含下划线_的命令字或空命令字（仅包含两个双引号的命令字），双引号不会在命令字内部出现；
 *
 * 请对指定索引的敏感字段进行加密，替换为******（6个*），并删除命令字前后多余的下划线_。
 * 如果无法找到指定索引的命令字，输出字符串ERROR。
 *
 * 输入描述
 * 输入为两行，第一行为命令字索引K（从0开始），第二行为命令字符串S。
 * 输出描述
 * 输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
 *
 * 示例1
 * 输入
 *
 * 1
 * password__a12345678_timeout_100
 * 1
 * 输出
 *
 * password_******_timeout_100
 * 说明
 *
 * 示例2
 * 输入
 *
 * 2
 * aaa_password_"a12_45678"_timeout__100_""_
 * 2
 * 输出
 *
 * aaa_password_******_timeout_100_""
 */
public class CommandMi {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int index = in.nextInt();
        in.nextLine();
        String command = in.nextLine();
        command = "_" + command + "_";

        int pointer = 0;
        StringBuilder sb = new StringBuilder();
        while (pointer < command.length()) {
            char current = command.charAt(pointer);
            if(current == '"') {
                pointer++;
                if(pointer < command.length() && command.charAt(pointer) == '"') {
                    sb.append("\"\"");
                    pointer++;
                }
                else {
                    while (pointer < command.length() && command.charAt(pointer) != '"') {
                        sb.append(command.charAt(pointer));
                        pointer++;
                    }
                    pointer++;
                }
            }
            else if(current == '_') {
                while (pointer < command.length() && command.charAt(pointer) == '_') pointer++;
                sb.append('_');
            }
            else {
                sb.append(command.charAt(pointer));
                pointer++;
            }
        }
        System.out.println(sb);
    }

}
