package com.example.demo1;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.Random;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;

public class Profile {
    Random random = new Random();
    public String newCaptcha = GenerateCaptcha();
    public EmailSender emailSender = new EmailSender();
    public  String code;


    public static String thisUsername;

    @FXML
    private Button btnDoneDeposit;

    @FXML
    private Button btnRefreshCaptchaDeposit;

    @FXML
    private Button btnSendEmailDeposit;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnDone;
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
    private  TextField txtNewName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewPhoneNumber;

    @FXML
    private Text btncurrentAsset;

    @FXML
    private TextField txtCVV2Deposit;

    @FXML
    private TextField txtCaptchaCodeDeposit;

    @FXML
    private TextField txtCardNumberDeposit;

    @FXML
    private TextField txtEXMonthDeposit;

    @FXML
    private TextField txtEXYearDeposit;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtLastName;

    @FXML
    public  Text txtName ;

    @FXML
    private Text txtPassword;

    @FXML
    private Text txtPhoneNumber;

    @FXML
    private TextField txtEmailDeposit;

    @FXML
    private Text txtUsername;

    @FXML
    private TextField txtPasswordDeposit;


    @FXML
    void PbtnDone(ActionEvent event) throws IOException {
        if (txtAmount.getText().isEmpty() || txtAccountNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();

//            LogeIn.str="d";

        }
        // Check if wallet has enough
    }

    @FXML
    void PbtnChange(ActionEvent event) throws IOException, SQLException {
        if (txtNewEmail.getText().isEmpty() && txtNewLastName.getText().isEmpty() && txtNewPassword.getText().isEmpty() && txtNewName.getText().isEmpty() && txtNewPhoneNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Change at least one of them!");
            alert.showAndWait();
            txtUsername.setText(thisUsername);
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, thisUsername);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        txtName.setText(resultSet.getString("firstname"));
                        txtLastName.setText(resultSet.getString("lastname"));
                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
                        txtEmail.setText(resultSet.getString("email"));
                        txtPassword.setText(resultSet.getString("password"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (!txtNewName.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET firstname = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewName.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your name to: " + txtNewName.getText());
                alert.showAndWait();
            }
            if (!txtNewLastName.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET lastname = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewLastName.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Last Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your last name to: " + txtNewLastName.getText());
                alert.showAndWait();
            }
            if (!txtNewPassword.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET password = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewPassword.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your password to: " + txtNewPassword.getText());
                alert.showAndWait();
            }
            if (!txtNewPhoneNumber.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET phonenumber = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewPhoneNumber.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Phone Number Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
                alert.showAndWait();
            }
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, thisUsername);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        txtName.setText(resultSet.getString("firstname"));
                        txtLastName.setText(resultSet.getString("lastname"));
                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
                        txtEmail.setText(resultSet.getString("email"));
                        txtPassword.setText(resultSet.getString("password"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    void PbtnDoneDeposit(ActionEvent event) throws IOException, SQLException {
       // setUser("ssss");
        if(txtPasswordDeposit.getText().equals(code) && newCaptcha.equals(txtCaptchaCodeDeposit.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("The transaction was completed successfully");
            alert.showAndWait();
        }else{
            if(!newCaptcha.equals(txtCaptchaCodeDeposit.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Captcha code is wrong");
                alert.showAndWait();
                ActionEvent ee = new ActionEvent();
                PbtnRefreshCaptchaDeposit(ee);
                txtCaptchaCodeDeposit.setText("");
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Pass is wrong");
                alert.showAndWait();
                ActionEvent ee = new ActionEvent();
                PbtnRefreshCaptchaDeposit(ee);
                txtCaptchaCodeDeposit.setText("");
                txtPasswordDeposit.setText("");
            }
        }
    }
    @FXML
    public  ImageView imgCaptcha2;
    @FXML
    public void PbtnRefreshCaptchaDeposit(ActionEvent event) throws IOException {
        newCaptcha = GenerateCaptcha();
        captcha(newCaptcha);
        File file = new File("C:\\Users\\mania\\Desktop\\New folder (3)\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
        InputStream inputStream = null;
        try{
            inputStream = (InputStream) new FileInputStream(file);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        imgCaptcha2.setImage(new Image(inputStream));
    }

    @FXML
    void PbtnSendEmailDeposit(ActionEvent event) {
        System.out.println(txtEmailDeposit.getText());
        code = String.valueOf((random.nextInt(1000000))+1000000);
        emailSender.send(txtEmailDeposit.getText(),code);
    }


    public void setUser(String username) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql = "SELECT * FROM signin WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("firstname"));
//                    txtName.setText(resultSet.getString("firstname"));
                    txtName.setText("firstname");

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("################!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}