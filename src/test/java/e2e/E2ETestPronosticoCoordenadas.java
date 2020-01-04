package e2e;

import core.model.Coordenadas;
import core.model.Tiempo;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class E2ETestPronosticoCoordenadas extends E2ETestBed {


    @Test
    public void pronosticoCoordenadas() throws IOException{
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9, 40.24);
        assertEquals(tiempos.length, 3);
    }

    @Test(expected = CityNotFoundException.class)
    public void pronosticoNombreInvalid() throws IOException, CityNotFoundException {
        Tiempo[] tiempos = simpleWeather.pronosticoCoordenadas(42.9, 40.24);
    }
}
