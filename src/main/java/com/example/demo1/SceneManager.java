package com.example.demo1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    public static void loadScene(Pane currentPane, String fxmlFile) {
        try {
            Parent newRoot = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
            Scene currentScene = currentPane.getScene();
            currentScene.setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
