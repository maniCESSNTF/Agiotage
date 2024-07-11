package com.example.demo1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.transform.Source;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo1.CaptchaGenerator.GenerateCaptcha;
import static com.example.demo1.CombinedTask.ReadPrices.calendarTime;
import static com.example.demo1.Methods.UserNumber;
import static com.example.demo1.Methods.users;
import static com.example.demo1.Profile.FileOperations.writeToAndPrintFile;
import static javafx.application.Application.launch;

public class Profile {
    private Client client;
    private String thisUsername;
    private String captchaCode = null;
    private String code = null;
    double amuntBuyForSwap = 0;
    boolean swSwap = false;
    String username2;


    public void setClient(Client client, String thisUsername) {
        this.client = client;
        this.thisUsername = thisUsername;
        username2 = thisUsername;
        initialize();

    }

    Random random = new Random();
    public int[] typeTransfer = {0, 0, 0, 0, 0, 0};
    public int[] typeTransferEx = {0, 0, 0, 0, 0, 0};
    public int[] typeTransferSw = {0, 0, 0, 0, 0, 0};
    public int[] typeTransferSw1 = {0, 0, 0, 0, 0, 0};


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






    @FXML
    private TableView<EX> EXtable;
    @FXML
    private TableColumn<EX, LocalDate> EXtableDate;
    @FXML
    private TableColumn<EX, LocalTime> EXtableTime;
    @FXML
    private TableColumn<EX, LocalTime> EXtableSellBuy;
    @FXML
    private TableColumn<EX, Integer> EXtablePrice;
    @FXML
    private TableColumn<EX, Integer> EXtableAmount;
    @FXML
    private TableColumn<EX, Integer> EXtableState;


    private ObservableList<EX> EXTable;







    @FXML
    public ImageView imgCaptcha2;

    @FXML
    public ImageView imageCaptcha3;

    @FXML
    public ImageView C;

    @FXML
    private Button btnGoHomePage;

    @FXML
    private Text txtCurrentTime;

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
    public static TextField Clock;

    @FXML
    private TextField txtCodeWthdrawal;

    @FXML
    private TextField txtpriceEx1;

    @FXML
    private Text txtMyRippel;

    @FXML
    private Text txtUserUsername;

    @FXML
    private Text txtMyAvalanche;

    @FXML
    private Text txtMyLightCoin;

    @FXML
    private Text txtMyday;

    @FXML
    private Text txtMyStellar;

    @FXML
    private Tab TabProfile;

    @FXML
    private Tab TabTarikh;

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
    private Button btnMasage;

    @FXML
    private Button ShowTableRippel;

    @FXML
    private Button ShowTableAvalanche;

    @FXML
    private Button ShowTableDay;

    @FXML
    private Button ShowTableLightCoin;

    @FXML
    private Button ShowTableStellar;

    @FXML
    void PShowTableRippel(ActionEvent event) {
        EXTable = FXCollections.observableArrayList();
        EXtableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EXtableSellBuy.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
        EXtablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        EXtableState.setCellValueFactory(new PropertyValueFactory<>("state"));
        EXtableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EXtableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EXtable.setItems(EXTable);
        EXtableFORexchangeTAB("rippel");

    }

    @FXML
    void PShowTableAvalanche(ActionEvent event) {
        EXTable = FXCollections.observableArrayList();
        EXtableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EXtableSellBuy.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
        EXtablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        EXtableState.setCellValueFactory(new PropertyValueFactory<>("state"));
        EXtableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EXtableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EXtable.setItems(EXTable);
        EXtableFORexchangeTAB("avalanche");
    }

    @FXML
    void PShowTableDay(ActionEvent event) {
        EXTable = FXCollections.observableArrayList();
        EXtableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EXtableSellBuy.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
        EXtablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        EXtableState.setCellValueFactory(new PropertyValueFactory<>("state"));
        EXtableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EXtableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EXtable.setItems(EXTable);
        EXtableFORexchangeTAB("day");
    }

    @FXML
    void PShowTableLightCoin(ActionEvent event) {
        EXTable = FXCollections.observableArrayList();
        EXtableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EXtableSellBuy.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
        EXtablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        EXtableState.setCellValueFactory(new PropertyValueFactory<>("state"));
        EXtableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EXtableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EXtable.setItems(EXTable);
        EXtableFORexchangeTAB("lightcoin");
    }

    @FXML
    void PShowTableStellar(ActionEvent event) {
        EXTable = FXCollections.observableArrayList();
        EXtableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EXtableSellBuy.setCellValueFactory(new PropertyValueFactory<>("sellbuy"));
        EXtablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        EXtableState.setCellValueFactory(new PropertyValueFactory<>("state"));
        EXtableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EXtableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EXtable.setItems(EXTable);
        EXtableFORexchangeTAB("stellar");
    }

    @FXML
    void PbtnMasage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MasagePage.fxml"));
            Parent mainView = loader.load();

            MasagePage masagePage = loader.getController();
            masagePage.setClient(client, thisUsername);

            Scene scene = new Scene(mainView);
            Stage primaryStage = (Stage) btnMasage.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button btnComputing;

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
    private TextField txtNewName;

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
    public Text txtName;

    @FXML
    private Text txtPassword;

    @FXML
    private Text txtPhoneNumber;

    @FXML
    private TextField txtEmailDeposit;

    @FXML
    private Text txtUsername;

    @FXML
    private Text txtMoneyProfile;

    @FXML
    private TextField txtPasswordDeposit;

    @FXML
    private Button btnRefreshWallet;

    @FXML
    private Button btnFile;


    String urll = null;

    @FXML
    private Button btnChangeCoins;

    @FXML
    private ImageView ProfilePicUp;

    @FXML
    private ImageView ProfilePic;

    @FXML
    void PbtnSendEmailDeposit(ActionEvent event) {
        if (client != null) {
            String response = client.sendMessage("PbtnSendEmailDeposit," + thisUsername + "," + txtEmailDeposit.getText());
            System.out.println("Response from server: " + response);
            if (response != null) {
                code = response;
            }
        } else {
            System.out.println("Client is not initialized");
        }
    }


    @FXML
    void PbtnSellEx(ActionEvent event) {
        btnBuyEx.setSelected(!btnSellEx.isSelected());
    }

    @FXML
    void PbtnBuyEx(ActionEvent event) {
        btnSellEx.setSelected(!btnBuyEx.isSelected());
    }

    @FXML
    void PbtnChangeCoins(ActionEvent event) {

        int s1, s2, s3, s4, s5;
        String stringS;

        s1 = typeTransferSw1[0];
        s2 = typeTransferSw1[1];
        s3 = typeTransferSw1[2];
        s4 = typeTransferSw1[3];
        s5 = typeTransferSw1[4];

        typeTransferSw1[0] = typeTransferSw[0];
        typeTransferSw1[1] = typeTransferSw[1];
        typeTransferSw1[2] = typeTransferSw[2];
        typeTransferSw1[3] = typeTransferSw[3];
        typeTransferSw1[4] = typeTransferSw[4];

        typeTransferSw[0] = s1;
        typeTransferSw[1] = s2;
        typeTransferSw[2] = s3;
        typeTransferSw[3] = s4;
        typeTransferSw[4] = s5;

        stringS = mnuTypeSWAP.getText();
        mnuTypeSWAP.setText(mnuTypeSWAP1.getText());
        mnuTypeSWAP1.setText(stringS);
        swSwap = false;
        txtamoutSWAP.setText("");
        txtamoutSWAP1.setText("");
    }

    @FXML
    void PbtnRefreshWallet(ActionEvent event) {
       initialize();
    }

    static boolean isPositiveInteger(String input) {

        Matcher matcher = Pattern.compile("^[1-9]\\d*$").matcher(input);
        return matcher.matches();
    }


    static boolean checkPositiveNumber(String input) {
        Matcher matcher = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$").matcher(input);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void PbtnDone(ActionEvent event) {
        if (txtAmount.getText().isEmpty() || txtAccountNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Complete all the parts!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void PbtnProfilePic() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getCanonicalPath());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ProfilePic.setImage(image);
            ProfilePicUp.setImage(image);
            urll = image.getUrl();

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                String query = "UPDATE signin SET imagePath = ? WHERE username = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, selectedFile.getCanonicalPath());
                    preparedStatement.setString(2, thisUsername);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
//
//    @FXML
//    void PbtnChange(ActionEvent event) throws SQLException {
//        if (txtNewEmail.getText().isEmpty() && txtNewLastName.getText().isEmpty() && txtNewPassword.getText().isEmpty() && txtNewName.getText().isEmpty() && txtNewPhoneNumber.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText(null);
//            alert.setContentText("Change at least one of them!");
//            alert.showAndWait();
//            txtUsername.setText(thisUsername);
//            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                String sql = "SELECT * FROM signin WHERE username = ?";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    statement.setString(1, thisUsername);
//                    ResultSet resultSet = statement.executeQuery();
//                    if (resultSet.next()) {
//                        txtName.setText(resultSet.getString("firstname"));
//                        txtLastName.setText(resultSet.getString("lastname"));
//                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
//                        txtEmail.setText(resultSet.getString("email"));
//                        txtPassword.setText(resultSet.getString("password"));
//                        txtWallet_id.setText(resultSet.getString("walletid"));
//                        txtUsername.setText(thisUsername);
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        } else {
//            if (!txtNewName.getText().isEmpty()) {
//                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                    String query = "UPDATE signin SET firstname = ? WHERE username = ?";
//                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                        preparedStatement.setString(1, txtNewName.getText());
//                        preparedStatement.setString(2, thisUsername);
//                        preparedStatement.executeUpdate();
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Name Changed");
//                alert.setHeaderText(null);
//                alert.setContentText("You changed your name to: " + txtNewName.getText());
//                alert.showAndWait();
//            }
//            if (!txtNewLastName.getText().isEmpty()) {
//                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                    String query = "UPDATE signin SET lastname = ? WHERE username = ?";
//                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                        preparedStatement.setString(1, txtNewLastName.getText());
//                        preparedStatement.setString(2, thisUsername);
//                        preparedStatement.executeUpdate();
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Last Name Changed");
//                alert.setHeaderText(null);
//                alert.setContentText("You changed your last name to: " + txtNewLastName.getText());
//                alert.showAndWait();
//            }
//            if (!txtNewPassword.getText().isEmpty()) {
//                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                    String query = "UPDATE signin SET password = ? WHERE username = ?";
//                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                        preparedStatement.setString(1, txtNewPassword.getText());
//                        preparedStatement.setString(2, thisUsername);
//                        preparedStatement.executeUpdate();
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Password Changed");
//                alert.setHeaderText(null);
//                alert.setContentText("You changed your password to: " + txtNewPassword.getText());
//                alert.showAndWait();
//            }
//            if (!txtNewPhoneNumber.getText().isEmpty()) {
//                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                    String query = "UPDATE signin SET phonenumber = ? WHERE username = ?";
//                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                        preparedStatement.setString(1, txtNewPhoneNumber.getText());
//                        preparedStatement.setString(2, thisUsername);
//                        preparedStatement.executeUpdate();
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Phone Number Changed");
//                alert.setHeaderText(null);
//                alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
//                alert.showAndWait();
//            }
//            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
//                String sql = "SELECT * FROM signin WHERE username = ?";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    statement.setString(1, thisUsername);
//                    ResultSet resultSet = statement.executeQuery();
//                    if (resultSet.next()) {
//                        txtName.setText(resultSet.getString("firstname"));
//                        txtLastName.setText(resultSet.getString("lastname"));
//                        txtPhoneNumber.setText(resultSet.getString("phonenumber"));
//                        txtEmail.setText(resultSet.getString("email"));
//                        txtPassword.setText(resultSet.getString("password"));
//                        txtWallet_id.setText(resultSet.getString("walletid"));
//                        txtUsername.setText(thisUsername);
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

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
                if(txtNewName.getText().length() <= 18 && txtNewName.getText().matches("[a-zA-Z]+")){
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
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("warning");
                    alert.setHeaderText(null);
                    alert.setContentText("name : " + txtNewName.getText()+"is not valid ");
                    alert.showAndWait();
                }
            }
            if (!txtNewLastName.getText().isEmpty()) {
                if (txtNewName.getText().length() <= 18 && txtNewName.getText().matches("[a-zA-Z]+")) {
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
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("warning");
                    alert.setHeaderText(null);
                    alert.setContentText("lastname : " + txtLastName.getText() + "is not valid");
                    alert.showAndWait();
                }
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
                if((String.valueOf(txtNewPhoneNumber).length()==11&&String.valueOf(txtNewPhoneNumber).matches("[0-9]+")&&String.valueOf(txtNewPhoneNumber).startsWith("09"))){
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
                    alert.setTitle("Phone Number Change");
                    alert.setHeaderText(null);
                    alert.setContentText("You changed your phone number to: " + txtNewPhoneNumber.getText());
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Phone Number Change");
                    alert.setHeaderText(null);
                    alert.setContentText(" phone number : " + txtNewPhoneNumber.getText()+" is not valid ");
                    alert.showAndWait();
                }


                if (!txtNewEmail.getText().isEmpty())
                    if((String.valueOf(txtNewEmail).matches("[a-zA-Z0-9.]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}"))){
                        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
                            String query = "UPDATE signin SET email = ? WHERE username = ?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                preparedStatement.setString(1, txtNewEmail.getText());
                                preparedStatement.setString(2, thisUsername);
                                preparedStatement.executeUpdate();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("email Change");
                        alert.setHeaderText(null);
                        alert.setContentText("You changed your email to: " + txtNewEmail.getText());
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("email Change");
                        alert.setHeaderText(null);
                        alert.setContentText(" email : " + txtNewEmail.getText()+" is not valid ");
                        alert.showAndWait();
                    }


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
    void PbtnDoneDeposit(ActionEvent event) throws IOException {
        if (txtPasswordDeposit.getText().equals(code) && captchaCode.equals(txtCaptchaCodeDeposit.getText())) {
            if (client != null) {
                String response = client.sendMessage("PbtnDoneDeposit," + thisUsername + "," + txtmoaneyDeposit1.getText());
                System.out.println("Response from server: " + response);

            } else {
                System.out.println("Client is not initialized");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("The transaction was completed successfully");
            alert.showAndWait();
        } else {
            if (!captchaCode.equals(txtCaptchaCodeDeposit.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Captcha code is wrong");
                alert.showAndWait();
                ActionEvent ee = new ActionEvent();
                PbtnRefreshCaptchaDeposit(ee);
                txtCaptchaCodeDeposit.setText("");
            }
            if (!code.equals(txtPasswordDeposit.getText())) {
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
    public void PbtnRefreshCaptchaDeposit(ActionEvent event) {
        if (client != null) {
            captchaCode = client.sendMessage("PbtnRefreshCaptchaDeposit," + thisUsername);
            System.out.println("Response from server for captchaCode: " + captchaCode);
            File file = new File("C:\\Users\\mania\\Desktop\\Final term project\\Agiotage\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
            InputStream inputStream;
            try {
                inputStream = (InputStream) new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imgCaptcha2.setImage(new Image(inputStream));
        } else {
            System.out.println("Client is not initialized");
        }
    }

    @FXML
    void PmnurippelEx(ActionEvent event) {
        typeTransferEx[0] = 1;
        typeTransferEx[1] = 0;
        typeTransferEx[2] = 0;
        typeTransferEx[3] = 0;
        typeTransferEx[4] = 0;
    }

    @FXML
    void PmnuAvalanchEx(ActionEvent event) {
        typeTransferEx[0] = 0;
        typeTransferEx[1] = 1;
        typeTransferEx[2] = 0;
        typeTransferEx[3] = 0;
        typeTransferEx[4] = 0;
    }

    @FXML
    void PmnuLightCoinEx(ActionEvent event) {
        typeTransferEx[0] = 0;
        typeTransferEx[1] = 0;
        typeTransferEx[2] = 1;
        typeTransferEx[3] = 0;
        typeTransferEx[4] = 0;
    }

    @FXML
    void PmnuDayEx(ActionEvent event) {
        typeTransferEx[0] = 0;
        typeTransferEx[1] = 0;
        typeTransferEx[2] = 0;
        typeTransferEx[3] = 1;
        typeTransferEx[4] = 0;
    }

    @FXML
    void PmnuStellarEx(ActionEvent event) {
        typeTransferEx[0] = 0;
        typeTransferEx[1] = 0;
        typeTransferEx[2] = 0;
        typeTransferEx[3] = 0;
        typeTransferEx[4] = 1;
    }

    @FXML
    void PbtnDoneEx(ActionEvent event) {
        String typeCoinEx = null;

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

        if (client != null) {
            String response = client.sendMessage("PbtnDoneEx," + thisUsername + "," + txtamountEx.getText() + "," + txtpriceEx1.getText() + "," + typeCoinEx + "," + btnBuyEx.isSelected() + "," + btnSellEx.isSelected());
            System.out.println("Response from server: " + response);
            switch (response) {
                case "59" -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("You do not have enough of this currency to sell!!");
                    alert.showAndWait();
                }
                case "69" -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("we are closed !");
                    alert.showAndWait();
                }
                case "true" -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The exchange was done");
                    alert.showAndWait();
                }
                case null, default -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The exchange went into waiting mode");
                    alert.showAndWait();
                }
            }
        } else {
            System.out.println("Client is not initialized");
        }

        typeTransferEx[0]=0;
        typeTransferEx[1]=0;
        typeTransferEx[2]=0;
        typeTransferEx[3]=0;
        typeTransferEx[4]=0;

    }

    @FXML
    void mnuCoin1(ActionEvent event) {
        typeTransfer[0] = 1;
    }

    @FXML
    void mnucoin2(ActionEvent event) {
        typeTransfer[1] = 1;
    }

    @FXML
    void mnuCoin3(ActionEvent event) {
        typeTransfer[2] = 1;
    }

    @FXML
    void mnuCoin4(ActionEvent event) {
        typeTransfer[3] = 1;
    }

    @FXML
    void mnuCoin5(ActionEvent event) {
        typeTransfer[4] = 1;
    }

    @FXML
    void mnuCoin6(ActionEvent event) {
        typeTransfer[5] = 1;
    }

    @FXML
    void PbtnDonetrans(ActionEvent event) throws SQLException {

        String typeCoinTr = null;

        if (typeTransfer[0] == 1)
            typeCoinTr = "rippel";
        else if (typeTransfer[1] == 1)
            typeCoinTr = "avalanche";
        else if (typeTransfer[2] == 1)
            typeCoinTr = "lightcoin";
        else if (typeTransfer[3] == 1)
            typeCoinTr = "day";
        else if (typeTransfer[4] == 1)
            typeCoinTr = "stellar";
        else if (typeTransfer[5] == 1)
            typeCoinTr = "money";

        if (client != null) {
            String response = client.sendMessage("PbtnDonetrans," + thisUsername + "," + typeCoinTr + "," + txtAmountTra.getText() + "," + txtWalletidTra.getText());
            System.out.println("Response from server: " + response);
            if ("13".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Wallet_id not found!");
                alert.showAndWait();
            } else if ("14".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Your assets from " + typeCoinTr + " are not sufficient to transfer to " + txtWalletidTra.getText());
                alert.showAndWait();
            } else if ("15".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You are in demo mode and cannot perform the transfer operation");
                alert.showAndWait();
            } else if ("16".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The transfer was successful");
                alert.showAndWait();
            }
        } else {
            System.out.println("Client is not initialized");
        }
        typeTransfer[0]=0;
        typeTransfer[1]=0;
        typeTransfer[2]=0;
        typeTransfer[3]=0;
        typeTransfer[4]=0;
        typeTransfer[5]=0;
    }

    private Timeline timeline;


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
                            txtMoneyProfile.setText(resultSet.getString("money"));
                            txtUsername.setText(thisUsername);
                            txtUserUsername.setText(thisUsername);
                            txtMyStellar.setText(String.valueOf(resultSet.getDouble("stellar")));
                            txtMyday.setText(String.valueOf(resultSet.getDouble("day")));
                            txtMyAvalanche.setText(String.valueOf(resultSet.getDouble("avalanche")));
                            txtMyRippel.setText(String.valueOf(resultSet.getDouble("rippel")));
                            txtMyLightCoin.setText(String.valueOf(resultSet.getDouble("lightcoin")));
                            String s = resultSet.getString("imagePath");

                            if (s != null) {
                                File file = new File(resultSet.getString("imagePath"));

                                InputStream inputStream = null;
                                try {
                                    inputStream = (InputStream) new FileInputStream(file);
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                ProfilePicUp.setImage(new Image(inputStream));
                                ProfilePic.setImage(new Image(inputStream));
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        });


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
                    txtMoneyProfile.setText(resultSet.getString("money"));
                    txtUsername.setText(thisUsername);
                    txtUserUsername.setText(thisUsername);
                    txtMyStellar.setText(String.valueOf(resultSet.getDouble("stellar")));
                    txtMyday.setText(String.valueOf(resultSet.getDouble("day")));
                    txtMyAvalanche.setText(String.valueOf(resultSet.getDouble("avalanche")));
                    txtMyRippel.setText(String.valueOf(resultSet.getDouble("rippel")));
                    txtMyLightCoin.setText(String.valueOf(resultSet.getDouble("lightcoin")));
                    String s = resultSet.getString("imagePath");

                    if (s != null) {
                        File file = new File(resultSet.getString("imagePath"));

                        InputStream inputStream = null;
                        try {
                            inputStream = (InputStream) new FileInputStream(file);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        ProfilePicUp.setImage(new Image(inputStream));
                        ProfilePic.setImage(new Image(inputStream));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        TabTarikh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
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

                ProfilePicUp.setId(urll);
                ProfilePic.setId(urll);
            }
        });


        updateTime(); // برای به روزرسانی اولیه زمان
        // ایجاد تایم لاین برای به روزرسانی زمان هر 1 ثانیه
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE); // تنظیم برای اجرا به صورت نامحدود
        timeline.play(); // شروع تایم لاین

    }



    @FXML
    void PbtnComputing(ActionEvent event) {

        double priceSelectCoinsell = 0;
        double priceSelectCoinbuy;

        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        String nowTime = calendarTime.formatTime35(calendarTime.now() -60000*60);///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String nowDate = calendarTime.formatDate(calendarTime.now());
        double priceUSD = 0;
        double priceEUR = 0;
        double priceTOMAN = 0;
        double priceYEN = 0;
        double priceGBP = 0;

        // خواندن قیمت
        String query = "SELECT * FROM prices WHERE TIMA = ? AND DATE = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nowTime);
            preparedStatement.setString(2, nowDate);
            System.out.println("nowTime : " + nowTime);
            System.out.println("nowDate : " + nowDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    priceUSD = resultSet.getDouble("USD");
                    priceEUR = resultSet.getDouble("EUR");
                    priceTOMAN = resultSet.getDouble("TOMAN");
                    priceYEN = resultSet.getDouble("YEN");
                    priceGBP = resultSet.getDouble("GBP");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            amuntBuyForSwap = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
        } else if (typeTransferSw1[1] == 1) {
            priceSelectCoinbuy = priceEUR;
            amuntBuyForSwap = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
        } else if (typeTransferSw1[2] == 1) {
            priceSelectCoinbuy = priceTOMAN;
            amuntBuyForSwap = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
        } else if (typeTransferSw1[3] == 1) {
            priceSelectCoinbuy = priceYEN;
            amuntBuyForSwap = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
        } else if (typeTransferSw1[4] == 1) {
            priceSelectCoinbuy = priceGBP;
            amuntBuyForSwap = priceSelectCoinsell * Integer.parseInt(txtamoutSWAP.getText()) / priceSelectCoinbuy;
        }
        txtamoutSWAP1.setText(String.valueOf(amuntBuyForSwap));
        swSwap = true;
    }

    @FXML
    void PbtnDoneSWAP(ActionEvent event) {
        if (swSwap) {
            if (client != null) {

                String typeSwap1 = "",typeSwap2 = "";
                if(mnuTypeSWAP.getText().equals("ریپل")){
                    typeSwap1="rippel";
                }else if(mnuTypeSWAP.getText().equals("آوالانچ")){
                    typeSwap1="avalanche";
                }else if(mnuTypeSWAP.getText().equals("استلار")){
                    typeSwap1="stellar";
                }else if(mnuTypeSWAP.getText().equals("دای")){
                    typeSwap1="day";
                }else if(mnuTypeSWAP.getText().equals("لایت کوین")){
                    typeSwap1="lightcoin";
                }

                if(mnuTypeSWAP1.getText().equals("ریپل")){
                    typeSwap2="rippel";
                }else if(mnuTypeSWAP1.getText().equals("آوالانچ")){
                    typeSwap2="avalanche";
                }else if(mnuTypeSWAP1.getText().equals("استلار")){
                    typeSwap2="stellar";
                }else if(mnuTypeSWAP1.getText().equals("دای")){
                    typeSwap2="day";
                }else if(mnuTypeSWAP1.getText().equals("لایت کوین")){
                    typeSwap2="lightcoin";
                }



                System.out.println("PbtnDoneSWAP," + thisUsername + "," + txtamoutSWAP.getText() + "," + txtamoutSWAP1.getText() + "," + typeSwap1 + "," + typeSwap2);
                String response = client.sendMessage("PbtnDoneSWAP," + thisUsername + "," + txtamoutSWAP.getText() + "," + txtamoutSWAP1.getText() + "," + typeSwap1 + "," + typeSwap2);
                System.out.println("Response from server: " + response);
                if ("20".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Your property is not denominated in " + mnuTypeSWAP.getText() + " currency!");
                    alert.showAndWait();
                    for (int i = 0; i < typeTransferSw.length; i++) {
                        typeTransferSw[i] = 0;
                        typeTransferSw1[i] = 0;
                    }
                    mnuTypeSWAP.setText("");
                    mnuTypeSWAP1.setText("");
                } else if ("21".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The market is closed by the manager");
                    alert.showAndWait();
                    mnuTypeSWAP.setText("");
                    mnuTypeSWAP1.setText("");
                    for (int i = 0; i < typeTransferSw.length; i++) {
                        typeTransferSw[i] = 0;
                        typeTransferSw1[i] = 0;
                    }
                } else if ("22".equals(response)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("mission accomplished");
                    alert.showAndWait();
                    mnuTypeSWAP.setText("");
                    mnuTypeSWAP1.setText("");
                    for (int i = 0; i < typeTransferSw.length; i++) {
                        typeTransferSw[i] = 0;
                        typeTransferSw1[i] = 0;
                    }
                }
            } else {
                System.out.println("Client is not initialized");
            }
            swSwap = false;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("First, check the values using the calculate button");
            alert.showAndWait();
        }

    }

    @FXML
    void PbtnFile(ActionEvent event) throws SQLException {

        String date;
        String time;
        int sellbuy;
        int price;
        double amount;
        String type;
        int state;
        File file = new File("data.txt");
        if (file.exists()) {
            file.delete();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "")) {
            String sql = "SELECT * FROM exchange WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, thisUsername);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    date = resultSet.getString("date");
                    time = resultSet.getString("time");
                    sellbuy = resultSet.getInt("sellbuy");
                    price = resultSet.getInt("price");
                    amount = resultSet.getDouble("copyAmount");
                    type = resultSet.getString("type");
                    state = resultSet.getInt("state");
                    writeToAndPrintFile(sellbuy, type, state, amount, price, date, time);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void PmnureppleSWAP(ActionEvent event) {
        typeTransferSw[0] = 1;
        typeTransferSw[1] = 0;
        typeTransferSw[2] = 0;
        typeTransferSw[3] = 0;
        typeTransferSw[4] = 0;
        mnuTypeSWAP.setText("ریپل");
    }

    @FXML
    void PmnuavalancheSWAP(ActionEvent event) {
        typeTransferSw[0] = 0;
        typeTransferSw[1] = 1;
        typeTransferSw[2] = 0;
        typeTransferSw[3] = 0;
        typeTransferSw[4] = 0;
        mnuTypeSWAP.setText("آوالانچ");
    }

    @FXML
    void PmnuLightcoinSWAP(ActionEvent event) {
        typeTransferSw[0] = 0;
        typeTransferSw[1] = 0;
        typeTransferSw[2] = 1;
        typeTransferSw[3] = 0;
        typeTransferSw[4] = 0;
        mnuTypeSWAP.setText("لایت کوین");
    }

    @FXML
    void PmnudaySWAP(ActionEvent event) {
        typeTransferSw[0] = 0;
        typeTransferSw[1] = 0;
        typeTransferSw[2] = 0;
        typeTransferSw[3] = 1;
        typeTransferSw[4] = 0;
        mnuTypeSWAP.setText("دای");
    }

    @FXML
    void PmnustellarSWAP(ActionEvent event) {
        typeTransferSw[0] = 0;
        typeTransferSw[1] = 0;
        typeTransferSw[2] = 0;
        typeTransferSw[3] = 0;
        typeTransferSw[4] = 1;
        mnuTypeSWAP.setText("استلار");
    }

    @FXML
    void PmnureppleSWAP1(ActionEvent event) {
        typeTransferSw1[0] = 1;
        typeTransferSw1[1] = 0;
        typeTransferSw1[2] = 0;
        typeTransferSw1[3] = 0;
        typeTransferSw1[4] = 0;
        mnuTypeSWAP1.setText("ریپل");
    }

    @FXML
    void PmnuavalancheSWAP1(ActionEvent event) {
        typeTransferSw1[0] = 0;
        typeTransferSw1[1] = 1;
        typeTransferSw1[2] = 0;
        typeTransferSw1[3] = 0;
        typeTransferSw1[4] = 0;
        mnuTypeSWAP1.setText("آوالانچ");
    }

    @FXML
    void PmnuLightcoinSWAP1(ActionEvent event) {
        typeTransferSw1[0] = 0;
        typeTransferSw1[1] = 0;
        typeTransferSw1[2] = 1;
        typeTransferSw1[3] = 0;
        typeTransferSw1[4] = 0;
        mnuTypeSWAP1.setText("لایت کوین");
    }

    @FXML
    void PmnudaySWAP1(ActionEvent event) {
        typeTransferSw1[0] = 0;
        typeTransferSw1[1] = 0;
        typeTransferSw1[2] = 0;
        typeTransferSw1[3] = 1;
        typeTransferSw1[4] = 0;
        mnuTypeSWAP1.setText("دای");
    }

    @FXML
    void PmnustellarSWAP1(ActionEvent event) {
        typeTransferSw1[0] = 0;
        typeTransferSw1[1] = 0;
        typeTransferSw1[2] = 0;
        typeTransferSw1[3] = 0;
        typeTransferSw1[4] = 1;
        mnuTypeSWAP1.setText("استلار");
    }

    @FXML
    void PbtnRefreshCaptchaWthdrawal(ActionEvent event) {
        if (client != null) {
            captchaCode = client.sendMessage("PbtnRefreshCaptchaDeposit," + thisUsername);
            System.out.println("Response from server for captchaCode: " + captchaCode);
            File file = new File("C:\\Users\\mania\\Desktop\\Final term project\\Agiotage\\Agiotage\\src\\main\\resources\\com\\example\\demo1\\d3e2686ead31b9f31970f8466f5a1ae0.jpg");
            InputStream inputStream;
            try {
                inputStream = (InputStream) new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageCaptcha3.setImage(new Image(inputStream));
        } else {
            System.out.println("Client is not initialized");
        }
    }

    @FXML
    void PbtnDoneWthdrawal(ActionEvent event) {
        if (client != null) {
            String response = client.sendMessage("PbtnDoneWthdrawal," + thisUsername + "," + txtAmountwWthdrawal.getText());
            System.out.println("Response from server: " + response);
            if ("65".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("successful");
                alert.setHeaderText(null);
                alert.setContentText("Your inventory is not enough");
                alert.showAndWait();
            } else if ("75".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("successful");
                alert.setHeaderText(null);
                alert.setContentText("The market is closed by the exchange manager!!");
                alert.showAndWait();
            } else if ("85".equals(response)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("successful");
                alert.setHeaderText(null);
                alert.setContentText("Ymission accomplished ☺");
                alert.showAndWait();
            }
        } else {
            System.out.println("Client is not initialized");
        }
    }

    @FXML
    void PbtnGoHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent Home = loader.load();

            HomePage homePage = loader.getController();
            homePage.setClient(client, thisUsername);

            Scene scene = new Scene(Home);
            Stage primaryStage = (Stage) btnGoHomePage.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PbtnLogout(ActionEvent event) {
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

    private void loadDataFromDatabase() {
        String Url = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";

        String querybef = "SELECT * FROM exchange WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(Url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
            preparedStatement.setString(1, username2);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    String type = resultSet.getString("type");
                    Integer price = resultSet.getInt("price");
                    Double amount = resultSet.getDouble("copyAmount");
                    Integer state = resultSet.getInt("state");
                    String stateEX;
                    if (state == 0)
                        stateEX = "pending";
                    else stateEX = "done";

                    Integer sellbuy = resultSet.getInt("sellbuy");

                    exchangeList.add(new Exchange(date, time, sellbuy, type, amount, price, stateEX));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    private void EXtableFORexchangeTAB(String type) {
        String Url = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";

        String querybef = "SELECT * FROM exchange WHERE type = ?";
        try (Connection connection = DriverManager.getConnection(Url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(querybef)) {
            preparedStatement.setString(1, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime time = resultSet.getTime("time").toLocalTime();
                    Integer price = resultSet.getInt("price");
                    Double amount = resultSet.getDouble("copyAmount");
                    Integer state = resultSet.getInt("state");
                    String stateEX;

                    if (state == 0)
                        stateEX = "pending";
                    else stateEX = "done";

                    Integer sellbuy = resultSet.getInt("sellbuy");

                    EXTable.add(new EX(date, time, sellbuy, amount, price, stateEX));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public Profile() {
    }

    private void updateTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        txtCurrentTime.setText(formatter.format(date));

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
                    txtMoneyProfile.setText(resultSet.getString("money"));
                    txtUsername.setText(thisUsername);
                    txtUserUsername.setText(thisUsername);
                    txtMyStellar.setText(String.valueOf(resultSet.getDouble("stellar")));
                    txtMyday.setText(String.valueOf(resultSet.getDouble("day")));
                    txtMyAvalanche.setText(String.valueOf(resultSet.getDouble("avalanche")));
                    txtMyRippel.setText(String.valueOf(resultSet.getDouble("rippel")));
                    txtMyLightCoin.setText(String.valueOf(resultSet.getDouble("lightcoin")));
                    String s = resultSet.getString("imagePath");

                    if (s != null) {
                        File file = new File(resultSet.getString("imagePath"));

                        InputStream inputStream = null;
                        try {
                            inputStream = (InputStream) new FileInputStream(file);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        ProfilePicUp.setImage(new Image(inputStream));
                        ProfilePic.setImage(new Image(inputStream));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
