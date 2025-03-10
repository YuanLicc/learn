package com.yl.learn.util.util;

public final class ObjectUtil {

    public static final String NULL = "NULL";

    public static String nullToString(Object o) {
        return o == null ? NULL : o.toString();
    }

}
