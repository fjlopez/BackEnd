package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.Stats;
import bluebomb.urlshortener.model.StatsAgent;
import bluebomb.urlshortener.services.UserAgentDetection;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class StatsController {
    @RequestMapping(value = "/{sequence}/stats/daily", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Stats> getStatsDaily(@PathVariable(value = "sequence") String urlSequenceCode,
                                          @RequestParam(value = "parameter") String parameter,
                                          @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                          @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                          @RequestParam(value = "sortType", required = false) String sortType,
                                          @RequestParam(value = "maxAmountOfDataToRetreive") Integer maxAmountOfDataToRetreive) {
        // TODO: Implement function
        ArrayList<Stats> example = new ArrayList<Stats>();
        ClickStat clickStat = new ClickStat("IE", 500);
        ArrayList<ClickStat> clickStatArrayList = new ArrayList<>();
        clickStatArrayList.add(clickStat);
        Stats stats = new Stats(new Date(), clickStatArrayList);
        example.add(stats);
        return example;
    }

    /**
     * Get supported agents
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
