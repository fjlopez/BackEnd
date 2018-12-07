package bluebomb.urlshortener.model;

public class ClickStat {
    private String agent = null;
    private Integer clicks = null;

    public ClickStat(String agent, Integer clicks) {
        this.agent = agent;
        this.clicks = clicks;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
    
    @Override
    public String toString() {
        return "{" +
            " agent='" + getAgent() + "'" +
            ", clicks='" + getClicks() + "'" +
            "}";
    }
}

