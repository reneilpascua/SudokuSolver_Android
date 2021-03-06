package projects.reneilpascua.sudokusolver.board;

import java.io.Serializable;

public class SudokuBoard implements Serializable {
    public Cell[][] cells;
    
    public SudokuBoard() {
        cells = new Cell[9][9];
        initCells();
    }
    
    public void initCells() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setInit(int x, int y, int value) {
        if (value <= 0 || value >9) {
            cells[x][y].num = 0;
            cells[x][y].isInit = false;
        } else if (value <= 9) {
            cells[x][y].num = value;
            cells[x][y].isInit = true;
        }
    }
    
    public void setValues(int[][] in) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                setInit(i,j, in[i][j]);
            }
        }
    }

    public void setValues(Cell[][] in) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                setInit(i,j, in[i][j].num);
            }
        }
    }
    
    public void printBoard() {
        for (int i=0; i<9; i++) {
            if (i%3==0) System.out.print("\n");
            for (int j=0; j<9; j++) {
                if (j%3==0) System.out.print("  ");
                System.out.print(cells[i][j].num+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<9; i++) {
            if (i%3==0) s+="\n";
            for (int j=0; j<9; j++) {
                if (j%3==0) s+="  ";
                s+=cells[i][j].num+" ";
            }
            s+="\n";
        }
        s+="\n";

        return s;
    }
}
