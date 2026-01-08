import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame {

    public Okno() {
        setTitle("Prostokąty");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Plansza plansza = new Plansza();

        JButton przyciskWyczysc = new JButton("Usuń prostokąty");

        przyciskWyczysc.addActionListener(e -> {
            plansza.wyczysc();
        });

        add(plansza);
        add(przyciskWyczysc, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        plansza.requestFocusInWindow();
    }

}