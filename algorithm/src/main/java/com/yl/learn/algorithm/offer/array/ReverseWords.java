package com.yl.learn.algorithm.offer.array;

/**
 * 翻转句子中的单词， 如 ： I love studying.
 * 结果： studying. love I
 */
public class ReverseWords {


    public static void reverseWords(char[] arr) {

        // 翻转整个句子：.gniyduts evol I
        reverse(arr, 0, arr.length - 1);

        // 遇见一个词再进行翻转，如：.gniyduts -> studying.
        int start = 0;
        for(int i = 0; i < arr.length; i++) {

            if(arr[i] == ' ') {

                reverse(arr, start, i - 1);
                start = i + 1;
            }
        }
    }

    private static void reverse(char[] arr, int start, int end) {

        int middle = (start + end) >> 1;

        for(int i = start; i <= middle; i++) {
            int rightIndex = end - i + start;

            char temp = arr[rightIndex];
            arr[rightIndex] = arr[i];
            arr[i] = temp;
        }

    }

}
