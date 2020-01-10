package core.OWM;

import core.SimpleWeather;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class App_persistence implements SimpleWeather {

    private OpenWeatherMap owm = new OpenWeatherMap("weather");

    HashMap<String, Tiempo> historial;
    HashMap<String, List<String>> ciudadEtiqueta;
    private ObservableList<String> observableFav;

    Connector connector;

    public App_persistence() {
        this.owm.setApiKey("8d92c4241ad64d0fe808a2f501954581");
        this.historial = new HashMap<String, Tiempo>();
        this.ciudadEtiqueta = new HashMap<>();
        this.observableFav = FXCollections.<String>observableArrayList("Cuenca",
                "Toledo", "San Sebastian", "Cordoba");

        connector = new Connector();
        connector.connect();
    }

    @Override
    public Tiempo[] pronosticoCoordenadas(float lat, float lon) {
        Tiempo[] ret = new Tiempo[3];
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
            Tiempo[] ret = new Tiempo[3];
            byte forecastDays = 3;

            HourlyForecast forecast = owm.dailyForecastByCityName(nombre, forecastDays);


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
                        .toLocalDate());

                System.out.println(ret[i / 8].getCiudad());
                System.out.println(ret[i / 8].getGrados());
                System.out.println(ret[i / 8].getHumedad());
                System.out.println(ret[i / 8].getEstado());
                System.out.println(ret[i / 8].getFecha().toString());
                System.out.println("________________________________________________________________");

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
            if (historial.containsKey(nombre)) {//MODIFICAR
                return historial.get(nombre);
            } else {
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

                Tiempo tiempo = new Tiempo(ciudad, temp, "" + s, humedad, localdate, LocalDate.now());

                connector.saveWeather(tiempo, new Localizacion(ciudad, new Coordenadas(
                        tiempoOWN.getCoordInstance().getLatitude(),
                        tiempoOWN.getCoordInstance().getLongitude()
                )));

                return tiempo;
            }


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tiempo buscaTiempoPorCoordenadas(float lat, float lon) throws IOException {
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

        List<String> etiquetas = ciudadEtiqueta.get(ciudad);

        // Si todavía no existe la entrada en ciudadEtiqueta, la creamos
        if (etiquetas == null) {
            etiquetas = new ArrayList<String>();
            etiquetas.add(etiqueta);
            ciudadEtiqueta.put(ciudad, etiquetas);
        } else {   // Si ya existe, anyadimos en ella
            if (!etiquetas.contains(etiqueta)) etiquetas.add(etiqueta);
            else return false;
        }
        return true;
    }

    @Override
    public Boolean remplaceEtiqueta(String ciudad, String etiqueta) {
        return null;
    }

    public Boolean addFavoritos(String ciudad) {
        return observableFav.add(ciudad);
    }

    @Override
    public Boolean removeFavoritos(String ciudad) {
        return null;
    }

    @Override
    public ArrayList<String> getFavoritos(String ciudad) {
        return null;
    }

    public ObservableList<String> getFavoritos() {
        return observableFav;
    }


}
