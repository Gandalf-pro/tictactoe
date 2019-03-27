import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * Board
 */
public class Board extends JFrame {
    public Canvas canvas;
    public BufferStrategy bs;
    public KeyThing ml;
    public Graphics g;
    public int margin = 12;
    public int space = 212;
    public boolean gameFinished = false;
    private int x1, x2, y1, y2;



    public void clearScreen() {
        canvas.setBackground(Color.DARK_GRAY);
        g.setColor(Color.DARK_GRAY);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        bs.show();
    }

    public int convertPixel(int x) {
        return ((x * space + margin) + 10);
    }

    public void drawWin() {
        if (gameFinished) {
            g.setColor(Color.GREEN);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void setWinLine(int x1, int y1, int x2, int y2) {
        this.x1 = convertPixel(x1) - 10 + space / 2;
        this.x2 = convertPixel(x2) - 10 + space / 2;
        this.y1 = convertPixel(y1) - 10 + space / 2;
        this.y2 = convertPixel(y2) - 10 + space / 2;
    }

    public void drawX(int x, int y) {
        int big = space - 20;
        g.setColor(Color.red);
        g.drawLine(x, y, x + big, y + big);
        g.drawLine(x + big, y, x, y + big);
    }

    public void drawY(int x, int y) {
        int big = space - 20;
        g.drawLine(x, y, x + big / 2, y + big / 2);
        g.drawLine(x + big, y, x + big / 2, y + big / 2);
        g.drawLine(x + big / 2, y + big / 2, x + big / 2, y + big);
    }

    public void drawO(int x, int y) {
        int big = space - 20;
        g.setColor(Color.blue);
        g.drawOval(x, y, big, big);
    }

    public void drawBoard() {
        g.setColor(Color.MAGENTA);
        g.drawLine(space + margin, margin, space + margin, ((space * 3) + margin));
        g.drawLine(margin + space * 2, margin, margin + space * 2, ((space * 3) + margin));
        g.drawLine(margin, margin + space, ((space * 3) + margin), margin + space);
        g.drawLine(margin, space * 2 + margin, ((space * 3) + margin), space * 2 + margin);
    }

    public void canvasSettings() {
        canvas = new Canvas();
        this.add(canvas);
        canvas.setBackground(Color.DARK_GRAY);
        canvas.addMouseListener(ml);
    }

    public void frameSettings() {
        this.setTitle("XOX");
        this.setSize(660, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void setBs() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            System.out.println("bs not found");
            System.out.println("Creating bs");
            canvas.createBufferStrategy(2);
            bs = canvas.getBufferStrategy();
            System.out.println(bs);
            return;
        }
    }

    public void setGraphics() {
        g = bs.getDrawGraphics();
    }

    public Board() {
        this.ml = new KeyThing(margin, space);
        frameSettings();
        canvasSettings();
        this.setVisible(true);
        setBs();
        setGraphics();
    }
}