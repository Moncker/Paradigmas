package core.view;

import core.MainApp;
import core.model.Tiempo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TemperaturaVistaController {

        @FXML
        private Label Logo;

        @FXML
        private Label cityname;

        @FXML
        private Label temperature;

        @FXML
        private Label fecha;

        @FXML
        private Label humedad;

        private Tiempo tiempo;
        private MainApp mainApp;


        @FXML
        private void initialize() {
        }

        @FXML
        private void volverASeleccionCiudad() {
                mainApp.showInicioVista();
        }

        public void setTiempo(Tiempo tiempo){
                temperature.setText("" + (Math.round(tiempo.getGrados() * 10) /10d));
                fecha.setText(tiempo.getFecha() + "");
                humedad.setText(tiempo.getHumedad() + "");
        }

        public void setMainApp(MainApp mainApp) {
                this.mainApp = mainApp;
        }

}

