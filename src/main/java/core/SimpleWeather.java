package core;

import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

public interface SimpleWeather {
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException;
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException;
    public String etiquetaCiudad(String etiqueta);
    public Boolean addEtiqueta (String ciudad, String etiqueta);
    public Boolean addFavoritos(String ciudad);
    public ObservableList<String> getFavoritos();
}
