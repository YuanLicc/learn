package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.algorithm.TreeNode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 来源：力扣（LeetCode）236
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 */
public class LowestCommonAncestor extends TestCase {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || (root.val == p.val || root.val == q.val)) return root;

        List<Integer> pre = preTravel(root);
        List<Integer> in = inTravel(root);

        for(int i = 0; i < pre.size();i++) {
            int middle = pre.get(i);

            if(middle == p.val || middle == q.val) return new TreeNode(middle);

            int middleIndex = in.indexOf(middle);
            int pIndex = in.indexOf(p.val);
            int qIndex = in.indexOf(q.val);

            if((pIndex > middleIndex && qIndex < middleIndex) || (pIndex < middleIndex && qIndex > middleIndex)) return new TreeNode(middle);
        }

        return null;
    }
    
    TreeNode rs = null;
    
    private boolean lowest(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) return false;
        
        boolean left = lowest(node.left, p, q);
        boolean right = lowest(node.right, p, q);
        
        if((left && right) || ((left || right) && (node.val == p.val || node.val == q.val))) {
            rs = node;
            return true;
        }
        else if(!left && !right) {
            return (node.val == p.val || node.val == q.val);
        }
        else {
            return true;
        }
    }

    private List<Integer> preTravel(TreeNode node) {
        List<Integer> rs = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cNode = node;

        while(cNode != null || !stack.isEmpty()) {
            if(cNode != null) {
                rs.add(cNode.val);

                stack.push(cNode);
                cNode = cNode.left;
            }
            else {
                cNode = stack.pop().right;
            }
        }
        return rs;
    }

    private List<Integer> inTravel(TreeNode node) {
        List<Integer> rs = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cNode = node;

        while(cNode != null || !stack.isEmpty()) {
            if(cNode == null) {
                stack.push(cNode);
                cNode = cNode.left;
            }
            else {
                TreeNode cc = stack.pop();
                rs.add(cc.val);
                cNode = cc.right;
            }
        }

        return rs;
    }


    private boolean find(TreeNode node, TreeNode aim) {
        if(node == null) return false;
        if(node != null && node.val == aim.val) return true;

        boolean leftIs = find(node.left, aim);
        if(leftIs) return true;

        return find(node.right, aim);
    }
}
