package core.persistence;

import core.Exceptions.CityNotFoundException;
import core.OWM.App_persistence;
import core.SimpleWeather;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class testBuscaTiempoPorNombre {
    SimpleWeather simpleWeather;
    IDataBase iDataBase;
    App_persistence app_persistence;


    @Before
    public void setUp(){
        simpleWeather = mock(SimpleWeather.class);
        iDataBase = mock(IDataBase.class);
    }

    @After
    public void tearDown(){
        simpleWeather = null;
        iDataBase = null;
    }

    @Test
    public void buscaTiempoPorNombreNoEnBBDD() throws IOException, CityNotFoundException {
        Localizacion loca = new Localizacion();
        Tiempo tiempo = new Tiempo();
        when(iDataBase.getWeather(loca)).thenReturn(new Tiempo());
        when(simpleWeather.buscaTiempoPorNombre("")).thenReturn(new Tiempo());

        verify(iDataBase, times(1)).saveWeather(tiempo, loca);
        verify(iDataBase, times(1)).getWeather(loca);
    }





}
