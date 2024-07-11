package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;

import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;

//import static com.example.demo1.Methods.UserNumber;
//import static com.example.demo1.Methods.users;

public class SingIn {

    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

    private String userName;
    @FXML
    private Button btnPass;

    @FXML
    private Button btnDoHaveAnAccont;

    @FXML
    private Button btnHome;

    @FXML
    private RadioButton rbtnClient;

    @FXML
    private RadioButton rbtndemo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFamilyName;

    @FXML
    public TextField txtName;

    @FXML
    private TextField txtPhoneNumber;


    public boolean validName(String name) {
        if (name.length() <= 18 && name.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validLastName(String name) {
        if (name.length() <= 18 && name.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validEmail(String email) {
        if ((email.matches("[a-zA-Z0-9.]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}"))) {
            return true;
        } else {
            return false;
        }
    }


    public boolean validPhoneNumber(String phoneNumber) {
        if ((phoneNumber.length() == 11 && phoneNumber.matches("[0-9]+") && phoneNumber.startsWith("09"))) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void PbtnDoHaveAnAccont(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnDoHaveAnAccont.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
        Scene scene = new Scene(root, 800, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void PbtnPass(ActionEvent event) throws IOException, SQLException {
        if (txtName.getText().isEmpty() || txtFamilyName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        } else if ((!validName(txtName.getText())) || (!validEmail(txtEmail.getText())) || (!validEmail(txtEmail.getText())) || (!validPhoneNumber(txtPhoneNumber.getText()))) {
            if (!validName(txtName.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your Name must be a maximum of 18 letters!");
                alert.showAndWait();

            }
            if (!validLastName(txtFamilyName.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your FamilyName must be a maximum of 18 letters!");
                alert.showAndWait();

            }
            if (!validEmail(txtEmail.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your Email must end with @gmail.com!");
                alert.showAndWait();

            }
            if (!validPhoneNumber(txtPhoneNumber.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your PhoneNumber must be made up from numbers & start with 09!");
                alert.showAndWait();
            }
        } else {
            int demo = 0;
            if (rbtndemo.isSelected()) {
                demo = 1;
            }
            userName = InputPass.UsernameGenerator();

            if (client != null) {
                String response = client.sendMessage("PbtnPass," + userName + "," + txtName.getText()  + "," + txtFamilyName.getText() + "," + txtEmail.getText() + "," + txtPhoneNumber.getText() + "," + String.valueOf(demo));
                System.out.println("Response from server: " + response);
                if ("23".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The operation encountered a problem. Please try again!");
                    alert.showAndWait();
                }
                else if ("33".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("A user with this phone number has already registered!!");
                    alert.showAndWait();
                }
                else if ("43".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("A user with this email has already registered!!");
                    alert.showAndWait();
                }
                else if ("53".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The operation encountered a problem. Please try again!");
                    alert.showAndWait();
                }
                else if ("63".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration was successful!");
                    alert.showAndWait();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("InputPass.fxml"));
                        Parent mainView = loader.load();

                        InputPass inputPass = loader.getController();
                        inputPass.setClient(client,userName);

                        Scene scene = new Scene(mainView);
                        Stage primaryStage = (Stage) btnPass.getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Client is not initialized");
            }
           // AddUser(new User(txtName.getText(), txtFamilyName.getText(), txtEmail.getText(), txtPhoneNumber.getText(), userName, demo));
        }
    }

    @FXML
    void PbtnHome(ActionEvent event) {
        // Your logic here
    }

    @FXML
    void PrbtnClient(ActionEvent event) {
        if (rbtnClient.isSelected()) {
            rbtndemo.setSelected(false);
        } else
            rbtndemo.setSelected(true);
    }

    @FXML
    void Prbtndemo(ActionEvent event) {
        if (rbtndemo.isSelected()) {
            rbtnClient.setSelected(false);
        } else
            rbtnClient.setSelected(true);
    }

//    public static void AddUser(User newUser) {
//        int searchResult = SearchUser(newUser);
//        if (searchResult == 1) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText(null);
//            alert.setContentText("A user has already registered with this email!");
//            alert.showAndWait();
//        } else if (searchResult == 2) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText(null);
//            alert.setContentText("A user has already registered with this Phone number!");
//            alert.showAndWait();
//        } else if (searchResult == -1) {
//            users[UserNumber] = newUser;
//            UserNumber++;
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information");
//            alert.setHeaderText(null);
//            alert.setContentText("Registration was successful!");
//            alert.showAndWait();
//        }
//    }

    public static int SearchUser(User newUser) {

        for (User user : users) {
            if (user != null) {
                if (user.getEmail().equals(newUser.getEmail())) {
                    return 1;
                } else if (user.getPhoneNumber().equals(newUser.getPhoneNumber())) {
                    return 2;
                }
            }
        }
        return -1;
    }
}