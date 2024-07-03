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
import javafx.scene.image.Image;


import javafx.scene.image.ImageView;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;

public class InputPass {
    String newCaptcha = GenerateCaptcha();
    static Random rand = new Random();
    @FXML
    private  Button btnDone;

    //   @FXML

    @FXML
    private AnchorPane passRE;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnRefresh;

    @FXML
    public  ImageView imgCaptcha;
    @FXML
    public void Pbtnrefresh(ActionEvent event) throws IOException {
        newCaptcha = GenerateCaptcha();
        captcha(newCaptcha);
        File file = new File("C:\\Users\\mania\\Desktop\\New folder (3)\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
        InputStream inputStream = null;
        try{
            inputStream = (InputStream) new FileInputStream(file);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        imgCaptcha.setImage(new Image(inputStream));
    }

    @FXML
    private TextField txtCapcha;

    @FXML
    private TextField txtPassword1;

    @FXML
    private TextField txtPassword2;
    public InputPass() throws IOException {
    }

    @FXML
    void PbtnDone(ActionEvent event) throws IOException {
//        imgCaptch=img;
        if(txtPassword1.getText().equals(txtPassword2.getText())){
            if(isPasswordValid(txtPassword1.getText())){
                if(!txtCapcha.getText().equals(newCaptcha)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("enterd captcha is wrong");
                    alert.showAndWait();
                }else{
//                users[UserNumber-1].setPassword(txtPassword1.getText());
//                users[UserNumber-1].setUsername(UsernameGenerator());
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String query = "UPDATE signin SET password = ? WHERE username = ?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                            preparedStatement.setString(1, txtPassword1.getText());
                            preparedStatement.setString(2, SingIn.userName);
                            preparedStatement.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("successful");
                    alert.setHeaderText(null);
                    alert.setContentText("your password is :"+txtPassword1.getText()+"\n"+"your Username is :"+users[UserNumber-1].getUsername());
                    alert.showAndWait();
                    Stage stage = (Stage) btnDone.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
                    Scene scene = new Scene(root, 794, 637);
                    primaryStage.setScene(scene);
                    primaryStage.show();

                }
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

    public static  String UsernameGenerator(){
        String newUsername = String.valueOf((rand.nextInt(1000)*1000+UserNumber)*1000+ rand.nextInt(1000));
        return newUsername;
    }

    public static String GenerateWalletId(){
        Random rand = new Random();
        int length = 10;
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for(int i=0;i<length;i++){
            captcha.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return captcha.toString();
    }
//
//    @FXML
//    void Pbtnrefresh(ActionEvent event) {
//
//    }
}
