package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage {

    @FXML
    private Button btnHome;

    @FXML
    private Text txt101;

    @FXML
    private Text txt102;

    @FXML
    private Text txt103;

    @FXML
    private Text txt11;

    @FXML
    private Text txt12;

    @FXML
    private Text txt13;

    @FXML
    private Text txt21;

    @FXML
    private Text txt22;

    @FXML
    private Text txt23;

    @FXML
    private Text txt31;

    @FXML
    private Text txt32;

    @FXML
    private Text txt33;

    @FXML
    private Text txt41;

    @FXML
    private Text txt42;

    @FXML
    private Text txt43;

    @FXML
    private Text txt51;

    @FXML
    private Text txt52;

    @FXML
    private Text txt53;

    @FXML
    private Text txt61;

    @FXML
    private Text txt62;

    @FXML
    private Text txt63;

    @FXML
    private Text txt71;

    @FXML
    private Text txt72;

    @FXML
    private Text txt73;

    @FXML
    private Text txt81;

    @FXML
    private Text txt82;

    @FXML
    private Text txt83;

    @FXML
    private Text txt91;

    @FXML
    private Text txt92;

    @FXML
    private Text txt93;

    @FXML
    void PbtnHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("coiinPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
