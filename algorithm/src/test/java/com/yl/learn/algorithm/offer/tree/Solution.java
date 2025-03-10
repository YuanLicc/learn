package com.yl.learn.algorithm.offer.tree;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(10);

        head.left = new TreeNode(11);
        head.right = new TreeNode(22);

        head.left.left = new TreeNode(10);
        head.left.right = new TreeNode(22);

        findPath(head, 43);
    }
    
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        
        if(root == null) {
            return new ArrayList<>();
        }
        
        ArrayList<ArrayList<Integer>> re = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<ArrayList<Integer>> paths = new Stack<>();
        Stack<Integer> totals = new Stack<>();
        totals.push(0);
        stack.push(root);
        paths.push(new ArrayList<>());
        int total = 0;

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ArrayList<Integer> cuPath = new ArrayList<>();
            cuPath.addAll(paths.pop());
            cuPath.add(node.val);
            total = node.val + totals.pop();

            if(node.right != null) {
                totals.push(total);
                paths.push(cuPath);
                stack.push(node.right);
            }
            if(node.left != null) {
                totals.push(total);
                paths.push(cuPath);
                stack.push(node.left);
            }

            if(node.left == null && node.right == null) {
                if(total == target) {
                    ArrayList<Integer> p = new ArrayList<>();
                    p.addAll(cuPath);
                    re.add(p);
                }
            }
        }
        
        return re;
    }
}