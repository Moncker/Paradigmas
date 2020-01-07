package e2e;

import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class E2ETestFavoritosAdd extends E2ETestBed {

    @Test
    public void favoritosAdd() throws IOException, CityNotFoundException {
        assertEquals("Ciudad correcta", "Madrid", simpleWeather.addFavoritos("Madrid"));
    }

    //TODO    @Test(expected = CityNotFoundException.class)
    @Test(expected = CityNotFoundException.class)
    public void favoritosAddInvalid() throws IOException, CityNotFoundException {
        assertNotEquals("Ciudad incorrecta", "XXX", simpleWeather.addFavoritos("XXX"));
    }

}
