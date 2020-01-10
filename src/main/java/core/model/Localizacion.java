package core.model;

import core.model.Coordenadas;

import java.util.Objects;

public class Localizacion {

    String name;            // Nombre ciudad
    Coordenadas coordenadas; // Coordenadas localizacion


    public Localizacion(String name, Coordenadas coor) {
        this.name = name;
        this.coordenadas = coor;

    }

    public Localizacion(String ciudad) {
    }

    public Localizacion() {

    }

    public String getName() {
        return name;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public boolean compareTo(Localizacion otro){
            return this.getName().equals(otro.getName()) && this.getCoordenadas().equals(otro.getCoordenadas());
    }
}