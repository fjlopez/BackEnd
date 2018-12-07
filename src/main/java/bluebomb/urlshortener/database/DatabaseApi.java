package bluebomb.urlshortener.database;

import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.RedirectURL;
import bluebomb.urlshortener.model.Size;

import bluebomb.urlshortener.model.Stats;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
        Connection connection = null;
        try {
            connection = DBmanager.getConnection();
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
            if(rs.first()) {
                return rs.getBytes("qr");
            }
            return null;
            //throw new DatabaseInternalException("There is no match with input values");
        } catch (SQLException e) {
            throw new DatabaseInternalException("getQrIfExist failed");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseInternalException("Cannot close connection");
            }
        }
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
    public Boolean saveQrInCache(String sequence, Size size, String errorCorrection, Integer margin,
                              int qrColor, int backgroundColor, String logo, String responseFormat, byte[] qrByteArray) throws DatabaseInternalException {
        Connection connection = null;
        try {
            connection = DBmanager.getConnection();
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
            if(rs.first()) {
                return rs.getBoolean("result");
            }
            return null;      
            //throw new SQLException();    
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DatabaseInternalException("saveQrInCache failed, rolling back");
            } catch (SQLException e1) {
                throw new DatabaseInternalException("saveQrInCache failed, cannot roll back");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseInternalException("Cannot close connection");
            }
        }
    }

    /**
     * Return sequence global stats fiter by parameter
     *
     * @param sequence
     * @param parameter
     * @return
     */
    public ArrayList<ClickStat> getSequenceGlobalStats(String sequence, String parameter) throws DatabaseInternalException {
        Connection connection = null;
        ArrayList<ClickStat> retVal = new ArrayList<ClickStat>();
        String query = "";
        switch(parameter.toLowerCase()) {
            case "os":
                query = "SELECT * FROM get_os_global_stats(?)";
                break;
            case "browser":
                query = "SELECT * FROM get_browser_global_stats(?)";
                break;
            default:
                throw new DatabaseInternalException(parameter + " not supported");
        }

        try {
            connection = DBmanager.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(query, 
                                            ResultSet.TYPE_SCROLL_SENSITIVE, 
                                            ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, sequence);
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
              do {
                retVal.add(new ClickStat(rs.getString("item"), rs.getInt("number")));
              } while(rs.next());
            }
            return retVal;
        } catch (SQLException e) {
            throw new DatabaseInternalException("getSequenceGlobalStats failed");
		} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseInternalException("Cannot close connection");
            }
        }
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

    /**
     * Get the original url related with sequence if exist, null in other case
     *
     * @param sequence
     * @return
     */
    public String getOriginalURL(String sequence) throws DatabaseInternalException {
        // TODO:
        return "www.unizar.es";
    }


    /**
     * Create a new Direct shortend URL without intersticialURL
     * @param headURL
     * @return
     * @throws DatabaseInternalException
     */
    public String createShortURL(String headURL) throws DatabaseInternalException {
        return createShortURL(headURL, "empty", 10);
    }

    /**
     * Create a shortened URL with default secondsToRedirect (10)
     * @param headURL
     * @param interstitialURL
     * @return
     * @throws DatabaseInternalException
     */
    public String createShortURL(String headURL, String interstitialURL) throws DatabaseInternalException {
        return createShortURL(headURL, interstitialURL, 10);
    }

    /**
     * Create a shortened URL and return the sequence related to it
     *
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
            return null;      
            //throw new SQLException();    
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

    /**
     * Return ADS sequence if exist in DB or null
     *
     * @param sequence
    */
    public String getADSIfExist(String sequence) throws DatabaseInternalException {
    
        return null;
    }
    
    /**
     * Return true if ADS html page exist in cache
     *
     * @param ads
     * @return
     */
    public boolean checkIfADSExistInCache(String ads) {
        // TODO:
        return true;
    }
    
    /**
     * save ADS html page in cache 
     * 
     * @param ads
     * @param adsCode
    */
    public void saveADSHTMLInCache(String ads,String adsCode) throws DatabaseInternalException {



    }

    /*
    *  return ADS html pae if exist in cache or null
    *
    * @param ads
    * @return adsCode
    */

    public String getADSHTMLfromCache(String ads) throws DatabaseInternalException{
        String adsCode="<html><body>hola!</body></html>";
        
        return adsCode;
    }








        /**
     * Return stats code if exist in cache or null
     * @param sequence
     * @param parameter parameters from which statistics will be obtained
     * @param startDate First day to get stats
     * @param endDate Last day to get stats
     * @param sortType Sort type (based on total clicks)
     * @param maxAmountofDataToRetrive 
    */
    public ArrayList<Stats> getSTATSifExist(String sequence, String parameter, Date startDate, Date endDate, String sortType, Integer maxAmountOfDataToRetreive)
                                                                                                                                throws DatabaseInternalException {
        
        return null;
    }
    /**
     * save stats code in cache 
     * 
     * @param sequence
     * @param parameter parameters from which statistics will be obtained
     * @param startDate First day to get stats
     * @param endDate Last day to get stats
     * @param sortType Sort type (based on total clicks)
     * @param maxAmountofDataToRetrive 
     */
    public void saveSTATSinCache(String sequence,String parameter,Date startDate,Date endDate,String sortType,Integer maxAmountOfDataToRetreive)
                                                                                                                                 throws DatabaseInternalException {
        
    }
}
