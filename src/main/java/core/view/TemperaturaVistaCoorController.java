package core.view;

import core.Exceptions.CityNotFoundException;
import core.MainApp;
import core.SimpleWeather;
import core.model.Tiempo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TemperaturaVistaCoorController {

    @FXML
    private Label Logo;

    @FXML
    private Label cityname;

    @FXML
    private Label fecha;

    @FXML
    private TextField txtEtiqueta;

    @FXML
    private Label temperature;

    @FXML
    private Label humedad;

    private Tiempo tiempo;
    private MainApp mainApp;
    private SimpleWeather servidor;
    private Stage primaryStage;

    @FXML
    private void volverASeleccionCiudad() {
        mainApp.showInicioVista();
    }

    public void setTiempo(Tiempo tiempo){
        this.tiempo = tiempo;
        cityname.setText(tiempo.getCiudad());
        temperature.setText("" + (Math.round(tiempo.getGrados() * 10) /10d));
        fecha.setText(tiempo.getFecha() + "");
        humedad.setText(tiempo.getHumedad() + "");
    }

    public void crearEtiqueta(){
        String etiqueta = txtEtiqueta.getText();
        servidor.addEtiquetaCoor(tiempo.getCiudad(), etiqueta);
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


