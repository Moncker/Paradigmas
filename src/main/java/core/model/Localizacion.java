package core.model;

import core.model.Coordenadas;

import java.util.Objects;

public class Localizacion {

    String name;            // Nombre ciudad
    Coordenadas coordenadas; // Coordenadas localizacion


    public Localizacion(String name, Coordenadas coor){
        this.name=name;
        this.coordenadas=coor;

    }

    public String getName() {
        return name;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacion that = (Localizacion) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(coordenadas, that.coordenadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordenadas);
    }
}
