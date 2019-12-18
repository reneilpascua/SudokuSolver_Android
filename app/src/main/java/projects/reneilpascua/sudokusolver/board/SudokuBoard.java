package projects.reneilpascua.sudokusolver.board;

public class SudokuBoard {
    public Square [][] squares;
    
    public SudokuBoard() {
        squares = new Square[9][9];
        initSquares();
    }
    
    public void initSquares() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    public void setInit(int x, int y, int value) {
        if (value <= 0) {
            squares[x][y].num = 0;
            squares[x][y].isInit = false;
        } else if (value <= 9) {
            squares[x][y].num = value;
            squares[x][y].isInit = true;
        }
    }
    
    public void setValues(int[][] in) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                setInit(i,j, in[i][j]);
            }
        }
    }
    
    public void printBoard() {
        for (int i=0; i<9; i++) {
            if (i%3==0) System.out.print("\n");
            for (int j=0; j<9; j++) {
                if (j%3==0) System.out.print("  ");
                System.out.print(squares[i][j].num+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
