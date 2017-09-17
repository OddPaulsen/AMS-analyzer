package DataHandler;

import java.util.List;

/**
 * Created by paulseno on 14.09.2017, 12:00.
 * Create from payload in LLC
 */
public class DlmsMessage {
    private Message1Effekt message1Effekt = null;

    public DlmsMessage(List<Integer> informationData) {
        switch (informationData.size()) {
            case 28:                                                        // 41 byte message
                message1Effekt = new Message1Effekt(informationData);
                break;

            case 110:
            case 144:
                break;

            default:
                System.out.println("DlmsMessage: unknown informationData.size " + informationData.size());
        }
    }

    public Message1Effekt getMessage1Effekt() {
        return message1Effekt;
    }
}
