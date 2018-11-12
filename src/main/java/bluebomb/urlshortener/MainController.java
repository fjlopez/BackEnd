package bluebomb.urlshortener;

import bluebomb.urlshortener.model.Browser;
import bluebomb.urlshortener.model.QR;
import bluebomb.urlshortener.model.URL;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {
    @RequestMapping(value = "/short", method= POST)
    public URL getShortURI(@RequestParam(value = "headURL") String headURL, @RequestParam(value = "interstitialURL", required = false) String interstitialURL) {
        return new URL(headURL);
    }

    @RequestMapping(value = "/qr/{sequence}")
    public QR getQR(@PathVariable(value = "sequence") String sequence){
        return new QR(sequence);
    }

    @RequestMapping(value = "/browsers")
    public ArrayList<Browser> getSupportedBrowsers(){
        ArrayList<Browser> toReturn = new ArrayList<>();
        toReturn.add(new Browser("IE"));
        toReturn.add(new Browser("EDGE"));
        return toReturn;
    }
}
