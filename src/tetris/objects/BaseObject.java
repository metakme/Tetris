package tetris.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BaseObject {

    int width;
    int height;
    int posx;
    int posy;
    Color light;
    Color mid;
    Color dark;
    public boolean isAnchor;

    public BaseObject(int[] rgb) {
        isAnchor = false;
        width = tetris.Tetris.width / 10;
        height = width;

        light = new Color(rgb[0] + 20, rgb[1] + 20, rgb[2] + 20);
        mid = new Color(rgb[0], rgb[1], rgb[2]);
        dark = new Color(rgb[0] - 20, rgb[1] - 20, rgb[2] - 20);
    }

    public BaseObject() {
        this(new int[]{
            (int) (Math.random() * 196) + 30,
            (int) (Math.random() * 196) + 30,
            (int) (Math.random() * 196) + 30
        });
    }

    public void draw(Graphics2D g, int py, int px) {

        this.posy = py * height;
        this.posx = px * width;
        g.setColor(light);
        g.fillRect(posx, posy, width, height);
        g.setColor(dark);
        int[] x = {posx, posx, posx + width};
        int[] y = {posy, posy + height, posy + height};
        g.fillPolygon(x, y, 3);
        g.setColor(mid);
        g.fillRect(posx + 7, posy + 7, width - 14, height - 14);
        g.setColor(light);
        g.drawRect(posx + 7, posy + 7, width - 14, height - 14);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        g.drawRect(posx, posy, width, height);
        g.setStroke(new BasicStroke(1));
        if (isAnchor) {
            g.setColor(light);
            g.fillRect(posx + 8, posy + 8, width - 15, height - 15);
            g.setColor(dark);
            x = new int[]{posx + 7, posx + width - 7, posx + width - 7};
            y = new int[]{posy + 7, posy + 7, posy + height - 7};
            g.fillPolygon(x, y, 3);
            g.setColor(mid);
            g.fillRect(posx + 11, posy + 12, width - 22, height - 22);
//            g.setColor(light);
//            g.drawRect(posx + 12, posy + 12, width - 24, height - 24);
        }
    }

    public void moveDown() {
        posy += height;
    }

    public void moveUp() {
        posy -= height;
    }
}
