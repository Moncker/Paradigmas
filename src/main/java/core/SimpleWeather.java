package core;

import core.Exceptions.NoValidCoordinatesException;
import core.model.Coordenadas;
import core.model.Tiempo;
import core.Exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface SimpleWeather {

    Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, NoValidCoordinatesException, NoValidCoordinatesException, NoValidCoordinatesException;

    Tiempo[] pronosticoNombre(String nombre) throws CityNotFoundException;
    Tiempo[] pronosticoCoordenadas(float lat, float lon) throws NoValidCoordinatesException;

    Boolean addEtiqueta (String ciudad, String etiqueta) throws CityNotFoundException;
    Boolean addEtiquetaCoor(String ciudad, String etiqueta);
    String etiquetaCiudad (String etiqueta); // get ciudad desde etiqueta
    String etiquetaCoordenada (String etiqueta);
    Map<String, ObservableList<String>> getCoordenadasEtiquetas();
    Map<String, ObservableList<String>> getCiudadesEtiquetas();
    Boolean removeEtiqueta(String ciudadValue, String etiquetaValue);
    Boolean removeEtiquetaCoor(String coorValue, String etiquetaValue);

    Boolean addFavoritos(String ciudad);
    Boolean removeFavoritos(String ciudad);
    ObservableList<String> getFavoritos();

    Coordenadas getCoordenadas(String selectedValue);
}
