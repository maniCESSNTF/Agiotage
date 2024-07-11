package com.example.demo1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day {
    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }



    @FXML
    private Button btnrippleDiaH;

    @FXML
    private Button btnrippleDiaD;

    @FXML
    private Button btnrippleDiaW;

    @FXML
    private Button btnrippleDiaM;

    @FXML
    private Button btnrippleDiaY;

    @FXML
    private Text txtNowPrice;

    @FXML
    private Text txtHajm;

    @FXML
    private Text txtPercentage;

    @FXML
    private Text txtOK101;

    @FXML
    private Text txtOK102;

    @FXML
    private Text txtOK103;

    @FXML
    private Text txtOK11;

    @FXML
    private Text txtOK12;

    @FXML
    private Text txtOK13;

    @FXML
    private Text txtOK21;

    @FXML
    private Text txtOK22;

    @FXML
    private Text txtOK23;

    @FXML
    private Text txtOK31;

    @FXML
    private Text txtOK32;

    @FXML
    private Text txtOK33;

    @FXML
    private Text txtOK41;

    @FXML
    private Text txtOK42;

    @FXML
    private Text txtOK43;

    @FXML
    private Text txtOK51;

    @FXML
    private Text txtOK52;

    @FXML
    private Text txtOK53;

    @FXML
    private Text txtOK61;

    @FXML
    private Text txtOK62;

    @FXML
    private Text txtOK63;

    @FXML
    private Text txtOK71;

    @FXML
    private Text txtOK72;

    @FXML
    private Text txtOK73;

    @FXML
    private Text txtOK81;

    @FXML
    private Text txtOK82;

    @FXML
    private Text txtOK83;

    @FXML
    private Text txtOK91;

    @FXML
    private Text txtOK92;

    @FXML
    private Text txtOK93;


    @FXML
    private Button btnHome;

    @FXML
    private Button btnset;

    @FXML
    private Text txt101;

    @FXML
    private Text txt102;

    @FXML
    private Text txt103;

    @FXML
    private Text txt11;

    @FXML
    private Text txt12;

    @FXML
    private Text txt13;

    @FXML
    private Text txt21;

    @FXML
    private Text txt22;

    @FXML
    private Text txt23;

    @FXML
    private Text txt31;

    @FXML
    private Text txt32;

    @FXML
    private Text txt33;

    @FXML
    private Text txt41;

    @FXML
    private Text txt42;

    @FXML
    private Text txt43;

    @FXML
    private Text txt51;

    @FXML
    private Text txt52;

    @FXML
    private Text txt53;

    @FXML
    private Text txt61;

    @FXML
    private Text txt62;

    @FXML
    private Text txt63;

    @FXML
    private Text txt71;

    @FXML
    private Text txt72;

    @FXML
    private Text txt73;

    @FXML
    private Text txt81;

    @FXML
    private Text txt82;

    @FXML
    private Text txt83;

    @FXML
    private Text txt91;

    @FXML
    private Text txt92;

    @FXML
    private Text txt93;

    @FXML
    private Button btnDayDia;

    private Timeline timeline;



    @FXML
    void PbtnrippleDiaH(ActionEvent event) throws IOException {
        LineChartExample.typeShowDiagram=1;
        LineChartExample.diagramShow();
        LineChartExample line = new LineChartExample(thisUsername,client);
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }

    @FXML
    void PbtnrippleDiaD(ActionEvent event) throws IOException {
        LineChartExample.typeShowDiagram=2;
        LineChartExample.diagramShow();
        LineChartExample line = new LineChartExample(thisUsername,client);
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }

    @FXML
    void PbtnrippleDiaW(ActionEvent event) throws IOException {
        LineChartExample.typeShowDiagram=3;
        LineChartExample.diagramShow();
        LineChartExample line = new LineChartExample(thisUsername,client);
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }

    @FXML
    void PbtnrippleDiaM(ActionEvent event) throws IOException {
        LineChartExample.typeShowDiagram=4;
        LineChartExample line = new LineChartExample(thisUsername,client);
        line.diagramShow();
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }

    @FXML
    void PbtnrippleDiaY(ActionEvent event) throws IOException {
        LineChartExample.typeShowDiagram=5;
        LineChartExample line = new LineChartExample(thisUsername,client);
        line.diagramShow();
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }
    @FXML
    void initialize() {
        updateTime(); // برای به روزرسانی اولیه زمان
        // ایجاد تایم لاین برای به روزرسانی زمان هر 1 ثانیه
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE); // تنظیم برای اجرا به صورت نامحدود
        timeline.play(); // شروع تایم لاین
    }

    private void updateTime() {
        String[][] changesDuring = new String[10][3];
        String[][] changesAccepted = new String[10][3];

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "");
            String initialEmail = "2024-06-12";

            String sql = "SELECT * FROM exchange WHERE date >= ? ORDER BY date";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, initialEmail);
                ResultSet rs = preparedStatementYOU.executeQuery();
                int iDuring = 0;
                int iAccepted = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("day")) {
                        if (rs.getInt("state") == 0 && iDuring < 10) {
                            changesDuring[iDuring][0] = rs.getString("copyAmount");
                            changesDuring[iDuring][1] = rs.getString("price");
                            if (rs.getString("sellbuy").equals("0")) {
                                changesDuring[iDuring][2] = "sell";
                            } else {
                                changesDuring[iDuring][2] = "buy";
                            }
                            iDuring++;
                        } else if (iAccepted < 10) {
                            changesAccepted[iAccepted][0] = rs.getString("copyAmount");
                            changesAccepted[iAccepted][1] = rs.getString("price");
                            changesAccepted[iAccepted][2] = rs.getString("date");
                            iAccepted++;
                        }
                    }
                }
                rs.close();
            }
            conn.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        double yen24 = 0;
        double yen = 0;
        boolean sw24 = true;
        CalendarTime calendarTime = new CalendarTime();
        long milicec = calendarTime.now() - 24*60*60*1000L;
        String agotime=calendarTime.formatTime35(milicec);
        String agodate=calendarTime.formatDate(milicec);

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String initialEmail =agodate;

            // همه ردیف‌هایی که ایمیل آن‌ها بزرگتر یا مساوی با ایمیل مورد نظر است را بخوانید
            String sql = "SELECT * FROM prices WHERE DATE >= ? ORDER BY DATE";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, initialEmail);
                ResultSet rs = preparedStatementYOU.executeQuery();
                while (rs.next()) {
                    if (sw24) {
                        yen24 = rs.getDouble("YEN");
                        sw24 = false;
                    }
                    yen = rs.getDouble("YEN");
                }
                rs.close();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DecimalFormat df = new DecimalFormat("#.##");

        txtNowPrice.setText(String.valueOf(Double.valueOf(df.format(yen))));
        txtPercentage.setText(String.valueOf(Double.valueOf(df.format(((yen - yen24) / yen) * 100))));
//        txtVolume-----------------------------------------------------حجم معاملات

        txt11.setText(changesDuring[0][0]);
        txt12.setText(changesDuring[0][1]);
        txt13.setText(changesDuring[0][2]);

        txt21.setText(changesDuring[1][0]);
        txt22.setText(changesDuring[1][1]);
        txt23.setText(changesDuring[1][2]);

        txt31.setText(changesDuring[2][0]);
        txt32.setText(changesDuring[2][1]);
        txt33.setText(changesDuring[2][2]);

        txt41.setText(changesDuring[3][0]);
        txt42.setText(changesDuring[3][1]);
        txt43.setText(changesDuring[3][2]);

        txt51.setText(changesDuring[4][0]);
        txt52.setText(changesDuring[4][1]);
        txt53.setText(changesDuring[4][2]);

        txt61.setText(changesDuring[5][0]);
        txt62.setText(changesDuring[5][1]);
        txt63.setText(changesDuring[5][2]);

        txt71.setText(changesDuring[6][0]);
        txt72.setText(changesDuring[6][1]);
        txt73.setText(changesDuring[6][2]);

        txt81.setText(changesDuring[7][0]);
        txt82.setText(changesDuring[7][1]);
        txt83.setText(changesDuring[7][2]);

        txt91.setText(changesDuring[8][0]);
        txt92.setText(changesDuring[8][1]);
        txt93.setText(changesDuring[8][2]);


        txtOK11.setText(changesAccepted[0][0]);
        txtOK12.setText(changesAccepted[0][1]);
        txtOK13.setText(changesAccepted[0][2]);

        txtOK21.setText(changesAccepted[1][0]);
        txtOK22.setText(changesAccepted[1][1]);
        txtOK23.setText(changesAccepted[1][2]);

        txtOK31.setText(changesAccepted[2][0]);
        txtOK32.setText(changesAccepted[2][1]);
        txtOK33.setText(changesAccepted[2][2]);

        txtOK41.setText(changesAccepted[3][0]);
        txtOK42.setText(changesAccepted[3][1]);
        txtOK43.setText(changesAccepted[3][2]);

        txtOK51.setText(changesAccepted[4][0]);
        txtOK52.setText(changesAccepted[4][1]);
        txtOK53.setText(changesAccepted[4][2]);

        txtOK61.setText(changesAccepted[5][0]);
        txtOK62.setText(changesAccepted[5][1]);
        txtOK63.setText(changesAccepted[5][2]);

        txtOK71.setText(changesAccepted[6][0]);
        txtOK72.setText(changesAccepted[6][1]);
        txtOK73.setText(changesAccepted[6][2]);

        txtOK81.setText(changesAccepted[7][0]);
        txtOK82.setText(changesAccepted[7][1]);
        txtOK83.setText(changesAccepted[7][2]);

        txtOK91.setText(changesAccepted[8][0]);
        txtOK92.setText(changesAccepted[8][1]);
        txtOK93.setText(changesAccepted[8][2]);


    }

    @FXML
    void PbtnHome(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent mainView = loader.load();

            HomePage homePage = loader.getController();
            homePage.setClient(client, thisUsername);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnHome.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void PbtnDayDia(ActionEvent event) throws IOException {
        LineChartExample line = new LineChartExample(thisUsername,client);
        Stage stage = (Stage) btnHome.getScene().getWindow();
        line.start(stage);
    }

    @FXML
    void Pbtnset(ActionEvent event) throws IOException, SQLException {
        String[][] changesDuring = new String[10][3];
        String[][] changesAccepted = new String[10][3];

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "");
            String initialEmail = "2024-06-12";

            String sql = "SELECT * FROM exchange WHERE date >= ? ORDER BY date";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, initialEmail);
                ResultSet rs = preparedStatementYOU.executeQuery();
                int iDuring = 0;
                int iAccepted = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("day")) {
                        if (rs.getInt("state") == 0 && iDuring < 10) {
                            changesDuring[iDuring][0] = rs.getString("copyAmount");
                            changesDuring[iDuring][1] = rs.getString("price");
                            if (rs.getString("sellbuy").equals("0")) {
                                changesDuring[iDuring][2] = "sell";
                            } else {
                                changesDuring[iDuring][2] = "buy";
                            }
                            iDuring++;
                        } else if (iAccepted < 10) {
                            changesAccepted[iAccepted][0] = rs.getString("copyAmount");
                            changesAccepted[iAccepted][1] = rs.getString("price");
                            changesAccepted[iAccepted][2] = rs.getString("date");
                            iAccepted++;
                        }
                    }
                }
                rs.close();
            }
            conn.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        double yen24 = 0;
        double yen = 0;
        boolean sw24 = true;

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String initialEmail = "2024-06-12";

            // همه ردیف‌هایی که ایمیل آن‌ها بزرگتر یا مساوی با ایمیل مورد نظر است را بخوانید
            String sql = "SELECT * FROM prices WHERE DATE >= ? ORDER BY DATE";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, initialEmail);
                ResultSet rs = preparedStatementYOU.executeQuery();
                while (rs.next()) {
                    if (sw24) {
                        yen24 = rs.getDouble("YEN");
                        sw24 = false;
                    }
                    yen = rs.getDouble("YEN");
                }
                rs.close();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DecimalFormat df = new DecimalFormat("#.##");

        txtNowPrice.setText(String.valueOf(Double.valueOf(df.format(yen))));
        txtPercentage.setText(String.valueOf(Double.valueOf(df.format(((yen - yen24) / yen) * 100))));
//        txtVolume-----------------------------------------------------حجم معاملات

        txt11.setText(changesDuring[0][0]);
        txt12.setText(changesDuring[0][1]);
        txt13.setText(changesDuring[0][2]);

        txt21.setText(changesDuring[1][0]);
        txt22.setText(changesDuring[1][1]);
        txt23.setText(changesDuring[1][2]);

        txt31.setText(changesDuring[2][0]);
        txt32.setText(changesDuring[2][1]);
        txt33.setText(changesDuring[2][2]);

        txt41.setText(changesDuring[3][0]);
        txt42.setText(changesDuring[3][1]);
        txt43.setText(changesDuring[3][2]);

        txt51.setText(changesDuring[4][0]);
        txt52.setText(changesDuring[4][1]);
        txt53.setText(changesDuring[4][2]);

        txt61.setText(changesDuring[5][0]);
        txt62.setText(changesDuring[5][1]);
        txt63.setText(changesDuring[5][2]);

        txt71.setText(changesDuring[6][0]);
        txt72.setText(changesDuring[6][1]);
        txt73.setText(changesDuring[6][2]);

        txt81.setText(changesDuring[7][0]);
        txt82.setText(changesDuring[7][1]);
        txt83.setText(changesDuring[7][2]);

        txt91.setText(changesDuring[8][0]);
        txt92.setText(changesDuring[8][1]);
        txt93.setText(changesDuring[8][2]);


        txtOK11.setText(changesAccepted[0][0]);
        txtOK12.setText(changesAccepted[0][1]);
        txtOK13.setText(changesAccepted[0][2]);

        txtOK21.setText(changesAccepted[1][0]);
        txtOK22.setText(changesAccepted[1][1]);
        txtOK23.setText(changesAccepted[1][2]);

        txtOK31.setText(changesAccepted[2][0]);
        txtOK32.setText(changesAccepted[2][1]);
        txtOK33.setText(changesAccepted[2][2]);

        txtOK41.setText(changesAccepted[3][0]);
        txtOK42.setText(changesAccepted[3][1]);
        txtOK43.setText(changesAccepted[3][2]);

        txtOK51.setText(changesAccepted[4][0]);
        txtOK52.setText(changesAccepted[4][1]);
        txtOK53.setText(changesAccepted[4][2]);

        txtOK61.setText(changesAccepted[5][0]);
        txtOK62.setText(changesAccepted[5][1]);
        txtOK63.setText(changesAccepted[5][2]);

        txtOK71.setText(changesAccepted[6][0]);
        txtOK72.setText(changesAccepted[6][1]);
        txtOK73.setText(changesAccepted[6][2]);

        txtOK81.setText(changesAccepted[7][0]);
        txtOK82.setText(changesAccepted[7][1]);
        txtOK83.setText(changesAccepted[7][2]);

        txtOK91.setText(changesAccepted[8][0]);
        txtOK92.setText(changesAccepted[8][1]);
        txtOK93.setText(changesAccepted[8][2]);

        int copyAmount=0;
        int doneAmount=0;

        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql1 = "SELECT * FROM exchange WHERE type = ? ";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql1)) {
                statement1.setString(1, "avalanche");
                ResultSet resultSet1 = statement1.executeQuery();
                while (resultSet1.next()) {
                    doneAmount += resultSet1.getInt("amount");
                    copyAmount += resultSet1.getInt("copyAmount");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }

        }
            double amountbuy = 0;

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql11 = "SELECT * FROM swap WHERE typebuy = ? ";
                try (PreparedStatement statement1 = connection.prepareStatement(sql11)) {
                    statement1.setString(1, "avalanche");
                    ResultSet resultSet1 = statement1.executeQuery();
                    while (resultSet1.next()) {
                        amountbuy += resultSet1.getDouble("amountbuy");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);

                }
                copyAmount -= doneAmount;
                txtHajm.setText(String.valueOf(copyAmount));
                txtHajm.setText(String.valueOf(copyAmount + amountbuy));
            }
        }


}