package GUI;

import DataHandler.*;
import DataStorage.CsvFileWriter;
import FileHandler.FileData;
import FileHandler.SerialLogfileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulseno on 13.09.2017, 13:49.
 */
public class AmsReaderMain extends JPanel {
    private JFrame frame;
    private ButtonsPanel buttonsPanel;
    private StatisticsPanel statisticsPanel;
    private FileData fileData;
    private File selectedFile;

    public AmsReaderMain() {
        createFrame();
        buttonsPanel = new ButtonsPanel(this);
        statisticsPanel = new StatisticsPanel();

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.WEST);
        add(statisticsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(this);
        frame.pack();
        frame.addWindowListener(new WindowAdapter()                                 // handle JFrame window events
        {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void FileSelectedAction(File selectedFile) {
        this.selectedFile = selectedFile;
        fileData = new FileData(selectedFile);
        statisticsPanel.ShowData(fileData.getStreamingDecoder());
    }

    public void WriteCsvAction() {
        String filename = selectedFile.getAbsolutePath().replace(".txt", ".csv");
        new CsvFileWriter(fileData.getMessage1EffektList(), filename);
    }

    private void createFrame() {
        frame = new JFrame("AMS Monitor");
        int wHeight = 300;
        int wWidth = 500;
        frame.setPreferredSize(new Dimension(wWidth, wHeight));
        frame.setVisible(true);
        frame.toFront();
        frame.setState(Frame.NORMAL);
    }


    public static void main(String[] args) {
        new AmsReaderMain();
    }
}
