package core;

import core.Exceptions.CoordenadasInvalidasException;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface SimpleWeather {
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, CoordenadasInvalidasException, core.Exceptions.CoordenadasInvalidasException, CoordenadasInvalidasException;

    public Tiempo[] pronosticoNombre(String nombre);
    public Tiempo[] pronosticoCoordenadas(float lat, float lon) throws CoordenadasInvalidasException;

    public Boolean addEtiqueta (String ciudad, String etiqueta) throws CityNotFoundException;
    public Boolean remplaceEtiqueta (String ciudad, String etiqueta);

    public Boolean addFavoritos(String ciudad);
    public Boolean removeFavoritos(String ciudad);
    public Boolean getFavoritos(String ciudad);


    public ObservableList<String> getFavoritos();


}
