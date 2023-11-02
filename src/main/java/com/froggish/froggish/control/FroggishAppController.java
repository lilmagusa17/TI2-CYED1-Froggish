package com.froggish.froggish.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FroggishAppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}