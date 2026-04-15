package com.expensetracking.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(ConfigReader.get("db.url"));
        ds.setUsername(ConfigReader.get("db.user"));
        ds.setPassword(ConfigReader.get("db.password"));

        ds.setInitialSize(Integer.parseInt(ConfigReader.get("db.initialSize")));
        ds.setMaxTotal(Integer.parseInt(ConfigReader.get("db.maxTotal")));
    }

    public static DataSource getDataSource() {
        return ds;
    }
}