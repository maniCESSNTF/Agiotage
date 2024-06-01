package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;

public class SingIn {

    public static String userName;
    public  static  String captcha1;

    public  static  String captcha2;
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
    public  TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

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
        } else {
            try {
                java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "");
                Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM captcha");
            while(resultSet.next()){
                captcha1 = resultSet.getString("captcha");
                System.out.println(resultSet.getString("captcha"));
            }
            }catch (SQLException e){
                e.printStackTrace();
            }

            userName=
        captcha2 = GenerateCaptcha();

            int conditionValue = 1; // Replace this with the actual ID of the record you want to update

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query = "UPDATE captcha SET captcha = ? WHERE id = 1";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, captcha2);
                    preparedStatement.executeUpdate();
                }
            }
            userName=InputPass.UsernameGenerator();
            captcha(captcha2);
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query = "INSERT INTO signin (firstname, lastname, email, phonenumber,password,username) VALUES (?, ?, ?, ?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, txtName.getText());
                    preparedStatement.setString(2, txtFamilyName.getText());
                    preparedStatement.setString(3, txtEmail.getText());
                    preparedStatement.setString(4, txtPhoneNumber.getText());
                    preparedStatement.setString(5, null);
                    preparedStatement.setString(6, userName);
                    preparedStatement.executeUpdate();
                }
            }
            AddUser(new User(txtName.getText(), txtFamilyName.getText(), txtEmail.getText(), txtPhoneNumber.getText(),userName));
            Stage stage = (Stage) btnPass.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("InputPass.fxml"));
            Scene scene = new Scene(root, 794, 637);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    @FXML
    void PbtnHome(ActionEvent event) {
        // Your logic here
    }

    @FXML
    void PrbtnClient(ActionEvent event) {
        if (rbtnManager.isSelected()) {
            rbtnManager.setSelected(false);
        }
    }

    @FXML
    void PrbtnManager(ActionEvent event) {
        if (rbtnClient.isSelected()) {
            rbtnClient.setSelected(false);
        }
    }

    public static void AddUser(User newUser) {
        int searchResult = SearchUser(newUser);
        if (searchResult == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("A user has already registered with this email!");
            alert.showAndWait();
        } else if (searchResult == 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("A user has already registered with this Phone number!");
            alert.showAndWait();
        } else if (searchResult == -1) {
            users[UserNumber] = newUser;
            UserNumber++;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Registration was successful!");
            alert.showAndWait();
        }
    }

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
