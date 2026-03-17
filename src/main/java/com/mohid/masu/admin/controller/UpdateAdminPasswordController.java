package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import com.mohid.masu.admin.session.AdminSession;
import com.mohid.masu.admin.service.ApiClient;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UpdateAdminPasswordController {

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleUpdatePassword() {
        try {
            String oldPassword = oldPasswordField.getText().trim();
            String newPassword = newPasswordField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                setError("Please fill all password fields.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                setError("New password and confirm password do not match.");
                return;
            }

            String adminId = AdminSession.getAdminId();

            String body = String.format(
                    "{\"oldPassword\":\"%s\",\"newPassword\":\"%s\",\"confirmPassword\":\"%s\"}",
                    oldPassword, newPassword, confirmPassword
            );

            String response = ApiClient.put("/admin/" + adminId + "/password", body);

            if (response.contains("updated successfully")) {
                setSuccess("Admin password updated successfully.");
                oldPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
            } else if (response.contains("incorrect")) {
                setError("Old password is incorrect.");
            } else if (response.contains("do not match")) {
                setError("New password and confirm password do not match.");
            } else {
                setError("Failed to update admin password.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            setError("Server error while updating password.");
        }
    }

    @FXML
    private void handleClear() {
        oldPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
        messageLabel.setText("");
    }

    @FXML
    private void goBack() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setError(String msg) {
        messageLabel.setText(msg);
        messageLabel.getStyleClass().remove("success-label");
        if (!messageLabel.getStyleClass().contains("error-label")) {
            messageLabel.getStyleClass().add("error-label");
        }
    }

    private void setSuccess(String msg) {
        messageLabel.setText(msg);
        messageLabel.getStyleClass().remove("error-label");
        if (!messageLabel.getStyleClass().contains("success-label")) {
            messageLabel.getStyleClass().add("success-label");
        }
    }
}