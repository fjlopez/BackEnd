package bluebomb.urlshortener.services;

import bluebomb.urlshortener.exceptions.DownloadHTMLInternalException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
        String htmlDownloaded = "";
        InputStream is = null;
        try {
            URL url = new URL(urlToDownload);
            is = url.openStream();  // throws an IOException
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                htmlDownloaded += line;
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

        if (htmlDownloaded.isEmpty()) throw new DownloadHTMLInternalException("Something go wrong");
        else return htmlDownloaded;
    }
}
