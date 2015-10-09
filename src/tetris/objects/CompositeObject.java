package tetris.objects;

import java.util.Arrays;

public class CompositeObject {

    int posx;
    int posy;
    int[] anchorOffset;
    int[] oldAnchorOffset;
    BaseObject[][] components;
    BaseObject[][] componentsNew;
    BaseObject[][] grid;
    int[] rgb;

    public CompositeObject(int[][] pattern, BaseObject[][] grid, int x, int y) {
        this.grid = grid;
        this.posx = x;
        this.posy = y - pattern.length;
        anchorOffset = new int[]{0, 0};
        rgb = new int[]{
            (int) (Math.random() * 196) + 30,
            (int) (Math.random() * 196) + 30,
            (int) (Math.random() * 196) + 30
        };
        if(Math.random()<0.5)
            invert(pattern);
        components = new BaseObject[pattern.length][pattern[0].length];
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if (pattern[i][j] == 1) {
                    components[i][j] = new BaseObject(rgb);
                }
                if (pattern[i][j] == 2) {
                    components[i][j] = new BaseObject(rgb);
                    components[i][j].isAnchor = true;

                    anchorOffset[0] = i;
                    anchorOffset[1] = j;
                }
            }
        }
    }

    private void addToGrid(BaseObject[][] toAdd) {
        for (int i = 0; i < toAdd.length; i++) {
            for (int j = 0; j < toAdd[0].length; j++) {
                try {
                    if (toAdd[i][j] != null) {
                        grid[i + posy - anchorOffset[0]][j + posx - anchorOffset[1]] = toAdd[i][j];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }

    private void removeFromGrid(BaseObject[][] toRemove) {
        for (int i = 0; i < toRemove.length; i++) {
            for (int j = 0; j < toRemove[0].length; j++) {
                try {
                    if (toRemove[i][j] != null) {
                        grid[i + posy - anchorOffset[0]][j + posx - anchorOffset[1]] = null;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }

    private boolean checkCollision(BaseObject[][] toCheck) {
        for (int i = 0; i < toCheck.length; i++) {
            for (int j = 0; j < toCheck[0].length; j++) {
                if (toCheck[i][j] != null) {
                    try {
                        if (grid[i + posy - anchorOffset[0]][j + posx - anchorOffset[1]] != null) {
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveDown() {
        removeFromGrid(components);
        posy++;
        if (checkCollision(components)) {
            posy--;
            addToGrid(components);
            return true;
        }
        addToGrid(components);
        return false;
    }

    public void moveLeft() {
        removeFromGrid(components);
        posx--;
        if (checkCollision(components)) {
            posx++;
            addToGrid(components);
        }
        addToGrid(components);
    }

    public void moveRight() {
        removeFromGrid(components);
        posx++;
        if (checkCollision(components)) {
            posx--;
            addToGrid(components);
        }
        addToGrid(components);
    }

    public void rotateLeft() {
        removeFromGrid(components);
        componentsNew = new BaseObject[components[0].length][components.length];

        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[0].length; j++) {
                componentsNew[components[0].length - 1 - j][i] = components[i][j];
                try {
                    if (components[i][j].isAnchor) {
                        oldAnchorOffset = Arrays.copyOf(anchorOffset, 2);
                        anchorOffset[0] = components[0].length - 1 - j;
                        anchorOffset[1] = i;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
        if (!checkCollision(componentsNew)) {
            components = componentsNew;
        } else {
            anchorOffset = Arrays.copyOf(oldAnchorOffset, 2);
        }
        addToGrid(components);

    }

    public void rotateRight() {
        removeFromGrid(components);
        componentsNew = new BaseObject[components[0].length][components.length];

        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[0].length; j++) {
                componentsNew[j][components.length - 1 - i] = components[i][j];
                try {
                    if (components[i][j].isAnchor) {
                        oldAnchorOffset = Arrays.copyOf(anchorOffset, 2);
                        anchorOffset[0] = j;
                        anchorOffset[1] = components.length - 1 - i;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
        if (!checkCollision(componentsNew)) {
            components = componentsNew;
        } else {
            anchorOffset = Arrays.copyOf(oldAnchorOffset, 2);
        }
        addToGrid(components);
    }
    
    private void invert(int[][] pattern){
        int temp;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length/2; j++) {
                temp = pattern[i][j];
                pattern[i][j]= pattern[i][pattern[0].length-1-j];
                pattern[i][pattern[0].length-1-j] = temp;
            }
        }
    }
    
    public static void print(BaseObject[][] in) {
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                if (in[i][j] != null) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }
}
