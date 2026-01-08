import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Okno().setVisible(true);
        });
    }
}

// nie udało mi się zrobić tylko kolizji prostokątów