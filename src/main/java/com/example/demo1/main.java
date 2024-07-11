package com.example.demo1;

import com.mysql.cj.xdevapi.JsonParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static javafx.application.Application.launch;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        captcha(GenerateCaptcha());


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String query = "INSERT INTO signin (firstname, lastname, email, phonenumber,password,username,WalletId,demo,money,rippel,avalanche,day,lightcoin,stellar,imagePath) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "kkk");
                preparedStatement.setString(2, "kkk");
                preparedStatement.setString(3, "kkk.abdolalizade@gmail.com");
                preparedStatement.setString(4, "09007111890");
                preparedStatement.setString(5, "123456Kk");
                preparedStatement.setString(6, "123456Kk");
                preparedStatement.setString(7, "123456Kk");
                preparedStatement.setInt(8, 0);
                preparedStatement.setDouble(9, 20000);
                preparedStatement.setDouble(10, 20000);
                preparedStatement.setDouble(11, 20000);
                preparedStatement.setDouble(12, 20000);
                preparedStatement.setDouble(13, 20000);
                preparedStatement.setDouble(14, 20000);
                preparedStatement.setString(15, "C:\\Users\\mania\\Desktop\\New folder\\New folder\\6783f52df44118a647d96c8dabaa83c1.jpg");
                preparedStatement.executeUpdate();
            }
        }
//        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("LogeIn.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 794, 637);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }


}