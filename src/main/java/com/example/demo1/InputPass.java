package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputPass {

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
    void PbtnDone(ActionEvent event) {
        if(txtPassword1.getText().equals(txtPassword2.getText())){
            //ست کردن پسورد برای اخرین فرد ثبت نام شده
        }
    }

}
