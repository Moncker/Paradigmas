package core.view;

import core.MainApp;
import core.SimpleWeather;
import core.model.Coordenadas;
import core.model.Localizacion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EtiquetaController {

    private MainApp mainApp;
    private SimpleWeather servidor;
    private Stage primaryStage;

    @FXML
    private ListView<String> localizaciones;

    @FXML
    private ListView<String> etiquetas;

    @FXML
    private void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // listView se alimenta de datos observables que cede el servidor
                actualizarCiudades();
            }
        });

    }

    void actualizarCiudades(){
        Map<String, ObservableList<String>> info = servidor.getCiudadesEtiquetas();

        ObservableList<String> ciudades = FXCollections.<String>observableArrayList();
        List<String> listaCiudades = new ArrayList<>();
        for(String localizacion : info.keySet()){
            listaCiudades.add(localizacion.substring(0,1).toUpperCase()+localizacion.substring(1));
        }
        ciudades.addAll(listaCiudades);

        localizaciones.setItems(ciudades);
    }

    @FXML
    void borrarEtiqueta() {
        String ciudadValue = localizaciones.getSelectionModel().getSelectedItems().toString().toLowerCase();
        ciudadValue = ciudadValue.substring(1, ciudadValue.length() - 1);
        String etiquetaValue = etiquetas.getSelectionModel().getSelectedItems().toString();
        etiquetaValue = etiquetaValue.substring(1, etiquetaValue.length() - 1);

        servidor.removeEtiqueta(ciudadValue, etiquetaValue);
        Map<String, ObservableList<String>> info = servidor.getCiudadesEtiquetas();
        if (!info.containsKey(ciudadValue)){
            actualizarCiudades();
        }
    }

    @FXML
    void mostrarEtiquetas() {
        Map<String, ObservableList<String>> info = servidor.getCiudadesEtiquetas();
        String selectedValue = localizaciones.getSelectionModel().getSelectedItems().toString();
        selectedValue = selectedValue.substring(1, selectedValue.length() - 1);

        etiquetas.setItems(info.get(selectedValue));
    }

    @FXML
    void volverMain() {
        mainApp.showInicioVista();
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setServer(SimpleWeather servidor) {
        this.servidor = servidor;
    }

}
