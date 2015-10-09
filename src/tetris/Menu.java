package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Menu extends GameState {

    Font font;
    Font title;
    String[] options = {"start", "exit"};
    private final int start = 0;
    private final int exit = 1;
    
    private int currentChoice;

    public Menu(GameStateManager gsm) {
        this.gsm = gsm;
        isTransparent = true;
        currentChoice = 0;
        font = new Font("Arial", Font.PLAIN, 26);
        title = new Font("Arial", Font.PLAIN, 36);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(50, 60, 80));
        g.fillRect(0, 0, Tetris.width, Tetris.height);
        g.setColor(Color.green);
        g.setFont(title);
        g.drawString("TETRIS", Tetris.width / 2 - 58, Tetris.height / 4);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }

            g.drawString(options[i], Tetris.width / 2 - 24, Tetris.height / 3 + i * 26);
        }

    }

    @Override
    public void update() {
    }

    public void select() {
        switch (currentChoice) {
            case start:
                gsm.setState(gsm.GAME);
                break;
            case exit:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyTyped(int k) {

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_UP && currentChoice > 0) {
            currentChoice--;
        }
        if (k == KeyEvent.VK_DOWN && currentChoice < options.length - 1) {
            currentChoice++;
        }
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
