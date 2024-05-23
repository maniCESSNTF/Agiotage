package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo1.Methods.users;

public class LogeIn {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnDoNoHaveAnAccont;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Label lblLogeIn;

    @FXML
    void PbtnSignIn(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnSignIn.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("SingIn.fxml"));
        Scene scene = new Scene(root,794,637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnDone(ActionEvent event) throws IOException {
        if( txtUsername.getText().compareTo("")==0|| txtPassword.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }else {
            User nowUser = SearchUser();
            if (nowUser instanceof User) {//برای ادمین باید جدا شود
                Stage stage = (Stage) btnDone.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Profile.fxml"));
                Scene scene = new Scene(root, 794, 637);
                primaryStage.setScene(scene);
                primaryStage.show();
                //ورود به صفحه کاربری
            } else {
                txtPassword.setText("");
                txtUsername.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Password or Username in wrong");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void PbtnHome(ActionEvent event) {

    }


    public User SearchUser(){
        for(User user : users){
            if(user!= null && user.getUsername().equals(txtUsername.getText()) && user.getPassword().equals(txtPassword.getText()))
                return user;
        }

        return null;
    }

    @FXML
    void PbtnForgotPassword(ActionEvent event) {
        //by send email!!...
    }
}
