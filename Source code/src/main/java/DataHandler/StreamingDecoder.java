package DataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 14.09.2017, 18:41.
 */
public class StreamingDecoder {
    private final int HDLC_FRAME_START_END = 0x7E;
    private final int HDLC_FRAME_FORMAT3 = 0xA0;
    private int dataReadingIndex = 0;
    private int frameLength;
    private int frameCounter;
    private int errorCounter;
    private int countFrame39;
    private int countFrame121;
    private int countFrame155;
    private List<HdlcFrameMessage> hdlcFrameMessageList;

    public StreamingDecoder(List<Integer> fileData) {
        hdlcFrameMessageList = new ArrayList<HdlcFrameMessage>();
        boolean isErrorRecovery = false;

        while (dataReadingIndex + 3 < fileData.size()) {
            boolean frameOk;
            try {
                frameOk = FindStartOfFrame(fileData, dataReadingIndex);                 // Check if we have a start Flag byte
                if (frameOk) {
                    frameOk = FindEndOfFrame(fileData, dataReadingIndex);               // Check if we have an end Flag byte, and CRC check OK
                    if (frameOk) {
                        isErrorRecovery = false;                                        // Ready to count next error
                        frameCounter++;
                        List<Integer> frameData = fileData.subList(dataReadingIndex + 1, dataReadingIndex + frameLength + 1);   // Extract the HDLC block, between the Flag bytes
                        hdlcFrameMessageList.add(new HdlcFrameMessage(frameData));                                              // Create HdlcFrameMessage, add to list
                        dataReadingIndex += (frameLength + 2);
                    } else {
                        if (!isErrorRecovery) {                                         // Count only one error until recovered
                            errorCounter++;
                            isErrorRecovery = true;
                        }
                    }
                } else {
                    if (!isErrorRecovery) {                                             // Count only one error until recovered
                        errorCounter++;
                        isErrorRecovery = true;
                    }
                }
            } catch (Exception e) {                                                     // Reading past end of list, stop
                break;
            }
            if (!frameOk) {
                dataReadingIndex++;                                                     // Lost frame sync, increment by one and try again
            }
        }

        System.out.println("StreamingDecoder frame count " + frameCounter);
        System.out.println("StreamingDecoder error count: " + errorCounter);

        System.out.println("StreamingDecoder stats: countFrame39, countFrame121, countFrame155: " + countFrame39 + ", " + countFrame121 + ", " + countFrame155);
    }

    public List<HdlcFrameMessage> getHdlcFrameMessageList() {
        return hdlcFrameMessageList;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public int getCountFrame39() {
        return countFrame39;
    }

    public int getCountFrame121() {
        return countFrame121;
    }

    public int getCountFrame155() {
        return countFrame155;
    }


    private boolean FindStartOfFrame(List<Integer> fileData, int startIndex) {
        int workIndex = startIndex;
        int data = 0;
        while (data != HDLC_FRAME_START_END) {                      // Find a Flag byte
            data = fileData.get(workIndex++);
        }
        int ffb1 = fileData.get(workIndex++);                       // Read the two Frame Format bytes
        int ffb2 = fileData.get(workIndex++);

        if ((ffb1 & 0xf0) != HDLC_FRAME_FORMAT3) {                  // Check that we have the Frame Format field, then this is start flag
            return false;
        }
        int segmentation = ffb1 & 0x08;
        frameLength = ((ffb1 & 0x03) << 8) + ffb2;                  // Get Framelength, used to find end flag
        Statistics1(frameLength, segmentation);
        return true;                                                // Assume we have found Flag + Frame format field
    }

    private boolean FindEndOfFrame(List<Integer> fileData, int startIndex) {
        int checkFlag = fileData.get(startIndex + frameLength + 1);
        if (checkFlag == HDLC_FRAME_START_END) {
            int fcsb1 = fileData.get(startIndex + frameLength - 1);
            int fcsb2 = fileData.get(startIndex + frameLength);
            int fcs = (fcsb1 << 8) + fcsb2;
            return CheckFcs(fileData, frameLength - 2, dataReadingIndex + 1, fcs);      // Check FCS.
        }
        return false;
    }

    private boolean CheckFcs(List<Integer> messageData, int length, int offset, int fcsValue) {
        int chksum = GXFCS16.countFCS16(messageData, offset, length);
        return chksum == fcsValue;
    }


    private void Statistics1(int frameLength, int segmentation) {
        if (frameLength == 39) {
            countFrame39++;
        } else if (frameLength == 121) {
            countFrame121++;
        } else if (frameLength == 155) {
            countFrame155++;
        } else {
            System.out.println("StreamingDecoder unknown frameLength " + frameLength);
        }

        if (segmentation != 0) {
            System.out.println("StreamingDecoder segmentation set");
        }
    }
}
