package core.model;


import core.OWM.AbstractWeather;
import core.OWM.CurrentWeather;
import core.OWM.OpenWeatherMap;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Tiempo {


    private String ciudad;
    private double grados;
    private String estado;
    private double humedad;
    private LocalDate fecha;

    //CONSTRUCTORES
    public Tiempo() {

    }


    public Tiempo(double degrees, String estado, double humedad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = LocalDate.now();
    }

    public Tiempo(double degrees, String estado, double humedad, int dias) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        //?????????????????
    }

    public Tiempo(double grades, String state, double humidity, LocalDate parse) {
    }

    public Tiempo(double temp, double humedad, Date date, String ciudad) {
        this.grados = temp;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = LocalDate.now();
        this.ciudad = ciudad;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean compareTo(Tiempo otro){
        return this.grados == otro.grados && this.humedad == otro.humedad && this.estado.equals(otro.estado);
    }

    @Override
    public String toString() {
        return grados + ", " + estado + ", " + humedad + ", " + fecha;
    }

    public String getCiudad() {
        return this.ciudad;
    }
}
