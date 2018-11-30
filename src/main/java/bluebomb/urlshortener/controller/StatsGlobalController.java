package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.config.CommonValues;
import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.model.ClickStat;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.websocket.server.ServerEndpointConfig;
import java.util.ArrayList;

@Controller
public class StatsGlobalController {

    /**
     * Send stats to all real time global subscribers of sequence and parameter
     *
     * @param sequence
     * @param parameter
     * @param simpMessagingTemplate
     * @param stats
     */
    public static void sendStatsToGlobalStatsSubscribers(String sequence, String parameter, ArrayList<ClickStat> stats,
                                                  SimpMessagingTemplate simpMessagingTemplate) {
        simpMessagingTemplate.convertAndSend("/ws/" + sequence + "/stats/" + parameter + "/global", stats);
    }

    /**
     * Subscribe to real time global stats
     *
     * @param sequence
     * @param parameter
     * @return
     */
    @SubscribeMapping("/{sequence}/stats/{parameter}/global")
    public ArrayList<ClickStat> getGlobalStats(@DestinationVariable String sequence, @DestinationVariable String parameter) {
        if (!CommonValues.AVAILABLE_STATS_PARAMETERS.contains(parameter)) {
            // TODO:
            // Unavailable parameter
            return new ArrayList<>();
        }

        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            // TODO:
            // Unavailable sequence
            return new ArrayList<>();
        }
        return DatabaseApi.getInstance().getSequenceGlobalStats(sequence, parameter);
    }
}
