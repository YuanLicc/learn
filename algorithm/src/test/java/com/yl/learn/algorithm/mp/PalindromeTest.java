package com.yl.learn.algorithm.mp;

import com.yl.learn.algorithm.Node;
import com.yl.learn.algorithm.NodeUtils;
import com.yl.learn.algorithm.pm.Palindrome;
import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

public class PalindromeTest extends TestSuper {

    @Test
    public void palindrome() {

        PrintUtil.println(Palindrome.palindrome("abcba"));

        PrintUtil.println(Palindrome.palindrome("abcbaf"));
    }


    @Test
    public void palindromeNode() {

        Node<Integer> node = NodeUtils.toNode(1,2, 3, 2, 1);

        Node<Integer> node1 = NodeUtils.toNode(1,2, 2, 1);

        PrintUtil.println(Palindrome.palindrome(node));

        PrintUtil.println(Palindrome.palindrome(node1));
    }

}
