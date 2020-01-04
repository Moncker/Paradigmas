package core.Exceptions;

public class CoordenadasInvalidasException extends Throwable {

    public CoordenadasInvalidasException(String message) {
        super(message);
        System.out.println(message);
    }


}
