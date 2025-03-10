package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class Trie extends TestCase {
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
        root.nexts = new Node[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node[] nexts = root.nexts;
        char[] chars = word.toCharArray();
        Node pre = root;
        for(int i = 0; i < chars.length; i++) {
            int cInt = chars[i] - 'a';
            
            if(nexts[cInt] == null) {
                for(int j = i; j < chars.length; j++) {
                    Node cur = new Node();
                    cur.nexts = new Node[26];
                    int cInt1 = chars[j] - 'a';
                    pre.nexts[cInt1] = cur;
                    pre = cur;
                }
                pre.isWord = true;
                return;
            }
            if(i == chars.length - 1) {
                nexts[cInt].isWord = true;
            }
            pre = nexts[cInt];
            nexts = nexts[cInt].nexts;
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node[] nexts = root.nexts;
        char[] chars = word.toCharArray();
        
        for(int i = 0; i < chars.length; i++) {
            if(nexts[chars[i] - 'a'] == null) {
                return false;
            }
            
            if(i == chars.length - 1 && nexts[chars[i] - 'a'].isWord) {
                return true;
            }
            nexts = nexts[chars[i] - 'a'].nexts;
        }
        
        return false;
    }
    
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node[] nexts = root.nexts;
        char[] chars = prefix.toCharArray();
    
        for(int i = 0; i < chars.length; i++) {
            if(nexts[chars[i] - 'a'] == null) {
                return false;
            }
            
            nexts = nexts[chars[i] - 'a'].nexts;
        }
    
        return true;
    }
    
    class Node {
        public boolean isWord = false;
        public Node[] nexts = null;
    }
    
    public void test() {
        Trie trie = new Trie();
        trie.insert("ab");
        PrintUtil.println(trie.search("abc"));
        PrintUtil.println(trie.search("ab"));
        PrintUtil.println(trie.startsWith("abc"));
        PrintUtil.println(trie.startsWith("ab"));
        trie.insert("ab");
        PrintUtil.println(trie.search("abc"));
        PrintUtil.println(trie.startsWith("abc"));
        trie.insert("abc");
        PrintUtil.println(trie.search("abc"));
        PrintUtil.println(trie.startsWith("abc"));
    }
}
