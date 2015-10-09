package tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import tetris.objects.BaseObject;
import tetris.objects.CompositeObject;
import static tetris.objects.Shapes.*;

public class Game extends GameState {

    BaseObject[][] grid;
    CompositeObject currentShape;
    int count = 0;
    int speed = 1;
    boolean sBoost;
    int[][] pattern;
    int hiddenRows;

    public Game() {
        isTransparent = false;
        sBoost = false;
        hiddenRows = 6;
        grid = new BaseObject[20+hiddenRows][10];
    }

    @Override
    public void init() {
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(50, 60, 80));
        g.fillRect(0, 0, Tetris.width, Tetris.height);
        for (int h = hiddenRows; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                if (grid[h][w] != null) {
                    grid[h][w].draw(g, h-hiddenRows , w);
                }
            }
        }
    }

    public void spawnObject() {
        pattern = randomShape();
        int x = (int) (Math.random() * (10-pattern[0].length) + getLeftSpace(pattern));
        int y = hiddenRows+getTopSpace(pattern);
        
        currentShape = new CompositeObject(pattern, grid, x, y);
    }

    public void moveDown() {
        if (currentShape.moveDown()) {
            clearFilled();
            spawnObject();
            currentShape.moveDown();
        }
    }

    public void clearFilled() {
        for (int l = 4; l < grid.length; l++) {
            if (checkLine(grid[l])) {
                for (int i = l; i > 0; i--) {
                    grid[i] = Arrays.copyOf(grid[i-1], 10);
                }
            }
        }
    }

    private boolean checkLine(BaseObject[] line) {
        for (BaseObject o : line) {
            if (o == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void update() {
        if (sBoost) {
            count += 15;
        }
        count += speed;
        if (count >= 15) {
            if (currentShape == null) {
                spawnObject();
            }
            moveDown();
            count = 0;
        }
    }

    @Override
    public void keyTyped(int k) {
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_UP) {
            currentShape.rotateRight();
        }
        if (k == KeyEvent.VK_DOWN) {
            currentShape.rotateLeft();
        }
        if (k == KeyEvent.VK_SPACE) {
            sBoost = true;
        }
        if (k == KeyEvent.VK_RIGHT) {
            currentShape.moveRight();
        }
        if (k == KeyEvent.VK_LEFT) {
            currentShape.moveLeft();
        }

        if (k == KeyEvent.VK_BACK_SPACE) {
            for (int h = 0; h < grid.length; h++) {
                for (int w = 0; w < grid[h].length; w++) {
                    grid[h][w] = null;
                }
            }
            spawnObject();
        }

    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_SPACE) {
            sBoost = false;
        }
    }

}
