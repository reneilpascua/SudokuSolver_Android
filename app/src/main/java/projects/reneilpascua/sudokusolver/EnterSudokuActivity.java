package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import projects.reneilpascua.sudokusolver.board.Cell;

public class EnterSudokuActivity extends AppCompatActivity {

    private GridView gv;
    private Cell[] cells;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sudoku);

        cells = new Cell[81];
        getNewCells();

        gv = (GridView) findViewById(R.id.gv_sudoku);
        refreshGrid();
    }

    private void getNewCells() {
        for (int i=0; i<81; i++) {
            cells[i] = new Cell();
        }
    }

    public void btnfn(View view) {
        cells[0].num++;
        refreshGrid();
    }

    private void refreshGrid() {
        CellAdapter a = new CellAdapter(EnterSudokuActivity.this, cells, false);
        gv.setAdapter(a);
    }
}
