package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by paulseno on 16.09.2017, 21:28.
 */
public class DefaultComponents {
    public static final Color LIGHTLIGHTGRAY = new Color(225, 225, 225);
    public static final Color LIGHTDARKBLUE = new Color(26, 76, 128);

    public static JTextField defaultTextField() {
        JTextField txtf = new JTextField();
        txtf.setSelectedTextColor(Color.black);
        txtf.setHorizontalAlignment(SwingConstants.CENTER);
        txtf.setEditable(false);                                // Read-only
        txtf.setBackground(LIGHTLIGHTGRAY);
        txtf.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtf.setSelectionColor(null);                           // Avoid the dark selection color
        return txtf;
    }

    public static JTextField defaultTextFieldR() {
        JTextField txtf = defaultTextField();
        txtf.setHorizontalAlignment(SwingConstants.RIGHT);
        return txtf;
    }

    public static JTextField defaultTextFieldREdit() {
        JTextField txtf = defaultTextFieldR();
        txtf.setEditable(true);
        txtf.setBackground(Color.WHITE);
        return txtf;
    }

    public static JTextField defaultTextFieldR(int fontSize) {
        JTextField txtf = defaultTextField();
        txtf.setHorizontalAlignment(SwingConstants.RIGHT);
        txtf.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return txtf;
    }

    public static JLabel defaultBoldLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.BOLD, fontSize));
        label.setForeground(LIGHTDARKBLUE);                   // Pure blue is too strong
        return label;
    }

    public static JLabel defaultPlainLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        label.setForeground(LIGHTDARKBLUE);                   // Pure blue is too strong
        return label;
    }

    public static JButton createBasicButton(String text) {
        JButton button = new JButton();
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.TOP);
        button.setText(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Dialog", Font.PLAIN, 10));
        return button;
    }

    public static boolean setDialog(String message) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, message, "Confirm", dialogButton);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}
