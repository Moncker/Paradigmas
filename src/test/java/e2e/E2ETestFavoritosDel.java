package e2e;

import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class E2ETestFavoritosDel extends E2ETestBed {

    @Test
    public void favoritosDelValid() throws IOException, CityNotFoundException {
        assertEquals("Ciudad correcta", "Madrid", simpleWeather.removeFavoritos("Madrid"));
    }

    //TODO    @Test(expected = CityNotFoundException.class)
    @Test(expected = CityNotFoundException.class)
    public void favoritosDelInvalid() throws IOException, CityNotFoundException {
        assertEquals("Ciudad incorrecta",simpleWeather.removeFavoritos("XXX"));
    }

}
