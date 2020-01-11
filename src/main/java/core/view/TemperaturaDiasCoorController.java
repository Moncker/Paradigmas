package core.view;

import core.MainApp;
import core.SimpleWeather;
import core.model.Tiempo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TemperaturaDiasCoorController {

    @FXML
    private Label Logo;

    @FXML
    private Label cityname;

    @FXML
    private Label t1;

    @FXML
    private Label h1;

    @FXML
    private Label d1;

    @FXML
    private Label d2;

    @FXML
    private Label d3;

    @FXML
    private Label t2;

    @FXML
    private Label t3;

    @FXML
    private Label h2;

    @FXML
    private Label h3;

    private Tiempo[] tiempos;
    private MainApp mainApp;
    private SimpleWeather servidor;
    private Stage primaryStage;

    @FXML
    private void volverASeleccionCiudad() {
        mainApp.showInicioVista();
    }

    public void setTiempos(Tiempo[] tiempos){
        this.tiempos = tiempos;
        cityname.setText(tiempos[0].getCiudad());
        t1.setText("" + (Math.round(tiempos[0].getGrados() * 10) /10d));
        t2.setText("" + (Math.round(tiempos[1].getGrados() * 10) /10d));
        t3.setText("" + (Math.round(tiempos[2].getGrados() * 10) /10d));

        d1.setText(tiempos[0].getFecha() + "");
        d2.setText(tiempos[1].getFecha() + "");
        d3.setText(tiempos[2].getFecha() + "");

        h1.setText(tiempos[0].getHumedad() + "");
        h2.setText(tiempos[1].getHumedad() + "");
        h3.setText(tiempos[2].getHumedad() + "");

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
