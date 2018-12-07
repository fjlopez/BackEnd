package bluebomb.urlshortener;

import bluebomb.urlshortener.exceptions.DownloadHTMLInternalException;
import bluebomb.urlshortener.services.DownloadHTML;
import org.junit.Ignore;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void mySimpleTests() {
        System.out.println("dfd");
        String colorBien = "0xFFBBFFFF";
        System.out.println(colorBien.matches("0x[a-f0-9A-F]{8}"));
    }

    @Test
    public void downloadHTML() {
        String webURL = "http://www.google.es";
        try {
            String webHTML = DownloadHTML.getInstance().download(webURL);
            System.out.println(webHTML);
        } catch (DownloadHTMLInternalException e) {
            assert false;
        }
    }
}
