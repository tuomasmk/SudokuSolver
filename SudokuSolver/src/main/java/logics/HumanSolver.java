package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ss.sudokusolver.Sudoku;


public class HumanSolver extends Solver {

    public HumanSolver(Sudoku sudoku) {
        super(sudoku);
    }
    
        /**
     * Logic that uses human like methods to solve a sudoku.
     * @return  true if sudoku is solved
     *          false if the sudoku cannot be solved with this method.
     */
    public boolean solve() {
        boolean resume = true;
        while (resume) {
            resume = candidateCheck() || placeFinding();
        }
        return sudoku.isSolved();
    }
    
    /**
     * Solves sudoku using place-finding method repeatedly.
     * @return true if sudoku is solved
     *          false if the sudoku cannot be solved with this method.
     */
    public boolean fillUsingPlaceFinding() {
        while (placeFinding());
        return sudoku.isSolved();
    }
    
    private boolean setValuesWithOnlyOnePlace(int length, HashMap<Integer, List<Integer>> nums) {
        boolean changed = false;
        for (int k:nums.keySet()) {
            if (nums.get(k) != null && nums.get(k).size() == 1) {
                int value = nums.get(k).get(0);
                int row = value / length;
                int col = value % length;
                sudoku.setNumber(k, row, col);
                changed = true;
            }
        }
        return changed;
    }
    
    public boolean placeFindingRow(int length, HashMap<Integer, List<Integer>> nums) {
        boolean changed = false;
        ArrayList<Integer> temp;
        for (int i = 0; i < length; i++) {
            nums.clear();
            for (int j = 0; j < length; j++) {
                if (sudoku.getNumber(i, j) == 0) {
                    temp = candidates(i, j);
                    for (int k:temp) {
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(i * length + j);
                    }
                }
            }
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }
    
    public boolean placeFindingCol(int length, HashMap<Integer, List<Integer>> nums) {
        boolean changed = false;
        ArrayList<Integer> temp;
        for (int j = 0; j < length; j++) {
            nums.clear();
            for (int i = 0; i < length; i++) {
                if (sudoku.getNumber(i, j) == 0) {
                    temp = candidates(i, j);
                    for (int k:temp) {
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(i * length + j);
                    }
                }
            }    
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }
    
    public boolean placeFindingBox(int length, int squareSize, HashMap<Integer, List<Integer>> nums) {
        boolean changed = false;
        ArrayList<Integer> temp;
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                nums.clear();
                for (int k = 0; k < squareSize; k++) {
                    for (int l = 0; l < squareSize; l++) {
                        int row = i * squareSize + k;
                        int col = j * squareSize + l;
                        if (sudoku.getNumber(row, col) == 0) {
                            temp = candidates(row, col);
                            for (int m:temp) {
                                if (!nums.containsKey(m)) {
                                    nums.put(m, new ArrayList());
                                }
                                nums.get(m).add(row * length + col);
                            }
                        }
                    }
                }
                changed = setValuesWithOnlyOnePlace(length, nums) || changed;
            }
        }
        return changed;
    }
    
    /**
     * Place-finding logic implementation.
     * Finds all candidates (numbers that may be entered in a cell)
     * in a row, column or box. Inserts numbers with only one available cell.
     * @return  true if a change has been made
     *          false if no change has been made
     *          i.e. sudoku cannot be solved with this method.
     */
    public boolean placeFinding() {
        boolean changed;
        HashMap<Integer, List<Integer>> nums = new HashMap();
        //Rows
        changed = placeFindingRow(sudoku.getLength(), nums);
        //Cols
        changed = placeFindingCol(sudoku.getLength(), nums) || changed;
        //Boxes
        changed = placeFindingBox(sudoku.getLength(), sudoku.getSquareSize(), nums) || changed;
        return changed;
    }
    
    /**
     * Solves sudoku with candidate-check method.
     * @return  true if sudoku was solved
     *          false if sudoku cannot be solved with this method.
     */
    public boolean fillUsingCandidateCheck() {
        while (candidateCheck());
        return sudoku.isSolved();
    }
    
    /**
     * Candidate-check logic implementation.
     * Finds all candidates (numbers that may be entered in a cell).
     * If only candidate is present, it is inserted in that cell.
     * @return  true if a change has been made
     *          false if no change has been made
     *          i.e. sudoku cannot be solved with this method.
     */
    public boolean candidateCheck() {
        boolean changed = false;
        for (int i = 0; i < sudoku.getLength(); i++) {
            for (int j = 0; j < sudoku.getLength(); j++) {
                if (sudoku.getNumber(i, j) == 0) {
                    ArrayList<Integer> candidates = candidates(i, j);
                    if (candidates.size() == 1) {
                        sudoku.setNumber(candidates.get(0), i, j);
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }

}
