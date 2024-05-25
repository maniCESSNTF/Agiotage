
package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.*;

public class Profile {

    @FXML
    private RadioButton rbtnFromAccountoWallet;

    @FXML
    private RadioButton rbtnFromWallettoAccount;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtAmount;


    @FXML
    private Button btnDone;
    void btnDone(ActiveEvent event){

        if(txtAmount.getText().compareTo("")==0||txtAccountNumber.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }
        /////if wallet enough nabood

    }

}
