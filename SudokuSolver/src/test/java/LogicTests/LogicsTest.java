package LogicTests;

import dataStructures.IntStack;
import logics.BacktrackSolver;
import logics.DancingLinksSolver;
import logics.HumanSolver;
import logics.ReferenceGraphSolver;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ss.sudokusolver.Sudoku;


public class LogicsTest {
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
    public void candidatesAreCorrect() {
        HumanSolver solver = new HumanSolver(sudoku);
        IntStack candidates = solver.candidates(0, 1);
        assertEquals(candidates.size(), 1);
        assertEquals((int) candidates.pop(), 1);
    }
    
    @Test
    public void testCandidateCheck() {
        HumanSolver solver = new HumanSolver(sudoku);
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
        HumanSolver solver = new HumanSolver(sudoku);
        solver.fillUsingPlaceFinding();
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
    public void testPlaceFinding9x9() {
        sudoku = new Sudoku(new int[][] {
            {0, 1, 0, 0, 0, 4, 5, 0, 9},
            {0, 0, 4, 9, 0, 0, 0, 0, 6},
            {6, 0, 7, 0, 2, 3, 0, 4, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 1, 7, 5, 0, 0, 0},
            {5, 7, 0, 8, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 7, 9, 0, 0},
            {0, 0, 5, 3, 0, 0, 4, 6, 0},
            {3, 0, 0, 2, 1, 6, 0, 0, 7}});
        HumanSolver solver = new HumanSolver(sudoku);
        solver.fillUsingPlaceFinding();
        assertEquals(sudoku.getNumber(0, 0), 8);
        assertEquals(sudoku.getNumber(1, 1), 5);
        assertEquals(sudoku.getNumber(2, 2), 7);
        assertEquals(sudoku.getNumber(3, 3), 6);
        assertEquals(sudoku.getNumber(4, 4), 7);
        assertEquals(sudoku.getNumber(5, 5), 9);
        assertEquals(sudoku.getNumber(6, 6), 9);
        assertEquals(sudoku.getNumber(7, 7), 6);
        assertEquals(sudoku.getNumber(8, 8), 7);
    }
    
    @Test
    public void testbackTrack() {
        BacktrackSolver solver = new BacktrackSolver(sudoku);
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
        BacktrackSolver solver = new BacktrackSolver(sudoku);
        solver.btwc();
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
        ReferenceGraphSolver solver = new ReferenceGraphSolver(sudoku);
        solver.solve();
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
    public void testHumanSolver() {
        sudoku = new Sudoku(new int[][] {
            {0, 1, 0, 0, 0, 4, 5, 0, 9},
            {0, 0, 4, 9, 0, 0, 0, 0, 6},
            {6, 0, 7, 0, 2, 3, 0, 4, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 1, 7, 5, 0, 0, 0},
            {5, 7, 0, 8, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 7, 9, 0, 0},
            {0, 0, 5, 3, 0, 0, 4, 6, 0},
            {3, 0, 0, 2, 1, 6, 0, 0, 7}});
        HumanSolver solver = new HumanSolver(sudoku);
        solver.solve();
        assertEquals(sudoku.getNumber(0, 0), 8);
        assertEquals(sudoku.getNumber(1, 1), 5);
        assertEquals(sudoku.getNumber(2, 2), 7);
        assertEquals(sudoku.getNumber(3, 3), 6);
        assertEquals(sudoku.getNumber(4, 4), 7);
        assertEquals(sudoku.getNumber(5, 5), 9);
        assertEquals(sudoku.getNumber(6, 6), 9);
        assertEquals(sudoku.getNumber(7, 7), 6);
        assertEquals(sudoku.getNumber(8, 8), 7);
    }
    
    @Test
    public void testHumanSolverWithBacktrack() {
        sudoku = new Sudoku(new int[][] {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}});
        HumanSolver solver = new HumanSolver(sudoku);
        solver.solveWbt();
        assertEquals(sudoku.getNumber(0, 0), 8);
        assertEquals(sudoku.getNumber(1, 1), 4);
        assertEquals(sudoku.getNumber(2, 2), 5);
        assertEquals(sudoku.getNumber(3, 3), 2);
        assertEquals(sudoku.getNumber(4, 4), 4);
        assertEquals(sudoku.getNumber(5, 5), 9);
        assertEquals(sudoku.getNumber(6, 6), 3);
        assertEquals(sudoku.getNumber(7, 7), 1);
        assertEquals(sudoku.getNumber(8, 8), 2);
    }
    
    @Test
    public void testDancingLinksSolver() {
        sudoku = new Sudoku(new int[][] {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}});
        DancingLinksSolver solver = new DancingLinksSolver(sudoku);
        solver.solve();
        assertEquals(sudoku.getNumber(0, 0), 8);
        assertEquals(sudoku.getNumber(1, 1), 4);
        assertEquals(sudoku.getNumber(2, 2), 5);
        assertEquals(sudoku.getNumber(3, 3), 2);
        assertEquals(sudoku.getNumber(4, 4), 4);
        assertEquals(sudoku.getNumber(5, 5), 9);
        assertEquals(sudoku.getNumber(6, 6), 3);
        assertEquals(sudoku.getNumber(7, 7), 1);
        assertEquals(sudoku.getNumber(8, 8), 2);
    }
}
