package logics;

import dataStructures.IntList;
import dataStructures.Map;
import dataStructures.IntStack;
import ss.sudokusolver.Sudoku;

public class HumanSolver extends Solver {

    public HumanSolver(Sudoku sudoku) {
        super(sudoku);
    }

    public HumanSolver() {
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

    public boolean solveWdl() {
        boolean resume = true;
        while (resume) {
            resume = candidateCheck() || placeFinding();
        }
        if (!sudoku.isSolved()) {
            DancingLinksSolver dls = new DancingLinksSolver(sudoku);
            dls.solve();
        }
        return sudoku.isSolved();
    }
    
    /**
     * Logic that uses human like methods to solve a sudoku.
     *
     * @return true if sudoku is solved false if the sudoku cannot be solved
     * with this method.
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
     *
     * @return true if sudoku is solved false if the sudoku cannot be solved
     * with this method.
     */
    public boolean fillUsingPlaceFinding() {
        while (placeFinding());
        return sudoku.isSolved();
    }

    /**
     * Inserts values with only one possible place.
     *
     * @param length size of sudoku
     * @param nums numbers and possible places
     * @return
     */
    private boolean setValuesWithOnlyOnePlace(int length, Map<Integer, IntList> nums) {
        boolean changed = false;
        IntStack keys = nums.keys();
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

    /**
     * Add possible slots to the map
     *
     * @param length size of sudoku.
     * @param row
     * @param col
     * @param nums numbers and possible places
     */
    private void populateMap(int length, int row, int col, Map<Integer, IntList> nums) {
        if (sudoku.getNumber(row, col) == EMPTY_CELL) {
            IntStack temp = candidates(row, col);
            while (!temp.isEmpty()) {
                int k = temp.pop();
                if (!nums.containsKey(k)) {
                    nums.put(k, new IntList(length));
                }
                nums.get(k).add(row * length + col);
            }
        }
    }

    private boolean placeFindingRow(int length, Map<Integer, IntList> nums) {
        boolean changed = false;
        for (int r = 0; r < length; r++) { //row
            nums.clear();
            for (int c = 0; c < length; c++) { //col
                populateMap(length, r, c, nums);
            }
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }

    private boolean placeFindingCol(int length, Map<Integer, IntList> nums) {
        boolean changed = false;
        for (int c = 0; c < length; c++) { //col
            nums.clear();
            for (int r = 0; r < length; r++) { //row
                populateMap(length, r, c, nums);
            }
            changed = setValuesWithOnlyOnePlace(length, nums) || changed;
        }
        return changed;
    }

    private boolean placeFindingBox(int length, int squareSize, Map<Integer, IntList> nums) {
        boolean changed = false;
        for (int i = 0; i < squareSize; i++) { //horizontal
            for (int j = 0; j < squareSize; j++) { //vertical
                nums.clear();
                for (int k = 0; k < squareSize; k++) {
                    for (int l = 0; l < squareSize; l++) {
                        int row = i * squareSize + k;
                        int col = j * squareSize + l;
                        populateMap(length, row, col, nums);
                    }
                }
                changed = setValuesWithOnlyOnePlace(length, nums) || changed;
            }
        }
        return changed;
    }

    /**
     * Place-finding logic implementation. Finds all candidates (numbers that
     * may be entered in a cell) in a row, column or box. Inserts numbers with
     * only one available cell.
     *
     * @return true if a change has been made false if no change has been made
     * i.e. sudoku cannot be solved with this method.
     */
    public boolean placeFinding() {
        boolean changed;
        Map<Integer, IntList> nums = new Map();
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
     *
     * @return true if sudoku was solved false if sudoku cannot be solved with
     * this method.
     */
    public boolean fillUsingCandidateCheck() {
        while (candidateCheck());
        return sudoku.isSolved();
    }

    /**
     * Candidate-check logic implementation. Finds all candidates (numbers that
     * may be entered in a cell). If only candidate is present, it is inserted
     * in that cell.
     *
     * @return true if a change has been made false if no change has been made
     * i.e. sudoku cannot be solved with this method.
     */
    public boolean candidateCheck() {
        boolean changed = false;
        for (int r = 0; r < sudoku.getLength(); r++) { //row
            for (int c = 0; c < sudoku.getLength(); c++) { //col
                if (sudoku.getNumber(r, c) == EMPTY_CELL) {
                    IntStack temp = candidates(r, c);
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
