package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import projects.reneilpascua.sudokusolver.board.Cell;
import projects.reneilpascua.sudokusolver.board.SudokuBoard;
import projects.reneilpascua.sudokusolver.solver.SudokuSolver;

public class SudokuSolutionActivity extends AppCompatActivity {

    Cell[] cells;
    RecyclerView rv;
    TextView solveStatus;
    CellAdapter2 a;
    final static int NUMBER_OF_COLUMNS = 9;
    final static int REFRESH_FRAME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_solution);

        Intent i = getIntent();
        cells = (Cell[]) i.getSerializableExtra("cells");
        print1DCells(cells);

        solveStatus = (TextView) findViewById(R.id.solveStatus);
        rv = (RecyclerView) findViewById(R.id.rv_solution);
        rv.setLayoutManager(new GridLayoutManager(this,NUMBER_OF_COLUMNS));

        refreshGrid();

        SudokuBoard sb = new SudokuBoard();
        sb.setValues(convertTo2D(cells));

        SudokuSolver sbs = new SudokuSolver(sb);
        sbs.solve();

        sb.printBoard();

        cells = convertTo1D(sb.cells);
        refreshGrid();

        solveStatus.setText("SOLVED!!");
    }

    private Cell[][] convertTo2D(Cell[] cells) {
        Cell[][] board = new Cell[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                int index = i*9 + j;
                board[i][j] = cells[index];
            }
        }
        return board;
    }
    private Cell[] convertTo1D(Cell[][] board) {
        Cell[] cells = new Cell[81];
        for (int i=0; i<81; i++) {
            int row = i/9;
            int col = i%9;
            cells[i] = board[row][col];
        }
        return cells;
    }
    private void print1DCells(Cell[] cells) {
        for (int i=0; i<81; i++) {
            System.out.print(cells[i]+", ");
        }
        System.out.print("\n");
    }

    private void refreshGrid() {
        a = new CellAdapter2(this, cells, true);
        rv.setAdapter(a);
    }
}
