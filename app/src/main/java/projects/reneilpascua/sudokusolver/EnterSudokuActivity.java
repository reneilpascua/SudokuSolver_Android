package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import projects.reneilpascua.sudokusolver.board.Cell;
import projects.reneilpascua.sudokusolver.board.SudokuBoard;

public class EnterSudokuActivity extends AppCompatActivity {

    private GridView gv;
    private Cell[] cells;
    CellAdapter a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sudoku);

        cells = new Cell[81];
        getNewCells();
        a = new CellAdapter(this, cells, true);

        gv = (GridView) findViewById(R.id.gv_sudoku);
        refreshGrid();
    }

    private void getNewCells() {
        for (int i=0; i<81; i++) {
            cells[i] = new Cell();
        }
    }


    public void setCellsFromInput(View view) {
        for (int i=0; i<81; i++) {
            int a;
            try {
                String s = ((EditText) gv.getChildAt(i).findViewById(R.id.et_value)).getText().toString();
                if (s.isEmpty()) {
                    a=0;
                } else {
                    a=Integer.parseInt(s);
                    cells[i].isInit=true;
                }
            } catch (Exception e) {
                a=0;
            }
            cells[i].num = a;
        }

        Cell[][] board = convertTo2D(cells);
        SudokuBoard sb = new SudokuBoard();
        sb.setValues(board);
//        System.out.println(sb);

        Intent i = new Intent(this, SudokuSolutionActivity.class);
        i.putExtra("cells",cells);
        i.putExtra("sb",sb);
        startActivity(i);
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

    private void refreshGrid() {
        gv.setAdapter(a);
    }
}
