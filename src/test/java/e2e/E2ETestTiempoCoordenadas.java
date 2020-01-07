package e2e;

import core.Exceptions.CoordenadasInvalidasException;
import core.model.Coordenadas;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestTiempoCoordenadas extends E2ETestBed {

    @Test
    public void tiempoCoordenadasValid() throws IOException, CityNotFoundException, CoordenadasInvalidasException {
        assertEquals("Coordenadas correctas", "Madrid",
                simpleWeather.buscaTiempoPorCoordenadas(42.9f, 40.24f).getCiudad());
    }

    //TODO    @Test(expected = CoordenadasInvalidasException.class)
    @Test(expected = CoordenadasInvalidasException.class)
    public void tiempoCoordenadasInalid() throws IOException, CoordenadasInvalidasException {
       simpleWeather.buscaTiempoPorCoordenadas(200f, 200f);
    }
}
