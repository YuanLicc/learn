package com.yl.learn.algorithm.od;

import java.util.Scanner;

public class Blocks {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int fileNums = in.nextInt();
        int[] blocks = new int[fileNums];
        int[] sizes = new int[fileNums];
        for (int i = 0; i < fileNums; i++) {
            int size = in.nextInt();
            sizes[i] = size;
            blocks[i] = size % 512 != 0 ? size / 512 + 1 : size / 512;
        }
        int allBlocks = 1474560 / 512;

        dfs(blocks, sizes, 0, 0, 0, allBlocks);
        System.out.println(maxSize);
    }

    static int maxSize = 0;

    private static void dfs(int[] blocks, int[] sizes, int index, int sumBlocks, int sumSize, int allBlocks) {
        if(index == blocks.length) {
            if(sumBlocks <= allBlocks) {
                maxSize = Math.max(maxSize, sumSize);
            }
            return;
        }
        if(sumBlocks > allBlocks) return;
        dfs(blocks, sizes, index + 1, sumBlocks + blocks[index], sumSize + sizes[index], allBlocks);
        dfs(blocks, sizes, index + 1, sumBlocks, sumSize, allBlocks);
    }
}
