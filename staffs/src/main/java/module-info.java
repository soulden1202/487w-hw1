module com.example.staffs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.controlsfx.controls;
    requires ojdbc8;
    requires java.sql;

    opens com.example.staffs to javafx.fxml;
    exports com.example.staffs;
}