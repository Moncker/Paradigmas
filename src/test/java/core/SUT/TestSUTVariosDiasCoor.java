package core.SUT;

import core.SUT.TestSUTBed;
import core.model.Coordenadas;
import core.model.Estado;
import core.model.Localizacion;
import core.model.Tiempo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestSUTVariosDiasCoor extends TestSUTBed {
    @Test
    public void demandaVariosDiasCoor() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        Tiempo tiempoTeo1 = new Tiempo(12.4, Estado.SOLEADO, 30);
        Tiempo tiempoTeo2= new Tiempo(13.2, Estado.NUBLADO, 60);
        Coordenadas coor= new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1);
        Localizacion loc= new Localizacion("Villarreal", coor);
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.coordenadaTiempo(coor ,new Date(01/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(02/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(03/01/2020));
        //falla debido a que aún no está  implementado el método addTiempo, que debe añadir los datos a las estructuras correspondientes.
        assertEquals( 12.2, sut.coordenadaTiempo(coor, new Date(01/01/2020)).getGrados(), 0.05);
        assertEquals( 12.4, sut.coordenadaTiempo(coor, new Date(02/01/2020)).getGrados(), 0.05);
        assertEquals( 13.2, sut.coordenadaTiempo(coor, new Date(03/01/2020)).getGrados(), 0.05);
    }
    @Test
    public void demandaVariosDiasCoorNotValid() {
        Tiempo tiempoTeo = new Tiempo(12.2, Estado.NUBLADO, 45);
        Tiempo tiempoTeo1 = new Tiempo(12.4, Estado.SOLEADO, 30);
        Tiempo tiempoTeo2= new Tiempo(13.2, Estado.NUBLADO, 60);
        Coordenadas coor= new Coordenadas(0.1,1.2,1.2,1.3,1.1, 1.1);
        Localizacion loc= new Localizacion("Villarreal", coor);
        //sut.addTiempo(loc,new Date(01/01/2020),tiempoTeo);
        //sut.addTiempo(loc,new Date(02/01/2020),tiempoTeo1);
        //sut.addTiempo(loc,new Date(03/01/2020),tiempoTeo2);
        //sut.coordenadaTiempo(coor ,new Date(01/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(02/01/2020));
        //sut.coordenadaTiempo(coor ,new Date(03/01/2020));
        //falla debido a que aún no está  implementado el método addTiempo, que debe añadir los datos a las estructuras correspondientes.
        assertNotEquals( 12.2, sut.coordenadaTiempo(coor, new Date(01/01/2020)).getGrados(), 0.05);
        assertNotEquals( 12.4, sut.coordenadaTiempo(coor, new Date(02/01/2020)).getGrados(), 0.05);
        assertNotEquals( 13.2, sut.coordenadaTiempo(coor, new Date(03/01/2020)).getGrados(), 0.05);
    }
}
