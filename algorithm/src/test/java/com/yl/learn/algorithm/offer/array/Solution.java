package com.yl.learn.algorithm.offer.array;

public class Solution {

    public static void main(String[] args) {
        reOrderArray(new int[]{1,2,3,4,5,6,7});
    }

    public static void reOrderArray(int [] array) {
        
        if(array.length == 0 || array.length == 1) {
            return;
        }
        
        int jIndex = 0;
        int oIndex = 0;
        
        while(jIndex < array.length && oIndex < array.length) {
            
            while(jIndex < array.length && (array[jIndex] & 1) != 1) {
                jIndex++;
            }
            
            while(oIndex < array.length && (array[oIndex] & 1) == 1) {
                oIndex++;
            }
            
            if(jIndex > oIndex && (array[jIndex] & 1) == 1) {
                array[jIndex] = array[jIndex] ^ array[oIndex];
                array[oIndex] = array[jIndex] ^ array[oIndex];
                array[jIndex] = array[jIndex] ^ array[oIndex];
                jIndex++;
                oIndex++;
            }
            else {
                jIndex++;
            }

        }
    }
}