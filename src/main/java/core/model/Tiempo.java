package core.model;

public class Tiempo {

    private double grados;
    private Estado estado;
    private double humedad;

    public Tiempo(double degrees, Estado estado, double humedad) {
        this.grados = degrees;
        this.estado = estado;
        this.humedad = humedad;
    }

    public double getGrados() {
        return grados;
    }
}
