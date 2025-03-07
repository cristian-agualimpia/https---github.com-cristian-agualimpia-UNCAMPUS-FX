package com.uncampus2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.uncampus2.estructuras.ListaEnlazada;
import com.uncampus2.mapeo.Lugar;
import com.uncampus2.mapeo.MapaGlobal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private ComboBox<String> origenComboBox;

    @FXML
    private ComboBox<String> destinoComboBox;

    @FXML
    private Button buscarRutaButton;

    @FXML
    private ListView<String> rutaListView;

    @FXML
    private Button nuevaBusquedaButton;

    // NUEVO: Botón "Ver Mapa"
    @FXML
    private Button verMapaButton;

    private MapaGlobal mapa;
    private ObservableList<String> lugaresNombres;
    private ObservableList<Lugar> lugares;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapa = new MapaGlobal();
        
        // Preparar las listas de lugares para los ComboBox
        lugares = FXCollections.observableArrayList();
        lugaresNombres = FXCollections.observableArrayList();
        
        // Llenar las listas con todos los lugares disponibles
        for (Lugar lugar : mapa.getAllLugares()) {
            lugares.add(lugar);
            lugaresNombres.add(lugar.getId() + " - " + lugar.getNombre());
        }
        
        // Configurar los ComboBox
        origenComboBox.setItems(lugaresNombres);
        destinoComboBox.setItems(lugaresNombres);
        
        // Inicialmente, deshabilitar el botón de nueva búsqueda
        nuevaBusquedaButton.setDisable(true);
        
        // Añadir listener para evitar seleccionar el mismo origen y destino
        origenComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.equals(destinoComboBox.getValue())) {
                mostrarAlerta("Advertencia", "Selección Inválida", 
                        "El origen y destino no pueden ser el mismo lugar.");
                origenComboBox.setValue(oldVal);
            }
        });
        
        destinoComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.equals(origenComboBox.getValue())) {
                mostrarAlerta("Advertencia", "Selección Inválida", 
                        "El origen y destino no pueden ser el mismo lugar.");
                destinoComboBox.setValue(oldVal);
            }
        });
    }

    @FXML
    void buscarRuta(ActionEvent event) {
        if (origenComboBox.getValue() == null || destinoComboBox.getValue() == null) {
            mostrarAlerta("Error", "Selección incompleta", "Por favor, seleccione tanto el origen como el destino.");
            return;
        }
        
        // Extraer IDs de los lugares seleccionados
        String origenCompleto = origenComboBox.getValue();
        String destinoCompleto = destinoComboBox.getValue();
        
        String idOrigen = origenCompleto.split(" - ")[0];
        String idDestino = destinoCompleto.split(" - ")[0];
        
        // Obtener la ruta más corta
        ListaEnlazada<Lugar> ruta = mapa.obtenerRutaMasCorta(idOrigen, idDestino);
        
        // Mostrar la ruta en el ListView
        rutaListView.getItems().clear();
        
        if (ruta != null) {
            int index = 1;
            for (Lugar paso : ruta) {
                rutaListView.getItems().add(index + ". " + paso.getNombre());
                index++;
            }
            rutaListView.getItems().add("--");
            rutaListView.getItems().add("Total: " + (index-1) + " lugares en la ruta");
        } else {
            rutaListView.getItems().add("No se encontró una ruta válida entre los lugares indicados.");
        }
        
        // Habilitar el botón de nueva búsqueda y deshabilitar el botón de buscar
        buscarRutaButton.setDisable(true);
        nuevaBusquedaButton.setDisable(false);
    }

    @FXML
    void nuevaBusqueda(ActionEvent event) {
        // Limpiar selecciones y resultados
        origenComboBox.setValue(null);
        destinoComboBox.setValue(null);
        rutaListView.getItems().clear();
        
        // Habilitar el botón de buscar y deshabilitar el botón de nueva búsqueda
        buscarRutaButton.setDisable(false);
        nuevaBusquedaButton.setDisable(true);
    }
    
    // NUEVO: método para cambiar a la vista de mapa
    @FXML
    void verMapa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uncampus2/MapView.fxml"));
            Parent root = loader.load();
            
            // Obtener la ventana actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Crear nueva escena con la vista de mapa
            Scene scene = new Scene(root, 700, 500);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del mapa", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
