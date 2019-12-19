package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import projects.reneilpascua.sudokusolver.board.Cell;

public class EnterSudokuActivity2 extends AppCompatActivity {

    Cell[] cells;
    RecyclerView rv;
    CellAdapter2 a;

    final static int NUMBER_OF_COLUMNS =9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sudoku2);

        cells = new Cell[81];
        getNewCells();

        rv = (RecyclerView) findViewById(R.id.rv_sudoku);
        rv.setLayoutManager(new GridLayoutManager(this,NUMBER_OF_COLUMNS));

        refreshGrid();
    }

    private void getNewCells() {
        for (int i=0; i<81; i++) {
            cells[i] = new Cell();
        }
    }

    private void refreshGrid() {
        a = new CellAdapter2(this, cells, false);
        rv.setAdapter(a);
    }

    public void goToSolution(View view) {
        for (int i=0; i<81; i++) {
            int a;
            try {
                String s = ((EditText) rv.getChildAt(i).findViewById(R.id.et_value)).getText().toString();
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

        Intent i = new Intent(this, SudokuSolutionActivity.class);
        i.putExtra("cells",cells);
        startActivity(i);
    }

    public void clearBoard(View view) {
        cells = new Cell[81];
        refreshGrid();
    }
}
