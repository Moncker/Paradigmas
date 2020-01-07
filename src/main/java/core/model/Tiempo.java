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

    // Attb solo de base de datos
    private LocalDate fecha_consulta;

    //CONSTRUCTORES
    public Tiempo() {}

    // Constructor tiempo de hoy sin nombre
    public Tiempo(double degrees, String estado, double humedad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = LocalDate.now();
    }

    // Constructor tiempo de hoy con nombre
    public Tiempo(String nombre, double degrees, String estado, double humedad) {
        this.ciudad = nombre;
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = LocalDate.now();
    }

    //Constructor tiempo con fecha y sin nombre
    public Tiempo(float degrees, String estado, float humedad, LocalDate fecha) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    //Constructor tiempo con fecha y nombre
    public Tiempo(String nombre, float degrees, String estado, float humedad, LocalDate fecha) {
        this.ciudad = nombre;
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    // Constructor esp de BBDD para gestionar fecha consulta
    public Tiempo(float grades, String state, float humidity, LocalDate parse, LocalDate fecha_consulta) {
        this.grados = grades;
        this.estado = state;
        this.humedad = humidity;
        this.fecha = parse;
        this.fecha_consulta = fecha_consulta;
    }

    public Tiempo(String nombre, float grades, String s, float humidity, LocalDate localdate, LocalDate fechaConsulta) {
        this.ciudad = nombre;
        this.grados = grades;
        this.estado = s;
        this.humedad = humidity;
        this.fecha = localdate;
        this.fecha_consulta = fechaConsulta;
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
        return this.grados == otro.grados && this.humedad == otro.humedad &&
                this.estado.equals(otro.estado) && this.fecha.toString().equals(otro.fecha.toString())
                && this.fecha_consulta.toString().equals(otro.fecha_consulta.toString());
    }

    @Override
    public String toString() {
        return grados + ", " + estado + ", " + humedad + ", " + fecha;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public LocalDate getFecha_consulta() {
        return fecha_consulta;
    }

}
