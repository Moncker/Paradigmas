package core.e2e;

import core.SimpleWeather;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;

public class SUT implements SimpleWeather {

    //HashMap <String, HashMap<Date,Tiempo>> ciudadesTiempos;
    //HashMap <Coordenadas, HashMap<Date,Tiempo>> cordenadasTiempos;
    //HashMap <Localizacion, HashMap<Date,Tiempo>> historial;


    public SUT() {
        //this.ciudadesTiempos = ciudadesTiempos;
        //this.cordenadasTiempos = cordenadasTiempos;
        //this.historial=historial;
    }


    @Override
    public Tiempo buscaTiempoPorNombre(String nombre) throws CityNotFoundException {
        if (nombre.equals("XXX"))                       // Nombre de ciudad no valida
            throw new CityNotFoundException("XXX");
        else
            return new Tiempo(23.5, "soleado", 45);
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