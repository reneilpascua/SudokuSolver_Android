package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import projects.reneilpascua.sudokusolver.board.Cell;
import projects.reneilpascua.sudokusolver.board.SudokuBoard;
import projects.reneilpascua.sudokusolver.solver.SudokuSolver;

public class SudokuSolutionActivity extends AppCompatActivity {

    SudokuBoard sb;
    Cell[] cells;
    RecyclerView rv;
    TextView solveStatus;
    CellAdapter2 a;
    final static int NUMBER_OF_COLUMNS = 9;
    String[] solnDetails;

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

        sb = new SudokuBoard();
        sb.setValues(convertTo2D(cells));

        SudokuSolver sbs = new SudokuSolver(sb);
        solnDetails = sbs.solve();

        updateSolution();
        solveStatus.setText("SOLVED!!");
    }

    private void updateSolution() {
        cells = convertTo1D(sb.cells);
        refreshGrid();
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

    public void enterNewSudoku(View view) {
        finish();
    }

    public void getDetails(View view) {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.solution_details_dialog);
        dialog.setTitle("a s d f");

        // set the custom dialog components - text, image and button
        TextView tv_solveStatus = (TextView) dialog.findViewById(R.id.tv_solveStatus);
        tv_solveStatus.setText(solnDetails[0]);
        TextView tv_numIter = (TextView) dialog.findViewById(R.id.tv_numIter);
        tv_numIter.setText("iterations: "+solnDetails[1]);
        TextView tv_numBT = (TextView) dialog.findViewById(R.id.tv_numBT);
        tv_numBT.setText("number of backtracks:"+ solnDetails[2]);

//        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//        // if button is clicked, close the custom dialog
//        dialogButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();
    }


}
