package bluebomb.urlshortener;

import bluebomb.urlshortener.services.UserAgentDetection;
import org.junit.Test;

import java.util.List;

public class UserAgentDetectionTest {
    @Test
    public void getAllOS(){
        List<String> os = UserAgentDetection.getSupportedOS();
        assert (os.size() > 1);
    }

    @Test
    public void getAllBrowsers(){
        List<String> browsers = UserAgentDetection.getSupportedBrowsers();
        assert (browsers.size() > 1);
    }
}
