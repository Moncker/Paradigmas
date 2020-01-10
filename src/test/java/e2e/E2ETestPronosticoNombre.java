package e2e;

import core.model.Tiempo;
import core.Exceptions.CityNotFoundException;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class E2ETestPronosticoNombre extends E2ETestBed {
    @Test
    public void pronosticoNombreValid() throws CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("Madrid");

        assertEquals(tiempos[0].getFecha(), LocalDate.now());
        assertEquals(tiempos[1].getFecha(), LocalDate.now().plusDays(1));
        assertEquals(tiempos[2].getFecha(), LocalDate.now().plusDays(2));

        assertEquals(tiempos.length, 3);
    }

    @Test(expected = CityNotFoundException.class)
    public void pronosticoNombreInvalid() throws CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("XXX");
    }
}
