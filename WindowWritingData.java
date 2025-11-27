import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WindowWritingData {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea showArea;

    public WindowWritingData(JTextField usernameField, JPasswordField passwordField, JTextArea showArea) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.showArea = showArea;
    }

    public void handleButtonClick() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Proszę wypełnić wszystkie pola!",
                    "Błąd",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String output = String.format("[%s]\nNazwa: %s\nHasło: %s\n---\n", currentDateTime, username, password);
        showArea.append(output);

        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }

    public void setupEnterKeySupport() {
        usernameField.addActionListener(e -> passwordField.requestFocusInWindow());
        passwordField.addActionListener(e -> handleButtonClick());
    }
}