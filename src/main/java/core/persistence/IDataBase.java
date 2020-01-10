package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;

import java.util.List;
import java.util.Map;

public interface IDataBase {

    void connect();
    void close();

    Tiempo getWeather(Localizacion localizacion);
    Tiempo[] getWeatherDays(Localizacion localizacion);
    Tiempo getWeatherCoor(float lat, float lon);
    Tiempo[] getWeatherDaysCoor(float lat, float lon);

    void saveWeatherCoor(Tiempo tiempo, float lat, float lon);
    void saveWeather(Tiempo tiempo, Localizacion localizacion);
    void updateWeatherDatabase();

    void saveFavourite(Localizacion localizacion);
    void deleteFavourite(Localizacion localizacion);
    List<Localizacion> getFavourites();

    void saveTag(Localizacion localizacion, String nombreTag);
    void deleteTag(Localizacion localizacion, String nombreTag);
    Map<Localizacion, List<String>> getTags();
    List<String> getTagsCity (Localizacion localizacion);

    Coordenadas getCoor(String nombre);
    String getNombre(Coordenadas coordenadas);






}
