package e2e;

import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class E2ETestTiempoNombre extends E2ETestBed {

    @Test
    public void tiempoNombreValid() throws IOException, CityNotFoundException {
        assertEquals("Ciudad correcta", "Madrid", simpleWeather.buscaTiempoPorNombre("Madrid").getCiudad());
    }

//TODO    @Test(expected = CityNotFoundException.class)
    @Test(expected = CityNotFoundException.class)
    public void tiempoNombreInvalid() throws IOException, CityNotFoundException {
        assertEquals("Ciudad incorrecta",simpleWeather.buscaTiempoPorNombre("XXX").getCiudad());
    }

}
