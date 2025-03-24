package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 给一个字符串和一个二维字符数组，如果该字符串存在于该数组中，则按字符串的字符顺序输出字符串每个字符所在单元格的位置下标字符串，如果找不到返回字符串“N”。
 * 需要按照字符串的字符组成顺序搜索，且搜索到的位置必须是相邻单元格，其中“相邻单元格”是指那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 假定在数组中最多只存在一个可能的匹配。
 *
 * 输入描述
 * 第1行为一个数字N指示二维数组在后续输入所占的行数。
 * 第2行到第N+1行输入为一个二维大写字符数组，每行字符用半角,分割。
 * 第N+2行为待查找的字符串，由大写字符组成。
 * 二维数组的大小为N*N，0<N<=100。
 * 单词长度K，0<K<1000。
 *
 * 输出描述
 * 出一个位置下标字符串，拼接格式为：第1个字符行下标+”,”+第1个字符列下标+”,”+第2个字符行下标+”,”+第2个字符列下标… +”,”+第N个字符行下标+”,”+第N个字符列下标。
 *
 * 示例1
 * 输入
 *
 * 4
 * A,C,C,F
 * C,D,E,D
 * B,E,S,S
 * F,E,C,A
 * ACCESS
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 输出
 *
 * 0,0,0,1,0,2,1,2,2,2,2,3
 */
public class TwoDString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lineNum = in.nextInt();
        in.nextLine();
        String[][] lines = new String[lineNum][];
        for (int i = 0; i < lineNum; i++) {
            lines[i] = in.nextLine().split(",");
        }
        String[] aim = in.nextLine().split("");

        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i];
            for (int j = 0; j < line.length; j++) {
                if(line[j].equals(aim[0])) {
                    dfs(lines, i, j, new StringBuilder(), aim, 0, rs);
                    if(rs.length() > 0) {
                        System.out.println(rs.substring(0, rs.length() - 1));
                        return;
                    }
                }
            }
        }
    }

    private static void dfs(String[][] lines, int i, int j, StringBuilder sb, String[] aim, int index, StringBuilder rs) {
        if(index >= aim.length) {
            if(rs.length() == 0) rs.append(sb);
            return;
        }
        if(i < 0 || i > lines.length || j < 0 || j > lines[0].length) {
            return;
        }
        if(lines[i][j].equals(aim[index])) {
            sb.append(i).append(",").append(j).append(",");
            dfs(lines, i + 1, j, sb, aim, index + 1, rs);
            dfs(lines, i - 1, j, sb, aim, index + 1, rs);
            dfs(lines, i, j + 1, sb, aim, index + 1, rs);
            dfs(lines, i, j - 1, sb, aim, index + 1, rs);
            sb.delete(sb.length() - 4, sb.length());
        }
    }


}
