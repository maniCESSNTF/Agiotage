package com.example.demo1;

import Email.EmailSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;
import static com.example.demo1.CombinedTask.ReadPrices.calendarTime;
import static com.example.demo1.Profile.FileOperations.writeToAndPrintFile;

public class Profile {
    Random random = new Random();
    public String newCaptcha = GenerateCaptcha();
    public EmailSender emailSender = new EmailSender();
    public  String code;
    public int[] typeTransfer = {0,0,0,0,0,0};
    public int[] typeTransferEx = {0,0,0,0,0,0};
    public int[] typeTransferSw = {0,0,0,0,0,0};
    public int[] typeTransferSw1 = {0,0,0,0,0,0};


    public static String thisUsername;

//    @FXML
//    public ImageView imgCaptchaCodeWthdrawal;
    @FXML
    private Button btnGoHomePage;

    @FXML
    private Button btnLogout;
    @FXML
    private Button btnDoneSWAP;

    @FXML
    private Button btnDoneEx;

    @FXML
    private MenuButton mnuTypeSWAP;

    @FXML
    private MenuButton mnuTypeSWAP1;

    @FXML
    private MenuItem mnuavalancheSWAP;

    @FXML
    private MenuItem mnuavalancheSWAP1;

    @FXML
    private MenuItem mnudaySWAP;
    @FXML
    private MenuItem LightcoinSWAP;

    @FXML
    private MenuItem LightcoinSWAP1;

    @FXML
    private MenuItem mnudaySWAP1;

    @FXML
    private MenuItem mnureppleSWAP;

    @FXML
    private MenuItem mnureppleSWAP1;

    @FXML
    private TextField txtamoutSWAP;

    @FXML
    private TextField txtamoutSWAP1;

    @FXML
    private MenuItem mnustellarSWAP;

    @FXML
    private MenuItem mnustellarSWAP1;

    @FXML
    private Button btnDoneWthdrawal;

    @FXML
    private Button btnRefreshCaptchaWthdrawal;

    @FXML
    private TextField txtAmountWthdrawal;

    @FXML
    private TextField txtAmountwWthdrawal;

    @FXML
    private TextField txtCodeWthdrawal;

    @FXML
    private TextField txtpriceEx1;

    @FXML
    private Tab TabProfile;

    @FXML
    private RadioButton btnSellEx;

    @FXML
    private RadioButton btnBuyEx;

    @FXML
    private TextField txtamountEx;

    @FXML
    private TextField txtmoaneyDeposit1;

    @FXML
    private MenuItem mnuAvalanchEx;

    @FXML
    private MenuButton mnuChoosEx;

    @FXML
    private MenuItem mnuDayEx;

    @FXML
    private MenuItem mnuLightCoinEx;

    @FXML
    private MenuItem mnuStellarEx;

    @FXML
    private MenuItem mnuonyEx;

    @FXML
    private MenuItem mnurippelEx;

    @FXML
    private Text txtWallet_id;

    @FXML
    private MenuItem coin1;

    @FXML
    private MenuItem coin2;

    @FXML
    private MenuItem coin3;

    @FXML
    private MenuItem coin4;

    @FXML
    private MenuItem coin5;

    @FXML
    private MenuItem coin6;

    @FXML
    private Button btnDoneDeposit;

    @FXML
    private Button btnRefreshCaptchaDeposit;

    @FXML
    private Button btnSendEmailDeposit;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnDoneTrans;

    @FXML
    private TextField txtWalletidTra;

    @FXML
    private TextField txtAmountTra;

    @FXML
    private Button btnDone;
    @FXML
    private RadioButton rbtnFromAccountoWallet;

    @FXML
    private RadioButton rbtnFromWallettoAccount;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtNewEmail;

    @FXML
    private TextField txtNewLastName;

    @FXML
    private  TextField txtNewName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewPhoneNumber;

    @FXML
    private Text btncurrentAsset;

    @FXML
    private TextField txtCVV2Deposit;

    @FXML
    private TextField txtCaptchaCodeDeposit;

    @FXML
    private TextField txtCardNumberDeposit;

    @FXML
    private TextField txtEXMonthDeposit;

    @FXML
    private TextField txtEXYearDeposit;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtLastName;

    @FXML
    public  Text txtName ;

    @FXML
    private Text txtPassword;

    @FXML
    private Text txtPhoneNumber;

    @FXML
    private TextField txtEmailDeposit;

    @FXML
    private Text txtUsername;

    @FXML
    private TextField txtPasswordDeposit;


    @FXML
    private Button btnFile;


    String urll=null;


    @FXML
    private ImageView ProfilePic;


    @FXML
    void PbtnDone(ActionEvent event) throws IOException {
        if (txtAmount.getText().isEmpty() || txtAccountNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void PbtnProfilePic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ProfilePic.setImage(image);
            urll=image.getUrl();
        //    ProfilePic.setId(image.getUrl());
        }
    }

    @FXML
    void PbtnChange(ActionEvent event) throws IOException, SQLException {
        if (txtNewEmail.getText().isEmpty() && txtNewLastName.getText().isEmpty() && txtNewPassword.getText().isEmpty() && txtNewName.getText().isEmpty() && txtNewPhoneNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Change at least one of them!");
            alert.showAndWait();
            txtUsername.setText(thisUsername);
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, thisUsername);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        txtName.setText(resultSet.getString("firstname"));
                        txtLastName.setText(resultSet.getString("lastname"));
                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
                        txtEmail.setText(resultSet.getString("email"));
                        txtPassword.setText(resultSet.getString("password"));
                        txtWallet_id.setText(resultSet.getString("walletid"));
                        txtUsername.setText(thisUsername);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (!txtNewName.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET firstname = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewName.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your name to: " + txtNewName.getText());
                alert.showAndWait();
            }
            if (!txtNewLastName.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET lastname = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewLastName.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Last Name Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your last name to: " + txtNewLastName.getText());
                alert.showAndWait();
            }
            if (!txtNewPassword.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET password = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewPassword.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your password to: " + txtNewPassword.getText());
                alert.showAndWait();
            }
            if (!txtNewPhoneNumber.getText().isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String query = "UPDATE signin SET phonenumber = ? WHERE username = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, txtNewPhoneNumber.getText());
                        preparedStatement.setString(2, thisUsername);
                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Phone Number Changed");
                alert.setHeaderText(null);
                alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
                alert.showAndWait();
            }
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sql = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, thisUsername);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        txtName.setText(resultSet.getString("firstname"));
                        txtLastName.setText(resultSet.getString("lastname"));
                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
                        txtEmail.setText(resultSet.getString("email"));
                        txtPassword.setText(resultSet.getString("password"));
                        txtWallet_id.setText(resultSet.getString("walletid"));
                        txtUsername.setText(thisUsername);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    void PbtnDoneDeposit(ActionEvent event) throws IOException, SQLException {
       // setUser("ssss");
        if(txtPasswordDeposit.getText().equals(code) ){//&& newCaptcha.equals(txtCaptchaCodeDeposit.getText())
            try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                    statementYOU1.setString(1, thisUsername);
                    ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                    if (resultSetYOU1.next()) {
                        try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryYOU = "UPDATE signin SET money = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                System.out.println(resultSetYOU1.getString("money"));
                                System.out.println(Integer.parseInt(txtmoaneyDeposit1.getText()));
//
                                Integer y =Integer.parseInt(resultSetYOU1.getString("money")) +Integer.parseInt(txtmoaneyDeposit1.getText());
                                System.out.println(y);

                                preparedStatementYOU.setString(1,String.valueOf(y));
                                preparedStatementYOU.setString(2, thisUsername);
                                preparedStatementYOU.executeUpdate();
                                System.out.println(y);


                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("The transaction was completed successfully");
            alert.showAndWait();
        }else{
            if(!newCaptcha.equals(txtCaptchaCodeDeposit.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Captcha code is wrong");
                alert.showAndWait();
                ActionEvent ee = new ActionEvent();
                PbtnRefreshCaptchaDeposit(ee);
                txtCaptchaCodeDeposit.setText("");
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Pass is wrong");
                alert.showAndWait();
                ActionEvent ee = new ActionEvent();
                PbtnRefreshCaptchaDeposit(ee);
                txtCaptchaCodeDeposit.setText("");
                txtPasswordDeposit.setText("");
            }
        }
    }
    @FXML
    public  ImageView imgCaptcha2;
    @FXML
    public void PbtnRefreshCaptchaDeposit(ActionEvent event) throws IOException {
        newCaptcha = GenerateCaptcha();
        captcha(newCaptcha);
        File file = new File("C:\\Users\\mania\\Desktop\\New folder (3)\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
        InputStream inputStream = null;
        try{
            inputStream = (InputStream) new FileInputStream(file);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        imgCaptcha2.setImage(new Image(inputStream));
        btnRefreshCaptchaDeposit.setLayoutY(402);
        btnRefreshCaptchaDeposit.setLayoutX(341);
    }

    @FXML
    void PbtnSendEmailDeposit(ActionEvent event) {
        System.out.println(txtEmailDeposit.getText());
        code = String.valueOf((random.nextInt(1000000))+1000000);
        emailSender.send(txtEmailDeposit.getText(),code);
    }
    public void setUser(String username) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql = "SELECT * FROM signin WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("firstname"));
//                    txtName.setText(resultSet.getString("firstname"));
                    txtName.setText("firstname");

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
    }

    @FXML
    void PbtnSellEx(ActionEvent event) {
        if (btnSellEx.isSelected()) {
            btnBuyEx.setSelected(false);
        }

    }

    @FXML
    void PbtnBuyEx(ActionEvent event) {
        if (btnBuyEx.isSelected()) {
            btnSellEx.setSelected(false);
        }
    }

    @FXML
    void PmnurippelEx(ActionEvent event) {

        typeTransferEx[0]=1;
        typeTransferEx[1]=0;
        typeTransferEx[2]=0;
        typeTransferEx[3]=0;
        typeTransferEx[4]=0;

    }

    @FXML
    void PmnuAvalanchEx(ActionEvent event) {
        typeTransferEx[0]=0;
        typeTransferEx[1]=1;
        typeTransferEx[2]=0;
        typeTransferEx[3]=0;
        typeTransferEx[4]=0;

    }

    @FXML
    void PmnuLightCoinEx(ActionEvent event) {
        typeTransferEx[0]=0;
        typeTransferEx[1]=0;
        typeTransferEx[2]=1;
        typeTransferEx[3]=0;
        typeTransferEx[4]=0;

    }

    @FXML
    void PmnuDayEx(ActionEvent event) {
        typeTransferEx[0]=0;
        typeTransferEx[1]=0;
        typeTransferEx[2]=0;
        typeTransferEx[3]=1;
        typeTransferEx[4]=0;
    }

    @FXML
    void PmnuStellarEx(ActionEvent event) {
        typeTransferEx[0]=0;
        typeTransferEx[1]=0;
        typeTransferEx[2]=0;
        typeTransferEx[3]=0;
        typeTransferEx[4]=1;
    }


    @FXML
    void PbtnDoneEx(ActionEvent event) throws SQLException {

        int newMoneyAmount=0;






        int moneyManager=0;
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










            if(Manager.closeOpen==0) {
            String url = "jdbc:mysql://localhost:3306/agiotage2";
            String user = "root";
            String password = "";

            String username1;
            double money1 = 0;
            double coin1 = 0;
            String username2;
            double money2 = 0;
            double coin2 = 0;
            int typeEX = 0;
            int sw = 0;
            String typeCoinEx = null;
            boolean SWExchange = false;
            double amountTxt = Double.parseDouble(txtamountEx.getText());
            String typeBefore;
            int priceBefore;
            int demo1=0;
            int demo2=0;


            String usernameBefore;
            String timeBefore;
            String dateBefore;
            double sellbuyBefore;

            String nowTime = calendarTime.formatTime(calendarTime.now());
            String nowDate = calendarTime.formatDate(calendarTime.now());

            if (btnSellEx.isSelected()) typeEX = 0;
            else typeEX = 1;

            if (typeTransferEx[0] == 1)
                typeCoinEx = "rippel";
            else if (typeTransferEx[1] == 1)
                typeCoinEx = "avalanche";
            else if (typeTransferEx[2] == 1)
                typeCoinEx = "lightcoin";
            else if (typeTransferEx[3] == 1)
                typeCoinEx = "day";
            else if (typeTransferEx[4] == 1)
                typeCoinEx = "stellar";

            //بررسی امکان ثبت درخواست تبادل جهت فروش
            try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
                try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                    statementYOU1.setString(1, thisUsername);
                    ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                    if (resultSetYOU1.next()) {
                        if (Double.valueOf(resultSetYOU1.getDouble(typeCoinEx)) > Double.parseDouble(txtamountEx.getText())) {
                            coin1 = resultSetYOU1.getDouble(typeCoinEx);
                            money1 = resultSetYOU1.getDouble("money");
                            demo1 = resultSetYOU1.getInt("demo");
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
            if (SWExchange || !btnSellEx.isSelected()) {
                try (Connection connectionSWAP = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String queryEx = "INSERT INTO exchange (username, time, date, sellbuy, type, amount, state, price, copyAmount,demo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatementSWAP = connectionSWAP.prepareStatement(queryEx)) {
                        CalendarTime calendarTime = new CalendarTime();
                        preparedStatementSWAP.setString(1, thisUsername);
                        preparedStatementSWAP.setString(2, nowTime);
                        preparedStatementSWAP.setString(3, nowDate);
                        preparedStatementSWAP.setInt(4, typeEX);
                        preparedStatementSWAP.setString(5, typeCoinEx);
                        preparedStatementSWAP.setDouble(6, Double.parseDouble(txtamountEx.getText()));
                        preparedStatementSWAP.setDouble(7, 0);
                        preparedStatementSWAP.setDouble(8, Double.parseDouble(txtpriceEx1.getText()));
                        preparedStatementSWAP.setDouble(9, Double.parseDouble(txtamountEx.getText()));
                        preparedStatementSWAP.setInt(10,demo1);
                        preparedStatementSWAP.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("you dont have enough : " + typeCoinEx);
                alert.showAndWait();
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
                        if (rs.getString("type").equals(typeCoinEx)) {// && typeTransferEx[0] == 1
                            if (btnBuyEx.isSelected() && rs.getInt("sellbuy") == 0) {
                                if (Integer.valueOf(rs.getInt("price")) <= Integer.parseInt(txtpriceEx1.getText())) {
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
                                    if (coin2 >= min && money1 >= min * Integer.parseInt(txtpriceEx1.getText()) && !thisUsername.equals(usernameBefore)) {
                                        double buyAmount = Double.parseDouble(txtamountEx.getText());
                                        double sellAmount = rs.getDouble("amount");
                                        int buyMoney = Integer.parseInt(txtpriceEx1.getText());
                                        int sellbuy = Integer.parseInt(txtpriceEx1.getText());
                                        System.out.println("min1 ; " + min);
                                        System.out.println("amountTxt1 ; " + amountTxt);
                                        System.out.println("sellAmount1 ; " + sellAmount);

                                        amountTxt -= min;
                                        sellAmount -= min;

                                        newMoneyAmount+=0.005*(min*Integer.parseInt(txtpriceEx1.getText()));

                                        //(فرد 2)اپدیت درخواس شخص فروشنده
                                        if((demo2==0 && demo1 ==1)) {

                                        }else{
//                                            method(money1,thisUsername,money2,usernameBefore,newMoneyAmount);
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
                                        if((demo2==1 && demo1 ==0)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, amountTxt);
                                                    if (amountTxt > 0)
                                                        preparedStatement.setDouble(2, 0);
                                                    else
                                                        preparedStatement.setInt(2, 1);
                                                    preparedStatement.setString(3, thisUsername);
                                                    preparedStatement.setString(4, nowTime);
                                                    preparedStatement.setString(5, nowDate);
                                                    preparedStatement.setInt(6, 1);
                                                    preparedStatement.setString(7, typeCoinEx);
                                                    preparedStatement.setInt(8, Integer.parseInt(txtpriceEx1.getText()));
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        //اپدیت اطلاعات دارایی شخص خریدار
                                        if((demo2==1 && demo1 ==0)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, money1 - (Integer.parseInt(txtpriceEx1.getText()) * min) );
                                                    preparedStatement.setDouble(2, coin1 + min*(0.995));
                                                    preparedStatement.setString(3, thisUsername);
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        //اپدیت اطلاعات دارایی شخص فروشنده
                                        if((demo2==0 && demo1 ==1)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, money2 + ((Integer.parseInt(txtpriceEx1.getText()) * min)*0.995));
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
                                                preparedStatement.setDouble(1, moneyManager +  (min*0.005*priceBefore));
                                                preparedStatement.setString(2, "1111111111");
                                                preparedStatement.executeUpdate();
                                            }
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }
                            } else if (btnSellEx.isSelected() && rs.getInt("sellbuy") == 1) {
                                if (Integer.valueOf(rs.getInt("price")) >= Integer.parseInt(txtpriceEx1.getText())) {
                                    double min = 0;
                                    int demo11=0;
                                    int demo22=0;
                                    usernameBefore = rs.getString("username");
                                    timeBefore = rs.getString("time");
                                    dateBefore = rs.getString("date");
                                    typeBefore = rs.getString("type");
                                    sellbuyBefore = rs.getInt("amount");
                                    priceBefore = rs.getInt("price");

                                    //خواندن اطلاعات فرد خریدار برای انجام تبادل
                                    String querybef = "SELECT * FROM signin WHERE username = ?";
                                    try (Connection connection = DriverManager.getConnection(url, user, password);
                                         PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
                                        preparedStatement.setString(1, thisUsername);
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

                                    if (coin2 >= min && money1 >= min * Integer.parseInt(txtpriceEx1.getText()) && !thisUsername.equals(usernameBefore)) {
                                        double buyAmount = Double.parseDouble(txtamountEx.getText());
                                        double sellAmount = rs.getDouble("amount");
                                        int buyMoney = Integer.parseInt(txtpriceEx1.getText());


                                        amountTxt -= min;
                                        sellAmount -= min;

                                        //اپدیت درخواس شخص خریدار
                                        if(demo22==1 && demo11 ==0) {

                                        }else {
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
                                        if((demo22==0 && demo11 ==1)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE exchange SET amount = ?, state = ? WHERE username = ? AND time = ? AND date = ? AND sellbuy = ? AND type = ? AND price = ?";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, amountTxt);
                                                    if (amountTxt > 0)
                                                        preparedStatement.setDouble(2, amountTxt);
                                                    else
                                                        preparedStatement.setInt(2, 1);
                                                    preparedStatement.setString(3, thisUsername);
                                                    preparedStatement.setString(4, nowTime);
                                                    preparedStatement.setString(5, nowDate);
                                                    preparedStatement.setInt(6, 0);
                                                    preparedStatement.setString(7, typeCoinEx);
                                                    preparedStatement.setInt(8, Integer.parseInt(txtpriceEx1.getText()));
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }

                                        //اپدیت اطلاعات دارایی شخص فروشنده
                                        if((demo22==0 && demo11 ==1)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, money2 + ((Integer.parseInt(txtpriceEx1.getText()) * min)*0.995));
                                                    preparedStatement.setDouble(2, coin2 - min);
                                                    preparedStatement.setString(3, thisUsername);
                                                    preparedStatement.executeUpdate();
                                                }
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        //اپدیت اطلاعات دارایی شخص فروشنده
                                        if((demo22==1 && demo11 ==0)) {

                                        }else {
                                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                                String query = "UPDATE signin SET money = ?, " + typeCoinEx + " = ? WHERE username = ? ";//AND amount = ?
                                                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                                    preparedStatement.setDouble(1, money1 - (Integer.parseInt(txtpriceEx1.getText()) * min));
                                                    preparedStatement.setDouble(2, coin1 + (min*0.995));
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
                                                preparedStatement.setDouble(1, moneyManager +  (min*0.005*priceBefore));
                                                preparedStatement.setString(2, "1111111111");
                                                preparedStatement.executeUpdate();
                                            }
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("we are closed !");
            alert.showAndWait();
        }
    }







    @FXML
    void mnuCoin1(ActionEvent event) {
        typeTransfer[0]=1;
    }

    @FXML
    void mnucoin2(ActionEvent event) {
        typeTransfer[1]=1;
    }

    @FXML
    void mnuCoin3(ActionEvent event) {
        typeTransfer[2]=1;
    }

    @FXML
    void mnuCoin4(ActionEvent event) {
        typeTransfer[3]=1;
    }

    @FXML
    void mnuCoin5(ActionEvent event) {
        typeTransfer[4]=1;
    }

    @FXML
    void mnuCoin6(ActionEvent event) {
        typeTransfer[5]=1;
    }

    @FXML
    void PbtnDonetrans(ActionEvent event) throws SQLException {
        int demo=0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql = "SELECT * FROM signin WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, thisUsername);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    demo = resultSet.getInt("demo");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }




        if(demo==0)
        {
        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql1 = "SELECT * FROM signin WHERE username = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql1)) {
                statement1.setString(1, thisUsername);
                ResultSet resultSetME = statement1.executeQuery();
                if (resultSetME.next()) {
                    int yy = Integer.parseInt(resultSetME.getString("rippel")) - Integer.parseInt(txtAmountTra.getText());

                    System.out.println("YIIIIIIIIIIII" + yy);

                    if (typeTransfer[0] == 1) {
                        //&& Integer.parseInt(resultSetME.getString("rippel")) >= Integer.parseInt(txtAmountTra.getText())
                        try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryME = "UPDATE signin SET rippel = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                int y = Integer.parseInt(resultSetME.getString("rippel")) - Integer.parseInt(txtAmountTra.getText());
                                preparedStatementME.setString(1, String.valueOf(y));
                                preparedStatementME.setString(2, thisUsername);
                                preparedStatementME.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                            try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                statementYOU1.setString(1, txtWalletidTra.getText());
                                ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                if (resultSetYOU1.next()) {
                                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryYOU = "UPDATE signin SET rippel = ? WHERE walletid = ?";
                                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                            int y = Integer.parseInt(resultSetYOU1.getString("rippel")) + Integer.parseInt(txtAmountTra.getText());
                                            preparedStatementYOU.setString(1, String.valueOf(y));
                                            preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                            preparedStatementYOU.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
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
                    } else if (typeTransfer[1] == 1&& Integer.parseInt(resultSetME.getString("avalanche")) >= Integer.parseInt(txtAmountTra.getText())) {
                    try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryME = "UPDATE signin SET avalanche = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                            int y = Integer.parseInt(resultSetME.getString("avalanche")) - Integer.parseInt(txtAmountTra.getText());
                            preparedStatementME.setString(1, String.valueOf(y));
                            preparedStatementME.setString(2, thisUsername);
                            preparedStatementME.executeUpdate();

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                        try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                            statementYOU1.setString(1, txtWalletidTra.getText());
                            ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                            if (resultSetYOU1.next()) {
                                try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                    String queryYOU = "UPDATE signin SET avalanche = ? WHERE walletid = ?";
                                    try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                        int y = Integer.parseInt(resultSetYOU1.getString("avalanche")) + Integer.parseInt(txtAmountTra.getText());
                                        preparedStatementYOU.setString(1, String.valueOf(y));
                                        preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                        preparedStatementYOU.executeUpdate();
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
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
                } else if (typeTransfer[2] == 1&& Integer.parseInt(resultSetME.getString("lightcoin")) >= Integer.parseInt(txtAmountTra.getText())) {
                        try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryME = "UPDATE signin SET lightcoin = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                int y = Integer.parseInt(resultSetME.getString("lightcoin")) - Integer.parseInt(txtAmountTra.getText());
                                preparedStatementME.setString(1, String.valueOf(y));
                                preparedStatementME.setString(2, thisUsername);
                                preparedStatementME.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                            try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                statementYOU1.setString(1, txtWalletidTra.getText());
                                ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                if (resultSetYOU1.next()) {
                                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryYOU = "UPDATE signin SET lightcoin = ? WHERE walletid = ?";
                                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                            int y = Integer.parseInt(resultSetYOU1.getString("lightcoin")) + Integer.parseInt(txtAmountTra.getText());
                                            preparedStatementYOU.setString(1, String.valueOf(y));
                                            preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                            preparedStatementYOU.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
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
                    } else if (typeTransfer[3] == 1&& Integer.parseInt(resultSetME.getString("day")) >= Integer.parseInt(txtAmountTra.getText())) {
                        try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryME = "UPDATE signin SET day = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                int y = Integer.parseInt(resultSetME.getString("day")) - Integer.parseInt(txtAmountTra.getText());
                                preparedStatementME.setString(1, String.valueOf(y));
                                preparedStatementME.setString(2, thisUsername);
                                preparedStatementME.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                            try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                statementYOU1.setString(1, txtWalletidTra.getText());
                                ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                if (resultSetYOU1.next()) {
                                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryYOU = "UPDATE signin SET day = ? WHERE walletid = ?";
                                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                            int y = Integer.parseInt(resultSetYOU1.getString("day")) + Integer.parseInt(txtAmountTra.getText());
                                            preparedStatementYOU.setString(1, String.valueOf(y));
                                            preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                            preparedStatementYOU.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
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
                    } else if (typeTransfer[4] == 1&& Integer.parseInt(resultSetME.getString("stellar")) >= Integer.parseInt(txtAmountTra.getText())) {
                        try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryME = "UPDATE signin SET stellar = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                int y = Integer.parseInt(resultSetME.getString("stellar")) - Integer.parseInt(txtAmountTra.getText());
                                preparedStatementME.setString(1, String.valueOf(y));
                                preparedStatementME.setString(2, thisUsername);
                                preparedStatementME.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                            try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                statementYOU1.setString(1, txtWalletidTra.getText());
                                ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                if (resultSetYOU1.next()) {
                                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryYOU = "UPDATE signin SET stellar = ? WHERE walletid = ?";
                                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                            int y = Integer.parseInt(resultSetYOU1.getString("stellar")) + Integer.parseInt(txtAmountTra.getText());
                                            preparedStatementYOU.setString(1, String.valueOf(y));
                                            preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                            preparedStatementYOU.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
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
                    } else if (typeTransfer[5] == 1&& Integer.parseInt(resultSetME.getString("money")) >= Integer.parseInt(txtAmountTra.getText())) {
                        try (Connection connectionME = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String queryME = "UPDATE signin SET money = ? WHERE username = ?";
                            try (PreparedStatement preparedStatementME = connectionME.prepareStatement(queryME)) {
                                int y = Integer.parseInt(resultSetME.getString("money")) - Integer.parseInt(txtAmountTra.getText());
                                preparedStatementME.setString(1, String.valueOf(y));
                                preparedStatementME.setString(2, thisUsername);
                                preparedStatementME.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String sqlYOU1 = "SELECT * FROM signin WHERE walletid = ?";
                            try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
                                statementYOU1.setString(1, txtWalletidTra.getText());
                                ResultSet resultSetYOU1 = statementYOU1.executeQuery();
                                if (resultSetYOU1.next()) {
                                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                                        String queryYOU = "UPDATE signin SET money = ? WHERE walletid = ?";
                                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                                            int y = Integer.parseInt(resultSetYOU1.getString("money")) + Integer.parseInt(txtAmountTra.getText());
                                            preparedStatementYOU.setString(1, String.valueOf(y));
                                            preparedStatementYOU.setString(2, txtWalletidTra.getText());
                                            preparedStatementYOU.executeUpdate();
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
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
                    }
            }else {
                    System.err.println("Wallet_id not found!");
                }
            }
        }}
        else {  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();

        }
    }

    @FXML
    public void initialize() {

        TabProfile.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                    String sql = "SELECT * FROM signin WHERE username = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, thisUsername);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            txtName.setText(resultSet.getString("firstname"));
                            txtLastName.setText(resultSet.getString("lastname"));
                            txtPhoneNumber.setText(resultSet.getString("phonenumber"));
                            txtEmail.setText(resultSet.getString("email"));
                            txtPassword.setText(resultSet.getString("password"));
                            txtWallet_id.setText(resultSet.getString("walletid"));
                            txtUsername.setText(thisUsername);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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


        ProfilePic.setId(urll);
    }

@FXML
void PbtnDoneSWAP(ActionEvent event) throws SQLException {
if(Manager.closeOpen==0){
    String url = "jdbc:mysql://localhost:3306/agiotage2";
    String user = "root";
    String password = "";

    double priceUSD = 0;
    double priceEUR = 0;
    double priceTOMAN = 0;
    double priceYEN = 0;
    double priceGBP = 0;
    String typeSell = null;
    String typeBuy = null;

    String query = "SELECT * FROM prices WHERE TIMA = ?";
    boolean sw = true;
    do {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "00:35:35.985667");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    priceUSD = resultSet.getDouble("USD");
                    priceEUR = resultSet.getDouble("EUR");
                    priceTOMAN = resultSet.getDouble("TOMAN");
                    priceYEN = resultSet.getDouble("YEN");
                    priceGBP = resultSet.getDouble("GBP");
                    sw = false;
                } else {
                    sw = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } while (sw);

    // خواندن قیمت
    try (Connection connectionYOU1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
        String sqlYOU1 = "SELECT * FROM signin WHERE username = ?";
        try (PreparedStatement statementYOU1 = connectionYOU1.prepareStatement(sqlYOU1)) {
            statementYOU1.setString(1, thisUsername);
            ResultSet resultSetYOU1 = statementYOU1.executeQuery();

            double priceSelectCoinsell = 0;
            double priceSelectCoinbuy = 0;
            double amuntBuy = 0;
            boolean swSWAP = false;

            if (typeTransferSw[0] == 1) {
                priceSelectCoinsell = priceUSD;
            } else if (typeTransferSw[1] == 1) {
                priceSelectCoinsell = priceEUR;
            } else if (typeTransferSw[2] == 1) {
                priceSelectCoinsell = priceTOMAN;
            } else if (typeTransferSw[3] == 1) {
                priceSelectCoinsell = priceYEN;
            } else if (typeTransferSw[4] == 1) {
                priceSelectCoinsell = priceGBP;
            }

            if (typeTransferSw1[0] == 1) {
                priceSelectCoinbuy = priceUSD;
                amuntBuy = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
                System.out.println("priceSelectCoinsell" + priceSelectCoinsell);
                System.out.println("Integer.parseInt(txtamoutSWAP.getText())" + Integer.parseInt(txtamoutSWAP.getText()));
                System.out.println("priceSelectCoinbuy" + priceSelectCoinbuy);
            } else if (typeTransferSw1[1] == 1) {
                priceSelectCoinbuy = priceEUR;
                amuntBuy = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
                System.out.println("priceSelectCoinsell" + priceSelectCoinsell);
                System.out.println("Integer.parseInt(txtamoutSWAP.getText())" + Integer.parseInt(txtamoutSWAP.getText()));
                System.out.println("priceSelectCoinbuy" + priceSelectCoinbuy);
            } else if (typeTransferSw1[2] == 1) {
                priceSelectCoinbuy = priceTOMAN;
                amuntBuy = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
            } else if (typeTransferSw1[3] == 1) {
                priceSelectCoinbuy = priceYEN;
                amuntBuy = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
            } else if (typeTransferSw1[4] == 1) {
                priceSelectCoinbuy = priceGBP;
                amuntBuy = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
            }

            System.out.println(amuntBuy + "+++++++++++++++++++++++");

            if (resultSetYOU1.next()) {
                if (typeTransferSw[0] == 1 && Double.parseDouble(resultSetYOU1.getString("rippel")) >= Double.parseDouble(txtamoutSWAP.getText())) {
                    swSWAP = true;
                    typeSell = "rippel";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET rippel = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("rippel")) - Double.parseDouble(txtamoutSWAP.getText());
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                            System.out.println("ava-------------------------------" + y);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw[1] == 1 && Double.parseDouble(resultSetYOU1.getString("avalanche")) >= Double.parseDouble(txtamoutSWAP.getText())) {
                    swSWAP = true;
                    typeSell = "avalanche";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET avalanche = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("avalanche")) - Double.parseDouble(txtamoutSWAP.getText());
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw[2] == 1 && Double.parseDouble(resultSetYOU1.getString("lightcoin")) >= Double.parseDouble(txtamoutSWAP.getText())) {
                    swSWAP = true;
                    typeSell = "lighcoin";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET lighcoin = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("lighcoin")) - Double.parseDouble(txtamoutSWAP.getText());
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw[3] == 1 && Double.parseDouble(resultSetYOU1.getString("day")) >= Double.parseDouble(txtamoutSWAP.getText())) {
                    swSWAP = true;
                    typeSell = "day";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET day = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("day")) - Double.parseDouble(txtamoutSWAP.getText());
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw[4] == 1 && Double.parseDouble(resultSetYOU1.getString("stellar")) >= Double.parseDouble(txtamoutSWAP.getText())) {
                    swSWAP = true;
                    typeSell = "stellar";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET stellar = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("stellar")) - Double.parseDouble(txtamoutSWAP.getText());
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (typeTransferSw1[0] == 1 && swSWAP) {
                    typeBuy = "rippel";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET rippel = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("rippel")) + amuntBuy;
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw1[1] == 1 && swSWAP) {
                    typeBuy = "avalanche";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET avalanche = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("avalanche")) + amuntBuy;
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw1[2] == 1 && swSWAP) {
                    typeBuy = "lightcoin";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET lightcoin = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("lightcoin")) + amuntBuy;
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw1[3] == 1 && swSWAP) {
                    typeBuy = "day";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET day = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("day")) + amuntBuy;
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (typeTransferSw1[4] == 1 && swSWAP) {
                    typeBuy = "stellar";
                    try (Connection connectionYOU = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String queryYOU = "UPDATE signin SET stellar = ? WHERE username = ?";
                        try (PreparedStatement preparedStatementYOU = connectionYOU.prepareStatement(queryYOU)) {
                            double y = Double.parseDouble(resultSetYOU1.getString("stellar")) + amuntBuy;
                            preparedStatementYOU.setString(1, String.valueOf(y));
                            preparedStatementYOU.setString(2, thisUsername);
                            preparedStatementYOU.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (swSWAP) {
                    try (Connection connectionSWAP = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                        String querySWAP = "INSERT INTO swap (username, time, date, typebuy, amountbuy, typesell, amountsell) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatementSWAP = connectionSWAP.prepareStatement(querySWAP)) {
                            CalendarTime calendarTime = new CalendarTime();
                            preparedStatementSWAP.setString(1, thisUsername);
                            preparedStatementSWAP.setString(2, calendarTime.formatTime(calendarTime.now()));
                            preparedStatementSWAP.setString(3, calendarTime.formatDate(calendarTime.now()));
                            preparedStatementSWAP.setString(4, typeBuy);
                            preparedStatementSWAP.setDouble(5, amuntBuy);
                            preparedStatementSWAP.setString(6, typeSell);
                            preparedStatementSWAP.setDouble(7, Double.parseDouble(txtamoutSWAP.getText()));
                            preparedStatementSWAP.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                for (int i = 0; i < typeTransferSw.length; i++) {
                    typeTransferSw[i] = 0;
                    typeTransferSw1[i] = 0;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("User not found!");
                alert.showAndWait();
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}else{
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText("we are closed !");
    alert.showAndWait();
}
}





//    public  void method(double money1,String thisUsername,double money2,String usernameBefore,double newMoneyAmount){
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//            String query = "UPDATE signin SET money = ? WHERE username = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, usernameBefore);
//                preparedStatement.setDouble(2, money2-(0.5*newMoneyAmount));
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//            String query = "UPDATE signin SET money = ? WHERE username = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, thisUsername);
//                preparedStatement.setDouble(2, money1-(0.5*newMoneyAmount));
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @FXML
    void PbtnFile(ActionEvent event) throws IOException, SQLException {

        String date= null;
        String time = null;
        int sellbuy=0;
        int price=0;
        double amount=0;
        String type= null;
        int state=0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql = "SELECT * FROM exchange WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, thisUsername);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    date= resultSet.getString("date");
                    time= resultSet.getString("time");
                    sellbuy= resultSet.getInt("sellbuy");
                    price= resultSet.getInt("price");
                    amount= resultSet.getDouble("copyAmount");
                    type= resultSet.getString("type");
                    state = resultSet.getInt("state");
                    writeToAndPrintFile(sellbuy, type, state, amount, price, date, time);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }





    public class FileOperations {
        private static final String FILENAME = "data.txt";

        public static void writeToAndPrintFile(int arg1, String arg2, int arg3, double arg4, int arg5, String arg6, String arg7) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true));
                 BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {

                // Write to file
                writer.println(arg1 + "," + arg2 + "," + arg3 + "," + arg4 + "," + arg5 + "," + arg6 + "," + arg7);
                writer.flush(); // Ensure the data is written immediately

                // Print file contents
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    @FXML
    void PmnureppleSWAP(ActionEvent event) {
        typeTransferSw[0]=1;
        typeTransferSw[1]=0;
        typeTransferSw[2]=0;
        typeTransferSw[3]=0;
        typeTransferSw[4]=0;
    }


    @FXML
    void PmnuavalancheSWAP(ActionEvent event) {
        typeTransferSw[0]=0;
        typeTransferSw[1]=1;
        typeTransferSw[2]=0;
        typeTransferSw[3]=0;
        typeTransferSw[4]=0;
    }

    @FXML
    void PmnuLightcoinSWAP(ActionEvent event) {
        typeTransferSw[0]=0;
        typeTransferSw[1]=0;
        typeTransferSw[2]=1;
        typeTransferSw[3]=0;
        typeTransferSw[4]=0;


    }
    @FXML
    void PmnudaySWAP(ActionEvent event) {
        typeTransferSw[0]=0;
        typeTransferSw[1]=0;
        typeTransferSw[2]=0;
        typeTransferSw[3]=1;
        typeTransferSw[4]=0;


    }
    @FXML
    void PmnustellarSWAP(ActionEvent event) {
        typeTransferSw[0]=0;
        typeTransferSw[1]=0;
        typeTransferSw[2]=0;
        typeTransferSw[3]=0;
        typeTransferSw[4]=1;

    }



    @FXML
    void PmnureppleSWAP1(ActionEvent event) {
        typeTransferSw1[0]=1;
        typeTransferSw1[1]=0;
        typeTransferSw1[2]=0;
        typeTransferSw1[3]=0;
        typeTransferSw1[4]=0;

    }


    @FXML
    void PmnuavalancheSWAP1(ActionEvent event) {

        typeTransferSw1[0]=0;
        typeTransferSw1[1]=1;
        typeTransferSw1[2]=0;
        typeTransferSw1[3]=0;
        typeTransferSw1[4]=0;
    }

    @FXML
    void PmnuLightcoinSWAP1(ActionEvent event) {
        typeTransferSw1[0]=0;
        typeTransferSw1[1]=0;
        typeTransferSw1[2]=1;
        typeTransferSw1[3]=0;
        typeTransferSw1[4]=0;

    }
    @FXML
    void PmnudaySWAP1(ActionEvent event) {
        typeTransferSw1[0]=0;
        typeTransferSw1[1]=0;
        typeTransferSw1[2]=0;
        typeTransferSw1[3]=1;
        typeTransferSw1[4]=0;

    }
    @FXML
    void PmnustellarSWAP1(ActionEvent event) {
        typeTransferSw1[0]=0;
        typeTransferSw1[1]=0;
        typeTransferSw1[2]=0;
        typeTransferSw1[3]=0;
        typeTransferSw1[4]=1;
    }

    @FXML
    void PbtnRefreshCaptchaWthdrawal(ActionEvent event) {

    }

    @FXML
    void PbtnDoneWthdrawal(ActionEvent event) {

    }

    @FXML
    void PbtnGoHomePage(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnGoHomePage.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
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

        String querybef = "SELECT * FROM exchange WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(Url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
            preparedStatement.setString(1, thisUsername);
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

