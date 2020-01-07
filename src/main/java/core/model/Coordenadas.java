package core.model;


import java.util.Objects;

public class Coordenadas {

    float X;
    float Y;

    public Coordenadas(float x, float y) {
        X = x;
        Y = y;
    }

    public float getX() {
        return X;
    }

    public float getY() {
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
