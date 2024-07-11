package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class MasagePage {

    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

    @FXML
    private Button btnProfile;

    @FXML
    private Button c;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnList;

    @FXML
    private Button btnShowMasage;

    @FXML
    private TextField txtMasageForSend;

    @FXML
    private Text txtPerson1;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtTime;

    @FXML
    private TextField txtMasageNumber;

    @FXML
    private TextArea txtMasageReceive;

    @FXML
    private TextField txtReceiverUsername;

    @FXML
    private TextField txtUsernameContact;


    @FXML
    void PbtnList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("messageList.fxml"));
            Parent mainView = loader.load();

            MessageList messageList = loader.getController();
            messageList.setClient(client, thisUsername);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnList.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PbtnMark(ActionEvent event) {
        if(txtPerson1.getText().equals("sender : ME")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You are the sender of this message!");
            alert.showAndWait();
        }else {
            try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query1 = "UPDATE masage SET mark = ? WHERE Receiver = ? AND sender = ? AND text = ? AND hash = ?";
                try (PreparedStatement preparedStatement = connection2.prepareStatement(query1)) {
                    preparedStatement.setInt(1, 1);
                    preparedStatement.setString(2, thisUsername);
                    preparedStatement.setString(3, txtUsernameContact.getText());
                    preparedStatement.setString(4, txtMasageReceive.getText());
                    preparedStatement.setString(5, txtMasageNumber.getText());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("✔✔✔✔✔✔✔");
            alert.showAndWait();
        }
    }



    @FXML
    void PbtnProfile(ActionEvent event) {
        if (thisUsername.equals("1111111111")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("manager.fxml"));
                Parent mainView = loader.load();

                Manager manager = loader.getController();
                manager.setClient(client, thisUsername);

                Scene scene = new Scene(mainView);
                Stage primaryStage = (Stage) btnProfile.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent mainView = loader.load();

                Profile profile = loader.getController();
                profile.setClient(client, thisUsername);

                Scene scene = new Scene(mainView);
                Stage primaryStage = (Stage) btnProfile.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void PbtnSend(ActionEvent event) throws SQLException {

        CalendarTime calendarTime = new CalendarTime();

        int hash = 0;

        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql3 = "SELECT * FROM masage WHERE Receiver = ? AND sender = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                statement1.setString(1, thisUsername);
                statement1.setString(2, txtReceiverUsername.getText());
                ResultSet resultSet = statement1.executeQuery();
                while(resultSet.next()) {
                    hash++;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql3 = "SELECT * FROM masage WHERE Receiver = ? AND sender = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                statement1.setString(1, txtReceiverUsername.getText());
                statement1.setString(2, thisUsername);
                ResultSet resultSet = statement1.executeQuery();
                while(resultSet.next()) {
                    hash++;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String queryEx = "INSERT INTO masage (date, time, text, Receiver, sender, hash) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatementSWAP = connection.prepareStatement(queryEx)) {
                preparedStatementSWAP.setString(1, calendarTime.formatDate(calendarTime.now()));
                preparedStatementSWAP.setString(2, calendarTime.formatTime(calendarTime.now()));
                preparedStatementSWAP.setString(3, txtMasageForSend.getText());
                preparedStatementSWAP.setString(4, txtReceiverUsername.getText());
                preparedStatementSWAP.setString(5, thisUsername);
                preparedStatementSWAP.setString(6, String.valueOf(hash));
                preparedStatementSWAP.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("The message was sent ☺");
        alert.showAndWait();
        txtMasageForSend.setText("");
        txtReceiverUsername.setText("");
    }

    @FXML
    void PbtnShowMasage(ActionEvent event) throws SQLException {
        boolean find = false;
        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql3 = "SELECT * FROM masage WHERE Receiver = ? AND sender = ? AND hash = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                statement1.setString(1, txtUsernameContact.getText());
                statement1.setString(2, thisUsername);
                statement1.setString(3, txtMasageNumber.getText());
                ResultSet resultSet = statement1.executeQuery();
                if(resultSet.next()) {
                    find = true;
                    txtMasageReceive.setText(resultSet.getString("text"));
                    txtPerson1.setText("sender : ME");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql3 = "SELECT * FROM masage WHERE Receiver = ? AND sender = ? AND hash = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                statement1.setString(1, thisUsername);
                statement1.setString(2, txtUsernameContact.getText());
                statement1.setString(3, txtMasageNumber.getText());
                ResultSet resultSet = statement1.executeQuery();
                if(resultSet.next()) {
                    find = true;
                    txtMasageReceive.setText(resultSet.getString("text"));
                    txtPerson1.setText("sender : " + txtUsernameContact.getText());
                    txtTime.setText(resultSet.getString("time"));
                    txtDate.setText(resultSet.getString("date"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(!find){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("There is no such conversation with this user!");
            alert.showAndWait();
        }
    }

}
