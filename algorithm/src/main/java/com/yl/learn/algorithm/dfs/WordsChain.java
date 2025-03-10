package com.yl.learn.algorithm.dfs;

/**
 * 单词接龙是一个与我们经常玩的成语接龙相类似的游戏，现在我们已知一组单词，
 * 且给定一个开头的字母，要求出以这个字母开头的最长的“龙”（每个单词都最多在“龙”中出现一次两次），
 * 在两个单词相连时，其重合部分合为一部分，例如beast和astonish，
 * 如果接成一条龙则变为 beastonish，另外相邻的两部分不能存在包含关系，
 * 例如at和atide间不能相连。
 *
 * 输入：
 * 输入的第一行为一个单独的整数n(n<=20)表示单词数，以下 n 行每行有一个单词，输入的最后一行为一个单个字符，表示“龙”开头的字母。
 * 你可以假定以此字母开头的“龙”一定存在.
 *
 * 输出：
 * 只需输出以此字母开头的最长的“龙”的长度
 *
 * 例子：
 * 5
 * at
 * touch
 * cheat
 * choose
 * tact
 * a
 *
 * 样例输出
 * 23（atoucheatactactouchoose）
 *
 * https://www.cnblogs.com/mind000761/p/8719832.html
 */
public class WordsChain {

    // 1. 为每个单词创建邻接表，邻接表表示可连接的单词
    // 2. 遍历邻接表，深度优先遍历，找出最长的即可。
    // 3. 注意事项：
    //      1) 每个单词可重复一次
    //      2) 相连的单词不能存在包含关系

    /*public static int length(String[] words) {

    }*/

}
