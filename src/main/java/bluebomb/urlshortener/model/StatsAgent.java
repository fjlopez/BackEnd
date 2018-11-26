package bluebomb.urlshortener.model;

public class StatsAgent {
    private String agent = null;

    public StatsAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
