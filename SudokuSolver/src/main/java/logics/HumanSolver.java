package logics;

import dataStructures.Map;
import dataStructures.Stack;
import java.util.ArrayList;
import java.util.List;
import ss.sudokusolver.Sudoku;


public class HumanSolver extends Solver {

    public HumanSolver(Sudoku sudoku) {
        super(sudoku);
    }
    
    public boolean solveWbt() {
        boolean resume = true;
        while (resume) {
            resume = candidateCheck() || placeFinding();
        }
        if (!sudoku.isSolved()) {
            BacktrackSolver bs = new BacktrackSolver(sudoku);
            bs.backtrack();
        }
        return sudoku.isSolved();
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
    
    private boolean setValuesWithOnlyOnePlace(int length, Map<Integer, List<Integer>> nums) {
        boolean changed = false;
        Stack keys = nums.keys();
        while (!keys.isEmpty()) {
            int k = keys.pop();
//        for (int k:nums.keySet()) {
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
    
    public boolean placeFindingRow(int length, Map<Integer, List<Integer>> nums) {
        boolean changed = false;
        Stack temp;
        for (int r = 0; r < length; r++) { //row
            nums.clear();
            for (int c = 0; c < length; c++) { //col
                if (sudoku.getNumber(r, c) == 0) {
                    temp = candidates(r, c);
                    while (!temp.isEmpty()) {
                        int k = temp.pop();
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(r * length + c);
                    }
                }
            }
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }
    
    public boolean placeFindingCol(int length, Map<Integer, List<Integer>> nums) {
        boolean changed = false;
        Stack temp;
        for (int c = 0; c < length; c++) { //col
            nums.clear();
            for (int r = 0; r < length; r++) { //row
                if (sudoku.getNumber(r, c) == 0) {
                    temp = candidates(r, c);
                    while (!temp.isEmpty()) {
                        int k = temp.pop();
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(r * length + c);
                    }
                }
            }    
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }
    
    public boolean placeFindingBox(int length, int squareSize, Map<Integer, List<Integer>> nums) {
        boolean changed = false;
        Stack temp;
        for (int i = 0; i < squareSize; i++) { //horizontal
            for (int j = 0; j < squareSize; j++) { //vertical
                nums.clear();
                for (int k = 0; k < squareSize; k++) {
                    for (int l = 0; l < squareSize; l++) {
                        int row = i * squareSize + k;
                        int col = j * squareSize + l;
                        if (sudoku.getNumber(row, col) == 0) {
                            temp = candidates(row, col);
                            while (!temp.isEmpty()) {
                                int m = temp.pop();
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
        Map<Integer, List<Integer>> nums = new Map();
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
        for (int r = 0; r < sudoku.getLength(); r++) { //row
            for (int c = 0; c < sudoku.getLength(); c++) { //col
                if (sudoku.getNumber(r, c) == 0) {
                    Stack temp = candidates(r, c);
                    if (temp.size() == 1) {
                        sudoku.setNumber(temp.pop(), r, c);
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }

}
