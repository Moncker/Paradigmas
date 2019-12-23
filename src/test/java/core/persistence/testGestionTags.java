package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.persistence.exceptions.AlreadyAddedException;
import core.persistence.exceptions.CityNotFoundException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class testGestionTags extends persistenceTest {

    @Test
    public void agregarTagCoordenadaExistente(){
        Localizacion localizacion = new Localizacion("Berlin", new Coordenadas(14.25, 5.45));
        String nombre_tag = "Berlino";
        connector.saveTag(localizacion, nombre_tag);
    }

    @Test(expected = AlreadyAddedException.class)
    public void agregarTagCoordenadaInexistente() {
        Localizacion localizacion = new Localizacion("Berlin", new Coordenadas(14.25, 5.45));
        String nombre_tag = "Berlino";
        connector.saveTag(localizacion, nombre_tag);
    }

    @Test
    public void eliminarTagExistente() {
        Localizacion localizacion = new Localizacion("Berlin", new Coordenadas(14.25, 5.45));
        connector.deleteTag(localizacion);
    }

    @Test(expected = CityNotFoundException.class)
    public void eliminarTagInexistente() {
        Localizacion localizacion = new Localizacion("Nueva Delhi", new Coordenadas(56.45, 89.25));
        connector.deleteTag(localizacion);
    }

    @Test
    public void recogerTags() {
        Map<Localizacion, String> mapEsperado = new HashMap<>();
        mapEsperado.put(new Localizacion("Castellon", new Coordenadas(0.25, 0.65)), "cs");
        mapEsperado.put(new Localizacion("Madrid", new Coordenadas(2.97, 1.32)), "madriz");

        Map<Localizacion, String> mapObtenido = connector.getTags();
        assertEquals(mapEsperado, mapObtenido);
    }

}