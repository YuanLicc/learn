package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class NQueen extends TestCase {

    public List<List<String>> solveNQueens(int n) {

        int[][] cell = new int[n][n];
        
        List<List<String>> rs = new ArrayList<>();

        dfs(cell, rs, 1, n);
        
        return rs;
    }

    private void init(int n, List<String> fangAn) {

    }
    
    private void dfs(int[][] cell, List<List<String>> rs, int queen, int n) {
        
        if(queen > n) {
            rs.add(cellToString(cell));
            return;
        }

        // 一层一层的放置
        for(int i = 0; i < n; i++) {
            if(queen == 1) {
                int[] newLine = new int[n];
                newLine[i] = 1;
                cell[queen - 1] = newLine;
                dfs(cell, rs, queen + 1, n);
            }
            else {
                if(check(cell, queen - 1, i)) {
                    int[][] cp = copy(cell, queen - 2);
                    cp[queen - 1][i] = 1;
                    dfs(cp, rs, queen + 1, n);
                }
            }
        }
    }

    private int[][] copy(int[][] cell, int level) {
        int[][] cp = new int[cell.length][cell[0].length];

        for(int i = 0; i <= level; i++) {
            for(int j = 0; j < cell[i].length; j++) {
                cp[i][j] = cell[i][j];
            }
        }
        return cp;
    }

    private List<String> cellToString(int[][] cell) {
        List<String> rs = new ArrayList<>(cell.length);

        for(int i = 0; i < cell.length; i++) {
            StringBuilder sb = new StringBuilder(cell[i].length);
            for(int j = 0; j < cell[i].length; j++) {
                sb.append(cell[i][j] == 0 ? '.' : 'Q');
            }
            rs.add(sb.toString());
        }
        return rs;
    }
    
    private boolean check(int[][] cell, int i, int j) {
        int i1 = i;
        int j1 = j;
        while(i1 >= 0 && j1 >= 0) {
            if(cell[i1][j1] == 1) return false;
            i1--;
            j1--;
        }

        i1 = i;
        j1 = j;
        while(i1 >= 0) {
            if(cell[i1][j1] == 1) return false;
            i1--;
        }

        i1 = i;
        j1 = j;
        while(i1 >= 0 && j1 < cell[i1].length) {
            if(cell[i1][j1] == 1) return false;
            i1--;
            j1++;
        }
        
        return true;
    }

    public void test() {
        solveNQueens(4);
    }
}
