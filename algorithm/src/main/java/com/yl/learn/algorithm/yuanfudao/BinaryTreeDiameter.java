package com.yl.learn.algorithm.yuanfudao;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 输出 3 （4，2，1，4）
 */
import com.yl.learn.util.tree.TreeNode;
import junit.framework.TestCase;

public class BinaryTreeDiameter extends TestCase {
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        
        len(root);
        
        return max;
    }
    
    private int len(TreeNode root) {
        if(root == null) return 0;
        
        int left = root.left == null ? 0 : len(root.left) + 1;
        int right = root.right == null ? 0 : len(root.right) + 1;
        
        max = max < left + right ? left + right : max;
        
        return left > right ? left : right;
    }
    
}
