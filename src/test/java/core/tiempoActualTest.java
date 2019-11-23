package core;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

public class tiempoActualTest {
    @Test
    public void tiempoActualFunctional(){
        Tiempo resultadoReal = new Tiempo(0, Estado.LLUVIOSO, 31);
        Tiempo resultadoEsperado = new Tiempo(32, Estado.SOLEADO, 32.5);
        throw new UnsupportedOperationException();
    }

    @Test
    public void tiempoActualDisfuntional() {
        throw new UnsupportedOperationException();
    }

}
