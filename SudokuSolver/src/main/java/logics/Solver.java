package logics;

import dataStructures.Stack;
import ss.sudokusolver.Sudoku;

public class Solver {
    Sudoku sudoku;
    int[][][] candidates;

    /**
     *Solver containing logic to solve sudoku.
     * @param sudoku
     */
    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    
    private void initializeCandidates() {
        candidates = new int[sudoku.getLength()][sudoku.getLength()][sudoku.getLength()];
        for (int r = 0; r < sudoku.getLength(); r++) { //row
            for (int c = 0; c < sudoku.getLength(); c++) { //col
                for (int k = 0; k < sudoku.getLength(); k++) {
                    candidates[r][c][k] = 0;
                }
            }
        }
    }
    
    /**
     * Finds all candidates (numbers that may be filled in a given cell)
     * @param row 
     * @param col
     * @return  candidates.
     */
    public Stack candidates(int row, int col) {
        Stack nums = new Stack();
        for (int i = 1; i <= sudoku.getLength(); i++) {
            if (sudoku.canPlace(i, row, col)) {
                nums.push(i);
            }
        }
        return nums;
    }
    
    public void findAllCandidates() {
        if (candidates == null) {
            initializeCandidates();
        }
        for (int r = 0; r < sudoku.getLength(); r++) { //row
            for (int c = 0; c < sudoku.getLength(); c++) { //col
                if (sudoku.getNumber(r, c) == 0) {
                    Stack cs = candidates(r, c);
                    while (!cs.isEmpty()) {
                        int candidate = cs.pop();
                        candidates[r][c][candidate - 1] = 1;
                    }
                }
            }
        }
    }
    
    /**
     * Array containing candidates for all cells.
     * @return array of all candidates, might not have been initialised.
     */
    public int[][][] getCandidates() {
        return candidates;
    }
    
    /**
     *
     * @return
     */
    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
