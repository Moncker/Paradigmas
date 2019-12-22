package core;

import core.model.Tiempo;
import org.junit.Test;

public class tiempoVariosDiasTest {
    @Test
    public void tiempoVariosDiasFunctional(){
        Tiempo resultadoReal = new Tiempo(0, "Lluvioso", 31);
        Tiempo resultadoEsperado = new Tiempo(32, "Soleado", 32.5);
        throw new UnsupportedOperationException();
    }

    @Test
    public void tiempoVariosDiasDisfuntional() {
        throw new UnsupportedOperationException();
    }

}

