package com.yl.learn.interview.jdbc;


import com.yl.learn.common.util.PrintUtil;

import java.sql.Driver;

public final class DriverHolder {

    public static final String mysqlDriver = "com.mysql.cj.jdbc.Driver";
    private static Driver jdbcDriver;

    static {
        try {
            Class driverClass = Class.forName(mysqlDriver);
            jdbcDriver = (Driver) driverClass.newInstance();
        }
        catch (Exception e) {
            PrintUtil.printlnLine();
            e.printStackTrace();
            PrintUtil.printlnLine();
        }
    }

    public static Driver getJdbcDriver() {
        return jdbcDriver;
    }

}
