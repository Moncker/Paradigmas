package core.Exceptions;

public class CityNotFoundException extends Throwable {
    public CityNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}