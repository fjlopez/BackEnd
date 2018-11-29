package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.URL;
import bluebomb.urlshortener.services.AvailableURI;
import bluebomb.urlshortener.services.UserAgentDetection;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
     * @param sequence
     * @param originalURL
     * @param username
     * @param simpMessagingTemplate
     */
    public static void sendOriginalUrlToSubscriber(String sequence, URL originalURL, String username,
                                                   SimpMessagingTemplate simpMessagingTemplate) {
        simpMessagingTemplate.convertAndSendToUser(username, "/ws/" + sequence + "/info", originalURL);
    }

    /**
     * Redirection function to get original URL and statics
     *
     * @param sequence
     * @param username
     * @return
     */
    @SubscribeMapping("/{sequence}/info")
    public Object getShortenedURL(@DestinationVariable String sequence, String username, @RequestHeader("User-Agent") String userAgent) {
        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            // TODO:
            // Unavailable sequence
            return "";
        }

        if (!AvailableURI.getInstance().isSequenceAdsAvailable(sequence) || !AvailableURI.getInstance().isSequenceAvailable(sequence)) {
            // TODO:
            // Sequence non reachable
            return "";
        }

        // Update statics
        String browser = UserAgentDetection.getInstance().detectBrowser(userAgent);
        String os = UserAgentDetection.getInstance().detectOS(userAgent);
        Pair<Long, Long> newStatics = DatabaseApi.getInstance().updateSecuenceStatics(sequence, os, browser);

        // TODO: Notify new statics to all subscribers
        //StatsGlobalController.sendStatsToGlobalStatsSubscribers(sequence,"os", );

        // TODO: If adds send ad and start thread and if not return url
         RedirectURL ru = new RedirectURL(12, "sfdfds");
        URL url = new URL("dfs");
        return ru;
    }
}
