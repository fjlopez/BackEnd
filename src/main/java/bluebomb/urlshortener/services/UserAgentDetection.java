package bluebomb.urlshortener.services;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * Detect OS and Browser from an user agent
 */
public class UserAgentDetection {
    private static UserAgentDetection ourInstance = new UserAgentDetection();

    /**
     * Get an instance of the class (Singleton pattern)
     * @return the instance of the class
     */
    public static UserAgentDetection getInstance() {
        return ourInstance;
    }

    private UserAgentDetection() {
    }

    /**
     * Detect user agent OS
     *
     * @param userAgentString user agent obtained from the client
     * @return OS relative to this user agent
     */
    public String detectOS(String userAgentString){
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return userAgent.getOperatingSystem().getName();
    }

    /**
     * Detect user agent browser
     *
     * @param userAgentString user agent obtained from the client
     * @return OS relative to this user agent
     */
    public String detectBrowser(String userAgentString){
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return userAgent.getBrowser().getName();
    }
}
