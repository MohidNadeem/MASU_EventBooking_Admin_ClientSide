package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import com.mohid.masu.admin.service.ApiClient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        try {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter username and password.");
                return;
            }

            String jsonBody = String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\"}",
                    username, password
            );

            // sending data to API at server side for admin authentication
            String response = ApiClient.post("/admin/login", jsonBody);  

            if (response.contains("Invalid username or password")) {
                messageLabel.setText("Invalid username or password.");
            } else {
                messageLabel.setText("");
                App.setRoot("/com/mohid/masu/admin/view/dashboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Login failed. Check API/server connection.");
        }
    }
}