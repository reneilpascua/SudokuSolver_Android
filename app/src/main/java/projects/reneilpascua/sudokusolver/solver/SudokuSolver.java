package projects.reneilpascua.sudokusolver.solver;

//import android.widget.EditText;
import projects.reneilpascua.sudokusolver.board.Cell;
import projects.reneilpascua.sudokusolver.board.SudokuBoard;

public class SudokuSolver {

    private SudokuBoard sb;

    public SudokuSolver(SudokuBoard sb) {
        setSudokuBoard(sb);
    }
    public void setSudokuBoard(SudokuBoard sb) {
        this.sb = sb;
    }

    public String[] solve() {

        String[] solnDetails = new String[3];

        boolean solved = false;
        int numBT = 0;
        int[] pos = {0,0};
        int iter = 0;
        try {
            // solve loop
            while (true) {
                System.out.println("iter #" + iter);
                // 1.) go to next non-init cell, *including current*
                forward(pos);
                System.out.println("position: "+pos[0]+", "+pos[1]);
                
                // 2.) increment that cell
                boolean incremented = increment(pos);

                // 2_1.) update the value in the GUI
//                ets[pos[0]][pos[1]].setText( Integer.toString(sb.cells[pos[0]][pos[1]].num) );

                // 3a.) if we had to reset value to 0, backtrack to non-init cell
                if (!incremented) {
                    prevPos(pos);
                    backtrack(pos);
                    System.out.println("========= B A C K T R A C K =========");
                    numBT++;
                    // skip to next iteration
                    continue;
                }

                // 3b.) else, check to see if incr'd value gives valid board
                if (isValid()) {

                // 4.) if valid, go to the next position
                    nextPos(pos);
                }

                // 5.) else, proceed to next iteration (position will stay the same)
                iter++;
                
                if (iter%10 == 0) {
                    sb.printBoard();
                }
                
            }

        } catch (SolvedException e) {
            System.out.println("\nSolved!");
            System.out.println("number of backtracks: "+numBT);
            sb.printBoard();

            solved=true;
        } catch (Exception e) { // any other caught exceptions terminate the algorithm
            System.out.println("Problem occured; Algorithm terminated.");
            e.printStackTrace();

            solved=false;
        }

        if (solved) {
            solnDetails[0] = "solved!";
            solnDetails[1] = Integer.toString(iter);
            solnDetails[2] = Integer.toString(numBT);

        } else {
            solnDetails[0] = "no solution found";
            for (int i=1; i<solnDetails.length; i++) {
                solnDetails[i] = "n/a";
            }
        }

        return solnDetails;
    }

    private boolean increment(int[] pos) {
        if (sb.cells[pos[0]][pos[1]].num == 9) {
            sb.cells[pos[0]][pos[1]].num = 0;
            return false;
        } else {
            sb.cells[pos[0]][pos[1]].num++;
            return true;
        }
    }

//    private void enableEditTexts(EditText[][] ets, boolean e) {
//        for (int i=0; i<ets.length; i++) {
//            for (EditText et:ets[i]) {
//                et.setEnabled(e);
//            }
//        }
//    }


//
// helper methods to step through the backtracking algorithm
//


    private void forward(int[] pos) throws Exception {
        Cell cur = sb.cells[pos[0]][pos[1]];
        // go forward until non-init cell
        while (cur.isInit) {
            try {
                nextPos(pos);
                cur = sb.cells[pos[0]][pos[1]];
            } catch (SolvedException e) {
                throw new SolvedException();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Error in forward method.");
            }
        }
    }
    private void backtrack(int[] pos) throws Exception {
        Cell cur = sb.cells[pos[0]][pos[1]];
        while (cur.isInit) {
            try {
                prevPos(pos);
                cur = sb.cells[pos[0]][pos[1]];
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Algorithm terminated.");
            }
        }
    }
    private void nextPos(int[] pos) throws SolvedException {
        if (pos[0]==8 && pos[1]==8) {
            // last cell, trying to move forward means solution has been found
            throw new SolvedException();
        } else if (pos[1]==8){
            pos[0]++;
            pos[1]=0;
        } else {
            pos[1]++;
        }
    }
    private void prevPos(int[] pos) throws Exception {
        if (pos[0]==0 && pos[1]==0) {
            throw new Exception("Attempt to backtrack before first cell."); // can't go back further
        } else if (pos[1]==0){
            pos[0]--;
            pos[1]=8;
        } else {
            pos[1]--;
        }
    }


//
// methods which check the validity of the current solution
//

    private boolean isValid() {
        System.out.println("validation conflicts (if any):");
        return (checkRows()
                && checkCols()
                && checkSubs()
        );
    }
    private boolean checkRows() {
        for (int i=0; i<9; i++) {
            int[] check = new int[9];
            for (int j=0; j<9; j++) {
                int val = sb.cells[i][j].num;
                if (val==0) {
                    continue;
                } else if (check[val-1] == 1) {
                    System.out.println("\trow conflict at row "+i);
                    return false;
                } else {
                    check[val-1]=1;
                }
            }
        }
        return true;
    }
    private boolean checkCols() {
        for (int i=0; i<9; i++) {
            int[] check = new int[9];
            for (int j=0; j<9; j++) {
                int val = sb.cells[j][i].num;
                if (val==0) {
                    continue;
                } else if (check[val-1] == 1) {
                    System.out.println("\tcol conflict at col "+i);
                    return false;
                } else {
                    check[val-1]=1;
                }
            }
        }
        return true;
    }
    private boolean checkSubs() {
        for (int sub=0; sub<9; sub++) {
            int[] check = new int[9];
            int startRow = 3*(sub/3);
            int startCol = 3*(sub%3);
            for (int i=startRow; i<(startRow+3); i++) {
                for (int j=startCol; j<(startCol+3); j++) {
                    int val = sb.cells[i][j].num;
                    if (val==0) {
                        continue;
                    } else if (check[val-1]==1) {
                        System.out.println("\tsub conflict at sub "+i);
                        return false;
                    } else {
                        check[val-1]=1;
                    }
                }
            }
        }
        return true;
    }
}
