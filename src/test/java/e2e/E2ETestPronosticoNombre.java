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

    @Test(expected = IOException.class)
    public void pronosticoNombreValid() throws IOException{
        Tiempo[] tiempos = simpleWeather.pronosticoNombre("Madrid");

        if (tiempos.length != 3)
            throw new IOException();
    }

    @Test(expected = CityNotFoundException.class)
    public void pronosticoNombreInvalid() throws IOException, CityNotFoundException {
        LocalDate hoyDate = LocalDate.now();
        LocalDate mananaDate = hoyDate.plusDays(1);
        LocalDate pasMananaDate = hoyDate.plusDays(2);
    }
}
