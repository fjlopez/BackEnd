package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.model.ClickStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class StatsGlobalController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Send stats to all subscribers of sequence and parameter
     * @param sequence
     * @param parameter
     * @param stats
     */
    public void sendStatsToSubscribers(String sequence, String parameter, ArrayList<ClickStat> stats){
        simpMessagingTemplate.convertAndSend("/ws/" + sequence + "/stats/" + parameter + "/global", stats);
    }

    @SubscribeMapping("/{sequence}/stats/{parameter}/global")
    public ArrayList<ClickStat> getGlobalStats(@DestinationVariable String sequence, @DestinationVariable String parameter) {
        // TODO:
        ArrayList<ClickStat> cs = new ArrayList<ClickStat>();
        return cs;
    }
}
