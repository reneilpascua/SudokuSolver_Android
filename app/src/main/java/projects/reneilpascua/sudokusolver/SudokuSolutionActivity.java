package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import projects.reneilpascua.sudokusolver.board.Cell;
import projects.reneilpascua.sudokusolver.board.SudokuBoard;
import projects.reneilpascua.sudokusolver.solver.SudokuSolver;

public class SudokuSolutionActivity extends AppCompatActivity {

    Cell[] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_solution);

        Intent i = getIntent();
        SudokuBoard sb = (SudokuBoard) i.getSerializableExtra("sb");

        System.out.println(sb);

        SudokuSolver sbs = new SudokuSolver(sb);

        sbs.solve();

        System.out.println(sb);
    }
}
