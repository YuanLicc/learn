package com.zh.learn.interview.map;

import com.yl.learn.common.util.PrintUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hong on 2018/7/26.
 * 对map的test
 */
public class MapTest {

    /**
     * 测试方法
     */
    @Test
    public void hashMapTest() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("first", "this is the first word");
        map.put("second", "this is the second");

        PrintUtil.println(map.get("first"));
    }

    /**
     * hashmap为空测试
     */
    @Test
    public void hashMapNullTest() {
        Map<String, String> map = new HashMap<>();
        String value = map.put(null, "This is a null value");
        String value2 = map.put("value2Key", "value2Value");

        PrintUtil.println("判断成功则出现passed");
        org.junit.Assert.assertTrue(value == null);
    }
}
