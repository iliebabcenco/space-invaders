public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
    public void setPoint(double x, double y, char c) {
        int posX = (int) Math.round(x);
        int posY = (int) Math.round(y);
        if (0 <= posX && posX < matrix[0].length && 0 <= posY && posY < matrix.length) {
            matrix[posY][posX] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }
    public void clear() {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                setPoint(j, i, ' ');
            }
        }
    }

    public void print() {
        for (int i = 0; i < matrix.length; i ++) {
            System.out.println("\n");
            for (int j = 0; j < matrix[i].length; j ++) {
                System.out.println(matrix[j][i]);
            }
        }
    }

}
