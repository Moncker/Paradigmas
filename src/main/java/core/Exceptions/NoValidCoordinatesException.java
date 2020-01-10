package core.Exceptions;

public class NoValidCoordinatesException extends Throwable {

    public NoValidCoordinatesException(String message) {
        super(message);
        System.out.println(message);
    }


}
