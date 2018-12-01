package bluebomb.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.errors.SequenceNotFoundError;
import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.services.AvailableURI;

/*
     * Generates ads for especific URL
     *
     * @param sequence Shortened URL sequence code
     * 
*/

@RestController
public class RedirectController {
    @RequestMapping(value = "{sequence}/ads", produces = MediaType.TEXT_HTML_VALUE)
    public String ads(@PathVariable(value = "sequence") String sequence) {
        //TODO emplementar la funcion de busqueda sequence->ads
        
        // Response
        String response = null;

        // Check sequence
        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            throw new SequenceNotFoundError();
        } else if (!AvailableURI.getInstance().isSequenceAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Original URL is not available");
        } else if (!AvailableURI.getInstance().isSequenceAdsAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associated ad is not available");
        }

         // Get ADS if is in cache
         try {
            response = DatabaseApi.getInstance().getADSIfExist(sequence);
        } catch (DatabaseInternalException e) {
            // Database not working
        }

        if (response != null) {
            // ADS have been cached
            return response;
        }








        return "<html><body> <P align=\"center\">hola! </P> </body> </html>";
    }
}