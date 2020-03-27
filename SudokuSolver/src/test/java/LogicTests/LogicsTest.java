package LogicTests;

import logics.Solver;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ss.sudokusolver.Sudoku;


public class LogicsTest {
    Sudoku sudoku;
    Solver solver;
    
    @Before
    public void setUp() {
        sudoku = new Sudoku(4);
        sudoku = new Sudoku(new int[][]{
                {4, 0, 0, 3},
                {0, 2, 1, 0},
                {0, 3, 4, 0},
                {1, 0, 0, 0}});
        solver = new Solver(sudoku);
    }
    
    @Test
    public void testCandidateCheck() {
        solver.candidateCheck();
        //sudoku is solvable with only one round of candidateCheck
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 1);
        assertEquals(sudoku.getNumber(0, 2), 2);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 3);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 4);
        assertEquals(sudoku.getNumber(2, 0), 2);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 1);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 4);
        assertEquals(sudoku.getNumber(3, 2), 3);
        assertEquals(sudoku.getNumber(3, 3), 2);
    }
    
    @Test
    public void testPlaceFinding() {
        solver.placeFinding();
        //sudoku is solvable with only one round of placeFinding
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 1);
        assertEquals(sudoku.getNumber(0, 2), 2);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 3);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 4);
        assertEquals(sudoku.getNumber(2, 0), 2);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 1);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 4);
        assertEquals(sudoku.getNumber(3, 2), 3);
        assertEquals(sudoku.getNumber(3, 3), 2);
    }
    
    @Test
    public void testbackTrack() {
        solver.backtrack();
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 1);
        assertEquals(sudoku.getNumber(0, 2), 2);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 3);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 4);
        assertEquals(sudoku.getNumber(2, 0), 2);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 1);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 4);
        assertEquals(sudoku.getNumber(3, 2), 3);
        assertEquals(sudoku.getNumber(3, 3), 2);
    }
    
    @Test
    public void testbackTrackWithCandidates() {
        solver.backtractWithCandidates();
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 1);
        assertEquals(sudoku.getNumber(0, 2), 2);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 3);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 4);
        assertEquals(sudoku.getNumber(2, 0), 2);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 1);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 4);
        assertEquals(sudoku.getNumber(3, 2), 3);
        assertEquals(sudoku.getNumber(3, 3), 2);
    }
    
    @Test
    public void testReferenceGraph() {
        solver.referenceGraph();
        assertEquals(sudoku.getNumber(0, 0), 4);
        assertEquals(sudoku.getNumber(0, 1), 1);
        assertEquals(sudoku.getNumber(0, 2), 2);
        assertEquals(sudoku.getNumber(0, 3), 3);
        assertEquals(sudoku.getNumber(1, 0), 3);
        assertEquals(sudoku.getNumber(1, 1), 2);
        assertEquals(sudoku.getNumber(1, 2), 1);
        assertEquals(sudoku.getNumber(1, 3), 4);
        assertEquals(sudoku.getNumber(2, 0), 2);
        assertEquals(sudoku.getNumber(2, 1), 3);
        assertEquals(sudoku.getNumber(2, 2), 4);
        assertEquals(sudoku.getNumber(2, 3), 1);
        assertEquals(sudoku.getNumber(3, 0), 1);
        assertEquals(sudoku.getNumber(3, 1), 4);
        assertEquals(sudoku.getNumber(3, 2), 3);
        assertEquals(sudoku.getNumber(3, 3), 2);
    }
}
