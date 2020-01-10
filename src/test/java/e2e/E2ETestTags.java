package e2e;

import core.Exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestTags extends E2ETestBed {

    @Test
    public void searchTagValid() throws IOException, CityNotFoundException {
        simpleWeather.addEtiqueta("Madrid", "MDR");

        assertEquals("etiqueta correcta","Madrid", simpleWeather.etiquetaCiudad("MDR"));
    }

    @Test(expected = CityNotFoundException.class)
    public void searchTagInvalid() throws IOException, CityNotFoundException {
        assertTrue(simpleWeather.addEtiqueta("XXX", "MDR"));
    }

}

