package bluebomb.urlshortener.model;

public class RedirectURL {
    private String headURL = null;
    private String interstitialURL = null;

    public RedirectURL(String headURL, String interstitialURL) {
        this.headURL = headURL;
        this.interstitialURL = interstitialURL;
    }

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public String getInterstitialURL() {
        return interstitialURL;
    }

    public void setInterstitialURL(String interstitialURL) {
        this.interstitialURL = interstitialURL;
    }
}

