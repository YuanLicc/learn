package com.yl.learn.algorithm.offer.tree;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

public class CreateBinaryTreeTest extends TestSuper {

    int pre[] = {1,2,4,7,3,5,6,8};
    int middle[] = {4,7,2,1,5,3,8,6};

    @Test
    public void test() {
        PrintUtil.template("Create binary tree:", () -> {
            PrintUtil.println(Arrays.toString(pre), "Pre traverse:\n\t", "");

            PrintUtil.println(Arrays.toString(middle), "Middle traverse: \n\t", "");

            PrintUtil.println(CreateBinaryTree.create(pre, middle), "Result : \n\t", "");
        });
    }

}
