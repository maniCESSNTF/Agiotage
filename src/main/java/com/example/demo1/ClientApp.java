package com.example.demo1;

import com.example.demo1.LogeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // استفاده از مسیر صحیح FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogeIn.fxml"));
            Parent root = loader.load();

            LogeIn controller = loader.getController();
            Client client = new Client("127.0.0.1", 11111); // اتصال به سرور
            controller.setClient(client, null);

            Scene scene = new Scene(root, 794, 637);
            primaryStage.setTitle("Agiotage Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // چاپ استک‌ترس برای اطلاعات بیشتر از خطا
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
