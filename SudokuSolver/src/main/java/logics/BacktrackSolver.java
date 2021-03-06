package logics;

import ss.sudokusolver.Sudoku;


public class BacktrackSolver extends Solver {

    public BacktrackSolver(Sudoku sudoku) {
        super(sudoku);
    }

    public BacktrackSolver() {
    }
    
    public boolean solve() {
        return backtrack(0, 0);
    }
    
    public boolean backtrack(int x, int y) {
        if (x == sudoku.getLength() - 1 && y == sudoku.getLength()) {
            return true;
        } else {
            if (y == sudoku.getLength()) {
                x++;
                y = 0;
            }
            if (sudoku.getNumber(x, y) != EMPTY_CELL) {
                return backtrack(x, y + 1);
            } else {
                for (int num = 1; num <= sudoku.getLength(); num++) {
                    if (sudoku.canPlace(num, x, y)) {
                        sudoku.setNumber(num, x, y);
                        if (backtrack(x, y + 1)) {
                            return true;
                        } else {
                            sudoku.setNumber(EMPTY_CELL, x, y);
                        }
                    }
                }
                return false;
            }
        }
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
    
    public boolean btwe() {
        sudoku.initializeEmpty();
        return backtrackWEmpty(0);
    }
    
    /**
     * Backtrack algorithm that iterates through
     * an array of empty cells.
     * @param current
     * @return 
     */
    private boolean backtrackWEmpty(int current) {
        if (current >= sudoku.getEmpty().length) {
            return true;
        }
        int slot = sudoku.getEmpty()[current];
        int row = slot / sudoku.getLength();
        int col = slot % sudoku.getLength();
        for (int i = 1; i <= sudoku.getLength(); i++) {
            if (sudoku.canPlace(i, row, col)) {
                sudoku.setNumber(i, row, col);
                if (backtrackWEmpty(current + 1)) {
                    return true;
                } else {
                    sudoku.setNumber(EMPTY_CELL, row, col);
                }
            }
        }
        return false;
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
    private boolean backtractWithCandidates() {
        int[] cell = sudoku.nextFreeCell();
        if (cell[0] != -1) {
            for (int k = 0; k < sudoku.getLength(); k++) {
                if (getCandidates()[cell[0]][cell[1]][k] != EMPTY_CELL) {
                    if (sudoku.canPlace(k + 1, cell[0], cell[1])) {
                        sudoku.setNumber(k + 1, cell[0], cell[1]);
                        if (backtractWithCandidates()) {
                            return true;
                        } else {
                            sudoku.setNumber(EMPTY_CELL, cell[0], cell[1]);
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

}
