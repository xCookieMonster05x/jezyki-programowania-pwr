import javax.swing.*;
import java.awt.*;

public class WindowColorChange {
    private JTextArea showArea;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton showButton;

    public WindowColorChange(JTextArea showArea, JTextField usernameField, JPasswordField passwordField, JButton showButton) {
        this.showArea = showArea;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.showButton = showButton;
    }

    public void changeBackgroundColor() {
        JColorChooser colorChooser = createRGBColorChooser();
        int result = JOptionPane.showConfirmDialog(null, colorChooser, "Wybierz kolor tła (RGB)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Color newColor = colorChooser.getColor();
            showArea.setBackground(newColor);
        }
    }

    public void changeTextColor() {
        JColorChooser colorChooser = createRGBColorChooser();
        int result = JOptionPane.showConfirmDialog(null, colorChooser, "Wybierz kolor tekstu (RGB)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Color newColor = colorChooser.getColor();
            showArea.setForeground(newColor);
            usernameField.setForeground(newColor);
            passwordField.setForeground(newColor);
        }
    }

    public void changeButtonColor() {
        JColorChooser colorChooser = createRGBColorChooser();
        int result = JOptionPane.showConfirmDialog(null, colorChooser, "Wybierz kolor przycisku (RGB)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Color newColor = colorChooser.getColor();
            showButton.setBackground(newColor);
            showButton.setForeground(getContrastColor(newColor));
        }
    }

    private JColorChooser createRGBColorChooser() {
        JColorChooser colorChooser = new JColorChooser();

        javax.swing.colorchooser.AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (javax.swing.colorchooser.AbstractColorChooserPanel panel : panels) {
            String displayName = panel.getDisplayName();
            if (!displayName.equals("Próbki") && !displayName.equals("Swatches")) {
                colorChooser.removeChooserPanel(panel);
            }
        }

        return colorChooser;
    }

    private Color getContrastColor(Color color) {
        double luminance = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }
}