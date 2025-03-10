package com.yl.learn.algorithm.offer.array;

/**
 * 两个有序的数组 A1, A2，请将他们合并且合并后有序
 */
public class ComposeArray {

    public static int[] compose(int[] a1, int[] a2) {

        int le1Index = a1.length - 1;
        int le2Index = a2.length - 1;

        int[] compose = new int[le1Index + le2Index + 2];

        int composeIndex = compose.length - 1;

        while (composeIndex >= 0) {

            if(le1Index < 0) {
                if(le2Index >= 0) {
                    while (le2Index >= 0) {
                        compose[composeIndex--] = a2[le2Index--];
                    }
                    break;
                }
                break;
            }
            else {
                if(le2Index < 0) {
                    while (le1Index >= 0) {
                        compose[composeIndex--] = a1[le1Index--];
                    }
                    break;
                }
                else {
                    if(a1[le1Index] >= a2[le2Index]) {
                        compose[composeIndex--] = a1[le1Index--];
                    }
                    else {
                        compose[composeIndex--] = a2[le2Index--];
                    }
                }
            }

        }
        return compose;
    }

}
