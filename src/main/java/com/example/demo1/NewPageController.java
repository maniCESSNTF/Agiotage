package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewPageController {
    @FXML
    private Label newPageLabel;
    @FXML
    private Button backButton;

    private Client client;

    @FXML
    public void initialize() {
        client = new Client("localhost", 12345);
        updateLabel();

        backButton.setOnAction(e -> {
            SceneManager.loadScene((VBox) newPageLabel.getScene().getRoot(), "LogeIn.fxml");
        });

    }

    private void updateLabel() {
        String response = client.sendMessage("get");
        newPageLabel.setText("Updated Counter: " + response);
    }
}
