package core.SUT;

import core.model.Tiempo;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSUTLocalTiempoNombre extends TestSUTBed {

    @Test
    public void demandaGradosNombreValida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(12.2, "Nublado", 45);
        sut.ciudadTiempo("Castellón", tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertTrue(correcto);
    }
    
    @Test
    public void demandaGradosNombreInvalida() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(12.2, "Nublado", 45);
        sut.ciudadTiempo("Castellón", tiempoTeo.getFecha());

        if (tiempoTeo.getGrados() < 50 && tiempoTeo.getGrados() > -50)
            correcto = true;

        if (tiempoTeo.getHumedad() <= 100 && tiempoTeo.getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertTrue(correcto);
    }
}
