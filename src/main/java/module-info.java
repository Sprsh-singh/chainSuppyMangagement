module com.example.suplychainmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.suplychainmanagement to javafx.fxml;
    exports com.example.suplychainmanagement;
}