package core;

import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.ConnectorStore;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * CLASE ENCARGADA DE UTILIZAR LA BBDD SI EL DATO EXISTE
 *      Y EL SIPLEWEATHER SI ES NECESARIO EXTRAERLO DEL SERVIDOR
 */

public class MemoizedSimpleWeather implements SimpleWeather {

    SimpleWeather parent;
    ConnectorStore connectorStore;

    public MemoizedSimpleWeather(SimpleWeather parent, ConnectorStore connectorStore) {
        this.parent = parent;
        this.connectorStore = connectorStore;
        connectorStore.connect();
    }

    @Override
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException, CityNotFoundException {
        Tiempo tiempo = connectorStore.getWeather(new Localizacion("Madrid"));

        if (tiempo != null)
            return tiempo;

        return parent.buscaTiempoPorNombre(nombre);
    }

    @Override
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException {
        return null;
    }

    @Override
    public String etiquetaCiudad(String etiqueta) {
        return null;
    }

    @Override
    public Boolean addEtiqueta(String ciudad, String etiqueta) {
        return null;
    }

    @Override
    public Boolean addFavoritos(String ciudad) {
        return null;
    }

    @Override
    public ObservableList<String> getFavoritos() {
        return null;
    }
}
