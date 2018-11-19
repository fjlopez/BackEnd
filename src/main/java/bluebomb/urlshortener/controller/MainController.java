package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.errors.NotFoundException;
import bluebomb.urlshortener.model.Browser;
import bluebomb.urlshortener.model.URL;
import bluebomb.urlshortener.qr.QRCodeGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {
    @RequestMapping(value = "/short", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public URL getShortURI(@RequestParam(value = "headURL") String headURL, @RequestParam(value = "interstitialURL", required = false) String interstitialURL) {
        return new URL("http://localhost/BoMb9");
    }

    @RequestMapping(value = "/{sequence}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQR(@PathVariable(value = "sequence") String sequence) {
        if (sequence.equals("error")) {
            throw new NotFoundException();
        }
        return QRCodeGenerator.generate("http://localhost/" + sequence);
    }

    @RequestMapping(value = "/{element}/support", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Browser> getSupportedBrowsers() {
        ArrayList<Browser> toReturn = new ArrayList<>();
        toReturn.add(new Browser("IE"));
        toReturn.add(new Browser("EDGE"));
        return toReturn;
    }
}
