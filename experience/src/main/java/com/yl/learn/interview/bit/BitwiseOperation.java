package com.yl.learn.interview.bit;

public class BitwiseOperation {

    /**
     * 左移位操作 <<
     * @param num 被移位数字
     * @param moveCount 移位位数
     * @return 移位后值
     */
    public static int leftMove(int num, int moveCount) {
        return num << moveCount;
    }

    /**
     * 右移位操作 >>
     * @param num 被移位数字
     * @param moveCount 移位位数
     * @return 移位后值
     */
    public static int rightMove(int num, int moveCount) {
        return num >> moveCount;
    }

}
