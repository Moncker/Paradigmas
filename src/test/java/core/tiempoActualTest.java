package core;

import core.model.Estado;
import core.model.Weather;
import org.junit.Test;

public class tiempoActualTest {
    @Test
    public void tiempoActualFunctional(){
        Weather resultadoReal = new Weather(0, Estado.LLUVIOSO, 31);
        Weather resultadoEsperado = new Weather(32, Estado.SOLEADO, 32.5);
        throw new UnsupportedOperationException();
    }

    @Test
    public void tiempoActualDisfuntional() {
        throw new UnsupportedOperationException();
    }

}
