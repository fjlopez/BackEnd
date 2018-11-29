package bluebomb.urlshortener.exceptions;

/**
 * Exception caused by problem in the QrGenerator
 */
public class QrGeneratorInternalException extends Exception {
    public QrGeneratorInternalException(String message) {
        super(message);
    }
}
