package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class InfoController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendStatsToSubscribers(String sequence, URL originalURL, String username) {
        simpMessagingTemplate.convertAndSendToUser(username, "/ws/" + sequence + "/info", originalURL);
    }

    @SubscribeMapping("/{sequence}/info")
    public Object getShortenedURL(@DestinationVariable String sequence, String username) {

        // TODO:
        RedirectURL ru = new RedirectURL(12, "sfdfds");
        URL url = new URL("dfs");
        return ru;
    }
}
