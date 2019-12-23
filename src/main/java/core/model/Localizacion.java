package core.model;

public class Localizacion {

    private String ciudad;            // Nombre ciudad
    private Coordenadas coordenadas; // Coordenadas localizacion


    public Localizacion(String name){
        this.ciudad =name;
        this.coordenadas= new Coordenadas(0.0,0.0);
    }


    public Localizacion(String name, Coordenadas coor){
        this.ciudad =name;
        this.coordenadas=coor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }
}
