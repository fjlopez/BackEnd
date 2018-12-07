package bluebomb.urlshortener.database;

import bluebomb.urlshortener.config.DbManager;
import bluebomb.urlshortener.exceptions.CacheInternalException;
import bluebomb.urlshortener.model.Size;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CacheApi {
    private static CacheApi ourInstance = new CacheApi();

    public static CacheApi getInstance() {
        return ourInstance;
    }

    private CacheApi() {
    }

    /**
     * Save Ad HTML page in cache
     *
     * @param adURL  Ad URL
     * @param adHTML Ad HTML
     * @throws CacheInternalException if cache fails
     */
    public void addAdHTML(@NotNull String adURL, @NotNull String adHTML) throws CacheInternalException {
        // TODO:
    }

    /**
     * Get Ad HTML exist in cache or null
     *
     * @param adURL Ad URL
     * @return Ad HTML exist in cache or null
     * @throws CacheInternalException if cache fails
     */
    public String getAdHTML(@NotNull String adURL) throws CacheInternalException {
        // TODO:
        String adsCode = "<html><body>hola!</body></html>";
        return adsCode;
    }

    /**
     * Save QR code in cache
     *
     * @param sequence        sequence associated
     * @param size            size associated
     * @param errorCorrection error correction level associated
     * @param margin          margin associated
     * @param qrColor         color of the QR associated
     * @param backgroundColor background color associated
     * @param logo            logo associated
     * @param responseFormat  response format associated
     * @param qrByteArray     generated QR
     * @throws CacheInternalException if cache fails
     */
    public void addQR(@NotNull String sequence, @NotNull Size size, @NotNull String errorCorrection, @NotNull Integer margin,
                      @NotNull Integer qrColor, @NotNull Integer backgroundColor, String logo, @NotNull String responseFormat, @NotNull byte[] qrByteArray) throws CacheInternalException {
        Connection connection = null;
        try {
            connection = DbManager.getConnection();
            String query = "SELECT * FROM insert_qr(?,?,?,?,?,?,?,?,?,?) AS result";
            PreparedStatement ps =
                    connection.prepareStatement(query,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, sequence);
            ps.setInt(2, size.getHeight());
            ps.setInt(3, size.getWidth());
            ps.setString(4, errorCorrection);
            ps.setInt(5, margin);
            ps.setInt(6, qrColor);
            ps.setInt(7, backgroundColor);
            ps.setString(8, logo);
            ps.setString(9, responseFormat);
            ps.setBytes(10, qrByteArray);

            ResultSet rs = ps.executeQuery(); //Execute query
            if (!rs.first() || !rs.getBoolean("result")) {
                throw new CacheInternalException("Operation failed");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new CacheInternalException("saveQrInCache failed, rolling back");
            } catch (SQLException e1) {
                throw new CacheInternalException("saveQrInCache failed, cannot roll back");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CacheInternalException("Cannot close connection");
            }
        }
    }

    /**
     * Return QR code if exist in cache or null
     *
     * @param sequence        sequence associated
     * @param size            size associated
     * @param errorCorrection error correction level associated
     * @param margin          margin associated
     * @param qrColor         color of the QR associated
     * @param backgroundColor background color associated
     * @param logo            logo associated
     * @param responseFormat  response format associated
     * @return QR code if exist in cache or null
     * @throws CacheInternalException if cache fails
     */
    public byte[] getQR(@NotNull String sequence, @NotNull Size size, @NotNull String errorCorrection, @NotNull Integer margin,
                        @NotNull Integer qrColor, @NotNull Integer backgroundColor, String logo, @NotNull String responseFormat)
            throws CacheInternalException {
        Connection connection = null;
        try {
            connection = DbManager.getConnection();
            String query = "SELECT * FROM get_qr( ?, ?, ?, ?, ?, ?, ?, ?, ?) AS qr";
            PreparedStatement ps =
                    connection.prepareStatement(query,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, sequence);
            ps.setInt(2, size.getHeight());
            ps.setInt(3, size.getWidth());
            ps.setString(4, errorCorrection);
            ps.setInt(5, margin);
            ps.setInt(6, qrColor);
            ps.setInt(7, backgroundColor);
            ps.setString(8, logo);
            ps.setString(9, responseFormat);

            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getBytes("qr");
            }
            return null;
        } catch (SQLException e) {
            throw new CacheInternalException("getQrIfExist failed");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CacheInternalException("Cannot close connection");
            }
        }
    }
}
