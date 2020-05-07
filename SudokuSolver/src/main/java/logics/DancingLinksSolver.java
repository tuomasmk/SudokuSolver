package logics;

import dataStructures.DLX;
import dataStructures.DancingNode;
import dataStructures.NodeStack;
import java.util.Arrays;
import ss.sudokusolver.Sudoku;

public class DancingLinksSolver extends Solver {

    // 4 constraints : cell, line, column, boxes
    private static final int CONSTRAINTS = 4;

    public DancingLinksSolver(Sudoku sudoku) {
        super(sudoku);
    }

    public DancingLinksSolver() {
    }

    // Index in the cover matrix
    private int indexInCoverMatrix(int row, int column, int num) {
        return (row - 1) * sudoku.getLength() * sudoku.getLength() + (column - 1) * sudoku.getLength() + (num - 1);
    }

    // Building of an empty cover matrix
    private int[][] createCoverMatrix() {
        int[][] coverMatrix = new int[sudoku.getLength() * sudoku.getLength() * sudoku.getLength()][sudoku.getLength() * sudoku.getLength() * CONSTRAINTS];

        int header = 0;
        header = createCellConstraints(coverMatrix, header);
        header = createRowConstraints(coverMatrix, header);
        header = createColumnConstraints(coverMatrix, header);
        createBoxConstraints(coverMatrix, header);

        return coverMatrix;
    }

    private int createBoxConstraints(int[][] matrix, int header) {
        for (int row = 1; row <= sudoku.getLength(); row += sudoku.getSquareSize()) {
            for (int column = 1; column <= sudoku.getLength(); column += sudoku.getSquareSize()) {
                for (int n = 1; n <= sudoku.getLength(); n++, header++) {
                    for (int rowDelta = 0; rowDelta < sudoku.getSquareSize(); rowDelta++) {
                        for (int columnDelta = 0; columnDelta < sudoku.getSquareSize(); columnDelta++) {
                            int index = indexInCoverMatrix(row + rowDelta, column + columnDelta, n);
                            matrix[index][header] = 1;
                        }
                    }
                }
            }
        }
        return header;
    }

    private int createColumnConstraints(int[][] matrix, int header) {
        for (int column = 1; column <= sudoku.getLength(); column++) {
            for (int n = 1; n <= sudoku.getLength(); n++, header++) {
                for (int row = 1; row <= sudoku.getLength(); row++) {
                    int index = indexInCoverMatrix(row, column, n);
                    matrix[index][header] = 1;
                }
            }
        }
        return header;
    }

    private int createRowConstraints(int[][] matrix, int header) {
        for (int row = 1; row <= sudoku.getLength(); row++) {
            for (int n = 1; n <= sudoku.getLength(); n++, header++) {
                for (int column = 1; column <= sudoku.getLength(); column++) {
                    int index = indexInCoverMatrix(row, column, n);
                    matrix[index][header] = 1;
                }
            }
        }

        return header;
    }

    private int createCellConstraints(int[][] matrix, int header) {
        for (int row = 1; row <= sudoku.getLength(); row++) {
            for (int column = 1; column <= sudoku.getLength(); column++, header++) {
                for (int n = 1; n <= sudoku.getLength(); n++) {
                    int index = indexInCoverMatrix(row, column, n);
                    matrix[index][header] = 1;
                }
            }
        }
        return header;
    }

    // Converting DancingLinksSudoku grid as a cover matrix
    private int[][] convertInCoverMatrix() {
        int[][] coverMatrix = createCoverMatrix();

        // Taking into account the values already entered in DancingLinksSudoku's grid instance
        for (int row = 1; row <= sudoku.getLength(); row++) {
            for (int column = 1; column <= sudoku.getLength(); column++) {
                int n = sudoku.getNumber(row - 1, column - 1); //grid[row - 1][column - 1];

                if (n != EMPTY_CELL) {
                    for (int num = 1; num <= sudoku.getLength(); num++) {
                        if (num != n) {
                            Arrays.fill(coverMatrix[indexInCoverMatrix(row, column, num)], 0);
                        }
                    }
                }
            }
        }

        return coverMatrix;
    }

    private int[][] convertDLXListToGrid(NodeStack answer) {

        int[][] result = new int[sudoku.getLength()][sudoku.getLength()];

        if (answer != null) {
            //for (DancingNode n : answer) {
            while (!answer.isEmpty()) {
                DancingNode n = answer.pop();
                DancingNode rcNode = n;
                int min = Integer.parseInt(rcNode.column.name);

                for (DancingNode tmp = n.right; tmp != n; tmp = tmp.right) {
                    int val = Integer.parseInt(tmp.column.name);

                    if (val < min) {
                        min = val;
                        rcNode = tmp;
                    }
                }

                // we get line and column
                int ans1 = Integer.parseInt(rcNode.column.name);
                int ans2 = Integer.parseInt(rcNode.right.column.name);
                int r = ans1 / sudoku.getLength();
                int c = ans1 % sudoku.getLength();
                // and the affected value
                int num = (ans2 % sudoku.getLength()) + 1;
                // we affect that on the result grid
                result[r][c] = num;
            }
        }

        return result;
    }

    public boolean solve() {
        int[][] cover = convertInCoverMatrix();
        //printCoverMatrix(cover);
        DLX dlx = new DLX(cover);
        dlx.solve();
        sudoku.setNumbers(convertDLXListToGrid(dlx.result));
        return true;
    }
}
