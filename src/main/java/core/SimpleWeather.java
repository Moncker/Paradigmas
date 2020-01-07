package core;

import core.Exceptions.CoordenadasInvalidasException;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface SimpleWeather {

    Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, CoordenadasInvalidasException, core.Exceptions.CoordenadasInvalidasException, CoordenadasInvalidasException;

    Tiempo[] pronosticoNombre(String nombre);
    Tiempo[] pronosticoCoordenadas(float lat, float lon) throws CoordenadasInvalidasException;

    Boolean addEtiqueta (String ciudad, String etiqueta) throws CityNotFoundException;
    String etiquetaCiudad (String etiqueta); // get ciudad desde etiqueta
    Boolean remplaceEtiqueta (String ciudad, String etiqueta);

    Boolean addFavoritos(String ciudad);
    Boolean removeFavoritos(String ciudad);
    Boolean getFavoritos(String ciudad);

    ObservableList<String> getFavoritos();


}
