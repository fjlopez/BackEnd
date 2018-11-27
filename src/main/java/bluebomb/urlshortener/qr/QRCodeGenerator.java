package bluebomb.urlshortener.qr;

import bluebomb.urlshortener.errors.ServerInternalError;
import bluebomb.urlshortener.exceptions.QrGeneratorBadParametersException;
import bluebomb.urlshortener.exceptions.QrGeneratorInternalException;
import bluebomb.urlshortener.model.Size;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.lang.NonNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class QRCodeGenerator {

    /**
     * Available response types
     */
    public enum ResponseType {
        TYPE_PNG,
        TYPE_JPEG
    }

    /**
     * Available error correction levels
     */
    public enum ErrorCorrectionLevel {
        L, // 7% correction
        M, // 15% correction
        Q, // 25% correction
        H // 30% correction
    }

    /**
     * Generate QR code
     *
     * @param url Shorten url to transform into QR code
     * @return QR code
     */
    public static byte[] generate(@NonNull String url, @NonNull ResponseType format, @NonNull Size size, @NonNull ErrorCorrectionLevel errorCorrectionLevel,
                                  @NonNull Integer margin, @NonNull String qrColor,@NonNull String backgroundColor, BufferedImage logo)
            throws QrGeneratorBadParametersException, QrGeneratorInternalException {
        // TODO:
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 500, 500);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ServerInternalError();
        }
    }
}
