package bluebomb.urlshortener.qr;

import bluebomb.urlshortener.exceptions.QrGeneratorBadParametersException;
import bluebomb.urlshortener.exceptions.QrGeneratorInternalException;
import bluebomb.urlshortener.model.Size;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.lang.NonNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {

    /**
     * Available response types
     */
    public enum ResponseType {
        TYPE_PNG("PNG"),
        TYPE_JPEG("JPEG");

        private final String type;

        ResponseType(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * Generate QR code
     *
     * @param url                  Url to be inserted as content
     * @param format               Generated image format
     * @param size                 Generated image size
     * @param errorCorrectionLevel Generated image correction level
     * @param margin               Generated image correction margins
     * @param qrColor              Generated image QR color
     * @param backgroundColor      Generated image background color
     * @param logo                 Generated image optional embedded logo
     * @return Generated QR code
     * @throws QrGeneratorBadParametersException Caused by parameters error
     * @throws QrGeneratorInternalException      Caused by internal function error
     */
    public static byte[] generate(@NonNull String url, @NonNull ResponseType format, @NonNull Size size, @NonNull ErrorCorrectionLevel errorCorrectionLevel,
                                  @NonNull Integer margin, @NonNull int qrColor, @NonNull int backgroundColor, BufferedImage logo)
            throws QrGeneratorBadParametersException, QrGeneratorInternalException {

        // Check size
        if (size.getHeight() <= 0 || size.getWidth() <= 0) {
            throw new QrGeneratorBadParametersException("Height and width of the QR must be greater than 0");
        }

        // Check margin
        if (margin < 0) {
            throw new QrGeneratorBadParametersException("Margin must be a natural number");
        }

        // Add options
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        hints.put(EncodeHintType.MARGIN, margin);

        // Generate QR Matrix
        BufferedImage qrImage;
        qrImage = getBufferedImage(url, size, qrColor, backgroundColor, hints);

        // Combine QR Matrix and logo
        if (logo != null) {
            qrImage = addLogo(size, margin, logo, qrImage);
        }

        // Write to byte stream
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(qrImage, format.getType(), pngOutputStream);
        } catch (IOException e) {
            throw new QrGeneratorInternalException("Qr write to image fails");
        }

        return pngOutputStream.toByteArray();
    }

    private static BufferedImage addLogo(@NonNull Size size, @NonNull Integer margin, BufferedImage logo, BufferedImage qrImage) {
        // Logo must be rescaled
        int logoFinalHeight = (size.getHeight() - margin) / 8;
        int logoFinalWidth = (size.getWidth() - margin) / 8;

        Image tmpLogo = logo.getScaledInstance(logoFinalWidth, logoFinalHeight, Image.SCALE_SMOOTH);
        BufferedImage rescaledLogo = new BufferedImage(logoFinalWidth, logoFinalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dRescaledLogo = rescaledLogo.createGraphics();
        g2dRescaledLogo.drawImage(tmpLogo, 0, 0, null);
        g2dRescaledLogo.dispose();

        // Combine images
        BufferedImage combinedImage = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2gCombinedImage = (Graphics2D) combinedImage.getGraphics();

        g2gCombinedImage.drawImage(qrImage, 0, 0, null);
        g2gCombinedImage.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Write logo centred
        g2gCombinedImage.drawImage(rescaledLogo, margin + (size.getWidth() - logoFinalWidth) / 2, margin + (size.getHeight() - logoFinalHeight) / 2, null);

        // qrImage now will point to the combined image
        qrImage = combinedImage;
        return qrImage;
    }

    private static BufferedImage getBufferedImage(@NonNull String url, @NonNull Size size, @NonNull int qrColor, @NonNull int backgroundColor, Map<EncodeHintType, Object> hints) throws QrGeneratorInternalException {
        BufferedImage qrImage;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix;
            bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size.getWidth(), size.getHeight(), hints);
            qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix,
                    new MatrixToImageConfig(qrColor, backgroundColor));

        } catch (WriterException e) {
            throw new QrGeneratorInternalException("Qr encoding fails");
        }
        return qrImage;
    }
}
