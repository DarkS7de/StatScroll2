module com.example.statscroll {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens com.example.statscroll to javafx.fxml;
    exports com.example.statscroll.model;
    opens com.example.statscroll.model to javafx.fxml;
}