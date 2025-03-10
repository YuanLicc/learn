package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 *
 * 来源：力扣（LeetCode）662
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 */
public class BTWidth extends TestCase {
    
    int max = 0;
    
    public int widthOfBinaryTree(TreeNode root) {
    
        List<TreeNode> rootLevel = new ArrayList<>();
        rootLevel.add(root);
        levelTravel(rootLevel);
        
        return max;
    }
    
    private void levelTravel(List<TreeNode> treeNodes) {
        if(treeNodes.isEmpty()) return;
        
        List<TreeNode> nextLevel = new ArrayList<>();
        int curLevelWd = width(treeNodes, nextLevel);
        
        max = max > curLevelWd ? max : curLevelWd;
        
        levelTravel(nextLevel);
    }
    
    private int width(List<TreeNode> treeNodes, List<TreeNode> next) {
        
        int index = 0;
        
        while(index < treeNodes.size() && treeNodes.get(index) == null) {
            index++;
        }
        int endIndex = treeNodes.size() - 1;
        while(endIndex >= 0 && treeNodes.get(endIndex) == null) {
            endIndex--;
        }
        
        if(endIndex >= index) {
            for(int i = index; i <= endIndex; i++) {
                TreeNode cur = treeNodes.get(i);
                
                if(cur == null) {
                    next.add(null);
                    next.add(null);
                }
                else {
                    next.add(cur.left);
                    next.add(cur.right);
                }
            }
        }
        
        return endIndex >= index ? endIndex - index + 1 : 0;
    }
    
    public void test() {
        widthOfBinaryTree(new BTCodec().deserialize("[1,3,2,5,3,null,9]"));
    }
}
