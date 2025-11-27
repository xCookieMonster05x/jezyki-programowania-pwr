import javax.swing.*;

public class WindowConfig {
    public static void createAndShowWindow() {
        JFrame frame = new JFrame("My Swing App");

        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        WindowPanel mainPanel = new WindowPanel();
        frame.add(mainPanel.createPanel());

        frame.setVisible(true);
    }
}
