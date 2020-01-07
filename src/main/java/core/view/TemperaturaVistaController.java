package core.view;

import core.MainApp;
import core.OWM.App;
import core.SimpleWeather;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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

        @FXML
        private TextField textoEtiqueta;

        private Tiempo tiempo;
        private MainApp mainApp;
        private SimpleWeather servidor;
        private Stage primaryStage;


        @FXML
        private void initialize() {
        }

        public TemperaturaVistaController(){

        }

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
                String etiqueta = textoEtiqueta.getText();

                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                                System.out.println(tiempo.getCiudad() + ": etiqueta: "+ etiqueta);
                                try {
                                        servidor.addEtiqueta(tiempo.getCiudad(), etiqueta);
                                } catch (CityNotFoundException e) {
                                        e.printStackTrace();
                                }

                                /*if( servidor.addEtiqueta(tiempo.getCiudad(), etiqueta) ){
                                        // lanza pop-up: Se ha anyadido

                                } else {
                                        /* lanza pop-up: Ya esta creado!
                                        mainApp.showPersonEditDialog(selectedPerson);
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.initOwner(dialogStage);
                                        alert.setTitle("Invalid Fields");
                                        alert.setHeaderText("Please correct invalid fields");
                                        alert.setContentText(errorMessage);

                                        alert.showAndWait();

                                        return false;
                                        *
                                         */
                                }


                                // La asociacion se genera entre ciudad y la tag


                                // Volvemos a la vista inicial y está la nueva etiqueta
                                //mainApp.showInicioVista();

                        });

        }

        public void addFavoritos(){
                servidor.addFavoritos(tiempo.getCiudad());
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

