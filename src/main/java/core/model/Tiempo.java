package core.model;

import java.util.Date;

public class Tiempo {

    private double grados;
    private Estado estado;
    private double humedad;
    private Date fecha;

    public Tiempo(double degrees, Estado estado, double humedad, Date fecha) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    public double getGrados() {
        return grados;
    }

    public Estado getEstado() { return estado; }

    public double getHumedad() { return humedad; }

    public Date getFecha() { return fecha; }
}
