package core.persistence.exceptions;

public class AlreadyAddedException extends Throwable {
    public AlreadyAddedException(String message) {
        super(message);
        System.out.println(message);
    }
}

