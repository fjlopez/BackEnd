package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.URL;
import bluebomb.urlshortener.services.AvailableURI;
import bluebomb.urlshortener.services.UserAgentDetection;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;

@Controller
public class InfoController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Send original url to subscriber
     *
     * @param sequence sequence that user request for
     * @param originalURL original url associated with sequence
     * @param username session if of the user that should receive the original url
     * @param simpMessagingTemplate a SimpMessagingTemplate instance to perform the call
     */
    public static void sendOriginalUrlToSubscriber(String sequence, URL originalURL, String username,
                                                   SimpMessagingTemplate simpMessagingTemplate) {
        simpMessagingTemplate.convertAndSendToUser(username, "/ws/" + sequence + "/info", originalURL);
    }

    /**
     * Redirection function to get original URL and statics
     *
     * @param sequence shortened URL sequence code
     * @param simpSessionId session id
     * @param userAgent user agent
     * @return original URL if no ad and add URL and the time to wait in the other case
     */
    @SubscribeMapping("/{sequence}/info")
    public Object getShortenedURL(@DestinationVariable String sequence, @Header("simpSessionId") String simpSessionId,
                                  @RequestHeader("User-Agent") String userAgent) throws Exception {
        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            // Unavailable sequence
            throw new Exception("Error: Unavailable sequence: " + sequence);
        }

        if (!AvailableURI.getInstance().isSequenceAdsAvailable(sequence) || !AvailableURI.getInstance()
                .isSequenceAvailable(sequence)) {
            // Sequence non reachable
            throw new Exception("Error: Sequence non reachable: " + sequence);
        }

        // Update statics
        String browser = UserAgentDetection.detectBrowser(userAgent);
        String os = UserAgentDetection.detectOS(userAgent);
        ImmutablePair<Integer, Integer> newStatics = DatabaseApi.getInstance().updateSequenceStatics(sequence, os, browser);

        // Notify new statics to all subscribers
        ArrayList<ClickStat> clickStatOS = new ArrayList<>();
        clickStatOS.add(new ClickStat(os, newStatics.getRight()));

        ArrayList<ClickStat> clickStatBrowser = new ArrayList<>();
        clickStatBrowser.add(new ClickStat(browser, newStatics.getLeft()));

        StatsGlobalController.sendStatsToGlobalStatsSubscribers(sequence, "os", clickStatOS, simpMessagingTemplate);
        StatsGlobalController.sendStatsToGlobalStatsSubscribers(sequence, "browser", clickStatBrowser, simpMessagingTemplate);

        // If adds send ad and start thread and if not return url
        RedirectURL ad = DatabaseApi.getInstance().checkIfGotAd(sequence);
        String originalURL = DatabaseApi.getInstance().getOriginalURL(sequence);
        if (ad == null) {
            return originalURL;
        } else {
            new Thread(() -> {
                // Start a thread that notify user when ad time has end
                try {
                    Thread.sleep(ad.getSecondsToRedirect());
                } catch (Exception e) {
                    // Error when thread try to sleep
                }
                InfoController.sendOriginalUrlToSubscriber(sequence, new URL(originalURL), simpSessionId, simpMessagingTemplate);
            }).start();
            return ad;
        }
    }
}
