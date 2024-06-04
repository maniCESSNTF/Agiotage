package com.example.demo1;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    Random random = new Random();
    public EmailSender emailSender = new EmailSender();
    String forgetPassCode;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtEmail;

    @FXML
    void PbtnSend(ActionEvent event) throws IOException, SQLException {

        if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        } else {
            if (validEmail(txtEmail.getText())) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sql = "SELECT * FROM signin WHERE email = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, txtEmail.getText());
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            forgetPassCode = String.valueOf((random.nextInt(1000000) + 1000000));
                            emailSender.send(txtEmail.getText(), forgetPassCode);
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
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Enter a valid email!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void PbtnDone(ActionEvent event) throws SQLException {
        String email=txtEmail.getText();
        if (txtCode.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter code!");
            alert.showAndWait();
        } else if (txtCode.getText().equals(forgetPassCode)) {
//                String email=txtEmail.getText();
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE email = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, txtEmail.getText());
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String password = resultSet.getString("password");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Your Password : " + password);
                        alert.showAndWait();

                        Stage stage = (Stage) btnDone.getScene().getWindow();
                        stage.close();
                        Stage primaryStage = new Stage();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
                        Scene scene = new Scene(root, 800, 650);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Email not found!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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