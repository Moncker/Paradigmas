package e2e;

import core.Exceptions.CityNotFoundException;
import core.Exceptions.NoValidCoordinatesException;
import core.model.Tiempo;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class E2ETestPronosticoCoordenadas extends E2ETestBed {
        @Test
        public void pronosticoCoordenadasValid() throws NoValidCoordinatesException {
            Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(0, 40);

            assertEquals(tiempos[0].getFecha(), LocalDate.now());
            assertEquals(tiempos[1].getFecha(), LocalDate.now().plusDays(1));
            assertEquals(tiempos[2].getFecha(), LocalDate.now().plusDays(2));

            assertEquals(tiempos.length, 3);
        }

        @Test(expected = NoValidCoordinatesException.class)
        public void pronosticoCoordenadasInvalid() throws NoValidCoordinatesException {
            simpleWeather.pronosticoCoordenadas(-3000, 0);
        }
}
