module com.example.javafxendassignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jbcrypt;

    opens com.example.javafxendassignment to javafx.fxml;
    opens com.example.javafxendassignment.controllers to javafx.fxml;
    exports com.example.javafxendassignment;
    exports com.example.javafxendassignment.controllers;

    opens com.example.javafxendassignment.model to javafx.base;
}