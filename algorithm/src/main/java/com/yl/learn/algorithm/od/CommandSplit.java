package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 有一个文件，包含以一定规则写作的文本，请统计文件中包含的文本数量。
 *
 * 规则如下：
 *
 * 文本以”;”分隔，最后一条可以没有”;”，但空文本不能算语句，比如”COMMAND A; ;”只能算一条语句。注意，无字符/空白字符/制表符都算作”空”文本；
 *
 * 文本可以跨行，比如下面，是一条文本，而不是三条；
 *
 * COMMAND A
 * AND
 * COMMAND B;
 * 文本支持字符串，字符串为成对的单引号(')或者成对的双引号(“)，字符串可能出现用转义字符()处理的单双引号(“your input is””)和转义字符本身，比如
 * COMMAND A "Say \"hello\"";
 * 支持注释，可以出现在字符串之外的任意位置注释以”–“开头，到换行结束，比如：
 * COMMAND A; --this is comment
 * COMMAND --comment
 * A AND COMMAND B;
 * 注意字符串内的”–“，不是注释。
 *
 * 输入描述
 * 文本文件
 *
 * 输出描述
 * 包含的文本数量
 *
 * 用例
 * 输入
 *
 * COMMAND TABLE IF EXISTS "UNITED STATE";
 * COMMAND A GREAT (
 * ID ADSAB,
 * download_length INTE-GER, -- test
 * file_name TEXT,
 * guid TEXT,
 * mime_type TEXT,
 * notifica-tionid INTEGER,
 * original_file_name TEXT,
 * pause_reason_type INTEGER,
 * resumable_flag INTEGER,
 * start_time INTEGER,
 * state INTEGER,
 * folder TEXT,
 * path TEXT,
 * total_length INTE-GER,
 * url TEXT
 * );
 * 输出
 *
 * 2
 */
public class CommandSplit {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            sb.append(in.nextLine()).append('\n');
        }
        char[] chars = sb.toString().toCharArray();
        // 开始解析
        // 首先命令以分号结束；注释以换行结束；文本以单引号或则双引号开始
        // 注释的优先级最高
        int index = 0;
        StringBuilder rs = new StringBuilder(), tmp = new StringBuilder();
        while (index < chars.length) {
            char currentChar = chars[index], nextChar = index < chars.length - 1 ? chars[index + 1] : '\0';
            if(chars[index] == '-' && nextChar == '-') { // 注释以换行结束
                while (index < chars.length && chars[index] != '\n') index++;
                index++; // 要么超出界限，要么当前字符为 \n 所以需要+1
            }
            else if(chars[index] == '\'') {
                while (index < chars.length) {

                }
            }
            // 无字符/空白字符/制表符都算作”空”文本；
        }
    }

}
