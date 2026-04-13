package com.expensetracker.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();

            // ✅ MOST RELIABLE WAY
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            System.out.println("InputStream = " + input);

            if (input == null) {
                throw new RuntimeException(
                        "application.properties NOT found in classpath (target/classes)"
                );
            }

            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB config", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("DB Connection failed", e);
        }
    }
}