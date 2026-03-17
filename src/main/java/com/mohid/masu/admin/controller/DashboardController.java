package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    private void goToCreateStudent() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/createStudent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToManageStudents() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/manageStudents");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToUpdateAdminPassword() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/updateAdminPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void goToManageEvents() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/manageEvents");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            App.setRoot("/com/mohid/masu/admin/view/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}