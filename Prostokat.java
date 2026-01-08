import java.awt.*;

public class Prostokat implements Runnable {
    private int x;
    private int y;
    private final int szerokosc;
    private final int wysokosc;
    private final Color kolor;
    private final Plansza plansza;
    private boolean aktywna = true;

    public Prostokat(int x, int y, int w, int h, Plansza plansza) {
        this.x = x;
        this.y = y;
        this.szerokosc = w;
        this.wysokosc = h;
        this.plansza = plansza;
        this.kolor = new Color((int)(Math.random() * 0x1000000));
    }

    public void zatrzymaj() {
        this.aktywna = false;
    }

    @Override
    public void run() {
        while (aktywna) {
            try {
                Thread.sleep(20);

                y += 3;

                if (y + getH() > plansza.getHeight()) {
                    aktywna = false;
                }

                plansza.repaint();
            } catch (InterruptedException e) {
                aktywna = false;
            }
        }
    }

    public void rysuj(Graphics g) {
        g.setColor(kolor);
        g.fillRect(x, y, szerokosc, wysokosc);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, szerokosc, wysokosc);
    }

    public int getH() {
        return wysokosc;
    }
}