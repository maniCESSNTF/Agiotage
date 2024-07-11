
    package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

    public class Manager {
        private Client client;
        private String thisUsername;

        public void setClient(Client client, String thisUsername) {
            this.client = client;
            this.thisUsername = thisUsername;
        }
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
        private Button btnRefresh;

        @FXML
        private Button btnmessage;

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
        void PbtnEkhtelas(ActionEvent event) throws SQLException {
            double userRippel=0,userAvalanche=0,userlightCoin=0,userDay=0,userStelar=0,userMoney=0,managerRippel=0,managerAvalanche=0,managerlightCoin=0,managerDay=0,managerStelar=0,managerMoney=0;
            String UserUsername;
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin ";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = statement.executeQuery();
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("username"));
                        userRippel=resultSet.getDouble("rippel");
                        userAvalanche=resultSet.getDouble("avalanche");
                        userDay=resultSet.getDouble("day");
                        userStelar=resultSet.getDouble("stellar");
                        userlightCoin=resultSet.getDouble("lightcoin");
                        userMoney=resultSet.getDouble("money");
                        UserUsername=resultSet.getString("username");

                        try (Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String query1 = "UPDATE signin SET rippel = ?,avalanche = ?,day = ?,stellar = ?,lightcoin = ?,money = ? WHERE username = ?";
                            try (PreparedStatement preparedStatement = connection2.prepareStatement(query1)) {
                                preparedStatement.setDouble(1, 0);
                                preparedStatement.setDouble(2, 0);
                                preparedStatement.setDouble(3, 0);
                                preparedStatement.setDouble(4, 0);
                                preparedStatement.setDouble(5, 0);
                                preparedStatement.setDouble(6, 0);
                                preparedStatement.setString(7, UserUsername);
                                preparedStatement.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sql3 = "SELECT * FROM manager WHERE username = ?";
                            try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                                statement1.setString(1, "1111111111");
                                ResultSet resultSet11 = statement1.executeQuery();
                                if (resultSet11.next()) {
                                    managerRippel=resultSet.getDouble("rippel");
                                    managerAvalanche=resultSet.getDouble("avalanche");
                                    managerDay=resultSet.getDouble("day");
                                    managerStelar=resultSet.getDouble("stellar");
                                    managerlightCoin=resultSet.getDouble("lightcoin");
                                    managerMoney=resultSet.getDouble("money");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        }

                        try (Connection connection23 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String query = "UPDATE manager SET rippel = ?,avalanche = ?,day = ?,stellar = ?,lightcoin = ?,money = ? WHERE username = ?";
                            try (PreparedStatement preparedStatement = connection23.prepareStatement(query)) {
                                preparedStatement.setDouble(1, managerRippel+userRippel);
                                preparedStatement.setDouble(2, managerAvalanche+userAvalanche);
                                preparedStatement.setDouble(3, managerDay+userDay);
                                preparedStatement.setDouble(4, managerStelar+userStelar);
                                preparedStatement.setDouble(5, managerlightCoin+userlightCoin);
                                preparedStatement.setDouble(6, managerMoney+userMoney);
                                preparedStatement.setString(7, "1111111111");
                                preparedStatement.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        @FXML
        void PbtnHome(ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                Parent mainView = loader.load();

                HomePage homePage = loader.getController();
                homePage.setClient(client, "1111111111");

                Scene scene = new Scene(mainView);
                Stage primaryStage = (Stage) btnHome.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @FXML
        void Pbtnmessage(ActionEvent event) throws SQLException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MasagePage.fxml"));
                Parent mainView = loader.load();

                MasagePage masagePage = loader.getController();
                masagePage.setClient(client, "1111111111");

                Scene scene = new Scene(mainView);
                Stage primaryStage = (Stage) btnmessage.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void PbtnRefresh(ActionEvent event) throws IOException, SQLException {
            initialize();
        }

        @FXML
        void PrBtnClose(ActionEvent event) {
            if(rBtnClose.isSelected()) {
                rBtnOpen.setSelected(false);
                closeOpen = 1;
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE manager SET openclose = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setInt(1, closeOpen);
                        preparedStatement.setString(2, "1111111111");
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                rBtnOpen.setSelected(true);
                closeOpen = 0;
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE manager SET openclose = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setInt(1, closeOpen);
                        preparedStatement.setString(2, "1111111111");
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(closeOpen);
        }

        @FXML
        void PrBtnOpen(ActionEvent event) {
            if (rBtnOpen.isSelected()) {
                closeOpen=0;
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE manager SET openclose = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setInt(1, closeOpen);
                        preparedStatement.setString(2, "1111111111");
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                rBtnClose.setSelected(false);
            } else {
                rBtnClose.setSelected(true);
                closeOpen = 1;
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE manager SET openclose = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setInt(1, closeOpen);
                        preparedStatement.setString(2, "1111111111");
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        @FXML
        void PbtnLogout(ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LogeIn.fxml"));
                Parent logeIn = loader.load();

                LogeIn Loge = loader.getController();
                Loge.setClient(client, null);

                Scene scene = new Scene(logeIn);
                Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
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


