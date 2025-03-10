package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class IslandNums extends TestCase {
    
    public int numIslands(char[][] grid) {
        int num = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    num += 1;
                    remove(grid, i, j);
                }
            }
        }
        
        return num;
    }
    
    private void remove(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        
        if(grid[i][j] == '1') {
            grid[i][j] = '0';
            
            remove(grid, i, j - 1);
            remove(grid, i, j + 1);
            remove(grid, i + 1, j);
            remove(grid, i - 1, j);
        }
    }
    
    public void test() {
        char[][] chars = new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };
        
        PrintUtil.println(numIslands(chars));
    }

}
