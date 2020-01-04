package e2e;

import core.Exceptions.CoordenadasInvalidasException;
import core.OWM.CurrentWeather;
import core.SimpleWeather;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import javax.rmi.CORBA.Tie;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;

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
            return new Tiempo("Madrid", 23.5, "soleado", 45);
    }

    @Override
    public Tiempo buscaTiempoPorCoordenadas(double lat, double lon) throws IOException, CoordenadasInvalidasException {
        // Si se pasan de las latitudes y longitudes existentes lanza excepcion
        if (-90 < lat  && 90 > lat   && -180 < lon && 180 > lon)                       // Nombre de ciudad no valida
           return new Tiempo("XXX", 23.5, "soleado", 45);


            //throw new CoordenadasInvalidasException("Coordenadas no validas");
        else
            return new Tiempo("Madrid", 23.5, "soleado", 45);
    }

    @Override
    public Tiempo[] pronosticoNombre(String nombre){
        Tiempo[] pronostico;
        if(nombre.equals("XXX")){
            pronostico = new Tiempo[]{new Tiempo(), new Tiempo()};
            return pronostico;
        }
            pronostico = new Tiempo[]{new Tiempo(), new Tiempo(), new Tiempo()};
            return pronostico;
    }

    @Override
    public Tiempo[] pronosticoCoordenadas(double lat, double lon) {
        return new Tiempo[0];
    }

    @Override
    public Boolean addEtiqueta(String ciudad, String etiqueta) {
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