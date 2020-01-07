package e2e;

import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestTags extends E2ETestBed {

    @Test
    public void insertTagValid() throws IOException, CityNotFoundException {
        assertTrue(simpleWeather.addEtiqueta("Madrid", "MDR"));
    }

    //TODO    @Test(expected = CityNotFoundException.class)
    @Test(expected = CityNotFoundException.class)
    public void tiempoNombreInvalid() throws IOException, CityNotFoundException {
        assertTrue(simpleWeather.addEtiqueta("XXX", "MDR"));
    }

}

