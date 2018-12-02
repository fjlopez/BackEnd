package bluebomb.urlshortener.services;

import bluebomb.urlshortener.model.StatsAgent;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Detect OS and Browser from an user agent
 */
public class UserAgentDetection {
    private static UserAgentDetection ourInstance = new UserAgentDetection();

    /**
     * Get an instance of the class (Singleton pattern)
     *
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
    public static String detectOS(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return userAgent.getOperatingSystem().getName();
    }

    /**
     * Detect user agent browser
     *
     * @param userAgentString user agent obtained from the client
     * @return OS relative to this user agent
     */
    public static String detectBrowser(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return userAgent.getBrowser().getName();
    }

    /**
     * Get supported operating systems
     *
     * @return supported operating systems
     */
    public static List<StatsAgent> getSupportedOS() {
        return Arrays.stream(OperatingSystem.values()).map(op -> new StatsAgent(op.getName())).collect(Collectors.toList());
    }

    /**
     * Get supported browsers
     *
     * @return supported browsers
     */
    public static List<StatsAgent> getSupportedBrowsers() {
        return Arrays.stream(Browser.values()).map(op -> new StatsAgent(op.getName())).collect(Collectors.toList());
    }
}
