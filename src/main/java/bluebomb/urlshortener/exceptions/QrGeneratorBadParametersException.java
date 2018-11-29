package bluebomb.urlshortener.exceptions;

/**
 * Exception caused by bad user input in the QrGenerator
 */
public class QrGeneratorBadParametersException extends Exception {
    public QrGeneratorBadParametersException(String message) {
        super(message);
    }
}
