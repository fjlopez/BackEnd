package bluebomb.urlshortener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bluebomb.urlshortener.controller.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlshortenerApplicationTests {

    /*@Test
    public void contextLoads() {
    }*/

    @Test
    public void testGetBrowsers() {
        Object response = new MainController().getSupportedBrowsers();
        assertNotNull(response);
        assertTrue(response instanceof ArrayList);
    }

    @Test
    public void testGetQr() {

    }

}
