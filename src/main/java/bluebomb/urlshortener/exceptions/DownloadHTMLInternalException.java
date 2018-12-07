package bluebomb.urlshortener.exceptions;

/**
 * Exception caused by problem when downloading HTML
 */
public class DownloadHTMLInternalException extends Exception {
    public DownloadHTMLInternalException(String message) {
        super(message);
    }
}
