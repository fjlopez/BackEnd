package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.errors.NotFoundException;
import bluebomb.urlshortener.model.Browser;
import bluebomb.urlshortener.model.ShortRequest;
import bluebomb.urlshortener.model.ShortResponse;
import bluebomb.urlshortener.model.URL;
import bluebomb.urlshortener.model.Size;

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
    public ShortResponse getShortURI(@RequestParam(value = "headURL") String headURL, @RequestParam(value = "interstitialURL", required = false) String interstitialURL,@RequestParam(value = "secondsToRedirect", required = false) Integer secondsToRedirect) {
        
         ArrayList<ShortRequest> example = new ArrayList<ShortRequest>();
         interstitialURL=interstitialURL.replace(null,"");
        
         ShortRequest shortRequest = new ShortRequest(headURL,interstitialURL,secondsToRedirect);
         example.add(shortRequest);

        
         // TODO: Implement function
         ShortResponse shortResponse= new ShortResponse("a1b2c3", "...", "...", "...", "...");
         return shortResponse;       
       
       
    }
    
   
    @RequestMapping(value = "/{sequence}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQR(@PathVariable(value = "sequence") String sequence,
                        @RequestParam(value = "size", required = false) Size size,
                        @RequestParam(value = "errorCorrection", required = false) String errorCorrection,
                        @RequestParam(value = "margin", required = false) Integer margin,
                        @RequestParam(value = "qrColor", required = false) String qrColor,
                        @RequestParam(value = "backgroundColor", required = false) String backgroundColor,
                        @RequestParam(value = "logo", required = false) String logo) {
                           
        if (sequence.equals("error")) {
            throw new NotFoundException();
        }

        return QRCodeGenerator.generate("http://localhost/" + sequence);


    }
}
