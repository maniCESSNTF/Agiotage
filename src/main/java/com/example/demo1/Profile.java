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

                if(txtNewName.getText().equals(txtName.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Name has not changed yet!");
                    alert.setHeaderText(null);
                    alert.setContentText("You entered the same name,Name has not changed yet!");
                    alert.showAndWait();
                }
                // Update name
               else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your name to: " + txtNewName.getText());
                alert.showAndWait();
            }}
            if (!txtNewLastName.getText().isEmpty()) {

                if(txtNewLastName.getText().equals(txtLastName.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Lastname has not changed yet!");
                    alert.setHeaderText(null);
                    alert.setContentText("You entered the same lastname,lastname has not changed yet!");
                    alert.showAndWait();
                }
                // Update name
                else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("LastName Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("You changed your lastname to: " + txtNewLastName.getText());
                    alert.showAndWait();
                }}
            if (!txtNewPassword.getText().isEmpty()) {

                if(txtNewPassword.getText().equals(txtPassword.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password has not changed yet!");
                    alert.setHeaderText(null);
                    alert.setContentText("You entered the same password,password has not changed yet!");
                    alert.showAndWait();
                }
                // Update name
                else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("password Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("You changed your password to: " + txtNewPassword.getText());
                    alert.showAndWait();
                }}
            if (!txtNewPhoneNumber.getText().isEmpty()) {

                if(txtNewPhoneNumber.getText().equals(txtPhoneNumber.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PhoneNumber has not changed yet!");
                    alert.setHeaderText(null);
                    alert.setContentText("You entered the same Phone Number,phone number has not changed yet!");
                    alert.showAndWait();
                }
                // Update name
                else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Phone number Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
                    alert.showAndWait();
                }}


            if (!txtNewEmail.getText().isEmpty()) {

                if(txtNewEmail.getText().equals(txtEmail.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Email has not changed yet!");
                    alert.setHeaderText(null);
                    alert.setContentText("You entered the same email,email has not changed yet!");
                    alert.showAndWait();
                }
                // Update name
                else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Email Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("You changed your email to: " + txtNewPhoneNumber.getText());
                    alert.showAndWait();
                }}
        }
    }
}