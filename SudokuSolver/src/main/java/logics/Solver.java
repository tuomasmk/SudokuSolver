package logics;

import java.util.ArrayList;
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
        for (int i = 0; i < sudoku.getLength(); i++) {
            for (int j = 0; j < sudoku.getLength(); j++) {
                for (int k = 0; k < sudoku.getLength(); k++) {
                    candidates[i][j][k] = 0;
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
    public ArrayList<Integer> candidates(int row, int col) {
        ArrayList<Integer> nums = new ArrayList();
        for (int i = 1; i <= sudoku.getLength(); i++) {
            if (sudoku.canPlace(i, row, col)) {
                nums.add(i);
            }
        }
        return nums;
    }
    
    public void findAllCandidates() {
        if (candidates == null) {
            initializeCandidates();
        }
        for (int i = 0; i < sudoku.getLength(); i++) {
            for (int j = 0; j < sudoku.getLength(); j++) {
                if (sudoku.getNumber(i, j) == 0) {
                    ArrayList<Integer> cs = candidates(i, j);
                    for (int candidate:cs) {
                        candidates[i][j][candidate - 1] = 1;
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