module com.example.agiotage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.agiotage to javafx.fxml;
    exports com.example.agiotage;
}