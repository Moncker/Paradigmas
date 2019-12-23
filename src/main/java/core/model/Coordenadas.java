package core.model;


import java.util.Objects;

public class Coordenadas {

    Double X;
    Double Y;

    public Coordenadas(Double x, Double y) {
        X = x;
        Y = y;
    }

    public Double getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenadas that = (Coordenadas) o;
        return Objects.equals(X, that.X) &&
                Objects.equals(Y, that.Y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
