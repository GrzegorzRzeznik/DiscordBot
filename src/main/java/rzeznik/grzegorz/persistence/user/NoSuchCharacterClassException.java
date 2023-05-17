package rzeznik.grzegorz.persistence.user;

public class NoSuchCharacterClassException extends RuntimeException{
    public NoSuchCharacterClassException(String message) {
        super(message);
    }
}
