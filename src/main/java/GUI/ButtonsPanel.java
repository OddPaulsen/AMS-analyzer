package GUI;

import Various.SwConstants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Created by paulseno on 16.09.2017, 16:21.
 */
public class ButtonsPanel extends JPanel {
    private final AmsReaderMain master;
    private JButton viewButton1;
    private JButton viewButton2;
    private String spacing1 = "10dlu";
    private int BORDERSIZE = 20;

    public ButtonsPanel(AmsReaderMain master) {
        this.master = master;
        createComponents();
        layoutComponents();
        setBackground(SwConstants.COLOR_MAIN_BACKGROUND);
        Border empty = BorderFactory.createEmptyBorder(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE);
        setBorder(empty);
        viewButton2.setEnabled(false);
    }

    private void createComponents() {
        viewButton1 = new JButton("File select");
        viewButton1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                FileSelectorAction();
            }
        });

        viewButton2 = new JButton("Write CSV");
        viewButton2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                master.WriteCsvAction();
            }
        });
    }

    private void FileSelectorAction() {
        JFileChooser fileChooser = new JFileChooser();
        FileSystemView fw = fileChooser.getFileSystemView();                 // Get the "Documents" folder in Windows
        fileChooser.setCurrentDirectory(new File(fw.getDefaultDirectory().toString() + File.separator + SwConstants.AMSFOLDER));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            master.FileSelectedAction(selectedFile);
            viewButton2.setEnabled(true);
        }
    }

    private void layoutComponents() {
        FormLayout layout = specifylayout();
        setLayout(layout);
        CellConstraints cc = new CellConstraints();

        add(viewButton1, cc.xy(2, 1));
        add(viewButton2, cc.xy(2, 3));
    }

    private FormLayout specifylayout() {
        ColumnSpec[] columnSpecs = new ColumnSpec[2];
        columnSpecs[0] = ColumnSpec.decode("30dlu");
        columnSpecs[1] = ColumnSpec.decode("60dlu");

        RowSpec[] rowSpecs = new RowSpec[3];
        rowSpecs[0] = RowSpec.decode("pref");
        rowSpecs[1] = RowSpec.decode(spacing1);
        rowSpecs[2] = RowSpec.decode("pref");

        return new FormLayout(columnSpecs, rowSpecs);
    }
}
