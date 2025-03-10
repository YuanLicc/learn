package com.yl.learn.algorithm.yy;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
    
    	Scanner in = new Scanner(System.in);
        
        String text = in.nextLine();
        
        if(text.equals("")) {
        	return;
        }
        
        String aim = in.nextLine();
        
        if(aim.equals("")) {
        	return;
        }
        
        System.out.println(isContains(text, aim));
    }
    
    public static boolean isContains(String text, String aim) {
        
        int count = aim.length() - 1;
        
    	for(int i = text.length() - 1; i >= 0; i--) {
            if(count == -1) {
            	return true;
            }
            
            if(text.charAt(i) == aim.charAt(count)) {
            	count--;
            }
            else {
            	count = aim.length() - 1;
            }
        }
        
        return false;
    }
}