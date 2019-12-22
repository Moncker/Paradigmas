package core.view;
import core.MainApp;
import core.OWM.App;
import core.model.Tiempo;
import javafx.application.Platform;
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

    private App servidor;
    private MainApp mainApp;
    private Tiempo tiempo;

    @FXML
    private void initialize() {
       servidor = new App();
    }
    public InicioController(){

    }
    public void setServer(App mainApp) {
        this.servidor = mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }



    @FXML
    public void busquedaCiudad() throws IOException {
        String t = cityname.getText();
        String lati = lat.getText();
        String longi = lon.getText();


        Platform.runLater(new Runnable() {
                              @Override
                              public void run() {
                                  try {
                                      Tiempo tiempo = servidor.buscaTiempoPorNombre(t);
                                      System.out.println(tiempo.getGrados() + "");
                                      mainApp.showTemperaturaVista(tiempo);
                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }

                              }
                          });
    }

    @FXML
    public void busquedaCoor() throws IOException {
        String t = cityname.getText();
        String lati = lat.getText();
        String longi = lon.getText();
        servidor.buscaTiempoPorCoordenadas(Float.parseFloat(lati),Float.parseFloat(longi));
    }


}
