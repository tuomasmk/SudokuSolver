package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ss.sudokusolver.Sudoku;


public class Solver {
    Sudoku sudoku;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    
    public boolean humanSolver() {
        boolean resume = true;
        while (resume) {
            resume = candidateCheck() || placeFinding();
        }
        return sudoku.isSolved();
    }
    
    public boolean fillUsingPlaceFinding() {
        while (placeFinding());
        return sudoku.isSolved();
    }
    
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
    
    public boolean fillUsingCandidateCheck() {
        while(candidateCheck());
        return sudoku.isSolved();
    }
    
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
    
    public boolean backtrack() {
        int[] slot = sudoku.nextFreeSlot();
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

    public Sudoku getSudoku() {
        return sudoku;
    }
}
