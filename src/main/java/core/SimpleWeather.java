package core;

import core.Exceptions.CoordenadasInvalidasException;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface SimpleWeather {
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    public Tiempo buscaTiempoPorCoordenadas(double lat, double lon) throws IOException, CoordenadasInvalidasException, core.Exceptions.CoordenadasInvalidasException, CoordenadasInvalidasException;

    public Tiempo[] pronosticoNombre(String nombre);
    public Tiempo[] pronosticoCoordenadas(double lat, double lon) ;

    public Boolean addEtiqueta (String ciudad, String etiqueta);
    public Boolean remplaceEtiqueta (String ciudad, String etiqueta);

    public Boolean addFavoritos(String ciudad);
    public Boolean removeFavoritos(String ciudad);
    public Boolean getFavoritos(String ciudad);


    public ObservableList<String> getFavoritos();


}
