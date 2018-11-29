package bluebomb.urlshortener.services;

public class UserAgentDetection {
    private static UserAgentDetection ourInstance = new UserAgentDetection();

    public static UserAgentDetection getInstance() {
        return ourInstance;
    }

    private UserAgentDetection() {
    }

    /**
     * Detect user agent OS
     *
     * @param userAgent
     * @return
     */
    public String detectOS(String userAgent){
       // TODO:
        return "NONE";
    }

    /**
     * Detect user agent browser
     *
     * @param userAgent
     * @return
     */
    public String detectBrowser(String userAgent){
        // TODO:
        return "NONE";
    }
}
