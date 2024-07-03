package com.example.demo1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
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
    static int typeCoin=0;
    static int typeShowDiagram=0;
    static String targetDate = "2024-06-14";
    static String targetTime = "12:12:35";


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Line Chart Example");
//        LineChartExample.diagramShow();
        // محورهای x و y را ایجاد کنید
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");

        // تنظیم واحدهای محور برای افزایش وضوح
        xAxis.setTickUnit(1);
        yAxis.setTickUnit(0.1);

        // نمودار خطی را ایجاد کنید
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Example Line Chart");

        // داده‌ها را اضافه کنید
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("My Data");

        SimpleRegression regressionUSD = new SimpleRegression();
        SimpleRegression regressionEUR = new SimpleRegression();
        SimpleRegression regressionTOMAN = new SimpleRegression();
        SimpleRegression regressionYEN = new SimpleRegression();
        SimpleRegression regressionGBP = new SimpleRegression();

        String dbUrl = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";


        try (Connection conn2 = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM prices WHERE (DATE > ?) OR (DATE = ? AND TIMA >= ?) ORDER BY DATE, TIMA";

            try (PreparedStatement preparedStatementYOU = conn2.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, targetDate);
                preparedStatementYOU.setString(2, targetDate);
                preparedStatementYOU.setString(3, targetTime);

                ResultSet rs1 = preparedStatementYOU.executeQuery();
                long milliseconds=0;
                int count = 0;
                CombinedTask.LinearRegressionExample linearRegressionExample = new CombinedTask.LinearRegressionExample();

                while (rs1.next()) {
                    String column1 = rs1.getString("TIMA");
                    String column2 = rs1.getString("DATE");
                    double columnUSD = rs1.getDouble("USD");
                    double columnEUR = rs1.getDouble("EUR");
                    double columnTOMAN = rs1.getDouble("TOMAN");
                    double columnYEN = rs1.getDouble("YEN");
                    double columnGBP = rs1.getDouble("GBP");
                    if (typeCoin==1) {
                        System.out.println(typeShowDiagram+"-------------------------1");
                        if (columnUSD != 0) {
                            count++;
                            String date = column2;
                            String time = column1;
                            milliseconds = linearRegressionExample.miliSec(date, time);
                            System.out.println("Milliseconds: " + milliseconds);
                            System.out.println("USD: " + columnUSD);
                            regressionUSD.addData(count, columnUSD);
                            series.getData().add(new XYChart.Data<>(count * 100, columnUSD));
                        }
                    }
                    if (typeCoin==2) {
                        System.out.println(typeShowDiagram+"-------------------------2");
                        if(columnEUR!=0) {
                            count++;
                            String date = column2;
                            String time = column1;
                            milliseconds = linearRegressionExample.miliSec(date, time);
                            System.out.println("Milliseconds: " + milliseconds);
                            System.out.println("EUR: " + columnEUR);
                            regressionEUR.addData(milliseconds, columnEUR);
                            series.getData().add(new XYChart.Data<>(count * 100, columnEUR));

                        }
                    }
                    if (typeCoin==3) {
                        System.out.println(typeShowDiagram+"-------------------------3");
                        if (columnTOMAN != 0) {
                            count++;
                            String date = column2;
                            String time = column1;
                            milliseconds = linearRegressionExample.miliSec(date, time);
                            System.out.println("Milliseconds: " + milliseconds);
                            System.out.println("TOMAN: " + columnTOMAN);
                            regressionTOMAN.addData(milliseconds, columnTOMAN);
                            series.getData().add(new XYChart.Data<>(count * 100, columnTOMAN));
                        }
                    }
                    if (typeCoin==4) {    System.out.println(typeShowDiagram+"-------------------------4");
                        if (columnYEN != 0) {
                            count++;
                            String date = column2;
                            String time = column1;
                            milliseconds = linearRegressionExample.miliSec(date, time);
                            System.out.println("Milliseconds: " + milliseconds);
                            System.out.println("YEN: " + columnYEN);
                            regressionYEN.addData(milliseconds, columnYEN);
                            series.getData().add(new XYChart.Data<>(count * 100, columnYEN));
                        }
                    }if (typeCoin==5) {    System.out.println(typeShowDiagram+"-------------------------5");
                        if (columnGBP != 0) {
                            count++;
                            String date = column2;
                            String time = column1;
                            milliseconds = linearRegressionExample.miliSec(date, time);
                            System.out.println("Milliseconds: " + milliseconds);
                            System.out.println("GBP: " + columnGBP);
                            regressionGBP.addData(milliseconds, columnGBP);
                            series.getData().add(new XYChart.Data<>(count * 100, columnGBP));
                        }
                    }                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lineChart.getData().add(series);

        // اضافه کردن قابلیت بزرگ‌نمایی (zooming)
        addZooming(lineChart, xAxis, yAxis);

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

    private void addZooming(LineChart<Number, Number> lineChart, NumberAxis xAxis, NumberAxis yAxis) {
        lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 0.1;
                if (event.getDeltaY() > 0) {
                    xAxis.setLowerBound(xAxis.getLowerBound() + zoomFactor);
                    xAxis.setUpperBound(xAxis.getUpperBound() - zoomFactor);
                    yAxis.setLowerBound(yAxis.getLowerBound() + zoomFactor);
                    yAxis.setUpperBound(yAxis.getUpperBound() - zoomFactor);
                } else {
                    xAxis.setLowerBound(xAxis.getLowerBound() - zoomFactor);
                    xAxis.setUpperBound(xAxis.getUpperBound() + zoomFactor);
                    yAxis.setLowerBound(yAxis.getLowerBound() - zoomFactor);
                    yAxis.setUpperBound(yAxis.getUpperBound() + zoomFactor);
                }
            }
        });
    }



    public static void diagramShow(){

//        CalendarTime calweek=new CalendarTime();
//        System.out.println(calweek.formatDate(calweek.now()) +"wwwwwwwwwwwwwwwwwwwwwwwwwww");
//        System.out.println(calweek.formatTime35(calweek.now()) +"ttttttttttttttttttttttttttttttttttw");
//        long iii=calweek.now();
//
//        long g=iii-1576000000L;
//        long milliweek=g-(7*24*60*60*1000);
//        long milliday=g-(24*60*60*1000L);
//        long millimonth=g-(30*24*60*60*1000L);
//        long millihour=g-(60*60*1000L);
//        String timeweek=calweek.formatDate(milliweek);
//        String dateweek=calweek.formatTime35(milliweek);
//
//        String timeday=calweek.formatDate(milliday);
//        String dateday=calweek.formatTime35(milliday);
//
//        String timemonth=calweek.formatDate(millimonth);
//        String datemonth=calweek.formatTime35(millimonth);
//
//        String timehour=calweek.formatDate(millihour);
//        String datehour=calweek.formatTime35(millihour);
//
//        String timeyear=calweek.formatDate(calweek.now());
//        String dateyear=calweek.formatTime35(calweek.now());
//
        CalendarTime Now=new CalendarTime();
        long milliNow=Now.now();
        String dateNow=Now.formatDate(milliNow);
        String timeNow=Now.formatTime35(milliNow);


        if(typeShowDiagram==0){
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        }
        if(typeShowDiagram==5){
            targetTime="12:12:35";
            targetDate="2024-05-06";
        }
        if(typeShowDiagram==4){
            targetTime="12:12:35";
            targetDate="2024-05-06";

        }
        if(typeShowDiagram==3){
            targetTime=Now.formatTime35(milliNow-7*24*60*60*1000L);
            targetDate=Now.formatDate(milliNow-7*24*60*60*1000L);

        }
        if(typeShowDiagram==2){
            targetTime=Now.formatTime35(milliNow-24*60*60*1000L);
            targetDate=Now.formatDate(milliNow-24*60*60*1000L);
        }
        if(typeShowDiagram==1){
            targetTime=Now.formatTime35(milliNow-60*60*1000L);
            targetDate=Now.formatDate(milliNow-60*60*1000L);
        }
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
