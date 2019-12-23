package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.persistence.exceptions.AlreadyAddedException;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testGestionFavoritos extends persistenceTest{

    @Test
    public void agregarFavoritoValido() {
        Localizacion localizacion = new Localizacion("Berlin", new Coordenadas(14.25, 5.45));
        connector.saveFavourite(localizacion);
    }

    @Test(expected = AlreadyAddedException.class)
    public void agregarFavoritoInvalido() {
        Localizacion localizacion = new Localizacion("Castellon", new Coordenadas(0.25, 0.65));
        connector.saveFavourite(localizacion);
    }

    @Test
    public void eliminarFavoritoExistente() {
        Localizacion localizacion = new Localizacion("Berlin", new Coordenadas(14.25, 5.45));
        connector.deleteFavourite(localizacion);
    }

    @Test(expected = CityNotFoundException.class)
    public void eliminarFavoritoNoExistente() {
        Localizacion localizacion = new Localizacion("Moscú", new Coordenadas(45.56, 34.76));
        connector.deleteFavourite(localizacion);
    }

    @Test
    public void recogerFavoritos() {
        List<Localizacion> listaEsperada = new ArrayList<>();
        listaEsperada.add(new Localizacion("Castellon", new Coordenadas(0.25, 0.65)));
        listaEsperada.add(new Localizacion("Villareal", new Coordenadas(0.45, 0.85)));

        List<Localizacion> listaObtenida = connector.getFavourites();
        assertEquals(listaEsperada, listaObtenida);
    }
}
