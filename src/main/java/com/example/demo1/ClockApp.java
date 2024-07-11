package com.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField timeField = new TextField();
        timeField.setEditable(false); // جلوگیری از ویرایش کاربر

        // فرمت تاریخ و زمان
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // ایجاد تایمر برای به‌روزرسانی زمان هر ثانیه
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    Profile.Clock.setText(now.format(formatter));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // تکرار بی‌نهایت
        timeline.play();

        VBox root = new VBox(timeField);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
