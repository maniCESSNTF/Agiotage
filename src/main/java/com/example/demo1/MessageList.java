package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageList {
    private Client client;
    private String thisUsername;

    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
    }

    @FXML
    private TableView<List> tableList;
    @FXML
    private TableColumn<List, Integer> number;
    @FXML
    private TableColumn<List, String> persone;

    private ObservableList<List> tableList1;

    @FXML
    private Button refresh;

    @FXML
    private Button btnBack;

    @FXML
    void PbtnBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MasagePage.fxml"));
            Parent mainView = loader.load();

            MasagePage masagePage = loader.getController();
            masagePage.setClient(client, thisUsername);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnBack.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void prefresh(ActionEvent event) {
        tableList1 = FXCollections.observableArrayList();
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        persone.setCellValueFactory(new PropertyValueFactory<>("persone"));
        tableList.setItems(tableList1);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String Url = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";

        String querybef = "SELECT * FROM masage WHERE Receiver = ? AND mark = ?";
        try (Connection connection = DriverManager.getConnection(Url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
            preparedStatement.setString(1, thisUsername);
            preparedStatement.setInt(2, 0);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String persone = resultSet.getString("sender");
                    int hash = resultSet.getInt("hash");

                    tableList1.add(new List(persone, hash));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
