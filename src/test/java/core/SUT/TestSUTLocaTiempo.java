package core.SUT;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestSUTLocaTiempo extends TestSUTBed {

    @Test
    public void demandaGradosValida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón", new Date());

        assertEquals( 12.2, tiempoTeo.getGrados(), 0.05);

    }
    
    @Test
    public void demandaGradosInvalida() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        sut.ciudadTiempo("Castellón", new Date());

        assertNotEquals(21, tiempoTeo.getGrados(), 0.05);
    }
    
    @Test
    public void demandaGradosCoordenadaValida() {
        Tiempo tiempoTeo = new Tiempo(15.2, Estado.NUBLADO, 60);
        sut.coordenadaTiempo(new Coordenadas(0.1, 1.2, 1.2, 1.3, 1.1, 1.1), new Date());

        assertEquals(15.2, tiempoTeo.getGrados(), 0.05);
    }

    @Test
    public void demandaGradosCoordenadaInvalida() {
        Tiempo tiempoTeo = new Tiempo(15.2, Estado.NUBLADO, 60);
        sut.coordenadaTiempo(new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1), new Date());

        assertNotEquals(21, tiempoTeo.getGrados(), 0.05);
    }
}
