package core;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

public class tiempoVariosDiasTest {
    @Test
    public void tiempoVariosDiasFunctional(){
        Tiempo resultadoReal = new Tiempo(0, Estado.LLUVIOSO, 31, new Date());
        Tiempo resultadoEsperado = new Tiempo(32, Estado.SOLEADO, 32.5, new Date());
        throw new UnsupportedOperationException();
    }

    @Test
    public void tiempoVariosDiasDisfuntional() {
        throw new UnsupportedOperationException();
    }

}

