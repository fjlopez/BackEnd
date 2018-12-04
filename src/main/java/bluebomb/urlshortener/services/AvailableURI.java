package bluebomb.urlshortener.services;

import org.springframework.lang.NonNull;

public class AvailableURI {
    private static AvailableURI ourInstance = new AvailableURI();

    public static AvailableURI getInstance() {
        return ourInstance;
    }

    AvailableURI() {
    }

    /**
     * Return true if URL is an available URL (get response status = 200)
     *
     * @param URL
     * @return
     */
    public boolean isURLAvailable(@NonNull String URL) {
        // TODO:
        return true;
    }

    /**
     * Return true if the original URL identified by id sequence is available
     *
     * @param sequence
     * @return
     */
    public boolean isSequenceAvailable(@NonNull String sequence) {
        // TODO:
        return true;
    }

    /**
     * Return true if the Ads associated with the original URL identified by id sequence is available.
     * If there are no Ads associated, return true.
     *
     * @param sequence
     * @return
     */
    public boolean isSequenceAdsAvailable(@NonNull String sequence) {
        // TODO:
        return true;
    }
}
