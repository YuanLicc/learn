package com.yl.learn.algorithm.mp;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.Node;
import com.yl.learn.algorithm.NodeUtils;
import com.yl.learn.algorithm.pm.ArrayReverse;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;
import java.util.Arrays;

public class ArrayReverseTest extends TestSuper {

    @Test
    public void testArrayReverse() {
        char[] ccc = {'a', 'b', 'c', 'd', 'e'};

        ArrayReverse.reverse(ccc);
        PrintUtil.println(Arrays.toString(ccc));
    }

    @Test
    public void testArrayReverseK() {
        char[] ccc = {'a', 'b', 'c', 'd', 'e'};

        ArrayReverse.reverse(ccc, 2);
        PrintUtil.println(Arrays.toString(ccc));
    }

    @Test
    public void testArrayReverseLastK() {
        char[] ccc = {'a', 'b', 'c', 'd', 'e'};

        ArrayReverse.reverseLastK(ccc, 3);
        PrintUtil.println(Arrays.toString(ccc));
    }

    @Test
    public void testArrayReverseNodeK() {
        Node<Integer> node = NodeUtils.toNode(1, 3, 4, 5, 6);

        Node<Integer> oo = ArrayReverse.reverse(node, 5);

        PrintUtil.println(NodeUtils.toString(oo));
    }

}
