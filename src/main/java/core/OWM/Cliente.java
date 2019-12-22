package core.OWM;

import core.Vistas.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Cliente extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private App cliente;


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        try{
            System.out.println("PASO 1");
            this.primaryStage = primaryStage;
            System.out.println("PASO 2");

            this.cliente=new App();
            System.out.println("PASO 3");
            initRootLayout();
            System.out.println("PASO 4");
            showInicio();
        }catch (Exception e){
            System.out.println("ERROR AL INCIAR");
        }



    }
    public void initRootLayout() throws IOException {

        System.out.println("1");
        FXMLLoader loader = new FXMLLoader();
        System.out.println("2");
        loader.setLocation(Cliente.class.getResource("../core/Vistas/RootLayout.fxml"));
        System.out.println("3");
        //rootLayout = (AnchorPane) loader.load();

        System.out.println("4");
        Scene scene = new Scene(rootLayout);

        System.out.println("5");
        primaryStage.setScene(scene);
        System.out.println("6");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    /**
     * Muestra la pantalla principal
     */
    public void showInicio() {
        try {
            System.out.println("PASO 1");
            FXMLLoader loader = new FXMLLoader();
            System.out.println("PASO 1");
            loader.setLocation(Cliente.class.getResource("../core/Vistas/InicioVista.fxml"));
            System.out.println("PASO 1");
            AnchorPane inicioView = (AnchorPane) loader.load();
            System.out.println("PASO 1");




            InicioController controller = loader.getController();
            controller.setMainApp(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
