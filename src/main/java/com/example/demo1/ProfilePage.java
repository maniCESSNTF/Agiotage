//package com.example.demo1;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.scene.Parent;
//import javafx.scene.control.Alert;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.text.Text;
//import javafx.scene.control.TextField;
//import java.io.IOException;
//
//public class ProfileController {
//
//    @FXML
//    private Button btnChangLastName;
//
//    @FXML
//    private Button btnChangeEmail;
//
//    @FXML
//    private Button btnChangeFirstname;
//
//    @FXML
//    private Button btnChangePassword;
//
//    @FXML
//    private Button btnChangePhoneNumber;
//
//    @FXML
//    private Button btnChangeProfilePic;
//
//    @FXML
//    private Button btnHome;
//
//    @FXML
//    private Button btnShowInfo;
//
//    @FXML
//    private ImageView imgProfilePic;
//
//    @FXML
//    private Text txtEmail;
//
//    @FXML
//    private Text txtFirstName;
//
//    @FXML
//    private Text txtLastName;
//
//    @FXML
//    private TextField txtNewEmail;
//
//    @FXML
//    private TextField txtNewFirstName;
//
//    @FXML
//    private TextField txtNewLastName;
//
//    @FXML
//    private TextField txtNewPassword;
//
//    @FXML
//    private TextField txtNewPhoneNumber;
//
//    @FXML
//    private Text txtPassword;
//
//    @FXML
//    private Text txtPhoneNumber;
//
//    private User currentUser = new User(" ", "  ", " ", " ");
//
//    @FXML
//    void btnHome(ActionEvent event) {
//
//    }
//
//    private void changeEmail(String newEmail) {
//        txtEmail.setText(newEmail);
//        currentUser.setEmail(newEmail);
//    }
//
//    private void changeFirstName(String newFirstName) {
//        txtFirstName.setText(newFirstName);
//        currentUser.setName(newFirstName);
//    }
//
//    private void changeLastName(String newLastName) {
//        txtLastName.setText(newLastName);
//        currentUser.setLastName(newLastName);
//    }
//
//    private void changePhoneNumber(String newPhoneNumber) {
//        txtPhoneNumber.setText(newPhoneNumber);
//        currentUser.setPhoneNumber(newPhoneNumber);
//    }
//
//    private void changePassword(String newPassword) {
//        txtPassword.setText(newPassword);
//        currentUser.setPassword(newPassword);
//    }
//
//    @FXML
//    void btnChangeLastName(ActionEvent event) {
//        if (txtNewLastName.getText().isEmpty()) {
//            showAlert("Enter a new Last name!");
//        } else {
//            changeLastName(txtNewLastName.getText());
//        }
//    }
//
//    @FXML
//    void btnChangeFirstname(ActionEvent event) {
//        if (txtNewFirstName.getText().isEmpty()) {
//            showAlert("Enter a new First name!");
//        } else {
//            changeFirstName(txtNewFirstName.getText());
//        }
//    }
//
//    @FXML
//    void btnChangePassword(ActionEvent event) {
//        if (txtNewPassword.getText().isEmpty()) {
//            showAlert("Enter a new Password!");
//        } else {
//            changePassword(txtNewPassword.getText());
//        }
//    }
//
//    @FXML
//    void btnChangePhoneNumber(ActionEvent event) {
//        if (txtNewPhoneNumber.getText().isEmpty()) {
//            showAlert("Enter a new Phone Number!");
//        } else {
//            changePhoneNumber(txtNewPhoneNumber.getText());
//        }
//    }
//
//    @FXML
//    void btnChangeEmail(ActionEvent event) {
//        if (txtNewEmail.getText().isEmpty()) {
//            showAlert("Enter a new Email!");
//        } else {
//            changeEmail(txtNewEmail.getText());
//        }
//    }
//
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Warning");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile_info.fxml"));
//        loader.setController(this);
//        Parent root = loader.load();
//
//        txtFirstName.setText(currentUser.gettName());
//        txtLastName.setText(currentUser.getLastName());
//        txtEmail.setText(currentUser.getEmail());
//        txtPhoneNumber.setText(currentUser.getPhoneNumber());
//        txtPassword.setText(currentUser.getPassword());
//
//        Scene scene = new Scene(root, 634, 510);
//        primaryStage.setTitle("Profile Information");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}