package com.yl.learn.algorithm.bytedance;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class SickSentenceTest extends TestSuper {

    String[] input = {
        "1,10;32,45",
        "78,94;5,16",
        "80,100;200,220;16,32"
    };

    @Test
    public void test() {
        PrintUtil.template("Find the wrong words: ", () -> {
            Arrays.asList(input).stream().forEach( (row) ->
                    PrintUtil.println(row)
            );
            SickSentence.sickSentence(toWrong(input)).stream().forEach((wrong) -> {
                PrintUtil.println(wrong);
            });
        });
    }

    private List<SickSentence.Wrong> toWrong(String[] input) {
        ArrayList<SickSentence.Wrong> list = new ArrayList();

        if(input == null || input.length == 0) {
            return null;
        }

        for (int i = 0; i < input.length; i++) {
            String[] splitRe = input[i].split(";");

            for(int j = 0; j < splitRe.length; j++) {
                String[] splitComma = splitRe[j].split(",");
                if(splitComma.length != 2) {
                    return null;
                }
                list.add(new SickSentence.Wrong(Integer.parseInt(splitComma[0])
                        , Integer.parseInt(splitComma[1])));
            }
        }
        return list;
    }

}
