package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SingIn {

    @FXML
    private Button btnSetPassword;

    @FXML
    private Button btnDoHaveAnAccont;

    @FXML
    private Button btnHome;

    @FXML
    private RadioButton rbtnClient;

    @FXML
    private RadioButton rbtnManager;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFamilyName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void PbtnSetPassword(MouseEvent event) throws IOException {
        User newUser = new User(txtName.getText(),txtFamilyName.getText(), txtEmail.getText(), txtPhoneNumber.getText());
        Stage stage =(Stage) btnDoHaveAnAccont.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("InputPass.fxml"));
        Scene scene = new Scene(root,800,650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnDoHaveAnAccont(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnDoHaveAnAccont.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
        Scene scene = new Scene(root,800,650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnHome(ActionEvent event) {

    }

    @FXML
    void PrbtnClient(ActionEvent event) {
        if(rbtnManager.isSelected()){
            rbtnManager.setSelected(false);
        }
    }

    @FXML
    void PrbtnManager(ActionEvent event) {
        if(rbtnClient.isSelected()){
            rbtnClient.setSelected(false);

        }
    }

}
