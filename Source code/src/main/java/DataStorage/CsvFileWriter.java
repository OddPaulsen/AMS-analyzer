package DataStorage;

import DataHandler.DlmsMessage;
import DataHandler.Message1Effekt;
import Various.SwConstants;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by paulseno on 16.09.2017, 13:28.
 */
public class CsvFileWriter {

    public CsvFileWriter(List<Message1Effekt> message1EffektList, String fileName) {
//        String fileName = name + ".csv";
        File dataFile = new File(fileName);

            try
            {
                PrintWriter out = new PrintWriter(dataFile);
                writeCsvHeader(out);
                writeCsvData(message1EffektList, out);
                out.close();
            }
            catch (Exception e)
            {
                System.out.println("CsvFileWriter " + e.getMessage());
            }
    }

    private void writeCsvHeader(PrintWriter out)
    {
        try
        {
            out.println("sep=" + SwConstants.EXCEL_DELIM);                                           // Tell Excel what the csv separator is
            out.print("Year" + SwConstants.EXCEL_DELIM);
            out.print("Month" + SwConstants.EXCEL_DELIM);
            out.print("DayMonth" + SwConstants.EXCEL_DELIM);
            out.print("DayWeek" + SwConstants.EXCEL_DELIM);
            out.print("Hour" + SwConstants.EXCEL_DELIM);
            out.print("Minute" + SwConstants.EXCEL_DELIM);
            out.print("Second" + SwConstants.EXCEL_DELIM);
            out.print("Effekt");
            out.println("");
        }
        catch (Exception e)
        {
            System.out.println("CsvFileWriter writeCsvHeader " + e.getMessage());
        }
    }


    private void writeCsvData(List<Message1Effekt> message1EffektList, PrintWriter out)
    {
        for (Message1Effekt message1Effekt: message1EffektList) {
            try
            {
                out.print(String.format("%d", message1Effekt.getYear()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getMonth()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getDayOfMonth()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getDayOfWeek()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getHour()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getMinute()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getSecond()) + SwConstants.EXCEL_DELIM);
                out.print(String.format("%d", message1Effekt.getEffekt()));
                out.println("");
            }
            catch (Exception e)
            {
                System.out.println("CsvFileWriter writeCsvData " + e.getMessage());
            }
        }
    }
}
