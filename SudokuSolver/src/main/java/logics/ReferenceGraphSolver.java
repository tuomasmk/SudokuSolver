package logics;

import java.util.ArrayList;
import ss.sudokusolver.Sudoku;


public class ReferenceGraphSolver extends Solver {
    int[][] graphReferencedMatrix;

    public ReferenceGraphSolver(Sudoku sudoku) {
        super(sudoku);
    }

    public ReferenceGraphSolver() {
    }
    
    public void initializeGRM() {
        graphReferencedMatrix = new int[sudoku.getLength() * sudoku.getLength()][(sudoku.getLength() - 1) * 2 + (sudoku.getSquareSize() - 1) * (sudoku.getSquareSize() - 1)];
        int row, col, count, p, startCol, startRow;
        for (int k = 0; k < sudoku.getLength() * sudoku.getLength(); k++) {
            row = k / sudoku.getLength();
            col = k % sudoku.getLength();
            count = 0;
            for (int j = 0; j < sudoku.getLength(); j++) {
                p = row * sudoku.getLength() + j;
                if (!(row * sudoku.getLength() + col == p)) {
                    graphReferencedMatrix[k][count] = p;
                    count++;
                }
            }
            for (int i = 0; i < sudoku.getLength(); i++) {
                p = i * sudoku.getLength() + col;
                if (!(row * sudoku.getLength() + col == p)) {
                    graphReferencedMatrix[k][count] = p;
                    count++;
                }
            }
            startCol = col / sudoku.getSquareSize() * sudoku.getSquareSize();
            startRow = row / sudoku.getSquareSize() * sudoku.getSquareSize();
            for (int i = startRow; i < startRow + sudoku.getSquareSize(); i++) {
                for (int j = startCol; j < startCol + sudoku.getSquareSize(); j++) {
                    p = i * sudoku.getLength() + j;
                    if (!(row * sudoku.getLength() + col == p || i == row || j == col)) {
                        graphReferencedMatrix[k][count] = p;
                        count++;
                    }
                }
            }
        }
    }
    
    public boolean solve() {
        sudoku.initializeEmpty();
        initializeGRM();
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
        for (int k = 0; k < graphReferencedMatrix[0].length; k++) {
            index = graphReferencedMatrix[slot][k];
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
}
