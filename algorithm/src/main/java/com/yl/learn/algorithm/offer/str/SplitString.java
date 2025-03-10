package com.yl.learn.algorithm.offer.str;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.
 * 则经过替换之后的字符串为We%20Are%20Happy。
 */
public class SplitString {

    private static final char SPACE = ' ';

    public static void split(StringBuffer buffer) {

        int spaceNum = 0;

        for(int i = 0; i < buffer.length(); i++) {
            if(buffer.charAt(i) == SPACE) {
                spaceNum++;
            }
        }

        int oldIndex = buffer.length() - 1;

        int newLength = oldIndex + 1 + spaceNum * 2;

        int newIndex = newLength - 1;

        buffer.setLength(newLength);

        while(oldIndex >= 0) {

            char oldChar = buffer.charAt(oldIndex);

            if(oldChar != SPACE) {
                buffer.setCharAt(newIndex, oldChar);
                newIndex --;
                oldIndex --;
            }
            else {
                buffer.setCharAt(newIndex--, '0');
                buffer.setCharAt(newIndex--, '2');
                buffer.setCharAt(newIndex--, '%');
                oldIndex --;
            }

        }

    }

}
