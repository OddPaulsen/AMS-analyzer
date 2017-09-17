package FileHandler;

import DataHandler.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 16.09.2017, 17:51.
 */
public class FileData {
    private List<DlmsMessage> dlmsMessageList;
    private List<Message1Effekt> message1EffektList;
    private StreamingDecoder streamingDecoder;

    public FileData(File selectedFile) {
        SerialLogfileReader serialLogfileReader = new SerialLogfileReader(selectedFile.getAbsolutePath());  // Read textfile to array of Integers
        streamingDecoder = new StreamingDecoder(serialLogfileReader.getFileData());                         // Extract HDLC frames

        dlmsMessageList = new ArrayList<DlmsMessage>();
        message1EffektList = new ArrayList<Message1Effekt>();
        for (HdlcFrameMessage frameMessage : streamingDecoder.getHdlcFrameMessageList()) {
            if (frameMessage.isCheckedOk()) {
                LlcMessage llcMessage = frameMessage.getLLCmessage();
                DlmsMessage dlmsMessage = llcMessage.getDlmsMessage();
                dlmsMessageList.add(dlmsMessage);
                if (dlmsMessage.getMessage1Effekt() != null) {
                    message1EffektList.add(dlmsMessage.getMessage1Effekt());
                }
            }
        }
        System.out.println("Total messages: " + dlmsMessageList.size());
    }

    public StreamingDecoder getStreamingDecoder() {
        return streamingDecoder;
    }

    public List<DlmsMessage> getDlmsMessageList() {
        return dlmsMessageList;
    }

    public List<Message1Effekt> getMessage1EffektList() {
        return message1EffektList;
    }
}
