package e2e;

import core.Exceptions.CoordenadasInvalidasException;
import core.model.Coordenadas;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class E2ETestPronosticoCoordenadas extends E2ETestBed {


    @Test
    public void PronosticoCoordenadasValid() throws IOException, CoordenadasInvalidasException {
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9F, 40.24F);
        assertEquals(tiempos.length, 3);
    }

    @Test
    public void PronosticoCoordenadasInvalid() throws IOException, CoordenadasInvalidasException {
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9F, 40.24F);
        assertEquals(tiempos.length, 3);
    }
}
