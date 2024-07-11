//package com.example.demo1;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import javax.sql.DataSource;
//
//public class DatabaseConfig {
//    private static HikariDataSource dataSource;
//
//    static {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/agiotage2");
//        config.setUsername("root");
//        config.setPassword("");
//        config.setMaximumPoolSize(10); // تعداد اتصالات همزمان
//
//        dataSource = new HikariDataSource(config);
//    }
//
//    public static DataSource getDataSource() {
//        return dataSource;
//    }
//}
