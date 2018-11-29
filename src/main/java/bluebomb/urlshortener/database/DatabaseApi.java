package bluebomb.urlshortener.database;

import bluebomb.urlshortener.exceptions.DatabaseInternalException;
import bluebomb.urlshortener.model.ClickStat;
import bluebomb.urlshortener.model.Size;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

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
    public ArrayList<ClickStat> getSequenceGlobalStats(String sequence, String parameter) {
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
    public ImmutablePair<Long, Long> updateSecuenceStatics(String sequence, String os, String browser){
        return new ImmutablePair<>(1L,2L);
    }
}
