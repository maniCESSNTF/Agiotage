package com.example.demo1;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;

public class CombinedTask {
    static class LinearRegressionExample {


        public long miliSec(String date, String time) {
            // ترکیب تاریخ و زمان با یک فاصله (space) بین آن‌ها
            String datetime = date + " " + time;
            String regex = "(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?";


            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(datetime);

            if (matcher.matches()) {
                int year = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2)) - 1; // ماه‌ها از 0 تا 11 هستند
                int day = Integer.parseInt(matcher.group(3));
                int hour = Integer.parseInt(matcher.group(4));
                int minute = Integer.parseInt(matcher.group(5));
                int second = Integer.parseInt(matcher.group(6));

                // استفاده از Calendar برای تبدیل به میلی‌ثانیه
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute, second);
                return calendar.getTimeInMillis();
            }
            return 1;
        }
    }

    static class ReadPrices extends TimerTask {
        static CalendarTime calendarTime = new CalendarTime();
        // اطلاعات اتصال به پایگاه داده MySQL
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/agiotage2";
        static final String USER = "root";
        static final String PASS = "";

        @Override
        public void run() {
            Connection conn = null;
            Statement stmt = null;
            SimpleRegression regressionUSD = new SimpleRegression();
            SimpleRegression regressionEUR = new SimpleRegression();
            SimpleRegression regressionTOMAN = new SimpleRegression();
            SimpleRegression regressionYEN = new SimpleRegression();
            SimpleRegression regressionGBP = new SimpleRegression();

            long milliseconds = 0;
            try {
                // Register JDBC driver
                Class.forName(JDBC_DRIVER);
                // Open a connection
                System.out.println("Connecting to database...");

                // Execute a query
                System.out.println("Creating statement...");

                // نمایش تاریخ و زمان فعلی
                String currentDate = getCurrentDate();
                String currentTime = getCurrentTime();

                ResultSet rs = null;

                LinearRegressionExample linearRegressionExample = new LinearRegressionExample();
                int count = 0;
                long nowMilli = linearRegressionExample.miliSec(currentDate, currentTime);
                System.out.println(nowMilli);
                System.out.println("Current date: " + currentDate);
                System.out.println("Current time: " + currentTime);

                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql = "SELECT * FROM prices";
                rs = stmt.executeQuery(sql);

                // Extract data from result set
                while (rs.next()) {
                    // Retrieve by column name
                    String column1 = rs.getString("TIMA");
                    String column2 = rs.getString("DATE");
                    double columnUSD = rs.getDouble("USD");
                    double columnEUR = rs.getDouble("EUR");
                    double columnTOMAN = rs.getDouble("TOMAN");
                    double columnYEN = rs.getDouble("YEN");
                    double columnGBP = rs.getDouble("GBP");
                    if (columnUSD != 0) {
                        count++;
                        String date = column2;
                        String time = column1;

                        milliseconds = linearRegressionExample.miliSec(date, time);
                        System.out.println("Milliseconds: " + milliseconds);
                        System.out.println("USD: " + columnUSD);

                        regressionUSD.addData(milliseconds, columnUSD);
                    }

                    if (columnEUR != 0) {
                        count++;
                        String date = column2;
                        String time = column1;

                        milliseconds = linearRegressionExample.miliSec(date, time);
                        System.out.println("Milliseconds: " + milliseconds);
                        System.out.println("EUR: " + columnEUR);

                        regressionEUR.addData(milliseconds, columnEUR);
                    }

                    if (columnTOMAN != 0) {
                        count++;
                        String date = column2;
                        String time = column1;

                        milliseconds = linearRegressionExample.miliSec(date, time);
                        System.out.println("Milliseconds: " + milliseconds);
                        System.out.println("TOMAN: " + columnTOMAN);

                        regressionTOMAN.addData(milliseconds, columnTOMAN);
                    }
                    if (columnYEN != 0) {
                        count++;
                        String date = column2;
                        String time = column1;

                        milliseconds = linearRegressionExample.miliSec(date, time);
                        System.out.println("Milliseconds: " + milliseconds);
                        System.out.println("YEN: " + columnYEN);

                        regressionYEN.addData(milliseconds, columnYEN);
                    }
                    if (columnGBP != 0) {
                        count++;
                        String date = column2;
                        String time = column1;

                        milliseconds = linearRegressionExample.miliSec(date, time);
                        System.out.println("Milliseconds: " + milliseconds);
                        System.out.println("GBP: " + columnGBP);

                        regressionGBP.addData(milliseconds, columnGBP);
                    }
                }
                double predictedUSD = regressionUSD.predict(milliseconds + 60000);
                double predictedEUR = regressionEUR.predict(milliseconds + 60000);
                double predictedTOMAN = regressionTOMAN.predict(milliseconds + 60000);
                double predictedYEN = regressionYEN.predict(milliseconds + 60000);
                double predictedGBP = regressionGBP.predict(milliseconds + 60000);

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "INSERT INTO prices (DATE, TIMA, USD, EUR, TOMAN, YEN, GBP) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, calendarTime.formatDate(milliseconds + 60000));
                        preparedStatement.setString(2, calendarTime.formatTime(milliseconds + 60000));
                        preparedStatement.setDouble(3, predictedUSD);
                        preparedStatement.setDouble(4, predictedEUR);
                        preparedStatement.setDouble(5, predictedTOMAN);
                        preparedStatement.setDouble(6, predictedYEN);
                        preparedStatement.setDouble(7, predictedGBP);
                        preparedStatement.executeUpdate();
                    }
                }

                // Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                // Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                // Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                // Finally block used to close resources
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            System.out.println("Goodbye!");
        }

        public static String getCurrentDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(new Date());
        }

        public static String getCurrentTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(new Date());
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new ReadPrices();

        // برنامه‌ریزی برای اجرای متد هر ۱۵ ثانیه (۱۵۰۰۰ میلی‌ثانیه)
        timer.schedule(task, 0, 1);
    }
}
