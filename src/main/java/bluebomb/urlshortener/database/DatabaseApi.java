package bluebomb.urlshortener.database;

import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.model.Size;

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
     * @return
     */
    public boolean checkIfSequenceExist(String sequence) {
        // TODO:
        return true;
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

    }

    /**
     * Return ADS code if exist in cache or null
     *
     * @param sequence
    */
    public String getADSIfExist(String sequence) throws DatabaseInternalException {
    
        return null;
    }
    /**
     * save ADS code in cache 
     * 
     * @param sequence
    */
    public void saveADSInCache(String sequence,String ads) throws DatabaseInternalException {

    }
}
