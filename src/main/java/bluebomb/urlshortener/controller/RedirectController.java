package bluebomb.urlshortener.controller;

import static org.mockito.Mockito.doReturn;
import java.io.*; 
import java.net.URL;
import java.util.stream.Collectors;
import java.net.MalformedURLException;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.errors.NotFoundError;
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
    public String ads(@PathVariable(value = "sequence") String sequence) throws DatabaseInternalException, IOException {
        //TODO emplementar la funcion de busqueda sequence->ads
        
        // Response
        String response = null;
        String page= null;
        // Check sequence
        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            throw new SequenceNotFoundError();
        } else if (!AvailableURI.getInstance().isSequenceAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Original URL is not available");
        } else if (!AvailableURI.getInstance().isSequenceAdsAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associated ads is not available");
        }

         // Get ADS URI if is in DB
         try {
            response = DatabaseApi.getInstance().getADSIfExist(sequence);
        } catch (DatabaseInternalException e) {
            // Database not working
        }

        if(response == null) throw new NotFoundError("This sequense have no ads");

        //Get the ads web page if it is in cache
     if(DatabaseApi.getInstance().checkIfADSExistInCache(response)){
         page=DatabaseApi.getInstance().getADSHTMLfromCache(response);
     }else{
         page=download(response);
         DatabaseApi.getInstance().saveADSHTMLInCache(response, page);
     }
     
     System.out.println(page);
     return page;
    }


/*
* @param webpage
* @return message
* recive a URI and return it's web page
*/
    public String download(String webpage) throws IOException {
        URL url;
        InputStream is = null;
        BufferedReader br=null;
        String line;
        String message="";
        try {
            url = new URL(webpage);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                message+=line;
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
        return message;
    }
}