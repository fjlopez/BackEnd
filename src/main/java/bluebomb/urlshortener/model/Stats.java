package bluebomb.urlshortener.model;

import java.util.ArrayList;
import java.util.Date;


/**
 * Stats
 */

public class Stats {
    private Date day = null;
    private ArrayList<ClickStat> clickStat = null;

    public Stats(Date day, ArrayList<ClickStat> clickStat) {
        this.day = day;
        this.clickStat = clickStat;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public ArrayList<ClickStat> getClickStat() {
        return clickStat;
    }

    public void setClickStat(ArrayList<ClickStat> clickStat) {
        this.clickStat = clickStat;
    }

    @Override
    public String toString() {
        return "{" +
            " day='" + getDay() + "'" +
            ", clickStat='" + getClickStat() + "'" +
            "}\n";
    }
}

