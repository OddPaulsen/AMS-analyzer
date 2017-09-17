package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 14.09.2017, 08:58.
 * Read text file, could be captured with HHD serial monitor.
 * Skip HHD timestamp tags if present.
 * Parse text as hex numbers, to array of Integers
 *
 */
public class SerialLogfileReader {
    private String fileContent;
    private List<Integer> fileData;                             // File data as sequence of numbers
    private int hhdTags;

    public SerialLogfileReader(String fileName) {
        fileData = new ArrayList<Integer>();

        File dataFile = new File(fileName);
        if (dataFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        FilterTags(line, sb);
                        line = br.readLine();
                    }
                    fileContent = sb.toString();
                    ParseData();
                } finally {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("SerialLogfileReader: " + e.getMessage());
            }
        } else {
            System.out.println("File " + fileName + " not found");
        }
    }

    private void FilterTags(String line, StringBuilder sb) {
        if (line.startsWith("[")) {                             // Skip tags eks: [2017-09-13 00.00.07.776 - Received 41 (0x29) bytes]
            hhdTags++;
        }
        else if (line.length() == 0) {                          // Skip empty lines
        } else {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
    }

    // Convert string to int array
    private void ParseData() {
        String[] tokens = fileContent.trim().split("\\s+");     // Split into tokens, which are separated by whitespace
        for (String st: tokens) {
            fileData.add(Integer.parseInt(st, 16));             // Parse string as hex number
        }
    }

    public List<Integer> getFileData() {
        return fileData;
    }
}
