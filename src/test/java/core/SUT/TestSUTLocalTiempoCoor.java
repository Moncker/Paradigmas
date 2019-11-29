package core.SUT;

import core.model.Coordenadas;
import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestSUTLocalTiempoCoor extends TestSUTBed {

    @Test
    public void demandaGradosCoordenadaValida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(15.2, Estado.NUBLADO, 60, new Date());
        sut.coordenadaTiempo(new Coordenadas(0.1, 1.2, 1.2, 1.3, 1.1, 1.1), tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertEquals(true, correcto);
    }

    @Test
    public void demandaGradosCoordenadaInvalida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(70, null, 120, new Date());
        sut.coordenadaTiempo(new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1), tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertNotEquals(21, tiempoTeo.getGrados(), 0.05);
    }
}
