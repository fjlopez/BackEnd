package bluebomb.urlshortener.services;

import bluebomb.urlshortener.exceptions.DownloadHTMLInternalException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class DownloadHTML {
    private static DownloadHTML ourInstance = new DownloadHTML();

    public static DownloadHTML getInstance() {
        return ourInstance;
    }

    private DownloadHTML() {
    }

    /**
     * Download the HTML that contains urlToDownload
     *
     * @param urlToDownload url
     * @return HTML that contains urlToDownload
     * @throws DownloadHTMLInternalException if something go wrong in download
     */
    public String download(String urlToDownload) throws DownloadHTMLInternalException {
        try {
            Document doc = Jsoup.connect(urlToDownload).get();
            return doc.html();
        } catch (IOException e) {
            throw new DownloadHTMLInternalException("Something go wrong");
        }
    }
}
