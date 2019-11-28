module com.kuuhakull {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires poi;

    opens com.kuuhakull to javafx.fxml;
    exports com.kuuhakull;
}