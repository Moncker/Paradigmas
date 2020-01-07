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


public class TemperaturaDiasController {

    @FXML
    private Label Logo;

    @FXML
    private Label cityname;

    @FXML
    private Label t1;
    @FXML
    private Label t2;
    @FXML
    private Label t3;
    @FXML
    private Label h1;
    @FXML
    private Label h2;
    @FXML
    private Label h3;
    @FXML
    private Label d1;
    @FXML
    private Label d2;
    @FXML
    private Label d3;

    @FXML
    private Label fecha;

    @FXML
    private Label humedad;

    @FXML
    private TextField textoEtiqueta;

    private Tiempo[] tiempos;
    private MainApp mainApp;
    private SimpleWeather servidor;
    private Stage primaryStage;


    @FXML
    private void initialize() {
    }

    public TemperaturaDiasController(){

    }

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

    public void crearEtiqueta(){
        String etiqueta = textoEtiqueta.getText();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println(tiempos[0].getCiudad() + ": etiqueta: "+ etiqueta);
                try {
                    servidor.addEtiqueta(tiempos[0].getCiudad(), etiqueta);
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
        servidor.addFavoritos(tiempos[0].getCiudad());
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

