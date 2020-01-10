package e2e;

import core.Exceptions.NoValidCoordinatesException;
import core.SimpleWeather;
import core.model.Coordenadas;
import core.model.Tiempo;
import core.Exceptions.CityNotFoundException;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class SUT implements SimpleWeather {

    ArrayList<String> favoritos;
    Hashtable<String, String> ciudadEtiquetas;


    //HashMap <String, HashMap<Date,Tiempo>> ciudadesTiempos;
    //HashMap <Coordenadas, HashMap<Date,Tiempo>> cordenadasTiempos;
    //HashMap <Localizacion, HashMap<Date,Tiempo>> historial;

    public SUT() {
        favoritos = new ArrayList<>();
        ciudadEtiquetas = new Hashtable<>();

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
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException, NoValidCoordinatesException {
        if (-90 > lat  || 90 < lat || -180 > lon || 180 < lon)
            throw new NoValidCoordinatesException("Coordenadas no validas");
        return new Tiempo("Madrid", 23.5, "soleado", 45);
    }

    @Override
    public Tiempo[] pronosticoNombre(String nombre) throws CityNotFoundException {
        Tiempo[] pronostico;
        if(nombre.equals("XXX"))
            throw new CityNotFoundException("XXX");

        Tiempo hoy = new Tiempo();
        Tiempo manana = new Tiempo();
        Tiempo pasManana = new Tiempo();

        hoy.setFecha(LocalDate.now());
        manana.setFecha(LocalDate.now().plusDays(1));
        pasManana.setFecha(LocalDate.now().plusDays(2));

        pronostico = new Tiempo[]{hoy, manana, pasManana};
        return pronostico;
    }

    @Override
    public Tiempo[] pronosticoCoordenadas(float lat, float lon) throws NoValidCoordinatesException {
        if (-90 > lat  || 90 < lat || -180 > lon || 180 < lon)
            throw new NoValidCoordinatesException("Coordenadas no validas");
        Tiempo[] pronostico = new Tiempo[]{new Tiempo(), new Tiempo(), new Tiempo()};
        return pronostico;
    }

    @Override
    public Boolean addFavoritos(String ciudad) {
        if (favoritos.contains(ciudad))
            return false;
        return favoritos.add(ciudad);
    }

    @Override
    public Boolean removeFavoritos(String ciudad) {
        if (! favoritos.contains(ciudad))
            return false;
        return favoritos.remove(ciudad);

    }





    @Override
    public Boolean addEtiqueta(String ciudad, String etiqueta) throws CityNotFoundException {
        if (! ciudadEtiquetas.contains(ciudad))
            return false;
        return true;
  //          ciudadEtiquetas.put(ciudad, new ArrayList<>());

//        return ciudadEtiquetas.get(ciudad).add(etiqueta);
    }

    @Override
    public String etiquetaCiudad(String etiqueta) {
        return ciudadEtiquetas.get(etiqueta);
    }

    @Override
    public Map<String, ObservableList<String>> getCiudadesEtiquetas() {
        return null;
    }

    @Override
    public Boolean removeEtiqueta(String ciudadValue, String etiquetaValue) {
        return null;
    }

    @Override
    public ObservableList<String> getFavoritos() {
        return null;
    }

    @Override
    public Coordenadas getCoordenadas(String selectedValue) {
        return null;
    }


}