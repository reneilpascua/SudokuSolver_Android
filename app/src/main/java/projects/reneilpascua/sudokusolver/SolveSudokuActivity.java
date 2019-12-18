package projects.reneilpascua.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class SolveSudokuActivity extends AppCompatActivity {

    EditText[][] ets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_sudoku);

        ets = new EditText[9][9];
    }

    private void findEditTextsById() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                // ets[i][j] = (EditText) findViewById(...);
            }
        }
    }
}
