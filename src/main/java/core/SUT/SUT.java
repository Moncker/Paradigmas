package core.SUT;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;

import java.util.Date;
import java.util.HashMap;

public class SUT {

    //HashMap <String, HashMap<Date,Tiempo>> ciudadesTiempos;
    //HashMap <Coordenadas, HashMap<Date,Tiempo>> cordenadasTiempos;
    //HashMap <Localizacion, HashMap<Date,Tiempo>> historial;


    public SUT() {
        //this.ciudadesTiempos = ciudadesTiempos;
        //this.cordenadasTiempos = cordenadasTiempos;
        //this.historial=historial;
    }

    public Tiempo ciudadTiempo (String ciudad, Date date){
        throw new UnsupportedOperationException();
    }
    public Tiempo coordenadaTiempo (Coordenadas coor, Date date){
        throw new UnsupportedOperationException();
    }
    public void addTiempo(Localizacion loc, Date date, Tiempo tiem){ throw  new UnsupportedOperationException(); }//al añadir tiempo a la localización se añadirá también a ciudadesTiempo y coordenadasTiempo
    public Tiempo getTiempo(Localizacion loc, Date date){ throw new UnsupportedOperationException();}








}
