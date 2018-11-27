package bluebomb.urlshortener.exceptions;

/**
 * Exception caused by problem in the QrGenerator
 */
public class DatabaseInternalException extends Exception {
    public DatabaseInternalException(String message) {
        super(message);
    }
}
