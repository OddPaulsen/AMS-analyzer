package DataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 15.09.2017, 10:09.
 * This is one complete HDLC message, except the start/end flag bytes. The FCS is included, has already been checked OK
 */
public class HdlcFrameMessage {
    private final int BYTE2POS = 1;
    private final int BYTE3POS = 2;
    private final int BYTE4POS = 3;
    private final int BYTE5POS = 4;
    private final int BYTE6POS = 5;
    private final int BYTE7POS = 6;
    private final int BYTE8POS = 7;
    private final int INFOPOS = 8;
    private final int HCS_CHECK_LENGTH = 6;
    private int frameLength;
    private List<Integer> informationData;          // Information payload in HDLC frame format type 3 MAC sublayer
    private boolean checkedOk = false;

    public HdlcFrameMessage(List<Integer> messageData) {
        informationData = new ArrayList<Integer>();

        boolean verifyOk = VerifyFixed(messageData);
        if (verifyOk) {
            int hcsValue = (messageData.get(BYTE7POS) << 8) + messageData.get(BYTE8POS);
            checkedOk = CheckFcs(messageData, HCS_CHECK_LENGTH, hcsValue);                  // Header CRC check. First 6 bytes
            if (!checkedOk) {
                System.out.println("HdlcFrameMessage: HCS check fail");
            }
        }

        int infoLength = frameLength - 8;
        if (checkedOk) {
            for (int n = 0; n < infoLength; n++) {
                informationData.add(messageData.get(INFOPOS + n));
            }
        }
    }

    public LlcMessage getLLCmessage() {
        return new LlcMessage(informationData);
    }

    public boolean isCheckedOk() {
        return checkedOk;
    }

    private boolean VerifyFixed(List<Integer> messageData) {
        if (messageData.size() > 0)
        {
            frameLength = messageData.get(BYTE2POS);

            if (messageData.get(BYTE3POS) != 0x01) {
                System.out.println("HdlcFrameMessage Fail D");              // Destination address
                return false;
            }

            if (messageData.get(BYTE4POS) != 0x02) {
                System.out.println("HdlcFrameMessage Fail E");              // Source address byte 1
                return false;
            }

            if (messageData.get(BYTE5POS) != 0x01) {                        // Source address byte 2
                System.out.println("HdlcFrameMessage Fail F");
                return false;
            }

            if (messageData.get(BYTE6POS) != 0x10) {                        // Control
                System.out.println("HdlcFrameMessage Fail G");
                return false;
            }
            return true;
        }
        System.out.println("HdlcFrameMessage: empty message");
        return false;
    }

    private boolean CheckFcs(List<Integer> messageData, int length, int fcsValue) {
        int chksum = GXFCS16.countFCS16(messageData, 0, length);
        return chksum == fcsValue;
    }
}
