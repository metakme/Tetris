package tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Tetris extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    long waitTime;
    boolean running;

    
    public Tetris() {
        super();
        width = 300;
        height =  width*2;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    private void init() {
        image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;
        gsm = new GameStateManager();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent key) {
            }

            @Override
            public void keyPressed(KeyEvent key) {
                gsm.keyPressed(key.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent key) {
                gsm.keyReleased(key.getKeyCode());
            }
        });
    }

    @Override
    public void run() {
        init();
        waitTime = 1000 / 30;
        
        while (running) {
            update();
            drawToImage();
            drawToScreen();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void drawToImage() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

}
