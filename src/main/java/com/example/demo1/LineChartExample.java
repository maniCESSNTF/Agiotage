package com.example.demo1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

    public LineChartExample(String thisUsername, Client client) {
        this.thisUsername = thisUsername;
        this.client = client;
    }

    static CalendarTime calendarTime = new CalendarTime();
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/agiotage2";
    static final String USER = "root";
    static final String PASS = "";
    static int typeCoin = 0;
    static int typeShowDiagram = 0;
    static String targetDate = "2024-06-05";
    static String targetTime = "12:12:35";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Line Chart Example");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");

        int cunt=0;

        xAxis.setTickUnit(1);
        yAxis.setTickUnit(0.1);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Example Line Chart");

        XYChart.Series<Number, Number> normalSeries = new XYChart.Series<>();
        normalSeries.setName("Normal Data");

        XYChart.Series<Number, Number> sharpSeries = new XYChart.Series<>();
        sharpSeries.setName("Sharp Data");

        SimpleRegression regressionUSD = new SimpleRegression();
        SimpleRegression regressionEUR = new SimpleRegression();
        SimpleRegression regressionTOMAN = new SimpleRegression();
        SimpleRegression regressionYEN = new SimpleRegression();
        SimpleRegression regressionGBP = new SimpleRegression();

        String dbUrl = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";

        int cuunt=0;
        try (Connection conn2 = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM prices WHERE (DATE > ?) OR (DATE = ? AND TIMA >= ?) ORDER BY DATE, TIMA";

            try (PreparedStatement preparedStatementYOU = conn2.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, targetDate);
                preparedStatementYOU.setString(2, targetDate);
                preparedStatementYOU.setString(3, targetTime);

                ResultSet rs1 = preparedStatementYOU.executeQuery();
                long milliseconds = 0;
                String type = "";
                int count = 0;
                int cuntSell=0;
                int cuntBuy=0;
                double Vsell=0;
                int Vbuy=0;
                double previousValue = Double.NaN;
                double threshold = 0.0001; // تعیین آستانه برای تشخیص نقاط تیز
                CombinedTask.LinearRegressionExample linearRegressionExample = new CombinedTask.LinearRegressionExample();

                while (rs1.next() && cunt< 5000) {
                    String column1 = rs1.getString("TIMA");
                    String column2 = rs1.getString("DATE");
                    double columnUSD = rs1.getDouble("USD");
                    double columnEUR = rs1.getDouble("EUR");
                    double columnTOMAN = rs1.getDouble("TOMAN");
                    double columnYEN = rs1.getDouble("YEN");
                    double columnGBP = rs1.getDouble("GBP");
                    double currentValue = 0.0;

                    cunt++;

                    if (typeCoin == 1) {
                        if (columnUSD != 0) {
                            count++;
                            currentValue = columnUSD;
                        }
                        type ="rippel";
                    } else if (typeCoin == 2) {
                        if (columnEUR != 0) {
                            count++;
                            currentValue = columnEUR;
                        }
                        type ="avalanche";
                    } else if (typeCoin == 3) {
                        if (columnTOMAN != 0) {
                            count++;
                            currentValue = columnTOMAN;
                        }
                        type ="lightcoin";
                    } else if (typeCoin == 4) {
                        if (columnYEN != 0) {
                            count++;
                            currentValue = columnYEN;
                        }
                        type ="day";
                    } else if (typeCoin == 5) {
                        if (columnGBP != 0) {
                            count++;
                            currentValue = columnGBP;
                        }
                        type ="stellar";
                    }

                    String url = "jdbc:mysql://localhost:3306/agiotage2";
                    String user = "root";
                    String password = "";

                    String query = "SELECT * FROM exchange WHERE type = ?";

                    try (Connection connection = DriverManager.getConnection(url, user, password);
                         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                        preparedStatement.setString(1,type );

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            while (resultSet.next()) {

                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


//                    if(currentValue){
//
//                    }






                    if (!Double.isNaN(currentValue)) {//
                        cuunt++;
                        if (!Double.isNaN(previousValue) && Math.abs(currentValue - previousValue) > threshold) {
                            sharpSeries.getData().add(new XYChart.Data<>(count, currentValue));
                        } else {
                            normalSeries.getData().add(new XYChart.Data<>(count, currentValue));
                        }
                        previousValue = currentValue;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lineChart.setStyle("-fx-background-color: #f0f0f0;");

        lineChart.getData().addAll(normalSeries, sharpSeries);

        addZooming(lineChart, xAxis, yAxis);

        Button closeButton = new Button("Close Chart");
        closeButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                Parent mainView = loader.load();

                HomePage homePage = loader.getController();
                homePage.setClient(client, thisUsername);

                Scene scene = new Scene(mainView);
                Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox vbox = new VBox(lineChart, closeButton);


        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
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

    public static void diagramShow() {
        CalendarTime Now = new CalendarTime();
        long milliNow = Now.now();
        String dateNow = Now.formatDate(milliNow);
        String timeNow = Now.formatTime35(milliNow);

        if (typeShowDiagram == 0) {
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        }
        if (typeShowDiagram == 5) {
            targetTime = "12:12:35";
            targetDate = "2024-05-06";
        }
        if (typeShowDiagram == 4) {
            targetTime = "12:12:35";
            targetDate = "2024-05-06";

        }
        if (typeShowDiagram == 3) {
            targetTime = Now.formatTime35(milliNow - 7 * 24 * 60 * 60 * 1000L);
            targetDate = Now.formatDate(milliNow - 7 * 24 * 60 * 60 * 1000L);

        }
        if (typeShowDiagram == 2) {
            targetTime = Now.formatTime35(milliNow - 24 * 60 * 60 * 1000L);
            targetDate = Now.formatDate(milliNow - 24 * 60 * 60 * 1000L);
        }
        if (typeShowDiagram == 1) {
            targetTime = Now.formatTime35(milliNow - 60 * 60 * 1000L);
            targetDate = Now.formatDate(milliNow - 60 * 60 * 1000L);
        }
    }

    public static void main(String[] args) {
        launch(args);
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
