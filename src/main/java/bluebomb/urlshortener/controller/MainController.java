package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.config.CommonValues;
import bluebomb.urlshortener.database.DatabaseApi;
import bluebomb.urlshortener.errors.SequenceNotFoundError;
import bluebomb.urlshortener.errors.ServerInternalError;
import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.exceptions.QrGeneratorBadParametersException;
import bluebomb.urlshortener.exceptions.QrGeneratorInternalException;
import bluebomb.urlshortener.model.ShortRequest;
import bluebomb.urlshortener.model.ShortResponse;
import bluebomb.urlshortener.model.Size;

import bluebomb.urlshortener.qr.QRCodeGenerator;
import bluebomb.urlshortener.services.AvailableURI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {
    @RequestMapping(value = "/short", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortResponse getShortURI(@RequestParam(value = "headURL") String headURL, @RequestParam(value = "interstitialURL", required = false) String interstitialURL, @RequestParam(value = "secondsToRedirect", required = false) Integer secondsToRedirect) {

        ArrayList<ShortRequest> example = new ArrayList<ShortRequest>();
        interstitialURL = interstitialURL.replace(null, "");

        ShortRequest shortRequest = new ShortRequest(headURL, interstitialURL, secondsToRedirect);
        example.add(shortRequest);


        // TODO: Implement function
        ShortResponse shortResponse = new ShortResponse("a1b2c3", "...", "...", "...", "...");
        return shortResponse;
    }

    @RequestMapping(value = "/{sequence}/qr", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getQr(@PathVariable(value = "sequence") String sequence,
                        @RequestParam(value = "size", required = false) Size size,
                        @RequestParam(value = "errorCorrection", required = false, defaultValue = "L") String errorCorrection,
                        @RequestParam(value = "margin", required = false, defaultValue = "20") Integer margin,
                        @RequestParam(value = "qrColor", required = false, defaultValue = "#000000") String qrColor,
                        @RequestParam(value = "backgroundColor", required = false, defaultValue = "#ffffff") String backgroundColor,
                        @RequestParam(value = "logo", required = false) String logo,
                        @RequestHeader("Accept") String acceptHeader) {

        // Response
        byte[] response = null;

        // Check sequence
        if (!DatabaseApi.getInstance().checkIfSequenceExist(sequence)) {
            throw new SequenceNotFoundError();
        } else if (!AvailableURI.getInstance().isSequenceAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Original URL is not available");
        } else if (!AvailableURI.getInstance().isSequenceAdsAvailable(sequence)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associated ad is not available");
        }

        // Get QR if is in cache
        try {
            response = DatabaseApi.getInstance().getQrIfExist(sequence, size, errorCorrection, margin, qrColor, backgroundColor, logo, acceptHeader);
        } catch (DatabaseInternalException e) {
            // Database not working
        }

        if (response != null) {
            // QR have been cached
            return response;
        }

        // Check logo
        if (!logo.isEmpty() && AvailableURI.getInstance().isURLAvailable(logo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Logo resource is not available");
        }

        // Check Size
        if (size == null) {
            size = new Size(500, 500);
        }

        // Download logo
        // TODO: Download logo and save it in bufferedLogo
        BufferedImage bufferedLogo = null;

        // Response type
        QRCodeGenerator.ResponseType responseType;
        switch (acceptHeader) {
            case MediaType.IMAGE_PNG_VALUE:
                responseType = QRCodeGenerator.ResponseType.TYPE_PNG;
                break;
            case MediaType.IMAGE_JPEG_VALUE:
                responseType = QRCodeGenerator.ResponseType.TYPE_JPEG;
                break;
            default:
                // Spring not filtering Accept
                throw new ServerInternalError();
        }

        // Error correction
        QRCodeGenerator.ErrorCorrectionLevel errorCorrectionLevel;
        switch (errorCorrection) {
            case "L":
                errorCorrectionLevel = QRCodeGenerator.ErrorCorrectionLevel.L;
                break;
            case "M":
                errorCorrectionLevel = QRCodeGenerator.ErrorCorrectionLevel.M;
                break;
            case "Q":
                errorCorrectionLevel = QRCodeGenerator.ErrorCorrectionLevel.Q;
                break;
            case "H":
                errorCorrectionLevel = QRCodeGenerator.ErrorCorrectionLevel.H;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in error correction level");
        }

        try {
            response = QRCodeGenerator.generate(CommonValues.FRONT_END_URI + sequence, responseType, size,
                    errorCorrectionLevel, margin, qrColor, backgroundColor, bufferedLogo);
            DatabaseApi.getInstance().saveQrInCache(sequence, size, errorCorrection, margin,
                    qrColor, backgroundColor, logo, acceptHeader, response);
        } catch (QrGeneratorBadParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (QrGeneratorInternalException e) {

            // Something went wrong in QR generation
            throw new ServerInternalError();
        } catch (DatabaseInternalException e) {
            // Database cache not working
        }

        return response;
    }
}
