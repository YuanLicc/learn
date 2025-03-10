package com.yl.learn.algorithm.leetcode;

import com.yl.learn.algorithm.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
 * 该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，
 * 其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
 * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
 * 那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class CopyRandomList {
    //
    public Node copyRandomList(Node head) { // 时间复杂度2N、空间复杂度 2N
        if(head == null) {
            return head;
        }
        Node newNode = new Node(head.val);
        Node newPre = newNode;
        Map<Node, Node> oldNewMap = new HashMap<Node, Node>();
        oldNewMap.put(head, newPre);
        Node oldP = head.next;
        while(oldP != null) { // 第一次遍历创建新的链表，并且保存 链表中 节点的map关系
            newPre.next = new Node(oldP.val);
            oldNewMap.put(oldP, newPre.next);
            oldP = oldP.next;
            newPre = newPre.next;
        }
        newPre = newNode;
        oldP = head;
        while(oldP != null) { // 第二次遍历对新链表进行 random 的赋值
            newPre.random = oldNewMap.get(oldP.random);
            newPre = newPre.next;
            oldP = oldP.next;
        }
        return newNode;
    }
}
