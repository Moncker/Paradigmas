package core.SUT;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestSUTLocaTiempo extends TestSUTBed {

    @Test
    public void demandaGradosNombreValida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón", new Date());

        assertEquals( 12.2, tiempoTeo.getGrados(), 0.05);

    }
    
    @Test
    public void demandaGradosNombreInvalida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón", new Date());

        assertNotEquals(21, tiempoTeo.getGrados(), 0.05);
    }
}
