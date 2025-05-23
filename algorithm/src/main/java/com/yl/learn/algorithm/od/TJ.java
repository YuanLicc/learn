package com.yl.learn.algorithm.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 推荐多样性需要从多个列表中选择元素，一次性要返回 N 屏数据（窗口数量），每屏展示 K 个元素（窗口大小），选择策略：
 *
 * 各个列表元素需要做穿插处理，即先从第一个列表中为每屏选择一个元素，再从第二个列表中为每屏选择一个元素，依次类推
 *
 * 每个列表的元素尽量均分为 N 份，如果不够 N 个，也要全部分配完，参考样例图：
 *
 * （1）从第一个列表中选择 4 条 0 1 2 3，分别放到 4 个窗口中
 *
 * （2）从第二个列表中选择 4 条 10 11 12 13，分别放到 4 个窗口中
 *
 * （3）从第三个列表中选择 4 条 20 21 22 23，分别放到 4 个窗口中
 *
 * （4）再从第一个列表中选择 4 条 4 5 6 7，分别放到 4 个窗口中
 *
 * …
 *
 * （5）再从第一个列表中选择，由于数量不足 4 条，取剩下的 2 条，放到 窗口1 和 窗口2
 *
 * （6）再从第二个列表中选择，由于数量不足 4 条并且总的元素数达到窗口要求，取 18 19 放到 窗口3 和 窗口4
 *
 * 在这里插入图片描述
 *
 * 输入描述
 * 第一行输入为 N，表示需要输出的窗口数量，取值范围 [1, 10]
 * 第二行输入为 K，表示每个窗口需要的元素数量，取值范围 [1, 100]
 * 之后的行数不定（行数取值范围 [1, 10]），表示每个列表输出的元素列表。
 * 元素之间以空格隔开，已经过排序处理，每个列表输出的元素数量取值范围 [1, 100]
 *
 * 输出描述
 * 输出元素列表，元素数量 = 窗口数量 * 窗口大小，元素之间以空格分隔，多个窗口合并为一个列表输出，参考样例：
 * 先输出窗口1的元素列表，再输出窗口2的元素列表，再输出窗口3的元素列表，最后输出窗口4的元素列表
 *
 * 备注
 * 每个列表会保证元素数量满足窗口要求，不需要考虑元素不足情况
 * 每个列表的元素已去重，不需要考虑元素重复情况
 * 每个列表的元素列表均不为空，不需要考虑列表为空的情况
 * 每个列表的元素列表已经过排序处理，输出结果要保证不改变同一个列表的元素顺序
 * 每个列表的元素数量可能是不同的
 * 用例
 * 输入
 * 4
 * 7
 * 0 1 2 3 4 5 6 7 8 9
 * 10 11 12 13 14 15 16 17 18 19
 * 20 21 22 23 24 25 26 27 28 29
 * 输出
 *
 * 0 10 20 4 14 24 8 1 11 21 5 15 25 9 2 12 22 6 16 26 18 3 13 23 7 17 27 19
 */
public class TJ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int windowCount  = Integer.parseInt(in.nextLine()); // 窗口个数
        int windowNum = Integer.parseInt(in.nextLine()); // 窗口元素个数
        List<Queue<Integer>> list = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if(line.equals("")) break;
            String[] lines = line.split(" ");
            list.add(Arrays.stream(lines).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new)));
        }

        int listIndex = 0, index = 0;
        int[] rs = new int[windowCount * windowNum];

        while (index < rs.length) {
            boolean remove = false;
            for (int i = 0; i < windowCount && !list.isEmpty();) {
                Queue<Integer> queue = list.get(listIndex);
                if(queue.isEmpty()) { // 如果队列为空，表示需要查找下一个列表数据
                    list.remove(listIndex);
                    listIndex %= list.size(); // 切换到下一个队列；
                    remove = true;
                }
                else { // 队列不为空
                    rs[index] = queue.poll();
                    index++;
                    i++;
                }
            }
            if(!remove) {
                listIndex = (listIndex + 1) % list.size();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < windowCount; i++) {
            for (int j = 0; j < windowNum; j++) {
                sb.append(rs[j * windowCount + i]).append(" ");
            }
        }
        System.out.println(sb);
    }

}
