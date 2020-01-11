package core.view;

import core.MainApp;
import core.SimpleWeather;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EtiquetaCoorController {

    private MainApp mainApp;
    private SimpleWeather servidor;
    private Stage primaryStage;

    @FXML
    private ListView<String> coordenadas;

    @FXML
    private ListView<String> etiquetas;

    @FXML
    private void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // listView se alimenta de datos observables que cede el servidor
                actualizarCoordenadas();
            }
        });

    }

    void actualizarCoordenadas(){
        Map<String, ObservableList<String>> info = servidor.getCoordenadasEtiquetas();

        ObservableList<String> coors = FXCollections.<String>observableArrayList();
        List<String> listaCoor = new ArrayList<>();
        for(String localizacion : info.keySet()){
            listaCoor.add(localizacion);
        }
        coors.addAll(listaCoor);

        coordenadas.setItems(coors);
    }

    @FXML
    void borrarEtiqueta() {
        String coorValue = coordenadas.getSelectionModel().getSelectedItems().toString();
        coorValue = coorValue.substring(1, coorValue.length() - 1);
        String etiquetaValue = etiquetas.getSelectionModel().getSelectedItems().toString();
        etiquetaValue = etiquetaValue.substring(1, etiquetaValue.length() - 1);

        servidor.removeEtiquetaCoor(coorValue, etiquetaValue);
        Map<String, ObservableList<String>> info = servidor.getCoordenadasEtiquetas();
        if (!info.containsKey(coorValue)){
            actualizarCoordenadas();
        }
    }

    @FXML
    void mostrarEtiquetas() {
        Map<String, ObservableList<String>> info = servidor.getCoordenadasEtiquetas();
        String selectedValue = coordenadas.getSelectionModel().getSelectedItems().toString();
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
