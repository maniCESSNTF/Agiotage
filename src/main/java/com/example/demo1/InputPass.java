package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.random.*;

import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;

public class InputPass {

    Random rand = new Random();
    @FXML
    private Button btnDone;

    @FXML
    private Button btnHome;

    @FXML
    private TextField txtCapcha;

    @FXML
    private TextField txtPassword1;

    @FXML
    private TextField txtPassword2;

    @FXML
    void PbtnDone(ActionEvent event) throws IOException {
        if(txtPassword1.getText().equals(txtPassword2.getText())){
            if(isPasswordValid(txtPassword1.getText())){
                users[UserNumber-1].setPassword(txtPassword1.getText());
                users[UserNumber-1].setUsername(UsernameGenerator());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("successful");
                alert.setHeaderText(null);
                alert.setContentText("your password is :"+txtPassword1.getText()+"\n"+"your Username is :"+users[UserNumber-1].getUsername());
                alert.showAndWait();
                Stage stage = (Stage) btnDone.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
                Scene scene = new Scene(root, 794, 637);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The password is not accepted!!");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("The passwords do not match!!");
            alert.showAndWait();
        }
    }

    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        boolean SWUppercase  = false;
        boolean SWlowercase  = false;
        boolean SWint = false;

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if ((character >= '0' && character <= '9' ) )
                SWint=true;
            if ((character >= 'A' && character <= 'Z' ))
                SWUppercase=true;
            if ((character >= 'a' && character <= 'z' ))
                SWlowercase=true;
        }
        return (SWint&&SWUppercase&&SWlowercase);
    }

    public String UsernameGenerator(){
        String newUsername = String.valueOf((rand.nextInt(1000)*1000+UserNumber)*1000+ rand.nextInt(1000));
        return newUsername;
    }
}
