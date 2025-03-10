package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 来源：力扣（LeetCode）297
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 */
public class BTCodec extends TestCase {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        if(root == null) return "[]";
        
        List<String> nums = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        
        while(!queue.isEmpty()) {
            TreeNode cNode = queue.pop();
            
            if(cNode == null) nums.add("null");
            else {
                nums.add(String.valueOf(cNode.val));
                queue.addLast(cNode.left);
                queue.addLast(cNode.right);
            }
        }
    
        StringBuilder sb = new StringBuilder("[");
        
        for(int i = nums.size() - 1; i >= 0; i--) {
            if(nums.get(i).equals("null")) nums.remove(i);
            else break;
        }
        
        for(int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i)).append(',');
        }
        
        return sb.substring(0, sb.length() - 1) + "]";
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() < 3) {
            return null;
        }
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        
        TreeNode head = toNode(nodes[0]);
        if(head == null) return null;
        
        int upLevelNodes = 1;
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        treeNodes[0] = head;
        
        int levelStartIndex = 1;
        
        while(levelStartIndex < nodes.length) {
            int levelEndIndex = levelStartIndex + upLevelNodes * 2 - 1;
            int upLevelStartIndex = levelStartIndex - upLevelNodes;
            int upIndex = upLevelStartIndex;
            
            upLevelNodes = 0;
            for(int i = levelStartIndex; i <= levelEndIndex && i < nodes.length; i += 2) {
                while(treeNodes[upIndex] == null){
                    upIndex++;
                }
                
                TreeNode p = treeNodes[upIndex];
                TreeNode left = toNode(nodes[i]);
                treeNodes[i] = left;
                TreeNode right = i + 1 < nodes.length ? toNode(nodes[i + 1]) : null;
                if(i + 1 < nodes.length) treeNodes[i + 1] = right;
                
                if(left != null || right != null) {
                    upLevelNodes += 2;
                }
                
                p.left = left;
                p.right = right;
                
                upIndex++;
            }
            levelStartIndex = levelEndIndex + 1;
        }
        
        return head;
    }
    
    private TreeNode toNode(String num) {
        Integer numI = checkAndParse(num);
        
        if(numI == null) return null;
        else return new TreeNode(numI);
    }
    
    private Integer checkAndParse(String num) {
        try {
            return Integer.parseInt(num);
        }
        catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    public void test() {
        TreeNode node = deserialize("[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]");
        String ss = serialize(node);
        PrintUtil.println(ss);
    }
}
