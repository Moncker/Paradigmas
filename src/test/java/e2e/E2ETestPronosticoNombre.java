package e2e;

import core.Exceptions.CityNotFoundException;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class E2ETestPronosticoNombre extends E2ETestBed {

    @Test
    public void pronosticoNombreValid() throws IOException, CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("Madrid");

        //TODO: comprobamos lo que hay dentro
        assertEquals(tiempos.length, 3);
    }

    @Test
    public void pronosticoNombreInvalid() throws IOException, CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("XXX");

        assertNotEquals(tiempos.length, 3);
    }
}
