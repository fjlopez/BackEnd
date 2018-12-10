package bluebomb.urlshortener.services;

import bluebomb.urlshortener.model.StatsAgent;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * Detect OS and Browser from an user agent
 */
public class UserAgentDetection {
    /**
     * Only support static calls
     */
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
        return stream(OperatingSystem.values())
                .map(OperatingSystem::getName)
                .map(StatsAgent::new)
                .collect(toList());
    }

    /**
     * Get supported browsers
     *
     * @return supported browsers
     */
    public static List<StatsAgent> getSupportedBrowsers() {
        return stream(Browser.values())
                .map(op -> new StatsAgent(op.getName()))
                .collect(toList());
    }
}
