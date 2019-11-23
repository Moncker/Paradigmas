package core.SUT;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestSUTLocaTiempo extends TestSUTBed {

    @Test
    public void demandaGradosValida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón");

        assertEquals( 12.2, tiempoTeo.getGrados(), 0.05);

    }

    public void demandaGradosInvalida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón");

        assertNotEquals(21, tiempoTeo.getGrados(), 0.05);
    }



}
