package e2e;

import core.Exceptions.CityNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestFavoritosDel extends E2ETestBed {
    @Test
    public void favoritosDelValid(){
        // aseguramos que la ciudad ya existe para poder eliminarla
        simpleWeather.addFavoritos("Madrid");

        assertTrue(simpleWeather.removeFavoritos("Madrid"));
    }

    @Test
    public void favoritosDelInvalid(){
        assertFalse(simpleWeather.removeFavoritos("Valencia"));
    }
}
