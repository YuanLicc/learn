package com.yl.learn.interview.jdbc;

import java.sql.Connection;
import java.util.Properties;

public class JdbcOperation {

    public static final String url = "jdbc:mysql://120.77.171.82:3306/test?userSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    public static final String password = "root";
    public static final String username = "root";

    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", username);
        return DriverHolder.getJdbcDriver().connect(url, new Properties());
    }

}
