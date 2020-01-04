package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class testGestionTiempos extends persistenceTest{

    @Test
    public void recogerTiempoValido() {
        Localizacion localizacion = new Localizacion("Villareal", new Coordenadas(0.45, 0.85));
        Tiempo tiempoRes = new Tiempo(11, "Nublado", 70);

        Tiempo tiempoMet = connectorStore.getWeather(localizacion);

        assertNotNull(tiempoMet);
        assertTrue(tiempoRes.compareTo(tiempoMet));
    }

    @Test(expected = CityNotFoundException.class)
    public void recogerTiempoInvalido() {
        Localizacion localizacion = new Localizacion("Lisboa", new Coordenadas(0.95, 6.85));
        connectorStore.getWeather(localizacion);
    }

    @Test
    public void almacenarTiempoCiudadGuardada() {
        Localizacion localizacion = new Localizacion("Castellon", new Coordenadas(0.25, 0.65));
        //String fecha = "2019-09-24";
        Tiempo tiempoRes = new Tiempo(38, "Soleado", 15);

        connectorStore.saveWeather(tiempoRes, localizacion);
        Tiempo tiempoMet = connectorStore.getWeather(localizacion);

        System.out.println(tiempoMet.getGrados() + ", "+tiempoMet.getEstado() + ", "+tiempoMet.getHumedad());
        assertTrue(tiempoMet.compareTo(tiempoRes));
    }

    @Test
    public void almacenarTiempoCiudadNoGuardada() {
        Localizacion localizacion = new Localizacion("Nueva Delhi", new Coordenadas(56.45, 10.85));
        //String fecha = "2019-02-23";
        Tiempo tiempoRes = new Tiempo(26, "Soleado", 35);

        connectorStore.saveWeather(tiempoRes, localizacion);
        Tiempo tiempoMet = connectorStore.getWeather(localizacion);
        assertTrue(tiempoMet.compareTo(tiempoRes));
    }

    @Test
    public void actualizarTiempos() {
        connectorStore.updateWeatherDatabase();
    }
}
