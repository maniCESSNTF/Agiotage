package com.example.demo1;

import com.mysql.cj.xdevapi.JsonParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketOption;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        captcha(GenerateCaptcha());
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("LogeIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 794, 637);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        
    }
}