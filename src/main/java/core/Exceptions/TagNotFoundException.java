package core.Exceptions;

public class TagNotFoundException extends Throwable {

    public TagNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}