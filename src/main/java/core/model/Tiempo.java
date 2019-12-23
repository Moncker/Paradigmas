package core.model;


import core.OWM.AbstractWeather;
import core.OWM.CurrentWeather;
import core.OWM.OpenWeatherMap;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Tiempo {

    private int id;
    private double grados;
    private String estado;
    private double humedad;
    private Date fecha;



    private String ciudad;

    //CONSTRUCTORES
    public Tiempo() {

    }
    public Tiempo(double grados,double humedad, Date date, String ciudad){
        this.grados=grados;
        this.humedad=humedad;
        this.fecha=date;
        this.ciudad = ciudad;
    }

    public Tiempo(double degrees, String estado, double humedad, String ciudad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.ciudad = ciudad;
        this.fecha = new Date();

    }

    public Tiempo(double degrees, String estado, double humedad, int dias) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        //?????????????????
    }

//__________________________________SETTERS Y GETTERS___________________________________________
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCiudad() { return ciudad; }

    @Override
    public String toString() {
        return grados + ", " + estado + ", " + humedad + ", " + fecha;
    }
}
