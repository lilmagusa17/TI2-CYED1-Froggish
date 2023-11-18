package com.froggish.froggish.control;

import com.froggish.froggish.FroggishApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FroggishAppController {

    @FXML
    private Button btn;

    @FXML
    private AnchorPane ap;

    public Stage stage;
    public Scene scene;
    public Parent root;


    @FXML
    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/froggish/froggish/thirdLevel-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) ap.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

