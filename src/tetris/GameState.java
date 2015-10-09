package tetris;

public abstract class GameState {

    protected GameStateManager gsm;
    public boolean isTransparent;
    
    public abstract void init();

    public abstract void draw(java.awt.Graphics2D g);

    public abstract void update();

    public abstract void keyTyped(int k);
    
    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);
}
