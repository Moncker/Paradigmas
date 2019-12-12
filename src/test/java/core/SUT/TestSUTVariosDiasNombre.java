package core.SUT;

import core.model.Coordenadas;
import core.model.Estado;
import core.model.Localizacion;
import core.model.Tiempo;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class TestSUTVariosDiasNombre extends TestSUTBed {

    @Test
    public void demandaVariosDiasNombre() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45, 0);
        Tiempo tiempoTeo1 = new Tiempo(12.4, Estado.SOLEADO, 30, 1);
        Tiempo tiempoTeo2= new Tiempo(13.2, Estado.NUBLADO, 60, 2);
        Localizacion loc= new Localizacion("Villarreal", new Coordenadas(0.1,1.2));
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.ciudadTiempo("Villareal",new Date(01/01/2020));
        //sut.ciudadTiempo("Villareal",new Date(02/01/2020));
        //sut.ciudadTiempo("Villareal",new Date(03/01/2020));

        if (sut.getTiempo(loc, tiempoTeo.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo.getFecha()).getGrados() > -50
                && sut.getTiempo(loc, tiempoTeo1.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo1.getFecha()).getGrados() > -50
                && sut.getTiempo(loc, tiempoTeo2.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo2.getFecha()).getGrados() > -50)
            correcto = true;


        if (sut.getTiempo(loc, tiempoTeo.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo.getFecha()).getHumedad() >= 0
                && sut.getTiempo(loc, tiempoTeo1.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo1.getFecha()).getHumedad() >= 0
                && sut.getTiempo(loc, tiempoTeo2.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo2.getFecha()).getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertNotNull(tiempoTeo1.getEstado());
        assertNotNull(tiempoTeo2.getEstado());
        assertTrue(correcto);
    }
    @Test
    public void demandaVariosDiasNombreNotValid() {
        boolean correcto = false;
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45, 0);
        Tiempo tiempoTeo1 = new Tiempo(12.4, Estado.SOLEADO, 30, 1);
        Tiempo tiempoTeo2= new Tiempo(13.2, Estado.NUBLADO, 60, 2);
        Localizacion loc= new Localizacion("Villarreal", new Coordenadas(0.1,1.2));
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.ciudadTiempo("Villareal",new Date(01/01/2020));
        //sut.ciudadTiempo("Villareal",new Date(02/01/2020));
        //sut.ciudadTiempo("Villareal",new Date(03/01/2020));
        if (sut.getTiempo(loc, tiempoTeo.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo.getFecha()).getGrados() > -50
                && sut.getTiempo(loc, tiempoTeo1.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo1.getFecha()).getGrados() > -50
                && sut.getTiempo(loc, tiempoTeo2.getFecha()).getGrados() < 50 && sut.getTiempo(loc, tiempoTeo2.getFecha()).getGrados() > -50)
            correcto = true;


        if (sut.getTiempo(loc, tiempoTeo.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo.getFecha()).getHumedad() >= 0
                && sut.getTiempo(loc, tiempoTeo1.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo1.getFecha()).getHumedad() >= 0
                && sut.getTiempo(loc, tiempoTeo2.getFecha()).getHumedad() <= 100 && sut.getTiempo(loc, tiempoTeo2.getFecha()).getHumedad() >= 0)
            correcto = true;

        assertNotNull(tiempoTeo.getEstado());
        assertNotNull(tiempoTeo1.getEstado());
        assertNotNull(tiempoTeo2.getEstado());
        assertTrue(correcto);
    }




}
