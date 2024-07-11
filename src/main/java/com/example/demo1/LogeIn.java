package com.example.demo1;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

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

    public static Profile profile1 = null;

    private void loadProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent mainView = loader.load();

            Profile profile = loader.getController();
            profile.setClient(client, txtUsername.getText());

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnSignIn.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PbtnSignIn(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SingIn.fxml"));
            Parent mainView = loader.load();

            SingIn singIn = loader.getController();
            singIn.setClient(client,null);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnSignIn.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if (client != null) {
                String response = client.sendMessage("PbtnDoneLogeIn," + username + "," + password);
                System.out.println("Response from server: " + response);
                if ("10".equals(response)) {
                    loadProfile();
                } else if ("30".equals(response)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("manager.fxml"));
                        Parent mainView = loader.load();

                        Manager manager = loader.getController();
                        manager.setClient(client,"1111111111");

                        Scene scene = new Scene(mainView);
                        Stage primaryStage = (Stage) btnSignIn.getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Password or Username is wrong!!");
                    alert.showAndWait();
                }
            } else {
                System.out.println("Client is not initialized");
            }


//
//
//
//            if( txtUsername.getText().equals("1111111111") && txtPassword.getText().equals("2222222222")){
//                Stage stage = (Stage) btnDone.getScene().getWindow();
//                stage.close();
//                Stage primaryStage = new Stage();
//                Profile.thisUsername = txtUsername.getText();
//                AnchorPane root = FXMLLoader.load(getClass().getResource("manager.fxml"));
//                Scene scene = new Scene(root, 794, 637);
//                primaryStage.setScene(scene);
//                primaryStage.show();
//            }else {
//                User nowUser = SearchUser();
//                if (nowUser != null) {
//
//                   loadProfile();
//                } else {
//                    txtPassword.setText("");
//                    txtUsername.setText("");
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Warning");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Password or Username is wrong");
//                    alert.showAndWait();
//                }
//            }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPass.fxml"));
            Parent mainView = loader.load();

            ForgetPass forgetPass = loader.getController();
            forgetPass.setClient(client);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnSignIn.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
