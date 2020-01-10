package core;

import core.Exceptions.CityNotFoundException;
import core.Exceptions.NoValidCoordinatesException;
import core.OWM.App;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.Connector;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Map;

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
        public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, NoValidCoordinatesException{
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
    public Boolean removeEtiqueta(String ciudadValue, String etiquetaValue) {
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
    public ObservableList<String> getFavoritos() {
        return null;
    }

    @Override
    public Map<String, ObservableList<String>> getCiudadesEtiquetas() {
        return null;
    }

    @Override
    public Coordenadas getCoordenadas(String selectedValue) {
        return null;
    }
}
