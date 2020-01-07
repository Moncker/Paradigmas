package core;

import core.Exceptions.CoordenadasInvalidasException;
import core.OWM.App;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.Connector;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * CLASE ENCARGADA DE UTILIZAR LA BBDD SI EL DATO EXISTE
 *      Y EL SIPLEWEATHER SI ES NECESARIO EXTRAERLO DEL SERVIDOR
 */

public class MemoizedSimpleWeather implements SimpleWeather {

    SimpleWeather parent;
    Connector connector;

    public MemoizedSimpleWeather() {
        this.parent = new App();
        this.connector = new Connector();
        connector.connect();
        //  inicializa
    }

    @Override
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException {
        Tiempo tiempo = connector.getWeather(new Localizacion(nombre));

        if (tiempo != null)
            return tiempo;

        return parent.buscaTiempoPorNombre(nombre);
    }

    @Override
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, CoordenadasInvalidasException{
        return null;
    }

    @Override
    public Tiempo[] pronosticoNombre(String nombre) {
        return new Tiempo[0];
    }

    @Override
    public Tiempo[] pronosticoCoordenadas(float lat, float lon) {
        return new Tiempo[0];
    }

    @Override
    public Boolean addEtiqueta(String ciudad, String etiqueta) {
        return null;
    }

    @Override
    public String etiquetaCiudad(String etiqueta) {
        return null;
    }

    @Override
    public Boolean remplaceEtiqueta(String ciudad, String etiqueta) {
        return null;
    }

    @Override
    public Boolean addFavoritos(String ciudad) {
        return null;
    }

    @Override
    public Boolean removeFavoritos(String ciudad) {
        return null;
    }

    @Override
    public Boolean getFavoritos(String ciudad) {
        return null;
    }

    @Override
    public ObservableList<String> getFavoritos() {
        return null;
    }
}
