package DataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 14.09.2017, 11:47.
 */
public class LlcMessage {
    private final int BYTE1POS = 0;
    private final int BYTE2POS = 1;
    private final int BYTE3POS = 2;
    private final int INFOPOS = 3;
    private List<Integer> informationData;                      // Information payload

    public LlcMessage(List<Integer> messageData) {
        informationData = new ArrayList<Integer>();
        VerifyFixed(messageData);                               // Check if fields are as assumed. No errors
        int infoLength = messageData.size() - 3;
        for (int n = 0; n < infoLength; n++) {
            informationData.add(messageData.get(INFOPOS + n));
        }
    }

    public DlmsMessage getDlmsMessage() {
        return new DlmsMessage(informationData);
    }


    private boolean VerifyFixed(List<Integer> messageData) {
        if ((messageData.get(BYTE1POS)) != 0xE6) {                  // Destination (remote) LSAP
            System.out.println("LlcMessage Fail LLC1");
            return false;
        }
        if ((messageData.get(BYTE2POS)) != 0xE7) {                  // Source (local) LSAP
            System.out.println("LlcMessage Fail LLC2");
            return false;
        }
        if ((messageData.get(BYTE3POS)) != 0) {                     // LLC_Quality
            System.out.println("LlcMessage Fail LLC3");
            return false;
        }
        return true;
    }
}
