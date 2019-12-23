package core;

import core.OWM.App;
import core.model.Localizacion;
import core.model.Tiempo;
import core.view.InicioController;
import core.view.TemperaturaVistaController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private App servidor;



    @Override
    public void start(Stage primaryStage) throws Exception {
        servidor = new App();

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




}