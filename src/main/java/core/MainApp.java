package core;

import core.OWM.App_persistence;
import core.model.Tiempo;
import core.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private SimpleWeather servidor;

    @Override
    public void start(Stage primaryStage) throws Exception {
         //servidor = new App();    // Version sin persistencia
        servidor = new App_persistence(); //Version con persistencia

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tiempo Sencillo");

        initRootLayout();
        showInicioVista();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/RootLayout.fxml"));

            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInicioVista(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InicioVista.fxml"));
            AnchorPane selectionRol = (AnchorPane) loader.load();

            rootLayout.setCenter(selectionRol);

            InicioController controller = loader.getController();
            controller.setServer(servidor);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTemperaturaVista(Tiempo tiempo) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TemperaturaVista.fxml"));
        AnchorPane temperaturaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(temperaturaVista);

        TemperaturaVistaController controller = loader.getController();
        controller.setServer(servidor);
        controller.setTiempo(tiempo);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);

    }

    public void showTemperaturaDiasVista(Tiempo[] tiempos) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TemperaturaDiasVista.fxml"));
        AnchorPane temperaturaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(temperaturaVista);


        TemperaturaDiasController controller = loader.getController();
        controller.setServer(servidor);
        controller.setTiempos(tiempos);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);
    }

    public void showTagVista() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/EtiquetaVista.fxml"));
        AnchorPane etiquetaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(etiquetaVista);

        EtiquetaController controller = loader.getController();
        controller.setServer(servidor);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);

    }

    public void showTagCoorVista() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/EtiquetaCoorVista.fxml"));
        AnchorPane etiquetaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(etiquetaVista);

        EtiquetaCoorController controller = loader.getController();
        controller.setServer(servidor);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);

    }

    public void showTemperaturaDiasCoorVista(Tiempo[] tiempos) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TemperaturaDiasCoorVista.fxml"));
        AnchorPane temperaturaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(temperaturaVista);


        TemperaturaDiasController controller = loader.getController();
        controller.setServer(servidor);
        controller.setTiempos(tiempos);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);
    }

    public void showTemperaturaCoorVista(Tiempo tiempos) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TemperaturaCoorVista.fxml"));
        AnchorPane temperaturaVista = (AnchorPane) loader.load();

        rootLayout.setCenter(temperaturaVista);


        TemperaturaVistaCoorController controller = loader.getController();
        controller.setServer(servidor);
        controller.setTiempo(tiempos);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(this);
    }
}
