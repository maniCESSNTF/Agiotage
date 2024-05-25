package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;

public class SingIn {

    @FXML
    private Button btnPass;



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


    public boolean  validName(String name){
        if (name.length() <= 18 && name.matches("[a-zA-Z]+")){
            return true;
        }
        else{
            return  false;
        }
    }

    public boolean  validLastName(String name){
        if (name.length() <= 18 && name.matches("[a-zA-Z]+")){
            return true;
        }
        else{
            return  false;
        }
    }

    public boolean  validEmail(String email){
        if((email.matches("[a-zA-Z0-9.]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}"))){
            return true;
        }
        else{
            return  false;
        }
    }



    public boolean  validPhoneNumber(String phoneNumber){
        if((phoneNumber.length()==11&&phoneNumber.matches("[0-9]+")&&phoneNumber.startsWith("09"))){
            return true;
        }
        else{
            return  false;
        }
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
    void PbtnPass(ActionEvent event) throws IOException {
        if( txtName.getText().compareTo("")==0|| txtFamilyName.getText().compareTo("")==0 || txtEmail.getText().compareTo("")==0 || txtPhoneNumber.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }else if((validName(txtName.getText()) == false)||(validEmail(txtEmail.getText()) == false)||(validEmail(txtEmail.getText()) == false)||(validPhoneNumber(txtPhoneNumber.getText()) == false) ){
            if (validName(txtName.getText()) == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your Name must be a maximum of 18 letters!");
                alert.showAndWait();

            }

            if (validLastName(txtFamilyName.getText()) == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your FamilyName must be a maximum of 18 letters!");
                alert.showAndWait();

            }
            if (validEmail(txtEmail.getText()) == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your Email must end with @gmail.com!");
                alert.showAndWait();

            }

            if (validPhoneNumber(txtPhoneNumber.getText()) == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your PhoneNumber must be made up from numbers & start with 09!");
                alert.showAndWait();

            }

        } else {

            AddUser(new User(txtName.getText(),txtFamilyName.getText(),txtEmail.getText(),txtPhoneNumber.getText()));
            Stage stage = (Stage) btnPass.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("InputPass.fxml"));
            Scene scene = new Scene(root, 794, 637);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
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

    public static void AddUser(User newUser){
        //همه چک کردن های اطلاعات یابد قبل از ورود به صفحه تنظیم پسوورد صورت بگیرد!!! و در اکسپشن ها به جای ارسال یک رشته یک Alert ارسال شود
        int Search = SearchUser(newUser);
        if(Search==1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("A user has already registered with this email!");
            alert.showAndWait();
        }
        else if(Search==2){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("A user has already registered with this Phone number!");
            alert.showAndWait();
        }
        else if(Search==-1){
            users[UserNumber]=newUser;
            UserNumber++;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Registration was successful!");
            alert.showAndWait();
        }
    }

    public static int SearchUser(User newUser){
        for(User user : users){
            if(user != null && user.getEmail().equals(newUser.getEmail()))
                return 1;
            else if (user != null && user.getPhoneNumber().equals(newUser.getPhoneNumber()))
                return 2;
        }
        return -1;
    }
}
