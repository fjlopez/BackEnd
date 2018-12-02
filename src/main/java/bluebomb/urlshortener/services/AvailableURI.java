package bluebomb.urlshortener.services;

import org.springframework.lang.NonNull;

/**
 * Check if an URL or an sequence is reachable
 */
public class AvailableURI {
    private static AvailableURI ourInstance = new AvailableURI();

    /**
     * Get an instance of the class (Singleton pattern)
     *
     * @return the instance of the class
     */
    public static AvailableURI getInstance() {
        return ourInstance;
    }

    private AvailableURI() {
    }

    /**
     * Return true if URL is an available URL (get response status = 200)
     *
     * @param URL URL to check
     * @return true if the response status with a GET over URL is 200
     */
    public boolean isURLAvailable(@NonNull String URL) {
        // TODO: In this function are two cases: The periodic process has already checked the URL and the opposite
        // First case : This function will not perform the GET petition, this one
        // will check the available URL tables created by the periodic process
        // Second case: This function will perform the GET petition and update the periodic process available URL tables.
        // In this case probably we will make a petition to the periodic process to check the new URL and wait until it
        // answer
        return true;
    }

    /**
     * Return true if the original URL identified by id sequence is available
     *
     * @param sequence sequence to check
     * @return true if the response status with a GET over the originURL associated with sequence is 200
     */
    public boolean isSequenceAvailable(@NonNull String sequence) {
        // TODO: This function will not perform the GET petition, this will be done by an external periodic process, this one
        // will check the available sequence tables created by this process
        return true;
    }

    /**
     * Return true if the Ads associated with the original URL identified by id sequence is available.
     * If there are no Ads associated, return true.
     *
     * @param sequence sequence to check
     * @return true if the response status with a GET over the ad associated with sequence is 200
     */
    public boolean isSequenceAdsAvailable(@NonNull String sequence) {
        // TODO: This function will not perform the GET petition, this will be done by an external periodic process, this one
        // will check the available sequence tables created by this process
        return true;
    }

    /**
     * Register url in the list.
     * @param url to be registered
     */
    public void registerURL(String url){

    }
}
