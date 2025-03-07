package com.uncampus2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Ancizar font if available
        try {
            // Adjust these paths to match where you store the font files
            Font.loadFont(getClass().getResourceAsStream("/fonts/Ancizar-Regular.ttf"), 14);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Ancizar-Bold.ttf"), 14);
            Font.loadFont(getClass().getResourceAsStream("/fonts/Ancizar-Italic.ttf"), 14);
            System.out.println("Ancizar font loaded successfully");
        } catch (Exception e) {
            System.out.println("Could not load Ancizar font: " + e.getMessage());
            // Will fallback to Arial as specified in CSS
        }
        
        // Load the FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uncampus2/MainView.fxml"));
        Parent root = loader.load();
        
        // Set up the scene
        Scene scene = new Scene(root, 700, 500);
        
        primaryStage.setTitle("Sistema de Rutas del Campus - UNAL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}