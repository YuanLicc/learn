package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。
 *
 * 支持命令：
 * 创建目录命令：mkdir 目录名称，如 mkdir abc 为在当前目录创建abc目录，如果已存在同名目录则不执行任何操作。此命令无输出。
 * 进入目录命令：cd 目录名称，如 cd abc 为进入abc目录，特别地，cd … 为返回上级目录，如果目录不存在则不执行任何操作。此命令无输出。
 * 查看当前所在路径命令：pwd，输出当前路径字符串。
 * 约束：
 *
 * 目录名称仅支持小写字母；mkdir 和 cd 命令的参数仅支持单个目录，如：mkdir abc 和 cd abc；不支持嵌套路径和绝对路径，如 mkdir abc/efg，cd abc/efg，mkdir /abc/efg，cd /abc/efg 是不支持的。
 * 目录符号为/，根目录/作为初始目录。
 * 任何不符合上述定义的无效命令不做任何处理并且无输出。
 * 输入描述
 * 输入 N 行字符串，每一行字符串是一条命令。
 *
 * 命令行数限制100行以内，目录名称限制10个字符以内。
 *
 * 输出描述
 * 输出最后一条命令运行结果字符串。
 *
 * 示例1
 * 输入
 *
 * mkdir abc
 * cd abc
 * pwd
 * 1
 * 2
 * 3
 * 输出
 *
 * /abc/
 */
public class DirectoryOperate {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int level = 1;
        Map<String, HashSet<String>> directories = new HashMap<>();
        LinkedList<String> directoryStack = new LinkedList<String>();
        directoryStack.addLast("/");
        directories.put("/", new HashSet<>());
        while (in.hasNextLine()) {
            String comd = in.nextLine().trim();
            if(comd.equals("")) continue;
            String[] commonds = comd.split(" ");
            if(commonds.length > 2) continue;
            String commondHead = commonds[0];
            switch (commondHead) {
                case "pwd" : {
                    printStack(directoryStack);
                    break;
                }
                case "mkdir" : {
                    if(commonds.length == 1) break;
                    if(!judgeParam(commonds[1])) {
                        break;
                    }
                    else {
                        HashSet<String> lineD = directories.getOrDefault(directoryStack.peekLast(), new HashSet<String>());
                        lineD.add(commonds[1]);
                        directories.put(directoryStack.peekLast(), lineD);
                        break;
                    }
                }
                case "cd" : {
                    if(commonds.length == 1) break;
                    if(commonds[1].equals("..")) {
                        if(directoryStack.size() == 1) break;
                        else {
                            directoryStack.removeLast();
                        }
                    }
                    else if(!judgeParam(commonds[1])) {
                        break;
                    }
                    else {
                        HashSet<String> lineD = directories.getOrDefault(directoryStack.peekLast(), new HashSet<>());
                        if(lineD.contains(commonds[1])) {
                            directoryStack.addLast(commonds[1] + "/");
                        }
                        break;
                    }
                }
                default: break;
            }
        }
    }

    private static void printStack(LinkedList<String> directoryStack) {
        StringBuilder sb = new StringBuilder();
        for (String s : directoryStack) {
            sb.append(s);
        }
        System.out.println(sb.length() == 1 ? sb.toString() : sb.substring(0, sb.length() - 1));
    }

    private static boolean judgeParam(String param) {
        for (int i = 0; i < param.length(); i++) {
            char curr = param.charAt(i);
            if(!(curr >= 'a' && curr <= 'z')) {
                return false;
            }
        }
        return true;
    }
}
