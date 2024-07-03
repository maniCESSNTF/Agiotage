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
//        captcha(GenerateCaptcha());

//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//            String query = "INSERT INTO signin (firstname, lastname, email, phonenumber,password,username,WalletId,demo,money,rippel,avalanche,day,lightcoin,stellar) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, "jjjjj");
//                preparedStatement.setString(2, "jjjjj");
//                preparedStatement.setString(3, "jjjjj.abdolalizade@gmail.com");
//                preparedStatement.setString(4, "09917289890");
//                preparedStatement.setString(5, "123456Jj");
//                preparedStatement.setString(6, "123456Jj");
//                preparedStatement.setString(7, "123456Jj");
//                preparedStatement.setInt(8, 1);
//                preparedStatement.setDouble(9, 20000);
//                preparedStatement.setDouble(10, 20000);
//                preparedStatement.setDouble(11, 20000);
//                preparedStatement.setDouble(12, 20000);
//                preparedStatement.setDouble(13, 20000);
//                preparedStatement.setDouble(14, 20000);
//                preparedStatement.executeUpdate();

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("LogeIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 794, 637);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
            }

   // }
        public static void main (String[]args){
            launch();

        }

//}
}