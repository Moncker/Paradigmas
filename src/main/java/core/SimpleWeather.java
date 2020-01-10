package core;

import core.Exceptions.NoValidCoordinatesException;
import core.model.Tiempo;
import core.Exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public interface SimpleWeather {

    Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, NoValidCoordinatesException, NoValidCoordinatesException, NoValidCoordinatesException;

    Tiempo[] pronosticoNombre(String nombre) throws CityNotFoundException;
    Tiempo[] pronosticoCoordenadas(float lat, float lon) throws NoValidCoordinatesException;

    Boolean addFavoritos(String ciudad);
    Boolean removeFavoritos(String ciudad);
    ArrayList<String> getFavoritos(String ciudad);

    Boolean addEtiqueta (String ciudad, String etiqueta) throws CityNotFoundException;
    String etiquetaCiudad (String etiqueta); // get ciudad desde etiqueta
    Boolean remplaceEtiqueta (String ciudad, String etiqueta);

    ObservableList<String> getFavoritos();


}
