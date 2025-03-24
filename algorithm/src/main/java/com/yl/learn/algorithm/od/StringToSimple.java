package com.yl.learn.algorithm.od;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给定一个输入字符串，字符串只可能由英文字母（ ‘a’ ~ ‘z’、‘A’ ~ ‘Z’ ）和左右小括号（ ‘(’、‘)’ ）组成。
 * 当字符里存在小括号时，小括号是成对的，可以有一个或多个小括号对，小括号对不会嵌套，小括号对内可以包含1个或多个英文字母，也可以不包含英文字母。
 * 当小括号对内包含多个英文字母时，这些字母之间是相互等效的关系，而且等效关系可以在不同的小括号对之间传递，即当存在 ‘a’ 和 ‘b’ 等效和存在 ‘b’ 和 ‘c’ 等效时，‘a’ 和 ‘c’ 也等效，另外，同一个英文字母的大写字母和小写字母也相互等效（即使它们分布在不同的括号对里）
 * 需要对这个输入字符串做简化，输出一个新的字符串，输出字符串里只需保留输入字符串里的没有被小括号对包含的字符（按照输入字符串里的字符顺序），并将每个字符替换为在小括号对里包含的且字典序最小的等效字符。
 * 如果简化后的字符串为空，请输出为"0"。
 * 示例 :
 * 输入字符串为"never(dont)give(run)up(f)()"，初始等效字符集合为(‘d’, ‘o’, ‘n’, ‘t’)、(‘r’, ‘u’, ‘n’)，由于等效关系可以传递，因此最终等效字符集合为(‘d’, ‘o’, ‘n’, ‘t’, ‘r’, ‘u’)，将输入字符串里的剩余部分按字典序最小的等效字符替换后得到"devedgivedp’
 * 输入描述
 * input_string
 * 输入为1行，代表输入字符串
 * 备注
 * 输入字符串的长度在1~100000之间
 *
 * 输出描述
 * output_string
 *
 * 输出为1行，代表输出字符串
 *
 * 示例1
 * 输入
 *
 * ()abd
 * 1
 * 输出
 *
 * abd
 * 1
 * 说明
 *
 * 输入字符串里没有被小括号包含的子字符串为"abd"，其中每个字符没有等效字符，输出为"abd"
 *
 * 示例2
 * 输入
 *
 * (abd)demand(fb)()for
 * 1
 * 输出
 *
 * aemanaaor
 * 1
 * 示例3
 * 输入
 *
 * ()happy(xyz)new(wxy)year(t)
 * 1
 * 输出
 *
 * happwnewwear
 */
public class StringToSimple {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine().toLowerCase();

        StringBuilder simple = new StringBuilder();
        ArrayList<int[]> khes = new ArrayList<>();
        List<Integer> minL = new ArrayList<>();

        int[] all = new int[26];
        int[] kh = new int[26];
        boolean is = false;
        for (int index = 0; index < line.length(); index++) {
            char current = line.charAt(index);
            if(current == '(') {
                is = true;
                kh = new int[26];
                continue;
            }
            else if(current == ')') {
                is = false;
                if(!merge(khes, kh)) {
                    khes.add(kh);
                }
                continue;
            }
            if(!is) {
                simple.append(current);
            }
            else {
                kh[current - 'a'] = 1;
                all[current - 'a'] = 1;
            }
        }

        for (int i = 0; i < khes.size(); i++) {
            int min = 26;
            int[] current = khes.get(i);
            for (int j = 0; j < 26; j++) {
                min = Math.min(min, current[j] == 1 ? j : min);
            }
            minL.add(min);
        }

        StringBuilder rs = new StringBuilder();

        for(int i = 0; i < simple.length(); i++) {
            char current = simple.charAt(i);
            if(all[current - 'a'] == 1) {
                for(int j = 0; j < khes.size(); j++) {
                    if(khes.get(j)[current - 'a'] == 1) {
                        rs.append((char)(minL.get(j) + 'a'));
                    }
                }
            }
            else {
                rs.append(current);
            }
        }

        System.out.println(rs);
    }


    private static boolean merge(List<int[]> abs, int[] ab) {
        for(int i = 0; i < ab.length; i++) {
            for (int j = 0; j < abs.size(); j++) {
                if(abs.get(j)[ab[i]] == 1) {
                    merge(abs.get(j), ab);
                    return true;
                }
            }
        }
        return false;
    }

    private static void merge(int[] main, int[] ab) {
        for (int i = 0; i < main.length; i++) {
            if(ab[i] == 1) {
                main[i] = 1;
            }
        }
    }
}
