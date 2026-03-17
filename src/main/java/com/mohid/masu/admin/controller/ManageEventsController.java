package com.mohid.masu.admin.controller;

import com.mohid.masu.admin.App;
import com.mohid.masu.admin.model.EventRow;
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

public class ManageEventsController {

    @FXML
    private TableView<EventRow> eventsTable;

    @FXML
    private TableColumn<EventRow, String> titleColumn;

    @FXML
    private TableColumn<EventRow, String> typeColumn;

    @FXML
    private TableColumn<EventRow, String> dateColumn;
    
    @FXML
    private TableColumn<EventRow, String> startTimeColumn;

    @FXML
    private TableColumn<EventRow, String> endTimeColumn;

    @FXML
    private TableColumn<EventRow, String> venueColumn;

    @FXML
    private TableColumn<EventRow, String> statusColumn;

    @FXML
    private TableColumn<EventRow, String> costColumn;

    @FXML
    private TableColumn<EventRow, String> participantsColumn;

    @FXML
    private TableColumn<EventRow, String> alumniSlotsColumn;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venueName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("maxParticipants"));
        alumniSlotsColumn.setCellValueFactory(new PropertyValueFactory<>("alumniReservedSlots"));

        loadEvents();
    }

    @FXML
    private void loadEvents() {
        try {
            String response = ApiClient.get("/events");
            JSONArray array = new JSONArray(response);

            List<EventRow> rows = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                EventRow row = new EventRow(
                        obj.optString("id", ""),
                        obj.optString("title", ""),
                        obj.optString("type", ""),
                        obj.optString("date", ""),
                        obj.optString("startTime", ""),
                        obj.optString("endTime", ""),
                        obj.optString("venueName", ""),
                        obj.optString("status", ""),
                        String.valueOf(obj.optDouble("cost", 0)),
                        String.valueOf(obj.optInt("maxParticipants", 0)),
                        String.valueOf(obj.optInt("alumniReservedSlots", 0))
                );

                rows.add(row);
            }

            ObservableList<EventRow> data = FXCollections.observableArrayList(rows);
            eventsTable.setItems(data);
            messageLabel.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load events.");
        }
    }

    @FXML
    private void cancelSelectedEvent() {
        try {
            EventRow selected = eventsTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                messageLabel.setText("Please select an event first.");
                return;
            }

            String body = "{\"status\":\"CANCELLED\"}";
            String response = ApiClient.put("/events/" + selected.getId() + "/status", body);

            if (response.contains("updated successfully")) {
                messageLabel.setText("Event cancelled successfully.");
                loadEvents();
            } else {
                messageLabel.setText("Failed to cancel event.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Server error while cancelling event.");
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