package bluebomb.urlshortener.model;

public class ShortenedInfo {
    private String HeadURL;
    private String interstitialURL;
    private Integer secondsToRedirect;

    public ShortenedInfo(){

    }

    public ShortenedInfo(String HeadURL, String interstitialURL, Integer secondsToRedirect) {
        this.HeadURL = HeadURL;
        this.interstitialURL = interstitialURL;
        this.secondsToRedirect = secondsToRedirect;
    }

    public String getHeadURL() {
        return HeadURL;
    }


    public String getinterstitialURL() {
        return interstitialURL;
    }



    public Integer getsecondsToRedirect() {
        return secondsToRedirect;
    }


}
