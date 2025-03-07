module com.uncampus2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens com.uncampus2 to javafx.fxml;  // Add this line for MainController
    opens com.uncampus2.controller to javafx.fxml;
    
    exports com.uncampus2;
    exports com.uncampus2.controller;
    exports com.uncampus2.mapeo;
    exports com.uncampus2.estructuras;
}