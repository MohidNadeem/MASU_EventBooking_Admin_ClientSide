package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import com.mohid.masu.admin.model.StudentRow;
import com.mohid.masu.admin.service.ApiClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class ManageStudentsController {

    @FXML
    private TableView<StudentRow> studentsTable;

    @FXML
    private TableColumn<StudentRow, String> fullNameColumn;

    @FXML
    private TableColumn<StudentRow, String> usernameColumn;

    @FXML
    private TableColumn<StudentRow, String> genderColumn;

    @FXML
    private TableColumn<StudentRow, String> statusColumn;

    @FXML
    private TableColumn<StudentRow, String> passwordColumn;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordDisplay"));

        loadStudents();
    }

    @FXML
    private void loadStudents() {
        try {
            String response = ApiClient.get("/students");
            JSONArray array = new JSONArray(response);

            List<StudentRow> rows = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String passwordDisplay = obj.optBoolean("passwordUpdatedByStudent", false)
                        ? "Updated by Student"
                        : obj.optString("password", "");

                StudentRow row = new StudentRow(
                        obj.optString("id", ""),
                        obj.optString("fullName", ""),
                        obj.optString("username", ""),
                        obj.optString("gender", ""),
                        obj.optString("status", ""),
                        passwordDisplay
                );

                rows.add(row);
            }

            ObservableList<StudentRow> data = FXCollections.observableArrayList(rows);
            studentsTable.setItems(data);
            messageLabel.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load students.");
        }
    }

    @FXML
    private void markSelectedAsAlumni() {
        try {
            StudentRow selected = studentsTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                messageLabel.setText("Please select a student first.");
                return;
            }

            String body = "{\"status\":\"ALUMNI\"}";
            String response = ApiClient.put("/students/" + selected.getId() + "/status", body);

            if (response.contains("updated successfully")) {
                messageLabel.setText("Student marked as alumni.");
                loadStudents();
            } else {
                messageLabel.setText("Failed to update student status.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Server error while updating student.");
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
}