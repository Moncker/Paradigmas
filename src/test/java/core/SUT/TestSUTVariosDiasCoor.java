package core.SUT;

import core.SUT.TestSUTBed;
import core.model.Coordenadas;
import core.model.Estado;
import core.model.Localizacion;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestSUTVariosDiasCoor extends TestSUTBed {
    @Test
    public void demandaVariosDiasCoor() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45, new Date(01/01/2020));
        Tiempo tiempoTeo1 = new Tiempo(12.4, Estado.SOLEADO, 30, new Date(02/01/2020));
        Tiempo tiempoTeo2= new Tiempo(13.2, Estado.NUBLADO, 60, new Date(03/01/2020));
        Coordenadas coor= new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1);
        Localizacion loc= new Localizacion("Villarreal", coor);
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.coordenadaTiempo(coor ,new Date(01/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(02/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(03/01/2020));
        //falla debido a que aún no está  implementado el método addTiempo, que debe añadir los datos a las estructuras correspondientes.
        if (sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getGrados() > -50
                && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getGrados() > -50
                && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getGrados() > -50)
            correcto = true;


        if (sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getHumedad() >= 0
                && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getHumedad() >= 0
                && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertNotNull(tiempoTeo1.getEstado());
        assertNotNull(tiempoTeo2.getEstado());
        assertEquals(true, correcto);

    }
    @Test
    public void demandaVariosDiasCoorNotValid() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(99, null, 101, new Date(01/01/2020));
        Tiempo tiempoTeo1 = new Tiempo(75, null, 220, new Date(02/01/2020));
        Tiempo tiempoTeo2= new Tiempo(-80, null, -20, new Date(03/01/2020));
        Coordenadas coor= new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1);
        Localizacion loc= new Localizacion("Villarreal", coor);
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.coordenadaTiempo(coor ,new Date(01/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(02/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(03/01/2020));
        //falla debido a que aún no está  implementado el método addTiempo, que debe añadir los datos a las estructuras correspondientes.
        if (sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getGrados() > -50
                && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getGrados() > -50
                && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getGrados() < 50 && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getGrados() > -50)
            correcto = true;


        if (sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo.getFecha()).getHumedad() >= 0
                && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo1.getFecha()).getHumedad() >= 0
                && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getHumedad() <= 100 && sut.coordenadaTiempo(coor, tiempoTeo2.getFecha()).getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertNotNull(tiempoTeo1.getEstado());
        assertNotNull(tiempoTeo2.getEstado());
        assertEquals(true, correcto);
    }
}
