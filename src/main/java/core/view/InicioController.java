package core.view;
import core.Exceptions.CoordenadasInvalidasException;
import core.MainApp;
import core.OWM.App;
import core.SimpleWeather;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;

public class InicioController {


    @FXML
    private ListView<String> favList;
    @FXML
    private TextField cityname;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;

    private SimpleWeather servidor;
    private MainApp mainApp;
    private Tiempo tiempo;

    @FXML
    private void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // listView se alimenta de datos observables que cede el servidor
                favList.setItems(servidor.getFavoritos());
            }
        });

    }



    @FXML
    public void busquedaCoor() throws IOException, CoordenadasInvalidasException {
        String t = cityname.getText();
        String lati = lat.getText();
        String longi = lon.getText();
        servidor.buscaTiempoPorCoordenadas(Float.parseFloat(lati),Float.parseFloat(longi));
    }

    @FXML
    public void busquedaCiudad() throws IOException {
        String t = cityname.getText();  //tambien etiqueta
        String lati = lat.getText();
        String longi = lon.getText();

        String ciudad = servidor.etiquetaCiudad(t);
        if (! ciudad.equals(""))
            t = ciudad;


        String finalT = t;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(finalT);
                    Tiempo tiempo = servidor.buscaTiempoPorNombre(finalT);
                    mainApp.showTemperaturaVista(tiempo);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @FXML
    public void busquedaCiudadDias() throws IOException {
        String t = cityname.getText();  //tambien etiqueta
        String lati = lat.getText();
        String longi = lon.getText();

        String ciudad = servidor.etiquetaCiudad(t);
        if (! ciudad.equals(""))
            t = ciudad;

        String finalT = t;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(finalT);
                    Tiempo[] tiempos = servidor.pronosticoNombre(finalT);

                    mainApp.showTemperaturaDiasVista(tiempos);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }




    @FXML
    public void busquedaFavorito() throws IOException{

        String selectedValue = favList.getSelectionModel().getSelectedItems().toString();
        selectedValue = selectedValue.substring(1, selectedValue.length() - 1);
        System.out.println(selectedValue);

        String finalSelectedValue = selectedValue;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(finalSelectedValue);
                    Tiempo tiempo = servidor.buscaTiempoPorNombre(finalSelectedValue);
                    mainApp.showTemperaturaVista(tiempo);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    public void setServer(SimpleWeather servidor) {
        this.servidor = servidor;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
