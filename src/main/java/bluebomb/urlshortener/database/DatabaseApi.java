package bluebomb.urlshortener.database;

import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.Size;
import net.bytebuddy.implementation.bytecode.Throw;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseApi {
    private static DatabaseApi ourInstance = new DatabaseApi();

    public static DatabaseApi getInstance() {
        return ourInstance;
    }

    private DatabaseApi() {
    }

    /**
     * Return true if sequence exist in DB
     *
     * @param sequence
     * @return if sequence exists in database
     * @throws DatabaseInternalException
     */
    public boolean checkIfSequenceExist(String sequence) throws DatabaseInternalException {
        Connection connection = null;
        try {
            connection = DBmanager.getConnection();
            String query = "SELECT * FROM short_sequences WHERE seq = ?";
            PreparedStatement ps = 
                connection.prepareStatement(query, 
                                            ResultSet.TYPE_SCROLL_SENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, sequence); //replace seq = ? -> seq = sequence
            ResultSet rs = ps.executeQuery(); //Execute query
            return rs.first(); //Return if result is not empty
        } catch (SQLException e) {
            throw new DatabaseInternalException("checkIfSequenceExist failed");
		} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseInternalException("Cannot close connection");
            }
        }
    }

    /**
     * Return QR code if exist in cache or null
     *
     * @param sequence
     * @param size
     * @param errorCorrection
     * @param margin
     * @param qrColor
     * @param backgroundColor
     * @param logo
     * @param responseFormat
     * @return
     * @throws DatabaseInternalException
     */
    public byte[] getQrIfExist(String sequence, Size size, String errorCorrection, Integer margin,
                               int qrColor, int backgroundColor, String logo, String responseFormat) throws DatabaseInternalException {
        // TODO:
        return null;
    }

    /**
     * Save QR code in cache
     *
     * @param sequence
     * @param size
     * @param errorCorrection
     * @param margin
     * @param qrColor
     * @param backgroundColor
     * @param logo
     * @param responseFormat
     * @param qrByteArray
     * @throws DatabaseInternalException
     */
    public void saveQrInCache(String sequence, Size size, String errorCorrection, Integer margin,
                              int qrColor, int backgroundColor, String logo, String responseFormat, byte[] qrByteArray) throws DatabaseInternalException {
        // TODO:
    }

    /**
     * Return sequence global stats fiter by parameter
     *
     * @param sequence
     * @param parameter
     * @return
     */
    public ArrayList<ClickStat> getSequenceGlobalStats(String sequence, String parameter) throws DatabaseInternalException {
        // TODO:
        return new ArrayList<>();
    }

    /**
     * Update static of some sequence
     *
     * @param sequence
     * @param os
     * @param browser
     * @return (New OS number of clicks, New Browser number of clicks)
     */
    public ImmutablePair<Integer, Integer> updateSequenceStatics(String sequence, String os, String browser) throws DatabaseInternalException {
        // TODO:
        return new ImmutablePair<>(1, 2);
    }

    /**
     * Check if the sequence got add
     *
     * @param sequence
     * @return null if no ad or ad in the other case
     */
    public RedirectURL checkIfGotAd(String sequence) throws DatabaseInternalException {
        // TODO:
        return null;
    }

    public String getOriginalURL(String sequence) throws DatabaseInternalException {
        // TODO:
        return "www.unizar.es";
    }

    public String createShortURL(String headURL) throws DatabaseInternalException {
        return createShortURL(headURL, "empty", 10);
    }

    public String createShortURL(String headURL, String interstitialURL) throws DatabaseInternalException {
        return createShortURL(headURL, interstitialURL, 10);
    }

    /**
     * Create a shortened URL and return the sequence related to it
     * @param headURL
     * @param interstitialURL
     * @param secondsToRedirect
     * @return
     * @throws DatabaseInternalException
     */
    public String createShortURL(String headURL, String interstitialURL, Integer secondsToRedirect) throws DatabaseInternalException {
        Connection connection = null;
        try {
            connection = DBmanager.getConnection();
            String query = "SELECT * FROM new_shortened_url(?,?,?) AS seq";
            PreparedStatement ps = 
                connection.prepareStatement(query, 
                                            ResultSet.TYPE_SCROLL_SENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, headURL); 
            ps.setString(2, interstitialURL);
            ps.setInt(3, secondsToRedirect);
            ResultSet rs = ps.executeQuery(); //Execute query
            if(rs.first()) {
                return rs.getString("seq");
            }       
            throw new SQLException();    
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DatabaseInternalException("createShortUrl failed, rolling back");
            } catch (SQLException e1) {
                throw new DatabaseInternalException("createShortUrl failed, cannot roll back");
            }
		} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseInternalException("Cannot close connection");
            }
        }
    }
}
