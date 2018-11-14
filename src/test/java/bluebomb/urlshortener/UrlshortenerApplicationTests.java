package bluebomb.urlshortener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bluebomb.urlshortener.controller.MainController;
import bluebomb.urlshortener.controller.RedirectController;
import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlshortenerApplicationTests {

    // MAIN CONTROLLER
    @Test
    public void testGetBrowsers() {
        Object response = new MainController().getSupportedBrowsers();
        assertNotNull(response);
        assertTrue(response instanceof ArrayList);
    }

    @Test
    public void testGetQr() {
       //TODO: implement Test
    }

    @Test
    public void testShortURL() {
        Object response = new MainController().getShortURI("http://www.google.es", "https://unizar.es");
        assertNotNull(response);
        assertTrue(response instanceof URL);
    }

    //REDIRECT CONTROLLER
    @Test
    public void testRedirect() {
        Object response = new RedirectController().redirect("BoMb9", "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36");
        assertNotNull(response);
        assertTrue(response instanceof RedirectURL);
    }

    //STATS CONTROLLER
    @Test
    public void testDailyStats() {
        //TODO: implement Test
    }
    
    @Test
    public void testGlobalStats() {
        //TODO: implement Test
    }

}
