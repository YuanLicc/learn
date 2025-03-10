package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import junit.framework.TestCase;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * 来源：力扣（LeetCode）437
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 */
public class BTPathSum extends TestCase {
    
    int cnt = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        
        sum(root, sum);
        
        return cnt;
    }
    
    public int pathSum1(TreeNode root, int sum) {
        if(root == null) return 0;
        
        sum(root, null, sum);
        
        return cnt;
    }
    
    private void sum(TreeNode node, int[] sums, int sum) {
        if(node == null) return;
        if(sums == null) {
            sums = new int[1];
            sums[0] = node.val;
        }
        else {
            int[] tmp = new int[sums.length + 1];
            
            for(int i = 0; i < sums.length; i++) {
                tmp[i] = sums[i] + node.val;
                if(tmp[i] == sum) cnt++;
            }
            tmp[tmp.length - 1] = node.val;
            
            sums = tmp;
        }
        
        if(node.val == sum) cnt++;
        sum(node.left, sums, sum);
        sum(node.right, sums, sum);
    }
    
    private void sum(TreeNode node, int sum) {
        if(node == null) return;
        
        calculate(node, sum, 0);
        sum(node.left, sum);
        sum(node.right, sum);
    }
    
    private void calculate(TreeNode node, int sum, int cur) {
        if(node == null) return;
        
        if (cur + node.val == sum) cnt++;
        calculate(node.left, sum, cur + node.val);
        calculate(node.right, sum, cur + node.val);
    }
    
}
