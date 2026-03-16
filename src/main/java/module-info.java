module com.mohid.masu.admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.net.http;

    opens com.mohid.masu.admin to javafx.fxml;
    opens com.mohid.masu.admin.controller to javafx.fxml;

    exports com.mohid.masu.admin;
    exports com.mohid.masu.admin.controller;
    exports com.mohid.masu.admin.service;
}