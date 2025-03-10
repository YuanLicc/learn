package com.yl.learn.algorithm.baidu;

import com.yl.learn.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrder {
    
    public List<Integer> rightSideView(TreeNode root) {
        
        if(root == null) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> rr = levelOrder(root);
        
        List<Integer> rs = new ArrayList<>(rr.size());
        rr.stream().forEach(list -> {
            rs.add(list.get(list.size() - 1));
        });
        
        return rs;
    }

    public List<List<Integer>> levelOrder(TreeNode node) {
        
        List<List<Integer>> rs = new ArrayList<>();
        if(node == null) return rs;
    
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(node);
        queue.addLast(null);
        
        while(!queue.isEmpty() && queue.getFirst() != null) {
            
            ArrayList<Integer> level = new ArrayList<>();
            
            while(queue.getFirst() != null) {
                TreeNode levelNode = queue.removeFirst();
                level.add(levelNode.val);
                if(levelNode.left != null) queue.addLast(levelNode.left);
                if(levelNode.right != null) queue.addLast(levelNode.right);
            }
            rs.add(level);
            queue.removeFirst();
            queue.addLast(null);
        }
        
        return rs;
    }

}
