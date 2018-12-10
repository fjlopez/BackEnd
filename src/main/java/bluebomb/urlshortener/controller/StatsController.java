package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.errors.SequenceNotFoundError;
import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.model.Stats;
import bluebomb.urlshortener.model.StatsAgent;
import bluebomb.urlshortener.services.AvailableURI;
import bluebomb.urlshortener.services.UserAgentDetection;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;


@RestController
public class StatsController {

    /**
     * Gets the stats of the shortened URL
     *
     * @param sequence                  Shortened URL sequence code
     * @param parameter                 parameters from which statistics will be obtained
     * @param startDate                 First day to get stats
     * @param endDate                   Last day to get stats
     * @param sortType                  Sort type (based on total clicks)
     * @param maxAmountOfDataToRetrieve Amount of data to get
     * @return the stats of the shortened URL associated with sequence
     */
    @RequestMapping(value = "/{sequence}/stats/daily", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Stats> getStatsDaily(@PathVariable(value = "sequence") String sequence,
                                          @RequestParam(value = "parameter") String parameter,
                                          @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                          @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                          @RequestParam(value = "sortType", required = false) String sortType,



                                          @RequestParam(value = "maxAmountOfDataToRetrieve") Integer maxAmountOfDataToRetrieve) throws DatabaseInternalException {
        if (!DatabaseApi.getInstance().containsSequence(sequence)) {
            throw new SequenceNotFoundError();
        }
        if (!AvailableURI.getInstance().isSequenceAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Original URL is not available");
        }
        // Get STATS
        return DatabaseApi.getInstance().getDailyStats(sequence, parameter, startDate, endDate, sortType, maxAmountOfDataToRetrieve);
    }

    // @ExceptionHandler(DatabaseInternalException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // use https://www.javadevjournal.com/spring/exception-handling-for-rest-with-spring/

    /**
     * Get supported agents
     *
     * @param element element to get all supported options
     * @return supported agents
     */
    @RequestMapping(value = "/{element}/support", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<StatsAgent> getSupportedAgents(@PathVariable(value = "element") String element) {
        ArrayList<StatsAgent> statsAgents;
        switch (element.toLowerCase()) {
            case "os":
                statsAgents = new ArrayList<>(UserAgentDetection.getSupportedOS());
                break;
            case "browser":
                statsAgents = new ArrayList<>(UserAgentDetection.getSupportedBrowsers());
                break;
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The searched parameter is not available");

        }
        return statsAgents;
    }
}
