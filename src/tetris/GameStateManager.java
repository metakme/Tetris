package tetris;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameStateManager {

    public final int MENU = 0;
    public final int GAME = 1;

    GameState[] states = {new Menu(this),new Game()};
    private int currentState = MENU;

    
    
    void setState(int state){
        currentState = state;
        states[currentState].init();
    }
    
    void draw(Graphics2D g) {
        states[currentState].draw(g);
    }

    void update() {
        states[currentState].update();
    }

    void keyTyped(int keyCode){
        states[currentState].keyTyped(keyCode);
    }
    
    void keyPressed(int keyCode) {
        states[currentState].keyPressed(keyCode);
    }

    void keyReleased(int keyCode) {
        states[currentState].keyReleased(keyCode);
    }

}
