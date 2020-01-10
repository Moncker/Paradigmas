package e2e;

import core.Exceptions.NoValidCoordinatesException;
import core.model.Tiempo;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestPronosticoCoordenadas extends E2ETestBed {


    @Test
    public void PronosticoCoordenadasValid() throws IOException, NoValidCoordinatesException {
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9F, 40.24F);
        assertEquals(tiempos.length, 3);
    }

    @Test
    public void PronosticoCoordenadasInvalid() throws IOException, NoValidCoordinatesException {
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9F, 40.24F);
        assertEquals(tiempos.length, 3);
    }
}
