package e2e;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class E2ETestGetFavoritos extends E2ETestBed {
    @Test
    public void favoritosGetAtLeastOneValid() throws IOException {
        simpleWeather.addFavoritos("Madrid");
        simpleWeather.addFavoritos("Valencia");

        assertEquals(simpleWeather.getFavoritos().size(), 2);
    }

    @Test
    public void favoritosGetAtLeastOneInValid() throws IOException{
        assertEquals(simpleWeather.getFavoritos().size(), 0);
    }
}
