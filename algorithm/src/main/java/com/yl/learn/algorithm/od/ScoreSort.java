package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 小明来到学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
 *
 * 输入描述
 * 第 1 行输入两个整数，学生人数 n 和科目数量 m。
 *
 * 0 < n < 100
 * 0 < m < 10
 * 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
 *
 * 科目名称只由英文字母构成，单个长度不超过10个字符。
 * 科目的出现顺序和后续输入的学生成绩一一对应。
 * 不会出现重复的科目名称。
 * 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
 *
 * 学生不会重名。
 * 学生姓名只由英文字母构成，长度不超过10个字符。
 * 成绩是0~100的整数，依次对应第2行种输入的科目。
 * 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 *
 * 输出描述
 * 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
 *
 * 示例1
 * 输入
 *
 * 3 2
 * yuwen shuxue
 * fangfang 95 90
 * xiaohua 88 98
 * minmin 100 82
 * shuxue
 * 输出
 *
 * xiaohua fangfang minmin
 * 说明
 *
 * 按照shuxue成绩排名依次是 xiaohua fangfang minmin
 */
public class ScoreSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int people = in.nextInt();
        int project = in.nextInt();
        in.nextLine();
        String[] projects = in.nextLine().split(" ");
        Map<String, List<Score>> map = new HashMap<>();
        for (int i = 0; i < people; i++) {
            String[] lineScore = in.nextLine().split(" ");
            String name = lineScore[0];
            int sumScore = 0;
            for (int j = 1; j < lineScore.length; j++) {
                Score score = new Score(Integer.parseInt(lineScore[j]), name);
                String projectName = projects[j - 1];
                List<Score> list = map.getOrDefault(projectName, new ArrayList<>());
                list.add(score);
                map.put(projectName, list);
                sumScore += score.score;
            }
            Score score = new Score(sumScore, name);
            List<Score> list = map.getOrDefault("all", new ArrayList<>());
            list.add(score);
            map.put("all", list);
        }

        String aimProject = in.nextLine();
        List<Score> list = map.get(aimProject);
        if(list == null) list = map.get("all");
        Collections.sort(list);
        print(list);
    }

    private static void print(List<Score> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).name).append(i == list.size() - 1 ? "" : " ");
        }
        System.out.println(sb.toString());
    }

    static class Score implements Comparable<Score> {
        int score;
        String name;
        Score(int score, String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(Score score) {
            // 名称正序排列
            if(this.score == score.score) {
                return this.name.compareTo(score.name);
            }
            // 分数逆序排列
            return score.score - this.score;
        }
    }
}
