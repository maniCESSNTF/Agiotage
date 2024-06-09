package com.example.demo1;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

import java.io.*;
import java.sql.*;
import java.util.Random;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CaptchaGenerator.captcha;

public class Profile {
    Random random = new Random();
    public String newCaptcha = GenerateCaptcha();
    public EmailSender emailSender = new EmailSender();
    public  String code;
    public int[] typeTransfer = {0,0,0,0,0,0};
    public int[] typeTransferEx = {0,0,0,0,0,0};


    public static String thisUsername;


    @FXML
    private RadioButton btnSellEx;
    @FXML
    private RadioButton btnBuyEx;

    @FXML
    private TextField txtamountEx;

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
    void PbtnDone(ActionEvent event) throws IOException {
        if (txtAmount.getText().isEmpty() || txtAccountNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();

//            LogeIn.str="d";

        }
        // Check if wallet has enough
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
        if(txtPasswordDeposit.getText().equals(code) && newCaptcha.equals(txtCaptchaCodeDeposit.getText())){
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
    void PmnuAvalanchEx(ActionEvent event) {
        typeTransferEx[1]=1;

    }

    @FXML
    void PmnuDayEx(ActionEvent event) {

        typeTransferEx[3]=1;
    }

    @FXML
    void PmnuLightCoinEx(ActionEvent event) {

        typeTransferEx[2]=1;
    }

    @FXML
    void PmnuStellarEx(ActionEvent event) {

        typeTransferEx[4]=1;
    }

    @FXML
    void PmnuonyEx(ActionEvent event) {

        typeTransferEx[5]=1;
    }

    @FXML
    void PmnurippelEx(ActionEvent event) {

        typeTransferEx[0]=1;
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
        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql1 = "SELECT * FROM signin WHERE username = ?";
            try (PreparedStatement statement1 = connection1.prepareStatement(sql1)) {
                statement1.setString(1, thisUsername);
                ResultSet resultSetME = statement1.executeQuery();
                if (resultSetME.next()) {
                    int yy = Integer.parseInt(resultSetME.getString("rippel")) - Integer.parseInt(txtAmountTra.getText());

                    System.out.println("YIIIIIIIIIIII" + yy);

                    if (typeTransfer[0] == 1&& Integer.parseInt(resultSetME.getString("rippel")) >= Integer.parseInt(txtAmountTra.getText())) {
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
        }
    }



}