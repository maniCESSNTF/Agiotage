package com.example.demo1;

import Email.EmailSender;
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

import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class ForgetPass {

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    Random random = new Random();
    public EmailSender emailSender = new EmailSender();
    String forgetPassCode=null;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnLogeIn;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    void PbtnSend(ActionEvent event) throws SQLException {
        if (txtEmail.getText().isEmpty() || txtUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        } else {
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql1 = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql1)) {
                    statement1.setString(1, txtUsername.getText());
                    ResultSet resultSet1 = statement1.executeQuery();
                    if (resultSet1.next()) {
                        if(resultSet1.getString("email").equals(txtEmail.getText())){
                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                String sql = "SELECT * FROM signin WHERE email = ?";
                                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                                    statement.setString(1, txtEmail.getText());
                                    ResultSet resultSet = statement.executeQuery();
                                    if (resultSet.next()) {
                                        if (client != null) {
                                            String response = client.sendMessage("PbtnSendEmailDeposit," + null + "," + txtEmail.getText());
                                            System.out.println("Response from server: " + response);
                                            if (response != null) {
                                                forgetPassCode = response;
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Warning");
                                                alert.setHeaderText(null);
                                                alert.setContentText("The internet password has been sent!");
                                                alert.showAndWait();
                                            }
                                        } else {
                                            System.out.println("Client is not initialized");
                                        }
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Warning");
                                        alert.setHeaderText(null);
                                        alert.setContentText("There is no user with this email!");
                                        alert.showAndWait();
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("There is no user with this username and email!");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("There is no user with this username!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    void PbtnLogeIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogeIn.fxml"));
            Parent mainView = loader.load();

            LogeIn logeIn = loader.getController();
            logeIn.setClient(client,null);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnLogeIn.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PbtnDone(ActionEvent event) throws SQLException {
        if (txtCode.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter code!");
            alert.showAndWait();
        } else
        if (txtUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter code!");
            alert.showAndWait();
        } else
        if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter code!");
            alert.showAndWait();
        } else if (txtCode.getText().equals(forgetPassCode) && forgetPassCode != null) {
            if (client != null) {
                String password = client.sendMessage("ForgetPass," + txtUsername.getText() + "," + txtEmail.getText());
                System.out.println("Response from server: " + password);
                if ("66".equals(password)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Email not found!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Password : " + password);
                    alert.showAndWait();
                }
            } else {
                System.out.println("Client is not initialized");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Entered code is wrong!");
            alert.showAndWait();
        }
    }

    public boolean validEmail(String email) {
        if ((email.matches("[a-zA-Z0-9.]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}"))) {
            return true;
        } else {
            return false;
        }
    }

}