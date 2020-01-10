package e2e;

import core.Exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestFavoritosAdd extends E2ETestBed {

    @Test
    public void favoritosAdd() throws IOException {
        assertTrue(simpleWeather.addFavoritos("Madrid"));
    }

    @Test
    public void favoritosAddInvalid() throws IOException{
        // aseguramos que exista la ciudad en favoritos
        simpleWeather.addFavoritos("Valencia");

        assertFalse(simpleWeather.addFavoritos("Valencia"));
    }

}
