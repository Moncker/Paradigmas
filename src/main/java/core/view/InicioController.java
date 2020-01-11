package core.view;
import core.Exceptions.NoValidCoordinatesException;
import core.MainApp;
import core.SimpleWeather;
import core.model.Tiempo;
import core.Exceptions.CityNotFoundException;
import core.tools.Geolocation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class InicioController {


    @FXML
    private ListView<String> favList;
    @FXML
    private TextField cityname;
    @FXML
    private TextField tag;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;

    private SimpleWeather servidor;
    private MainApp mainApp;

    @FXML
    private void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // listView se alimenta de datos observables que cede el servidor
                favList.setItems(servidor.getFavoritos());
            }
        });

    }

    @FXML
    public void busquedaCoor() throws IOException, NoValidCoordinatesException {
        String lati = lat.getText();
        String longi = lon.getText();

        Tiempo tiempo = servidor.buscaTiempoPorCoordenadas(Float.parseFloat(lati), Float.parseFloat(longi));
        mainApp.showTemperaturaCoorVista(tiempo);
    }

    @FXML
    public void busquedaCiudad() throws IOException, NoValidCoordinatesException, CityNotFoundException {
        String t = cityname.getText();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Tiempo tiempo = servidor.buscaTiempoPorNombre(t);
                    mainApp.showTemperaturaVista(tiempo);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @FXML
    public void pronosticoCoor() throws IOException, NoValidCoordinatesException {
        String lati = lat.getText();
        String longi = lon.getText();
        Tiempo[] tiempos = servidor.pronosticoCoordenadas(Float.parseFloat(lati), Float.parseFloat(longi));

        mainApp.showTemperaturaDiasCoorVista(tiempos);
    }


    @FXML
    public void pronosticoCiudad() throws IOException, NoValidCoordinatesException, CityNotFoundException {
        String t = cityname.getText();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Tiempo[] tiempos = servidor.pronosticoNombre(t);
                    mainApp.showTemperaturaDiasVista(tiempos);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @FXML
    public void busquedaFavorito() throws IOException{

        String selectedValue = favList.getSelectionModel().getSelectedItems().toString();
        selectedValue = selectedValue.substring(1, selectedValue.length() - 1);
        System.out.println(selectedValue);

        String finalSelectedValue = selectedValue;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(finalSelectedValue);
                    Tiempo tiempo = servidor.buscaTiempoPorNombre(finalSelectedValue);
                    mainApp.showTemperaturaVista(tiempo);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @FXML
    public void borrarFavs() {
        String selectedValue = favList.getSelectionModel().getSelectedItems().toString().toLowerCase();
        selectedValue = selectedValue.substring(1, selectedValue.length() - 1);

        servidor.removeFavoritos(selectedValue);
    }

    @FXML
    void mostrarTags() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mainApp.showTagVista();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void mostrarTagsCoor(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mainApp.showTagCoorVista();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void busquedaTag(){
        String t = tag.getText();

        String coors = servidor.etiquetaCoordenada(t);
        String ciudad = servidor.etiquetaCiudad(t);

        if (!coors.equals("")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] coordenadas = coors.split(" - ");
                        float lat = Float.parseFloat(coordenadas[0]);
                        float lon = Float.parseFloat(coordenadas[1]);
                        Tiempo tiempo = servidor.buscaTiempoPorCoordenadas(lat, lon);
                        mainApp.showTemperaturaCoorVista(tiempo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoValidCoordinatesException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else if(!ciudad.equals("")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Tiempo tiempo = servidor.buscaTiempoPorNombre(ciudad);
                        mainApp.showTemperaturaVista(tiempo);
                    } catch (IOException | CityNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });

        }else{
            System.out.println("No existe el tag proporcionado.");
        }
    }

    @FXML
    void pronosticoTag(){
        String t = tag.getText();

        String coors = servidor.etiquetaCoordenada(t);
        String ciudad = servidor.etiquetaCiudad(t);

        if (!coors.equals("")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] coordenadas = coors.split(" - ");
                        float lat = Float.parseFloat(coordenadas[0]);
                        float lon = Float.parseFloat(coordenadas[1]);
                        Tiempo[] tiempos = servidor.pronosticoCoordenadas(lat,lon);
                        mainApp.showTemperaturaDiasCoorVista(tiempos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoValidCoordinatesException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else if(!ciudad.equals("")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Tiempo[] tiempos = servidor.pronosticoNombre(ciudad);
                        mainApp.showTemperaturaDiasVista(tiempos);
                    } catch (IOException | CityNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });

        }else{
            System.out.println("No existe el tag proporcionado.");
        }
    }

    @FXML
    public  void busquedaGeo() throws IOException{
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Geolocation.getCityByPublicIp());
                    Tiempo tiempo = servidor.buscaTiempoPorNombre(Geolocation.getCityByPublicIp());
                    mainApp.showTemperaturaVista(tiempo);
                } catch (IOException | CityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

    }



    public void setServer(SimpleWeather servidor) {
        this.servidor = servidor;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
