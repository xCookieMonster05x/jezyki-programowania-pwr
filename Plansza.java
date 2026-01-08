import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plansza extends JPanel {
    private List<Prostokat> prostokaty = Collections.synchronizedList(new ArrayList<>());
    private Point start;

    public void wyczysc() {
        for (Prostokat p : prostokaty) {
            p.zatrzymaj();
        }
        prostokaty.clear();
        repaint();
    }

    public Plansza() {
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (start != null) {
                    Point punktKoncowy = e.getPoint();

                    int x = Math.min(start.x, punktKoncowy.x);
                    int y = Math.min(start.y, punktKoncowy.y);
                    int w = Math.abs(start.x - punktKoncowy.x);
                    int h = Math.abs(start.y - punktKoncowy.y);

                    if (w > 5 && h > 5) {
                        Prostokat p = new Prostokat(x, y, w, h, Plansza.this);
                        prostokaty.add(p);
                        new Thread(p).start();
                    }
                    start = null;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (prostokaty) {
            for (Prostokat p : new ArrayList<>(prostokaty)) {
                p.rysuj(g);
            }
        }
    }
}