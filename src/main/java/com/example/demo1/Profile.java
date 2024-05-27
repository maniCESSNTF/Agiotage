package com.example.demo1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class Profile {
    boolean  rbtnFromAccountoWalletSW=false;
    boolean rbtnFromWallettoAccountSW=false;


    @FXML
    private RadioButton rbtnFromAccountoWallet;

    @FXML
    private RadioButton rbtnFromWallettoAccount;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtNewEmail;

    @FXML
    private TextField txtNewLastName;

    @FXML
    private TextField txtNewName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewPhoneNumber;

    @FXML
    private Text btncurrentAsset;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtLastName;

    @FXML
    private Text txtName;

    @FXML
    private Text txtPassword;

    @FXML
    private Text txtPhoneNumber;

    @FXML
    private Text txtUsername;

    @FXML
    void rbtnFromAccountoWallet(ActionEvent event) {
        rbtnFromAccountoWalletSW=true;
    }

    @FXML
    void rbtnFromWallettoAccount(ActionEvent event) {
        rbtnFromWallettoAccountSW=true;
    }

    @FXML
    void btnDone(ActionEvent event) throws IOException {
        if (txtAmount.getText().isEmpty() || txtAccountNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }
        if(rbtnFromWallettoAccountSW==rbtnFromAccountoWalletSW){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You can only transfer From Wallet to Account or From Account  to Wallet !");
            alert.showAndWait();
            }

        // Check if wallet has enough
    }


/////////////////////////////////////////////////////////صفحه پروفایل



    @FXML
    void btnChange(ActionEvent event) throws IOException {
        if (txtNewEmail.getText().isEmpty() && txtNewLastName.getText().isEmpty() && txtNewPassword.getText().isEmpty() && txtNewName.getText().isEmpty() && txtNewPhoneNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Change at least one of them!");
            alert.showAndWait();
        } else {
            if (!txtNewName.getText().isEmpty()) {
                // Update name
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your name to: " + txtNewName.getText());
                alert.showAndWait();
            }
            if (!txtNewLastName.getText().isEmpty()) {
                // Update last name
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Last Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your last name to: " + txtNewLastName.getText());
                alert.showAndWait();
            }
            if (!txtNewPassword.getText().isEmpty()) {
                // Update password
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your password to: " + txtNewPassword.getText());
                alert.showAndWait();
            }
            if (!txtNewPhoneNumber.getText().isEmpty()) {
                // Update phone number
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Phone Number Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
                alert.showAndWait();
            }
        }
    }
}