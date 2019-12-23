package core.model;

import core.model.Coordenadas;

public class Localizacion {

    String name;            // Nombre ciudad
    Coordenadas coordenadas; // Coordenadas localizacion


    public Localizacion(String name, Coordenadas coor){
        this.name=name;
        this.coordenadas=coor;

    }

    public Localizacion(String ciudad) {
    }

    public String getName() {
        return name;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }
}
