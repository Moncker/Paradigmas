package core.OWM;

import core.SimpleWeather;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

public class App_persistence implements SimpleWeather {

    private OpenWeatherMap owm = new OpenWeatherMap("weather");

    //HashMap<String, Tiempo> historial;
    Map<String, ObservableList<String>> ciudadEtiqueta;
    private ObservableList<String> observableFav;

    Connector connector;

    public App_persistence() {
        this.owm.setApiKey("8d92c4241ad64d0fe808a2f501954581");
        //this.historial = new HashMap<String, Tiempo>();

        connector = new Connector();
        connector.connect();
        this.ciudadEtiqueta = new HashMap<>();
        cargarTags();
        this.observableFav = FXCollections.<String> observableArrayList();
        cargarFavs();
        connector.updateWeatherDatabase();
    }

    private void cargarTags(){
        Map<Localizacion, List<String>> tags = connector.getTags();
        for (Localizacion localizacion : tags.keySet()){
            this.ciudadEtiqueta.put(localizacion.getName(), observableArrayList(connector.getTagsCity(localizacion)));
        }
    }

    private void cargarFavs (){
        FXCollections.<String> observableArrayList();
        List<Localizacion> favs = connector.getFavourites();
        for (Localizacion localizacion : favs)
            this.observableFav.add(localizacion.getName().substring(0,1).toUpperCase() + localizacion.getName().substring(1));
    }

    // ____________________________
    // METODOS TIEMPO
    // _____________________________

    @Override
    public Tiempo[] pronosticoCoordenadas(float lat, float lon) {
        Tiempo[] ret = new Tiempo[3];

        String nombre = connector.getNombre(new Coordenadas(lat,lon));

        if (nombre != null) {
            Localizacion localizacion = new Localizacion(nombre, new Coordenadas(lat,lon));
            Tiempo[] t = connector.getWeatherDays(localizacion);
            if(t[0] != null && t[1] != null && t[2] != null) {
                return t;
            }
        }

        byte forecastDays = 3;

        HourlyForecast forecast = owm.dailyForecastByCoordinates(lat, lon, forecastDays);

        int numForecasts = forecast.getForecastCount();//40 SIEMPRE

        for (int i = 0; i < 21; i += 8) {
            HourlyForecast.Forecast dayForecast = forecast.getForecastInstance(i);
            Float temperature = dayForecast.getMainInstance().getTemperature();
            Float humidity = dayForecast.getMainInstance().getHumidity();
            Float s = dayForecast.getCloudsInstance().getPercentageOfClouds();
            String a = dayForecast.getWeatherInstance(0).getWeatherDescription();
            System.out.println(i / 8);
            ret[i / 8] = new Tiempo(temperature, s.toString(), humidity, dayForecast.getDateTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            connector.saveWeather(ret[i/8], new Localizacion(nombre, new Coordenadas(lat,lon)));

            System.out.println(ret[i / 8].getCiudad());
            System.out.println(ret[i / 8].getGrados());
            System.out.println(ret[i / 8].getHumedad());
            System.out.println(ret[i / 8].getEstado());
            System.out.println(ret[i / 8].getFecha().toString());
            System.out.println("________________________________________________________________");


        }
        return ret;
    }

    @Override
    public Tiempo[] pronosticoNombre(String nombre) {
        try {

            Coordenadas coordenadas = connector.getCoor(nombre);

            if (coordenadas != null) {
                Localizacion localizacion = new Localizacion(nombre, coordenadas);
                Tiempo[] t = connector.getWeatherDays(localizacion);
                if(t[0] != null && t[1] != null && t[2] != null) {
                    return t;
                }
            }

            Tiempo[] ret = new Tiempo[3];
            byte forecastDays = 3;
            float lat;
            float lon;

            HourlyForecast forecast = owm.dailyForecastByCityName(nombre, forecastDays);
            if (coordenadas == null){
                lat = forecast.getCityInstance().getCoordInstance().getLatitude();
                lon = forecast.getCityInstance().getCoordInstance().getLongitude();
            } else{
                lat = coordenadas.getX();
                lon = coordenadas.getY();
            }

            int numForecasts = forecast.getForecastCount();//40 SIEMPRE


            for (int i = 0; i < 21; i += 8) {
                HourlyForecast.Forecast dayForecast = forecast.getForecastInstance(i);
                Float temperature = dayForecast.getMainInstance().getTemperature();
                Float humidity = dayForecast.getMainInstance().getHumidity();
                Float s = dayForecast.getCloudsInstance().getPercentageOfClouds();
                String a = dayForecast.getWeatherInstance(0).getWeatherDescription();
                System.out.println(i / 8);
                ret[i / 8] = new Tiempo(nombre, temperature, s.toString(), humidity, dayForecast.getDateTime().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(), LocalDate.now());
                connector.saveWeather(ret[i/8], new Localizacion(nombre, new Coordenadas(lat,lon)));

                /*System.out.println(ret[i / 8].getCiudad());
                System.out.println(ret[i / 8].getGrados());
                System.out.println(ret[i / 8].getHumedad());
                System.out.println(ret[i / 8].getEstado());
                System.out.println(ret[i / 8].getFecha().toString());
                System.out.println("________________________________________________________________");

                 */

            }

            return ret;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException {
        try {
            Coordenadas coordenadas = connector.getCoor(nombre);

            if (coordenadas != null) {
                Localizacion localizacion = new Localizacion(nombre, coordenadas);
                Tiempo t = connector.getWeather(localizacion);
                if(t != null)
                    return t;
            }

            CurrentWeather tiempoOWN = owm.currentWeatherByCityName(nombre);

            // anyadimos a variables los datos dados por la API
            String ciudad = tiempoOWN.getCityName();
            float temp = tiempoOWN.getMainInstance().getTemperature();
            float humedad = tiempoOWN.getMainInstance().getHumidity();
            float s = tiempoOWN.getCloudsInstance().getPercentageOfClouds();
            Date date = tiempoOWN.getDateTime();
            LocalDate localdate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            Tiempo tiempo = new Tiempo(ciudad.toLowerCase(), temp, "" + s, humedad, localdate, LocalDate.now());

            connector.saveWeather(tiempo, new Localizacion(nombre, new Coordenadas(
                    tiempoOWN.getCoordInstance().getLatitude(),
                    tiempoOWN.getCoordInstance().getLongitude()
            )));

            return tiempo;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException {

        String nombre = connector.getNombre(new Coordenadas(lat,lon));

        if (nombre != null) {
            Localizacion localizacion = new Localizacion(nombre, new Coordenadas(lat,lon));
            Tiempo t = connector.getWeather(localizacion);
            if(t != null)
                return t;
        }

        CurrentWeather tiempo = owm.currentWeatherByCoordinates(lat, lon);
        String ciudad = tiempo.getCityName();
        float temp = tiempo.getMainInstance().getTemperature();
        float humedad = tiempo.getMainInstance().getHumidity();
        float s = tiempo.getCloudsInstance().getPercentageOfClouds();
        Date date = tiempo.getDateTime();
        LocalDate localdate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Tiempo ti = new Tiempo(ciudad, temp, "" + s, humedad, localdate);
        return ti;

    }

    // ____________________________
    // METODOS ETIQUETA
    // _____________________________

    public String etiquetaCiudad(String etiqueta) {

        if (ciudadEtiqueta.isEmpty()) {
            return "";
        }

        for (String ciudad : ciudadEtiqueta.keySet()) {
            if (ciudadEtiqueta.get(ciudad).contains(etiqueta)) {
                return ciudad;
            }
        }
        return "";


    }

    @Override
    public Boolean addEtiqueta(String ciudad, String etiqueta) {
        Coordenadas coordenadas = connector.getCoor(ciudad.toLowerCase());
        Localizacion localizacion = new Localizacion(ciudad.toLowerCase(), coordenadas);

        connector.saveTag(localizacion, etiqueta);
        if(ciudadEtiqueta.containsKey(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1))){
            // añadimos a lista
            System.out.println("AÑADIMOS A LISTA");
            this.ciudadEtiqueta.get(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1)).add(etiqueta);
        } else {
            // creamos nuevo
            System.out.println("CREAMOS NUEVO");
            this.ciudadEtiqueta.put(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1), FXCollections.observableArrayList(etiqueta));
        }

        return this.ciudadEtiqueta.get(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1)).contains(etiqueta);
    }

    @Override
    public Boolean removeEtiqueta(String ciudad, String etiqueta) {
        Coordenadas coordenadas = connector.getCoor(ciudad);
        Localizacion localizacion = new Localizacion(ciudad, coordenadas);

        connector.deleteTag(localizacion, etiqueta);
        boolean res= ciudadEtiqueta.get(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1)).remove(etiqueta);
        if (ciudadEtiqueta.get(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1)).isEmpty()){
            ciudadEtiqueta.remove(ciudad.substring(0,1).toUpperCase()+ciudad.substring(1));
        }
        return res;
    }

    // ____________________________
    // METODOS FAVORITOS
    // _____________________________

    public Boolean addFavoritos(String ciudad) {
        Coordenadas coordenadas = connector.getCoor(ciudad.toLowerCase());
        Localizacion localizacion = new Localizacion(ciudad.toLowerCase(), coordenadas);

        connector.saveFavourite(localizacion);
        if(!observableFav.contains(ciudad.substring(0,1).toUpperCase() + ciudad.substring(1))){
            return observableFav.add(ciudad.substring(0,1).toUpperCase() + ciudad.substring(1));
        }
        return false;
    }

    @Override
    public Boolean removeFavoritos(String ciudad) {
        Coordenadas coordenadas = connector.getCoor(ciudad.toLowerCase());
        Localizacion localizacion = new Localizacion(ciudad.toLowerCase(), coordenadas);

        connector.deleteFavourite(localizacion);
        return observableFav.remove(ciudad.substring(0,1).toUpperCase() + ciudad.substring(1));
    }


    public ObservableList<String> getFavoritos() {
        return observableFav;
    }

    @Override
    public Map<String, ObservableList<String>> getCiudadesEtiquetas() {
        for(String localizacion : ciudadEtiqueta.keySet()){
            System.out.println(localizacion);
        }
        return ciudadEtiqueta;
    }

    public Coordenadas getCoordenadas(String nombre){
        return connector.getCoor(nombre);
    }
}
