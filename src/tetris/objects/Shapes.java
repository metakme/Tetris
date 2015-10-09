package tetris.objects;

public class Shapes {

    public static final int[][] LShape = {
        {0, 1},
        {0, 2},
        {1, 1}
    };
    public static final int[][] SShape = {
        {1, 1, 0},
        {0, 2, 1}
    };
    public static final int[][] IShape = {
        {1},
        {1},
        {2},
        {1}
    };
    public static final int[][] SqShape = {
        {1, 1},
        {1, 1}
    };
    public static final int[][] TShape = {
        {0, 1, 0},
        {1, 2, 1}
    };

    public static int[][] randomShape() {
        int[][] ret = LShape;
        switch ((int) (Math.random() * 5)) {
            case 0:
                ret = LShape;
                break;
            case 1:
                ret = SShape;
                break;
            case 2:
                ret = IShape;
                break;
            case 3:
                ret = SqShape;
                break;
            case 4:
                ret = TShape;
                break;
        }
        return ret;
    }
    public static int getBottomSpace(int[][] pattern){
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if(pattern[i][j]==2)
                    return pattern.length-i-1;
            }
        }
        return pattern.length-1;
    }
    
    public static int getTopSpace(int[][] pattern){
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if(pattern[i][j]==2)
                    return i;
            }
        }
        return 0;
    }
    
    public static int getLeftSpace(int[][] pattern){
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if(pattern[i][j]==2)
                    return j;
            }
        }
        return 0;
    }
}
