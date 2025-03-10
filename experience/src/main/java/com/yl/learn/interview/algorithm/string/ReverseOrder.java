package com.yl.learn.interview.algorithm.string;

/**
 * 字符串是否为反序的
 * @author YuanLi
 */
public class ReverseOrder {

    /**
     * 非 buffer.reverse()
     * @param source 源串
     * @return true or false
     */
    public static boolean isReverseOrder(String source) {

        if(source == null) return false;

        int maxIndex = source.length() - 1;

        if(source.length() == 0) return true;

        for(int i = (maxIndex) >> 1; i >= 0; i--) {
            if(source.charAt(i) != source.charAt(maxIndex - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isReverseOrder_buffer(String source) {
        if(source == null) return false;

        if(source.length() == 0) return true;

        StringBuffer re = new StringBuffer(source).reverse();

        if(re.toString().equals(source)) {
            return true;
        }
        return false;
    }

}
