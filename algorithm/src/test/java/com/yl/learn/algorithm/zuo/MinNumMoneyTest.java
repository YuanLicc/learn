package com.yl.learn.algorithm.zuo;

import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 商店补钱，输入纸币面额：{10, 5, 2, 1}，目标补钱额度：20
 * 输出补钱张数最少的数目，如上面栗子中，仅需两张 10 元即可满足 20 额度，即返回 2。
 */
public class MinNumMoneyTest {

    int[] moneys = {2, 1, 4, 10};

    int aim = 10;

    @Test
    public void test() {
        PrintUtil.template("Min num of money, the aim is:" + aim, () -> {

            PrintUtil.println(Arrays.toString(moneys));

            PrintUtil.println(MinNumMoney.moneyNum(moneys, aim));
        });
    }


    int[] moneys1 = {5, 10, 25, 1};

    int aim1 = 15;

    @Test
    public void testAllNum() {

        PrintUtil.template("All types num of money, the aim is:" + aim1, () -> {

            PrintUtil.println(Arrays.toString(moneys1));

            PrintUtil.println(NumOfCoins.coins(moneys1, aim1));
        });
    }

}
