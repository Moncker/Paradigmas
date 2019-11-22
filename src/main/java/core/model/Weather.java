package core.model;

public class Weather {

    private double degrees;
    private Estado estado;
    private double humedad;

    public Weather(double degrees, Estado estado, double humedad) {
        this.degrees = degrees;
        this.estado = estado;
        this.humedad = humedad;
    }

}
