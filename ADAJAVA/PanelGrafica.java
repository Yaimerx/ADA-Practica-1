import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class PanelGrafica extends JPanel {
    private Map<String, List<NodoGrafica>> series = new HashMap<>();

    private static class NodoGrafica {
        int x, y;
        long tiempo;
        NodoGrafica(int x, int y, long t) {
            this.x = x; this.y = y; this.tiempo = t;
        }
    }

    public void agregarPunto(String algo, int tam, long t) {
        series.putIfAbsent(algo, new ArrayList<>());
        int posX = (int) (Math.log10(tam) * 80);
        int posY = (int) t; 
        series.get(algo).add(new NodoGrafica(posX, posY, t));
        repaint();
    }

    public void limpiar() {
        series.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = 60;
        int h = getHeight() - 2 * pad;
        int w = getWidth() - 2 * pad;

        g2.setColor(new Color(230, 230, 230));
        for (int i = 0; i <= 10; i++) {
            int yLine = pad + h - (i * h / 10);
            g2.drawLine(pad, yLine, pad + w, yLine);
        }

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(pad, pad, pad, pad + h);
        g2.drawLine(pad, pad + h, pad + w, pad + h);

        Color[] colores = {Color.BLUE, Color.RED, new Color(0, 150, 0)};
        int idx = 0;

        for (String s : series.keySet()) {
            g2.setColor(colores[idx % colores.length]);
            List<NodoGrafica> puntos = series.get(s);
            
            for (int i = 0; i < puntos.size(); i++) {
                NodoGrafica p = puntos.get(i);
                int xCoord = pad + p.x;
                int yCoord = pad + h - Math.min(p.y, h);

                if (i > 0) {
                    NodoGrafica ant = puntos.get(i - 1);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(pad + ant.x, pad + h - Math.min(ant.y, h), xCoord, yCoord);
                }

                g2.fillOval(xCoord - 4, yCoord - 4, 8, 8);
                g2.drawString(p.tiempo + "ms", xCoord + 5, yCoord - 5);
            }
            g2.drawString(s, pad + 10, pad + 20 + (idx * 20));
            idx++;
        }
    }
}