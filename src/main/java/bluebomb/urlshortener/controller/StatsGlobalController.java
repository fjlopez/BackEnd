package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.config.CommonValues;
import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.model.ClickStat;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class StatsGlobalController {

    /**
     * Send stats to all globalStats subscribers for some sequence and parameter
     *
     * @param sequence shortened URL sequence code
     * @param parameter parameter from which statistics will be obtained
     * @param simpMessagingTemplate SimpMessagingTemplate instance
     * @param stats stats to send
     */
    public static void sendStatsToGlobalStatsSubscribers(String sequence, String parameter, ArrayList<ClickStat> stats,
                                                         SimpMessagingTemplate simpMessagingTemplate) {
        simpMessagingTemplate.convertAndSend("/ws/" + sequence + "/stats/" + parameter + "/global", stats);
    }

    /**
     * Subscribe to real time global stats
     *
     * @param sequence shortened URL sequence code
     * @param parameter parameter from which statistics will be obtained
     * @return actual global stats
     */
    @SubscribeMapping("/{sequence}/stats/{parameter}/global")
    public ArrayList<ClickStat> getGlobalStats(@DestinationVariable String sequence, @DestinationVariable String parameter) throws Exception {
        if (!CommonValues.AVAILABLE_STATS_PARAMETERS.contains(parameter)) {
            // Unavailable parameter
            throw new Exception("Error: Unavailable parameter: " + parameter);
        }

        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            // Unavailable sequence
            throw new Exception("Error: Unavailable sequence: " + sequence);
        }
        return DatabaseApi.getInstance().getSequenceGlobalStats(sequence, parameter);
    }

    /**
     * Catch getGetGlobalStats produced Exceptions
     *
     * @param e exception captured
     * @return error message
     */
    @MessageExceptionHandler
    public String errorHandlerGetGlobalStats(Exception e) {
        return e.getMessage();
    }
}
