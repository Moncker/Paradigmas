package core.model;

import java.util.Calendar;
import java.util.Date;

public class Tiempo {

    private double grados;
    private Estado estado;
    private double humedad;
    private Date fecha;

    public Tiempo(double degrees, Estado estado, double humedad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = new Date();
    }

    public Tiempo(double degrees, Estado estado, double humedad, int dias) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+dias);
        this.fecha = cal.getTime();
    }

    public double getGrados() {
        return grados;
    }

    public Estado getEstado() { return estado; }

    public double getHumedad() { return humedad; }

    public Date getFecha() { return fecha; }
}
