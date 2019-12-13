package core.model;


import java.time.LocalDate;

public class Tiempo {

    private int id;
    private double grados;
    private String estado;
    private double humedad;
    private LocalDate fecha;

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
        this.fecha = LocalDate.now().plusDays(dias);
    }

    public double getGrados() {
        return grados;
    }

    public String getEstado() { return estado; }

    public double getHumedad() { return humedad; }

    public LocalDate getFecha() { return fecha; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return grados + ", " + estado + ", " + humedad + ", " + fecha;
    }
}
