package GUI;

import DataHandler.StreamingDecoder;
import Various.SwConstants;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Created by paulseno on 16.09.2017, 21:22.
 */
public class StatisticsPanel extends JPanel {
    private JTextField value1;
    private JTextField value2;
    private JTextField value3;
    private JTextField value4;
    private JTextField value5;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private final int LABEL_FONT_SIZE = 12;
    private String spacing1 = "10dlu";
    private int BORDERSIZE = 20;

    public StatisticsPanel() {
        createComponents();
        layoutComponents();
        setBackground(SwConstants.COLOR_MAIN_BACKGROUND);
        Border empty = BorderFactory.createEmptyBorder(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE);
        setBorder(empty);
    }

    public void ShowData(StreamingDecoder streamingDecoder) {
        value1.setText(String.format("%d", streamingDecoder.getFrameCounter()));
        value2.setText(String.format("%d", streamingDecoder.getErrorCounter()));
        value3.setText(String.format("%d", streamingDecoder.getCountFrame39()));
        value4.setText(String.format("%d", streamingDecoder.getCountFrame121()));
        value5.setText(String.format("%d", streamingDecoder.getCountFrame155()));
    }

    private void createComponents() {
        value1 = DefaultComponents.defaultTextFieldR(16);
        value2 = DefaultComponents.defaultTextFieldR(16);
        value3 = DefaultComponents.defaultTextFieldR(16);
        value4 = DefaultComponents.defaultTextFieldR(16);
        value5 = DefaultComponents.defaultTextFieldR(16);
        label1 = DefaultComponents.defaultPlainLabel("Frame count", LABEL_FONT_SIZE);
        label2 = DefaultComponents.defaultPlainLabel("Error count", LABEL_FONT_SIZE);
        label3 = DefaultComponents.defaultPlainLabel("Type 1 count", LABEL_FONT_SIZE);
        label4 = DefaultComponents.defaultPlainLabel("Type 2 count", LABEL_FONT_SIZE);
        label5 = DefaultComponents.defaultPlainLabel("Type 3 count", LABEL_FONT_SIZE);

    }

    private void layoutComponents() {
        FormLayout layout = specifylayout();
        setLayout(layout);
        CellConstraints cc = new CellConstraints();

        add(label1,   cc.xy(1, 1));
        add(value1,   cc.xy(3, 1));
        add(label2,   cc.xy(1, 3));
        add(value2,   cc.xy(3, 3));
        add(label3,   cc.xy(1, 5));
        add(value3,   cc.xy(3, 5));
        add(label4,   cc.xy(1, 7));
        add(value4,   cc.xy(3, 7));
        add(label5,   cc.xy(1, 9));
        add(value5,   cc.xy(3, 9));
    }

    private FormLayout specifylayout() {
        ColumnSpec[] columnSpecs = new ColumnSpec[3];
        columnSpecs[0] = ColumnSpec.decode("40dlu");
        columnSpecs[1] = ColumnSpec.decode("10dlu");
        columnSpecs[2] = ColumnSpec.decode("40dlu");

        RowSpec[] rowSpecs = new RowSpec[9];
        rowSpecs[0] = RowSpec.decode("pref");
        rowSpecs[1] = RowSpec.decode(spacing1);
        rowSpecs[2] = RowSpec.decode("pref");
        rowSpecs[3] = RowSpec.decode(spacing1);
        rowSpecs[4] = RowSpec.decode("pref");
        rowSpecs[5] = RowSpec.decode(spacing1);
        rowSpecs[6] = RowSpec.decode("pref");
        rowSpecs[7] = RowSpec.decode(spacing1);
        rowSpecs[8] = RowSpec.decode("pref");

        return new FormLayout(columnSpecs, rowSpecs);
    }
}
