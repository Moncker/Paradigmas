package core.SUT;

import core.model.Coordenadas;
import core.model.Tiempo;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSUTLocalTiempoCoor extends TestSUTBed {

    @Test
    public void demandaGradosCoordenadaValida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(15.2, "Nublado", 60);
        sut.coordenadaTiempo(new Coordenadas(0.1, 1.2), tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertTrue(correcto);
    }

    @Test
    public void demandaGradosCoordenadaInvalida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(70, null, 120);
        sut.coordenadaTiempo(new Coordenadas(0.1,1.2), tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertTrue(correcto);
    }
}
