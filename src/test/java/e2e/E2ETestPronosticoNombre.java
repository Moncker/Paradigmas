package e2e;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class E2ETestPronosticoNombre extends E2ETestBed {

    @Test
    public void pronosticoNombreValid() throws IOException{
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("Madrid");

        assertEquals(tiempos.length, 3);
    }

    @Test
    public void pronosticoNombreInvalid() throws IOException, CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("XXX");

        assertNotEquals(tiempos.length, 3);
    }
}
