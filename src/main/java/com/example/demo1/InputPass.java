package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    String captchaCode;

    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

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
        if (client != null) {
            captchaCode = client.sendMessage("PbtnRefreshCaptchaDeposit," + thisUsername);
            System.out.println("Response from server for captchaCode: " + captchaCode);
            File file = new File("C:\\Users\\mania\\Desktop\\Final term project\\Agiotage\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
            InputStream inputStream;
            try {
                inputStream = (InputStream) new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imgCaptcha.setImage(new Image(inputStream));
        } else {
            System.out.println("Client is not initialized");
        }
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
        if(txtPassword1.getText().equals(txtPassword2.getText())){
            if(isPasswordValid(txtPassword1.getText())){
                if(!txtCapcha.getText().equals(captchaCode)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("enterd captcha is wrong");
                    alert.showAndWait();
                }else{
                    if (client != null) {
                        String response = client.sendMessage("setPassword," + thisUsername + "," + txtPassword1.getText());
                        thisUsername =response;
                        System.out.println("Response from server: " + response);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("successful");
                        alert.setHeaderText(null);
                        alert.setContentText("your password is :"+txtPassword1.getText()+"\n"+"your Username is :"+response);
                        alert.showAndWait();
                    } else {
                        System.out.println("Client is not initialized");
                    }
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogeIn.fxml"));
                        Parent mainView = loader.load();

                        LogeIn logeIn = loader.getController();
                        logeIn.setClient(client,thisUsername);

                        Scene scene = new Scene(mainView);
                        Stage primaryStage = (Stage) btnDone.getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The password is not valid!!");
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

}
