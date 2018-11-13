package bluebomb.urlshortener.model;

public class ClickStat {
    private String browser = null;
    private Integer clicks = null;

    public ClickStat(String browser, Integer clicks) {
        this.browser = browser;
        this.clicks = clicks;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}

