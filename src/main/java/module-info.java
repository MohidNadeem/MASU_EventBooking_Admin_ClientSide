module com.mohid.masu_adminapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mohid.masu.admin to javafx.fxml;
    exports com.mohid.masu.admin;
}
