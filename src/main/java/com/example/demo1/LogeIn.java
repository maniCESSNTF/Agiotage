package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogeIn {

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    @FXML
    private Button btnDoNoHaveAnAccont;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSingIn;

    @FXML
    private Label lblLogeIn;

    @FXML
    void PbtnDoNoHaveAnAccont(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnDoNoHaveAnAccont.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("SingIn.fxml"));
        Scene scene = new Scene(root,800,650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnDone(ActionEvent event) {

    }

    @FXML
    void PbtnHome(ActionEvent event) {

    }

    @FXML
    void PbtnSingIn(ActionEvent event) {

    }

}
