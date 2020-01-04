package core.e2e;

import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class E2ETiempoNombre extends E2ETestBed {

    @Test
    public void tiempoNombreValid() throws IOException, CityNotFoundException {
        simpleWeather.buscaTiempoPorNombre("Madrid");
    }

    @Test(expected = CityNotFoundException.class)
    public void tiempoNombreInvalid() throws IOException, CityNotFoundException {
        simpleWeather.buscaTiempoPorNombre("XXX");
    }

}
