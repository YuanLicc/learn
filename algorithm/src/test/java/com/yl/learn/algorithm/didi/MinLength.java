package com.yl.learn.algorithm.didi;

public class MinLength {

    public static void main(String[] args) {

        String[] test = {"slep", "slap", "sleep", "step", "shoe", "shop", "snap", "slep"};

        for(int i = 1; i < test.length; i++) {

            System.out.println(min(test[0], test[i]));
        }
    }

    public static int min(String a, String b) {

        int[][] cell = new int[b.length()][a.length()];

        cell[0][0] = a.charAt(0) == b.charAt(0) ? 0 : 1;

        for(int j = 1; j < a.length(); j++) {
            cell[0][j] = cell[0][j - 1] + 3;
        }

        for(int i = 1; i < cell.length; i++) {
            cell[i][0] = cell[i - 1][0] + 3;
        }

        for(int i = 1; i < cell.length; i++) {
            char bc = b.charAt(i);

            for(int j = 1; j < a.length(); j++) {
                char ac = a.charAt(j);

                if(bc == ac) {
                    cell[i][j] = cell[i - 1][j - 1];
                }
                else {
                    int add = cell[i][j - 1] + 3;
                    int delete = cell[i - 1][j] + 3;
                    int edit = cell[i - 1][j - 1] + 1;

                    int tmp = add > delete ? delete : add;
                    cell[i][j] = tmp > edit ? edit : tmp;
                }
            }
        }

        return cell[b.length() - 1][a.length() - 1];

    }

}
