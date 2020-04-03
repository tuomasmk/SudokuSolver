/*
 */

package logics;

import java.util.ArrayList;
import ss.sudokusolver.Sudoku;


public class BacktrackSolver extends Solver {

    public BacktrackSolver(Sudoku sudoku) {
        super(sudoku);
    }
    
        /**
     * Backtracking logic implementation.
     * Tries all numbers to every empty cell in order.
     * After filling in a number checks if all (sudoku rule) conditions are met.
     *  - if yes, then proceeds to the next cell recursively.
     *  - if no, then tries a bigger number 
     *  - or returns false and increases an earlier filled cell.
     * @return  true if the sudoku is solved
     *          false if the sudoku cannot be solved.
     */
    public boolean backtrack() {
        int[] slot = sudoku.nextFreeCell();
        if (slot[0] != -1) {
            for (int i = 1; i <= sudoku.getLength(); i++) {
                if (sudoku.canPlace(i, slot[0], slot[1])) {
                    sudoku.setNumber(i, slot[0], slot[1]);
                    if (backtrack()) {
                        return true;
                    } else {
                        sudoku.setNumber(0, slot[0], slot[1]);
                    }
                }
            }
            return false;
        }
        return true;
    }
    
        public boolean btwc() {
        findAllCandidates();
        return backtractWithCandidates();
    }

    /**
     * Backtracking logic implementation.
     * Tries all possible numbers to every empty cell in order.
     * After filling in a number checks if all (sudoku rule) conditions are met.
     *  - if yes, then proceeds to the next cell recursively.
     *  - if no, then tries a bigger number 
     *  - or returns false and increases an earlier filled cell.
     * @return  true if the sudoku is solved
     *          false if the sudoku cannot be solved.
     */
    public boolean backtractWithCandidates() {
        int[] cell = sudoku.nextFreeCell();
        if (cell[0] != -1) {
            for (int k = 0; k < sudoku.getLength(); k++) {
                if (getCandidates()[cell[0]][cell[1]][k] != 0) {
                    if (sudoku.canPlace(k + 1, cell[0], cell[1])) {
                        sudoku.setNumber(k + 1, cell[0], cell[1]);
                        if (backtractWithCandidates()) {
                            return true;
                        } else {
                            sudoku.setNumber(0, cell[0], cell[1]);
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

}
