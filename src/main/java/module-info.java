module com.example.medtrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.medtrack to javafx.fxml;
    exports com.example.medtrack;
}