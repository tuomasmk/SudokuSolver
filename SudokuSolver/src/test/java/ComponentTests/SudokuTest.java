package ComponentTests;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import ss.sudokusolver.Sudoku;


public class SudokuTest {
    Sudoku sudoku;
    
    @Before
    public void setUp() {
        sudoku = new Sudoku(4);
        sudoku = new Sudoku(new int[][]{
                {4, 0, 0, 3},
                {0, 2, 1, 0},
                {0, 3, 4, 0},
                {1, 0, 0, 0}});
    }
    
    @Test
    public void sudokuIsInitializedCorrectly() {
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 0);
        assertEquals(sudoku.getNumber(0, 2), 0);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 0);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 0);
        assertEquals(sudoku.getNumber(2, 0), 0);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 0);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 0);
        assertEquals(sudoku.getNumber(3, 2), 0);
        assertEquals(sudoku.getNumber(3, 3), 0);
    }
    
    @Test
    public void nextFreeCellIsReturnCorrectly() {
        int[] freeCell = sudoku.nextFreeCell();
        assertEquals(freeCell[0], 0);
        assertEquals(freeCell[1], 1);
    }

    @Test
    public void correctNumberIsReturned() {
        assertEquals(sudoku.getNumber(0, 0), 4);
    }
    
    @Test
    public void correctlySetsNumber() {
        sudoku.setNumber(1, 0, 1);
        assertEquals(sudoku.getNumber(0, 1), 1);
    }
    
    @Test
    public void lengthIsCorrect() {
        assertEquals(sudoku.getLength(), 4);
    }
    
    @Test
    public void squareSizeIsCorrect() {
        assertEquals(sudoku.getSquareSize(), 2);
    }
    
    @Test
    public void isInRow() {
        assertTrue(sudoku.isInRow(4, 0, 1));
    }
    
    @Test
    public void isNotInRow() {
        assertFalse(sudoku.isInRow(1, 0, 0));
    }
    
    @Test
    public void isInCol() {
        assertTrue(sudoku.isInCol(4, 1, 0));
    }
    
    @Test
    public void isNotInCol() {
        assertFalse(sudoku.isInCol(2, 0, 0));
    }
    
    @Test
    public void isInBox() {
        assertTrue(sudoku.isInBox(2, 0, 0));
    }
    
    @Test
    public void isNotInBox() {
        assertFalse(sudoku.isInBox(3, 0, 0));
    }
    
    @Test
    public void canPlace() {
        assertTrue(sudoku.canPlace(1, 0, 1));
    }
    
    @Test
    public void cannotPlace() {
        assertFalse(sudoku.canPlace(3, 0, 1));
    }
    
    @Test
    public void candidatesAreCorrect() {
        ArrayList<Integer> candidates = sudoku.candidates(0, 1);
        assertEquals(candidates.size(), 1);
        assertEquals((int) candidates.get(0), 1);
    }
    
    @Test
    public void notSolved() {
        assertFalse(sudoku.isSolved());
    }
    
    @Test
    public void isSolved() {
        sudoku.setNumber(1, 0, 1);
        sudoku.setNumber(2, 0, 2);
        sudoku.setNumber(3, 1, 0);
        sudoku.setNumber(4, 1, 3);
        sudoku.setNumber(2, 2, 0);
        sudoku.setNumber(1, 2, 3);
        sudoku.setNumber(4, 3, 1);
        sudoku.setNumber(3, 3, 2);
        sudoku.setNumber(2, 3, 3);
        assertTrue(sudoku.isSolved());
    }
    
    @Test
    public void testEmptySudokuConstructor() {
        sudoku = new Sudoku(4);
        assertEquals(sudoku.getNumber(0, 0), 0);
        assertEquals(sudoku.getNumber(0, 1), 0);
        assertEquals(sudoku.getNumber(0, 2), 0);
        assertEquals(sudoku.getNumber(0, 3), 0);
        assertEquals(sudoku.getNumber(1, 0), 0);
        assertEquals(sudoku.getNumber(1, 1), 0);
        assertEquals(sudoku.getNumber(1, 2), 0);
        assertEquals(sudoku.getNumber(1, 3), 0);
        assertEquals(sudoku.getNumber(2, 0), 0);
        assertEquals(sudoku.getNumber(2, 1), 0);
        assertEquals(sudoku.getNumber(2, 2), 0);
        assertEquals(sudoku.getNumber(2, 3), 0);
        assertEquals(sudoku.getNumber(3, 0), 0);
        assertEquals(sudoku.getNumber(3, 1), 0);
        assertEquals(sudoku.getNumber(3, 2), 0);
        assertEquals(sudoku.getNumber(3, 3), 0);
    }
}
