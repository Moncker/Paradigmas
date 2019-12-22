package core.Vistas;
import core.OWM.App;
import core.OWM.Cliente;
import core.model.Tiempo;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class InicioController {
    @FXML
    private TextField cityname;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;
    @FXML
    private Button ok1;
    @FXML
    private Button ok2;

    private App cliente;

    @FXML
    private void initialize() {
    }
    public InicioController(){

    }
    public void setMainApp(App mainApp) {
        this.cliente = mainApp;
    }
    @FXML
    public void pulsaEnviaCiudad() throws IOException {
        String t = cityname.getText();
        String lati = lat.getText();
        String longi = lon.getText();
        cliente.buscaTiempoPorNombre(t);
    }
    @FXML
    public void pulsaEnviaCoordenadas() throws IOException {
        String t = cityname.getText();
        String lati = lat.getText();
        String longi = lon.getText();
        cliente.buscaTiempoPorCoordenadas(Float.parseFloat(lati),Float.parseFloat(longi));
    }


}
