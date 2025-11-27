import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowExtra {
    private JPanel mainPanel;
    private JLabel capsLockLabel;

    public WindowExtra(JPanel mainPanel, JLabel capsLockLabel) {
        this.mainPanel = mainPanel;
        this.capsLockLabel = capsLockLabel;
    }

    public void setupEscapeKey() {
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeApp");
        actionMap.put("closeApp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApplication();
            }
        });
    }

    private void closeApplication() {
        int result = JOptionPane.showConfirmDialog(
                mainPanel,
                "Czy na pewno chcesz zamknąć aplikację?",
                "Zamknięcie aplikacji",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void setupCapsLockDetection(JTextField usernameField, JPasswordField passwordField) {
        KeyAdapter capsLockListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                checkCapsLock();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                checkCapsLock();
            }
        };

        usernameField.addKeyListener(capsLockListener);
        passwordField.addKeyListener(capsLockListener);
        checkCapsLock();
    }

    private void checkCapsLock() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        boolean capsLockOn = toolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

        if (capsLockOn) {
            capsLockLabel.setText("CAPS LOCK WŁĄCZONY");
            capsLockLabel.setForeground(Color.RED);
        } else {
            capsLockLabel.setText("");
        }
    }
}