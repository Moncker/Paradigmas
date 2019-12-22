package core.model;

import java.time.LocalDate;
import java.util.Date;

public class Tiempo {

    private double grados;
    private String estado;
    private double humedad;
    private Date fecha;

    //CONSTRUCTORES
    public Tiempo() {

    }

    // Metodo para cargar tiempo desde BBDD
    public Tiempo(double degrees, String estado, double humedad, LocalDate fecha) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    public Tiempo(double grados,double humedad, Date date){
        this.grados=grados;
        this.humedad=humedad;
        this.fecha=date;
    }

    public Tiempo(double degrees, String estado, double humedad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = new Date();
    }

    public Tiempo(double degrees, String estado, double humedad, int dias) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        //?????????????????
    }

//__________________________________SETTERS Y GETTERS___________________________________________

    public double getGrados() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados = grados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean compareTo(Tiempo otro){
        return this.grados == otro.grados && this.humedad == otro.humedad && this.estado.equals(otro.estado);
    }

    @Override
    public String toString() {
        return grados + ", " + estado + ", " + humedad + ", " + fecha;
    }
}
