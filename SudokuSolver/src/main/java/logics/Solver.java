package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ss.sudokusolver.Sudoku;

public class Solver {
    Sudoku sudoku;

    /**
     *Solver containing logic to solve sudoku.
     * @param sudoku
     */
    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    
    /**
     * Logic that uses human like methods to solve a sudoku.
     * @return  true if sudoku is solved
     *          false if the sudoku cannot be solved with this method.
     */
    public boolean humanSolver() {
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
    
    /**
     * Place-finding logic implementation.
     * Finds all candidates (numbers that may be entered in a cell)
     * in a row, column or box. Inserts numbers with only one available cell.
     * @return  true if a change has been made
     *          false if no change has been made
     *          i.e. sudoku cannot be solved with this method.
     */
    public boolean placeFinding() {
        boolean changed = false;
        HashMap<Integer, List<Integer>> nums = new HashMap();
        //Rows
        for (int i = 0; i < sudoku.getLength(); i++) {
            nums.clear();
            for (int j = 0; j < sudoku.getLength(); j++) {
                if (sudoku.getNumber(i, j) == 0) {
                    ArrayList<Integer> candidates = sudoku.candidates(i, j);
                    for (int k:candidates) {
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(j);
                    }
                }
            }
            for (int k:nums.keySet()) {
                if (nums.get(k) != null && nums.get(k).size() == 1) {
                    sudoku.setNumber(k, i, nums.get(k).get(0));
                    changed = true;
                }
            }
        }
        //Cols
        for (int j = 0; j < sudoku.getLength(); j++) {
            nums.clear();
            for (int i = 0; i < sudoku.getLength(); i++) {
                if (sudoku.getNumber(i, j) == 0) {
                    ArrayList<Integer> candidates = sudoku.candidates(i, j);
                    for (int k:candidates) {
                        if (!nums.containsKey(k)) {
                            nums.put(k, new ArrayList());
                        }
                        nums.get(k).add(i);
                    }
                }
            }    
            for (int k:nums.keySet()) {
                if (nums.get(k) != null && nums.get(k).size() == 1) {
                    sudoku.setNumber(k, nums.get(k).get(0), j);
                    changed = true;
                }
            }
        }
        //Boxes
        for (int i = 0; i < sudoku.getSquareSize(); i++) {
            for (int j = 0; j < sudoku.getSquareSize(); j++) {
                for (int k = 0; k < sudoku.getSquareSize(); k++) {
                    for (int l = 0; l < sudoku.getSquareSize(); l++) {
                        if (sudoku.getNumber(j * sudoku.getSquareSize() + k, i * sudoku.getSquareSize() + l) == 0) {
                            ArrayList<Integer> candidates = sudoku.candidates(j * sudoku.getSquareSize() + k, i * sudoku.getSquareSize() + l);
                            for (int m = 0; m < candidates.size(); m++) {
                                if (!nums.containsKey(candidates.get(m))) {
                                    nums.put(candidates.get(m), new ArrayList());
                                }
                                nums.get(candidates.get(m)).add(j * sudoku.getSquareSize() + k);
                            }
                            for (int m:nums.keySet()) {
                                if (nums.get(m).size() == 1) {
                                    sudoku.setNumber(m, i * sudoku.getSquareSize() + l, nums.get(m).get(0));
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        }
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
                    ArrayList<Integer> candidates = sudoku.candidates(i, j);
                    if (candidates.size() == 1) {
                        sudoku.setNumber(candidates.get(0), i, j);
                        changed = true;
                    }
                }
            }
        }
        return changed;
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
        sudoku.findAllCandidates();
        int[] cell = sudoku.nextFreeCell();
        if (cell[0] != -1) {
            for (int k = 0; k < sudoku.getLength(); k++) {
                if (sudoku.getCandidates()[cell[0]][cell[1]][k] != 0) {
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
    
    public boolean referenceGraph() {
        sudoku.initializeEmpty();
        sudoku.initializeGRM();
        return backtrackWRG(0);
    }
        
    public boolean backtrackWRG(int current) {
        if (current >= sudoku.getEmpty().length) {
            return true;
        }
        int row, col, slot, index, value;
        ArrayList<Integer> used = new ArrayList();
        slot = sudoku.getEmpty()[current];
        row = slot / sudoku.getLength();
        col = slot % sudoku.getLength();
        for (int k = 0; k < sudoku.getGraphReferencedMatrix()[0].length; k++) {
            index = sudoku.getGraphReferencedMatrix()[slot][k];
            value = sudoku.getNumber(index / sudoku.getLength(), index % sudoku.getLength());
            if (value != 0) {
                used.add(value);
            }
        }
        for (int i = 1; i <= sudoku.getLength(); i++) {
            if (!(used.contains(i))) {
                sudoku.setNumber(i, row, col);
                if (backtrackWRG(current + 1)) {
                    return true;
                } else {
                    sudoku.setNumber(0, row, col);
                }
            }
        }
        return false;
    }
    
    public void findPreemptiveSets() {
        
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
