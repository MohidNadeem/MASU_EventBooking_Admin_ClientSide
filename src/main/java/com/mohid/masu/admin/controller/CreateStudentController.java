package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import com.mohid.masu.admin.service.ApiClient;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateStudentController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("BOY", "GIRL");
    }

    @FXML
    private void handleCreateStudent() {
        try {
            String fullName = fullNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String gender = genderComboBox.getValue();

            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || gender == null) {
                setErrorMessage("Please fill all fields.");
                return;
            }

            String jsonBody = String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\",\"fullName\":\"%s\",\"gender\":\"%s\"}",
                    username, password, fullName, gender
            );

            String response = ApiClient.post("/students", jsonBody);

            if (response.contains("Student created successfully")) {
                setSuccessMessage("Student created successfully.");
                clearFields();
            } else if (response.contains("Username already exists")) {
                setErrorMessage("Username already exists.");
            } else {
                setErrorMessage("Failed to create student.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            setErrorMessage("Server error while creating student.");
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
        messageLabel.setText("");
        messageLabel.getStyleClass().remove("success-label");
        if (!messageLabel.getStyleClass().contains("error-label")) {
            messageLabel.getStyleClass().add("error-label");
        }
    }

    @FXML
    private void goBack() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        fullNameField.clear();
        usernameField.clear();
        passwordField.clear();
        genderComboBox.setValue(null);
    }

    // Functions to set Label to show message after execution
    private void setErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().remove("success-label");
        if (!messageLabel.getStyleClass().contains("error-label")) {
            messageLabel.getStyleClass().add("error-label");
        }
    }

    private void setSuccessMessage(String message) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().remove("error-label");
        if (!messageLabel.getStyleClass().contains("success-label")) {
            messageLabel.getStyleClass().add("success-label");
        }
    }
}