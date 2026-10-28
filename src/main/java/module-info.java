module com.example.eightoff {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.eightoff to javafx.fxml;
    opens com.example.eightoff.vista to javafx.fxml;
    opens com.example.eightoff.controlador to javafx.fxml;

    exports com.example.eightoff;
    exports com.example.eightoff.modelo;
    exports com.example.eightoff.vista;
    exports com.example.eightoff.controlador;
}