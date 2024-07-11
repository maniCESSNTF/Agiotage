package com.example.demo1;

import Email.EmailSender;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static com.example.demo1.CombinedTask.ReadPrices.calendarTime;

public class Server extends Application {
    private static AtomicInteger counter = new AtomicInteger(0);
    public static EmailSender emailSender = new EmailSender();
    static Random random = new Random();


    @Override
    public void start(Stage primaryStage) {
        // اینجا نیازی به کد خاصی نیست، فقط برای راه‌اندازی محیط JavaFX استفاده می‌شود
    }

    public static void main(String[] args) {
        // راه‌اندازی JavaFX در یک thread جداگانه
        new Thread(() -> Application.launch(Server.class)).start();

        try (ServerSocket serverSocket = new ServerSocket(11111)) {
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
                System.out.println(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized int incrementCounter(int increment) {
        return counter.addAndGet(increment);
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String request;
                while ((request = in.readLine()) != null) {
                    String[] parts = request.split(",");
                    String command = parts[0];
                    String increment = parts.length > 1 ? parts[1] : "0";
                    String result;
                    switch (command) {
                        case "PbtnDoneLogeIn":
                            if(parts[1].equals("1111111111") && parts[2].equals("2222222222")){
                                result="30";
                                break;
                            }else {
                                System.out.println(parts[1]);
                                User newUser = search(parts[1], parts[2]);//parts[1].equals("1111111111") && parts[2].equals("2222222222")
                                if (newUser != null)
                                    result = "10";
                                else
                                    result = "20";
                                break;
                            }
                        case "PbtnProfilePicProfile":
                            result = chooseImage();
                            break;
                        case "PbtnPass":
                            result = SignIn(parts[1],parts[2], parts[3],parts[4], parts[5],parts[6]);
                            break;
                        case "PbtnRefreshCaptchaDeposit":
                            result = captcha();
                            break;
                        case "PbtnDoneDeposit":
                            result = String.valueOf(DoneDeposit(parts[1], Double.parseDouble(parts[2])));
                            break;
                        case "PbtnSendEmailDeposit":
                            result = sendEmail(parts[2]);
                            break;
                        case "ForgetPass":
                            result = ForgetPass(parts[1],parts[2]);
                            break;
                        case "setPassword":
                            result = setPassword(parts[1],parts[2]);
                            break;
                        case "PbtnDoneWthdrawal":
                            result = DoWthdrawal(parts[1],parts[2]);
                            break;
                        case "PbtnDoneEx":
                            result = DoExchange(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                            break;
                        case "PbtnDonetrans":
                            result = DoTrans(parts[1], parts[2], parts[3], parts[4]);
                            break;
                        case "PbtnDoneSWAP":
                            result = btnSwap(parts[1], parts[2], parts[3], parts[4], parts[5]);
                            break;
                        default:
                            result = "-1"; // Unknown command
                            break;
                    }
                    out.println(result);
                }
            } catch (IOException e) {
                System.err.println("Client handler exception: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static synchronized String setPassword(String username,String pass){
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query = "UPDATE signin SET password = ? WHERE username = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, pass);
                    preparedStatement.setString(2, username);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return username;
        }

        public static synchronized String ForgetPass(String username,String  email) throws SQLException {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ? AND email = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, email);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    } else {
                        return "66";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public static synchronized String SignIn(String username,String firstName,String lastName,String Email,String phone,String demo) throws SQLException {

            //چک کردن تکراری نبورن username
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql3 = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                    statement1.setString(1, username);
                    ResultSet resultSet11 = statement1.executeQuery();
                    if (resultSet11.next()) {
                        return "23";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            //چک کردن تکراری نبورن phonenumber
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql3 = "SELECT * FROM signin WHERE phonenumber = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                    statement1.setString(1, phone);
                    ResultSet resultSet11 = statement1.executeQuery();
                    if (resultSet11.next()) {
                        return "33";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            //چک کردن تکراری نبورن Email
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql3 = "SELECT * FROM signin WHERE email = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                    statement1.setString(1, Email);
                    ResultSet resultSet11 = statement1.executeQuery();
                    if (resultSet11.next()) {
                        return "43";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("^%^%^^%^%^^^%^%^%^%");
            //ثبت اطلاعات
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query = "INSERT INTO signin (firstname, lastname, email, phonenumber,password,username,WalletId,demo,money) VALUES (?, ?, ?, ?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, Email);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setString(5, null);
                    preparedStatement.setString(6, username);
                    preparedStatement.setString(7, InputPass.GenerateWalletId());
                    preparedStatement.setInt(8, Integer.valueOf(demo));
                    if (demo.equals("1")) {
                        preparedStatement.setInt(9, 5000);
                    } else
                        preparedStatement.setInt(9, 0);

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                return "53";
            }
            return "63";
        }

        public static synchronized String DoWthdrawal(String username,String amount) throws SQLException {
            int openclose = 0;
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM manager WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "1111111111");
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        openclose = resultSet.getInt("openclose");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(openclose==0){

                double beformaney=0;

                try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sql3 = "SELECT * FROM manager WHERE username = ?";
                    try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                        statement1.setString(1, "1111111111");
                        ResultSet resultSet11 = statement1.executeQuery();
                        if (resultSet11.next()) {
                            beformaney=resultSet11.getDouble("money");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                if(beformaney < Double.valueOf(amount)){
                    return "65";
                }
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET money = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1,String.valueOf(beformaney - Double.valueOf(amount)));
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else return "75";
            return "85";
        }

        public static synchronized String DoTrans(String username, String typeCoinTr, String Amont, String walletId) {
            int demo = 0;
            boolean swWallet = false;
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        demo = resultSet.getInt("demo");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (demo == 0) {
                try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sql1 = "SELECT * FROM signin WHERE username = ?";
                    try (PreparedStatement statement1 = connection1.prepareStatement(sql1)) {
                        statement1.setString(1, username);
                        ResultSet resultSetME = statement1.executeQuery();
                        if (resultSetME.next()) {
                            if (resultSetME.getDouble(typeCoinTr) >= Double.parseDouble(Amont)) {
                                try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                    String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                                    try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                        statementYOU1.setString(1, walletId);
                                        ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                        if (resultSetYOU1.next()) {
                                            try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String queryYOU = "UPDATE signin SET " + typeCoinTr + " = ? WHERE walletid = ?";
                                                try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                                    double y = resultSetYOU1.getDouble(typeCoinTr) + Integer.parseInt(Amont);
                                                    preparedStatementYOU.setString(1, String.valueOf(y));
                                                    preparedStatementYOU.setString(2, walletId);
                                                    preparedStatementYOU.executeUpdate();
                                                    swWallet = true;
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        } else {
                                            return "13";
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                if (swWallet) {
                                    try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryME = "UPDATE signin SET " + typeCoinTr + " = ? WHERE username = ?";
                                        try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                            double y = resultSetME.getDouble(typeCoinTr) - Integer.parseInt(Amont);
                                            preparedStatementME.setString(1, String.valueOf(y));
                                            preparedStatementME.setString(2, username);
                                            preparedStatementME.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            } else {
                                return "14";
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "15";
            }
            return "16";
        }

        public static synchronized User search(String username, String password) {
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql3 = "SELECT * FROM signin WHERE username = ? AND password = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                    statement1.setString(1, username);
                    statement1.setString(2, password);

                    ResultSet resultSet11 = statement1.executeQuery();
                    if (resultSet11.next()) {
                        return new User(resultSet11.getString("firstname"), resultSet11.getString("lastname"), resultSet11.getString("email"), resultSet11.getString("phonenumber"), resultSet11.getString("username"), resultSet11.getInt("demo"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        public static synchronized String btnSwap(String username, String amount1, String amount2, String type1, String type2) throws SQLException {

         //   System.out.println("amount1 : "+amount1+"\namount2 : "+amount2+"\ntype1 : "+type1+"\ntype2 : "+type2);
            int openclose = 0;
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM manager WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "1111111111");
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        openclose = resultSet.getInt("openclose");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (openclose == 0) {
                try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
                    try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                        statementYOU1.setString(1, username);
                        ResultSet resultSetYOU1 = statementYOU1.executeQuery();

                        boolean swSWAP = false;
                        if (resultSetYOU1.next()) {
                            if (resultSetYOU1.getDouble(type1) >= Double.valueOf(amount1)) {
                                swSWAP = true;
                                try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                    String queryYOU = "UPDATE signin SET " + type1 + " = ? ," + type2 + " = ? WHERE username = ?";
                                    try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                        preparedStatementYOU.setDouble(1, resultSetYOU1.getDouble(type1) - Double.parseDouble(amount1));
                                        preparedStatementYOU.setDouble(2, resultSetYOU1.getDouble(type2) + Double.parseDouble(amount2)*(0.99));
                                        preparedStatementYOU.setString(3, username);
                                        preparedStatementYOU.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }


                                double beforCoinManger=0.0;
                                try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                    String sql3 = "SELECT * FROM manager WHERE username = ?";
                                    try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                                        statement1.setString(1, "1111111111");
                                        ResultSet resultSet11 = statement1.executeQuery();
                                        if (resultSet11.next()) {
                                            beforCoinManger=resultSet11.getDouble(type2);
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                    String queryYOU = "UPDATE manager SET "+type2+" = ? WHERE username = ?";
                                    try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                        preparedStatementYOU.setDouble(1, beforCoinManger + Double.parseDouble(amount2)*(0.01));
                                        preparedStatementYOU.setString(2, "1111111111");
                                        preparedStatementYOU.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                return "20";
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "21";
            }
            return "22";
        }

        public static synchronized String chooseImage() {
            final String[] result = new String[1];
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
                );
                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {
                    result[0] = selectedFile.toURI().toString();
                } else {
                    result[0] = null;
                }
                synchronized (result) {
                    result.notify();
                }
            });

            synchronized (result) {
                while (result[0] == null) {
                    try {
                        result.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                }
            }
            return result[0];
        }

        public static synchronized String captcha() throws IOException {
            String captcha = GenerateCaptcha();
            CaptchaGenerator.captcha(captcha);
            return captcha;
        }

        public static synchronized double DoneDeposit(String username, double amount) throws SQLException {
            Double y = 0.0;
            try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                    statementYOU1.setString(1, username);
                    ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                    if (resultSetYOU1.next()) {
                        try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryYOU = "UPDATE signin SET money = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                y = Double.parseDouble(resultSetYOU1.getString("money")) + amount;
                                preparedStatementYOU.setString(1, String.valueOf(y));
                                preparedStatementYOU.setString(2, username);
                                preparedStatementYOU.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return y;
        }

        public static synchronized String sendEmail(String email) {
            String code = String.valueOf((random.nextInt(1000000)) + 1000000);
            emailSender.send(email, code);
            return code;
        }

        public static synchronized String DoExchange(String username, String amount, String price, String type, String buy, String sell) throws SQLException {
            System.out.println("buy :" + buy);
            System.out.println("sell :" + sell);
            int newMoneyAmount = 0;
            int moneyManager = 0;

            boolean btnbuyEx, btnsellEx;
            if (buy.equals("true"))//ورودی شکدار////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                btnbuyEx = true;
            else btnbuyEx = false;
            if (sell.equals("true"))
                btnsellEx = true;
            else btnsellEx = false;

            //بررسی شرایط بازار
            try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql3 = "SELECT * FROM manager WHERE username = ?";
                try (PreparedStatement statement1 = connection1.prepareStatement(sql3)) {
                    statement1.setString(1, "1111111111");
                    ResultSet resultSet11 = statement1.executeQuery();
                    if (resultSet11.next()) {
                        moneyManager = resultSet11.getInt("money");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


            int openclose = 0;
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM manager WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "1111111111");
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        openclose = resultSet.getInt("openclose");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (openclose == 0) {

                String url = "jdbc:mysql://localhost:3306/agiotage2";
                String user = "root";
                String password = "";

                double money1 = 0;
                double coin1 = 0;

                double money2 = 0;
                double coin2 = 0;
                int typeEX = 0;
                int sw = 0;
                String typeCoinEx = null;
                boolean SWExchange = false;
                double amountTxt = Double.parseDouble(amount);
                String typeBefore;
                int priceBefore;
                int demo1 = 0;
                int demo2 = 0;

                String usernameBefore;
                String timeBefore;
                String dateBefore;
                double sellbuyBefore;
                typeCoinEx = type;


                String nowTime = calendarTime.formatTime(calendarTime.now());
                String nowDate = calendarTime.formatDate(calendarTime.now());

                if (btnsellEx)
                    typeEX = 0;
                else typeEX = 1;

                //بررسی امکان ثبت درخواست جهت تبادل
                try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
                    try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                        statementYOU1.setString(1, username);
                        ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                        if (resultSetYOU1.next()) {
                            coin1 = resultSetYOU1.getDouble(typeCoinEx);
                            money1 = resultSetYOU1.getDouble("money");
                            demo1 = resultSetYOU1.getInt("demo");
                            if (Double.valueOf(resultSetYOU1.getDouble(typeCoinEx)) < Double.parseDouble(amount) && typeEX == 0) {
                                return "59";
                            } else SWExchange = true;
                            if (typeEX == 1) {
                                SWExchange = true;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("################!");
                            alert.showAndWait();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                //ثبت درخواست
                if (SWExchange || !btnsellEx) {
                    try (Connection connectionSWAP = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryEx = "INSERT INTO exchange (username, time, date, sellbuy, type, amount, state, price, copyAmount,demo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatementSWAP = connectionSWAP.prepareStatement(queryEx)) {
                            preparedStatementSWAP.setString(1, username);
                            preparedStatementSWAP.setString(2, nowTime);
                            preparedStatementSWAP.setString(3, nowDate);
                            preparedStatementSWAP.setInt(4, typeEX);
                            preparedStatementSWAP.setString(5, typeCoinEx);
                            preparedStatementSWAP.setDouble(6, Double.parseDouble(amount));
                            preparedStatementSWAP.setDouble(7, 0);
                            preparedStatementSWAP.setDouble(8, Integer.parseInt(price));
                            preparedStatementSWAP.setDouble(9, Double.parseDouble(amount));
                            preparedStatementSWAP.setInt(10, demo1);
                            preparedStatementSWAP.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                //بررسی تمامی درخواست های قبلی برای مچ کردن با درخواست فعلی
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String initialEmail = "2000-06-30";
                    String sql = "SELECT * FROM exchange WHERE date >=? ORDER BY date";
                    try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                        preparedStatementYOU.setString(1, initialEmail);
                        ResultSet rs = preparedStatementYOU.executeQuery();
                        while (rs.next() && amountTxt > 0) {
                            if (rs.getString("type").equals(typeCoinEx)) {
                                if (btnbuyEx && rs.getInt("sellbuy") == 0) {
                                    if (rs.getInt("price") <= Integer.parseInt(price)) {
                                        double min = 0;
                                        usernameBefore = rs.getString("username");
                                        sellbuyBefore = rs.getInt("amount");
                                        priceBefore = rs.getInt("price");

                                        //خواندن اطلاعات فرد فروشنده(فرد ۲) برای انجام تبادل
                                        String querybef = "SELECT * FROM signin WHERE username = ?";
                                        try (Connection connection = DriverManager.getConnection(url, user, password);
                                             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
                                            preparedStatement.setString(1, usernameBefore);
                                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                                if (resultSet.next()) {
                                                    money2 = resultSet.getInt("money");
                                                    coin2 = resultSet.getInt(typeCoinEx);
                                                    demo2 = resultSet.getInt("demo");
                                                }
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        if (amountTxt >= sellbuyBefore) {
                                            min = sellbuyBefore;
                                        } else {
                                            min = amountTxt;
                                        }
                                        System.out.println("min : " + min + " coin2 : " + coin2 + " money1 : " + money1 + "  min * Integer.parseInt(price) :" + (min * Integer.parseInt(price)));

                                        //بررسی کافی بودن پول شخص خریدار و مقدار ارز شخص فروشنده
                                        if (coin2 >= min && money1 >= min * Integer.parseInt(price) && !username.equals(usernameBefore)) {

                                            System.out.println("2222222222222222222min : " + min + " coin2 : " + coin2 + " money1 : " + money1 + "  min * Integer.parseInt(price) :" + (min * Integer.parseInt(price)));

                                            double buyAmount = Double.parseDouble(amount);
                                            double sellAmount = rs.getDouble("amount");
                                            int buyMoney = Integer.parseInt(price);
                                            int sellbuy = Integer.parseInt(price);

                                            amountTxt -= min;
                                            sellAmount -= min;

                                            newMoneyAmount += 0.005 * (min * Integer.parseInt(price));

                                            //(فرد 2)اپدیت درخواس شخص فروشنده
                                            if ((demo2 == 0 && demo1 == 1)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, sellAmount);
                                                        preparedStatement.setInt(2, sellAmount > 0 ? 0 : 1);
                                                        preparedStatement.setString(3, rs.getString("username"));
                                                        preparedStatement.setString(4, rs.getString("time"));
                                                        preparedStatement.setString(5, rs.getString("date"));
                                                        preparedStatement.setInt(6, 0);
                                                        preparedStatement.setString(7, typeCoinEx);
                                                        preparedStatement.setInt(8, rs.getInt("price"));
                                                        int rowsUpdated = preparedStatement.executeUpdate();
                                                        if (rowsUpdated == 0) {
                                                            System.out.println("Update failed: No rows updated");
                                                        } else {
                                                            System.out.println("Update succeeded: " + rowsUpdated + " rows updated");
                                                        }
                                                    }
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            //اپدیت درخواست شخص خریدار
                                            if ((demo2 == 1 && demo1 == 0)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, amountTxt);
                                                        if (amountTxt > 0)
                                                            preparedStatement.setDouble(2, 0);
                                                        else
                                                            preparedStatement.setInt(2, 1);
                                                        preparedStatement.setString(3, username);
                                                        preparedStatement.setString(4, nowTime);
                                                        preparedStatement.setString(5, nowDate);
                                                        preparedStatement.setInt(6, 1);
                                                        preparedStatement.setString(7, typeCoinEx);
                                                        preparedStatement.setInt(8, Integer.parseInt(price));
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            //اپدیت اطلاعات دارایی شخص خریدار
                                            if ((demo2 == 1 && demo1 == 0)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, money1 - (Integer.parseInt(price) * min));
                                                        preparedStatement.setDouble(2, coin1 + min * (0.995));
                                                        preparedStatement.setString(3, username);
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            //اپدیت اطلاعات دارایی شخص فروشنده
                                            if ((demo2 == 0 && demo1 == 1)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, money2 + ((Integer.parseInt(price) * min) * 0.995));
                                                        preparedStatement.setDouble(2, coin2 - min);
                                                        preparedStatement.setString(3, usernameBefore);
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }

                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE manager SET money = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, moneyManager + (min * 0.01 * (Integer.parseInt(price))));
                                                    preparedStatement.setString(2, "1111111111");
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                            return "true";
                                        }
                                    }

                                } else if (btnsellEx && rs.getInt("sellbuy") == 1) {
                                    if (Integer.valueOf(rs.getInt("price")) >= Integer.parseInt(price)) {
                                        double min = 0;
                                        int demo11 = 0;
                                        int demo22 = 0;
                                        usernameBefore = rs.getString("username");
                                        timeBefore = rs.getString("time");
                                        dateBefore = rs.getString("date");
                                        typeBefore = rs.getString("type");
                                        sellbuyBefore = rs.getInt("amount");
                                        priceBefore = rs.getInt("price");

                                        //خواندن اطلاعات فرد فروشنده برای انجام تبادل(فردی که هم اکنون درخواست می دهد)
                                        String querybef = "SELECT * FROM signin WHERE username = ?";
                                        try (Connection connection = DriverManager.getConnection(url, user, password);
                                             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
                                            preparedStatement.setString(1, username);
                                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                                if (resultSet.next()) {
                                                    money2 = resultSet.getInt("money");
                                                    coin2 = resultSet.getInt(typeCoinEx);
                                                    demo22 = resultSet.getInt("demo");
                                                }
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        //خواندن اطلاعات فرد خریدار برای انجام تبادل
                                        String querybef1 = "SELECT * FROM signin WHERE username = ?";
                                        try (Connection connection = DriverManager.getConnection(url, user, password);
                                             PreparedStatement preparedStatement = connection.prepareStatement(querybef1)) {
                                            preparedStatement.setString(1, usernameBefore);
                                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                                if (resultSet.next()) {
                                                    money1 = resultSet.getInt("money");
                                                    coin1 = resultSet.getInt(typeCoinEx);
                                                    demo11 = resultSet.getInt("demo");

                                                }
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        if (amountTxt >= sellbuyBefore) {
                                            min = sellbuyBefore;
                                        } else {
                                            min = amountTxt;
                                        }
                                        //بررسی دارایی مالی شخص خریدار  و داراییی ارزی شخص فروشنده برای انجام تبادل
                                        if (coin2 >= min && money1 >= min * Integer.parseInt(price) && !username.equals(usernameBefore)) {
                                            double buyAmount = Double.parseDouble(amount);
                                            double sellAmount = rs.getDouble("amount");
                                            int buyMoney = Integer.parseInt(price);

                                            amountTxt -= min;
                                            sellAmount -= min;

                                            //اپدیت درخواس شخص خریدار
                                            if ((demo22 == 1 && demo11 == 0)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, sellAmount);
                                                        preparedStatement.setInt(2, sellAmount > 0 ? 0 : 1);
                                                        preparedStatement.setString(3, rs.getString("username"));
                                                        preparedStatement.setString(4, rs.getString("time"));
                                                        preparedStatement.setString(5, rs.getString("date"));
                                                        preparedStatement.setInt(6, 1);
                                                        preparedStatement.setString(7, typeCoinEx);
                                                        preparedStatement.setInt(8, rs.getInt("price"));
                                                        int rowsUpdated = preparedStatement.executeUpdate();
                                                        if (rowsUpdated == 0) {
                                                            System.out.println("Update failed: No rows updated");
                                                        } else {
                                                            System.out.println("Update succeeded: " + rowsUpdated + " rows updated");
                                                        }
                                                    }
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            //اپدیت درخواست شخص فروشنده
                                            if ((demo22 == 0 && demo11 == 1)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";//AND amount = ?
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, amountTxt);
                                                        if (amountTxt > 0)
                                                            preparedStatement.setInt(2, 0);
                                                        else
                                                            preparedStatement.setInt(2, 1);
                                                        preparedStatement.setString(3, username);
                                                        preparedStatement.setString(4, nowTime);
                                                        preparedStatement.setString(5, nowDate);
                                                        preparedStatement.setInt(6, 0);
                                                        preparedStatement.setString(7, typeCoinEx);
                                                        preparedStatement.setInt(8, Integer.parseInt(price));
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            //اپدیت اطلاعات دارایی شخص فروشنده
                                            if ((demo22 == 0 && demo11 == 1)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, money2 + ((Integer.valueOf(price) * min) * 0.995));
                                                        preparedStatement.setDouble(2, coin2 - min);
                                                        preparedStatement.setString(3, username);
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            //اپدیت اطلاعات دارایی شخص فروشنده
                                            if ((demo22 == 1 && demo11 == 0)) {
                                            } else {
                                                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                    String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                        preparedStatement.setDouble(1, money1 - (Integer.valueOf(price) * min));
                                                        preparedStatement.setDouble(2, coin1 + (min * 0.995));
                                                        preparedStatement.setString(3, usernameBefore);
                                                        preparedStatement.executeUpdate();
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE manager SET money = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, moneyManager + (min * 0.01 * Integer.valueOf(price)));
                                                    preparedStatement.setString(2, "1111111111");
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                            return "true";
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                return "69";
            }
            return "false";
        }
    }
}
