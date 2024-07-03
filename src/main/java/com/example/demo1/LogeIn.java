package com.example.demo1;

import Email.EmailSender;
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

import javax.mail.Address;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;



public class LogeIn {


    public String str;

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

    public static Profile profile1 =null;

    @FXML
    void PbtnSignIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("SingIn.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnDone(ActionEvent event) throws IOException, SQLException {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        } else {
            if( txtUsername.getText().equals("1111111111") && txtPassword.getText().equals("2222222222")){
                Stage stage = (Stage) btnDone.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                Profile.thisUsername = txtUsername.getText();
                AnchorPane root = FXMLLoader.load(getClass().getResource("manager.fxml"));
                Scene scene = new Scene(root, 794, 637);
                primaryStage.setScene(scene);
                primaryStage.show();
            }else {

                User nowUser = SearchUser();
                if (nowUser != null) {

                    Stage stage = (Stage) btnDone.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    usernnameSaver.nowUsername=txtUsername.getText();
                    Profile.thisUsername = usernnameSaver.nowUsername;

                   // Profile.txtUsernameNow.setText(Profile.thisUsername);

                    AnchorPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                    Scene scene = new Scene(root, 794, 637);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } else {
                    txtPassword.setText("");
                    txtUsername.setText("");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Password or Username is wrong");
                    alert.showAndWait();
                }
            }
          //  Profile.setUser(txtUsername.getText());

        }
    }

    @FXML
    void PbtnHome(ActionEvent event) {
        // Your logic here
    }

    public User SearchUser() {
        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        String query = "SELECT * FROM signin WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.setString(2, txtPassword.getText());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Assuming User has a constructor that takes all necessary fields
                    return new User(
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            resultSet.getString("phonenumber"),
                            resultSet.getString("username"),
                            resultSet.getInt("demo")
                            // Add other fields as necessary
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @FXML
    void PbtnForgotPassword(ActionEvent event) throws IOException, SQLException {
        Stage stage = (Stage) btnForgotPassword.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));
        Scene scene = new Scene(root, 800, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
