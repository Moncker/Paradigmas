package e2e;

import core.Exceptions.NoValidCoordinatesException;
import core.Exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestTiempoCoordenadas extends E2ETestBed {

    @Test
    public void tiempoCoordenadasValid() throws IOException, CityNotFoundException, NoValidCoordinatesException {
        assertEquals("Coordenadas correctas", "Madrid",
                simpleWeather.buscaTiempoPorCoordenadas(42.9f, 40.24f).getCiudad());
    }

    @Test(expected = NoValidCoordinatesException.class)
    public void tiempoCoordenadasInalid() throws IOException, NoValidCoordinatesException {
       simpleWeather.buscaTiempoPorCoordenadas(200f, 200f);
    }
}
