package com.example.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineChartExample extends Application {
    static CalendarTime calendarTime = new CalendarTime();
    // اطلاعات اتصال به پایگاه داده MySQL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/agiotage2";
    static final String USER = "root";
    static final String PASS = "";
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Line Chart Example");

        // محورهای x و y را ایجاد کنید
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");

        // نمودار خطی را ایجاد کنید
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Example Line Chart");

        // داده‌ها را اضافه کنید
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("My Data");
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

            CombinedTask.LinearRegressionExample linearRegressionExample = new CombinedTask.LinearRegressionExample();
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

//
                    regressionUSD.addData(count, columnUSD);
                    series.getData().add(new XYChart.Data<>(count, columnUSD));

//                   regressionUSD.addData(milliseconds, columnUSD);
//
//                   series.getData().add(new XYChart.Data<>(milliseconds-1500000000000L, columnUSD));


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

        lineChart.getData().add(series);
//
//        Scene scene = new Scene(lineChart, 800, 600);
//        scene.getStylesheets().add("chartStyles.css");
//        stage.setScene(scene);
//        stage.show();

        // اضافه کردن دکمه برای بستن پنجره
        Button closeButton = new Button("Close Chart");
        closeButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("RippelPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 794, 637);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // استفاده از یک VBox برای قرار دادن نمودار و دکمه
        VBox vbox = new VBox(lineChart, closeButton);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new java.util.Date());
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}

