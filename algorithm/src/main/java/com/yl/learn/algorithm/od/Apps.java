package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 智能手机方便了我们生活的同时，也侵占了我们不少的时间。
 * “手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
 * 它的大概原理是这样的：
 *
 * 在一天24小时内，可以注册每个App的允许使用时段
 * 一个时间段只能使用一个App
 * App有优先级，数值越高，优先级越高。注册使用时段时，
 * 如果高优先级的App时间和低优先级的时段有冲突，
 * 则系统会自动注销低优先级的时段，如果App的优先级相同，则后添加的App不能注册。
 * 请编程实现，根据输入数据注册App，并根据输入的时间点，
 * 返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串“NA”。
 *
 * 输入描述
 * 第一行表示注册的App数量 N（N ≤ 100）
 * 第二部分包括 N 行，每行表示一条App注册数据
 * 最后一行输入一个时间点，程序即返回该时间点使用的App
 *
 * 2
 * App1 1 09:00 10:00
 * App2 2 11:00 11:30
 * 09:30
 *
 * 数据说明如下：
 *
 * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
 * 优先级1~5，数字越大，优先级越高
 * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
 * 起始时间需小于结束时间，否则注册不上
 * 注册信息中的时间段包含起始时间点，不包含结束时间点
 * 输出描述
 * 输出一个字符串，表示App名称，或NA表示空闲时间
 *
 * 示例1
 * 输入
 *
 * 1
 * App1 1 09:00 10:00
 * 09:30
 * 输出
 *
 * App1
 */
public class Apps {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lineNum = in.nextInt();
        in.nextLine();
        List<App> appMap = new ArrayList<>();
        for (int i = 0; i < lineNum; i++) {
            String[] line = in.nextLine().split(" ");
            App app = new App(line[0], Integer.parseInt(line[1]), line[2], line[3]);
            if(app.startTime >= app.endTime) continue;
            if(dfs(appMap, app)) {
                appMap.add(app);
            }
        }
        if(appMap.size() == 0) System.out.println("NA");
        int aimTime = toTime(in.nextLine());
        System.out.println(find(appMap, aimTime));
    }

    private static String find(List<App> appMap, int aimTime) {
        for (int i = appMap.size() - 1; i >= 0; i--) {
            App cApp = appMap.get(i);
            if(aimTime >= cApp.startTime && aimTime < cApp.endTime) {
                return cApp.appName;
            }
        }
        return "NA";
    }

    private static boolean dfs(List<App> appMap, App app) {
        if(appMap.size() == 0) return true;
        for (int j = appMap.size() - 1; j >= 0; j--) {
            App compareApp = appMap.get(j);
            boolean isCom = isCom(compareApp.startTime, compareApp.endTime, app.startTime, app.endTime);
            if(compareApp.priority < app.priority && isCom) {
                appMap.remove(j);
                return dfs(appMap, app);
            }
            else if(isCom && compareApp.priority == app.priority) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCom(int time1, int time2, int time3, int time4) {
        if(time1 <= time3) {
            return time2 > time3;
        }
        else {
            return time1 < time4;
        }
    }

    static class App {
        String appName;
        int priority;
        int startTime, endTime;
        App(String appName, int priority, String startTime, String endTime) {
            this.appName = appName;
            this.priority = priority;
            this.startTime = toTime(startTime);
            this.endTime = toTime(endTime);
        }
    }

    private static int toTime(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

}
