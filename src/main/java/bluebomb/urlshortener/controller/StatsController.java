package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.Stats;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class StatsController {
    @RequestMapping(value = "/stats/daily/{sequence}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Stats> getStatsDaily(@PathVariable(value = "sequence") String urlSequenceCode){
        ArrayList<Stats> example = new ArrayList<Stats>();
        ClickStat clickStat = new ClickStat("IE", 500);
        ArrayList<ClickStat> clickStatArrayList = new ArrayList<>();
        clickStatArrayList.add(clickStat);
        Stats stats = new Stats(new Date(), clickStatArrayList);
        example.add(stats);
        return example;
    }

    @RequestMapping(value = "/stats/global/{sequence}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ClickStat> getGlobalStats(@PathVariable(value = "sequence") String sequence){
        ClickStat clickStat = new ClickStat("IE", 500);
        ArrayList<ClickStat> clickStatArrayList = new ArrayList<>();
        clickStatArrayList.add(clickStat);
        return clickStatArrayList;
    }
}
