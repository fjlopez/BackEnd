package bluebomb.urlshortener.exceptions;

/**
 * Exception caused by problem in the Cache
 */
public class CacheInternalException extends Exception {
    public CacheInternalException(String message) {
        super(message);
    }
}
