
    package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

    public class Manager {

        static int closeOpen=0;



        @FXML
        private Text txtUsername;

        @FXML
        private Text txtWallet;

        @FXML
        private Text txtpass;
        @FXML
        private Button btnEkhtelas;

        @FXML
        private Button btnLogout;

        @FXML
        private Button btnHome;

        @FXML
        private   RadioButton rBtnClose;

        @FXML
        private   RadioButton rBtnOpen;

        @FXML
        private Text txtAvalanch;

        @FXML
        private Text txtDay;

        @FXML
        private Text txtLightcoin;

        @FXML
        private Text txtMoney;

        @FXML
        private Text txtRippel;

        @FXML
        private Text txtStellar;

        @FXML
        void PbtnEkhtelas(ActionEvent event) {

        }

        @FXML
        void PbtnHome(ActionEvent event) throws IOException {
            Stage stage = (Stage) btnHome.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(root, 794, 637);
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        @FXML
        void PrBtnClose(ActionEvent event) {
            if(rBtnClose.isSelected()) {
                rBtnOpen.setSelected(false);
                closeOpen = 1;
            } else {
                rBtnOpen.setSelected(true);
                closeOpen = 0;
            }
            System.out.println(closeOpen);
            }

        @FXML
        void PbtnLogout(ActionEvent event) throws IOException {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("LogeIn.fxml"));
            Scene scene = new Scene(root, 794, 637);
            primaryStage.setScene(scene);
            primaryStage.show();
        }


        @FXML
        void PrBtnOpen(ActionEvent event) {
            if (rBtnOpen.isSelected()) {
                closeOpen=0;
                rBtnClose.setSelected(false);
            } else {
                rBtnClose.setSelected(true);
                closeOpen = 1;
            }
            System.out.println(closeOpen+"##########");

        }

        @FXML
        public void initialize() throws SQLException {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM manager WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "1111111111");
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        txtMoney.setText(String.valueOf(resultSet.getInt("money")));
                        txtRippel.setText(String.valueOf(resultSet.getInt("rippel")));
                        txtAvalanch.setText(String.valueOf(resultSet.getInt("avalanche")));
                        txtDay.setText(String.valueOf(resultSet.getInt("day")));
                        txtLightcoin.setText(String.valueOf(resultSet.getInt("lightcoin")));
                        txtStellar.setText(String.valueOf(resultSet.getInt("stellar")));
                        txtWallet.setText(resultSet.getString("walletid"));
                        txtUsername.setText(resultSet.getString("username"));
                        txtpass.setText(resultSet.getString("password"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

            exchangeList = FXCollections.observableArrayList();

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            sellbuyColumn.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

            tableView.setItems(exchangeList);
            loadDataFromDatabase();
    }



        @FXML
        private TableView<Exchange> tableView;
        @FXML
        private TableColumn<Exchange, LocalDate> dateColumn;
        @FXML
        private TableColumn<Exchange, LocalTime> timeColumn;

        @FXML
        private TableColumn<Exchange, Integer> sellbuyColumn;
        @FXML
        private TableColumn<Exchange, Integer> priceColumn;
        @FXML
        private TableColumn<Exchange, Integer> stateColumn;
        @FXML
        private TableColumn<Exchange, Double> amountColumn;
        @FXML
        private TableColumn<Exchange, String> typeColumn;

        private ObservableList<Exchange> exchangeList;



        private void loadDataFromDatabase() {
            String Url = "jdbc:mysql://localhost:3306/agiotage2";
            String dbUser = "root";
            String dbPassword = "";

            String querybef = "SELECT * FROM exchange";
            try (Connection connection = DriverManager.getConnection(Url, dbUser, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        LocalDate date = resultSet.getDate("date").toLocalDate();
                        LocalTime time = resultSet.getTime("time").toLocalTime();
                        String type = resultSet.getString("type");
                        Integer price = resultSet.getInt("price");
                        Double amount = resultSet.getDouble("copyAmount");
                        Integer state = resultSet.getInt("state");
                        String stateEX=null;
                        if(state==0)
                            stateEX="pending";
                        else  stateEX="done";

                        Integer sellbuy = resultSet.getInt("sellbuy");

                        exchangeList.add(new Exchange(date, time,sellbuy,type,amount,price,stateEX));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


