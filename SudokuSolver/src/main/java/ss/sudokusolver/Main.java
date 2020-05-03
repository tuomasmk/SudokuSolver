package ss.sudokusolver;

import logics.DancingLinksSolver;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import logics.BacktrackSolver;
import logics.HumanSolver;
import logics.ReferenceGraphSolver;
import utilities.FileReader;


public class Main {
    
    public static void comparePerformances(List<Sudoku> sudokus) {
        HumanSolver hs = new HumanSolver();
        BacktrackSolver bts = new BacktrackSolver();
        ReferenceGraphSolver rgs = new ReferenceGraphSolver();
        DancingLinksSolver dls  = new DancingLinksSolver();
        long start, end;
        int solvedCount = 0;
        boolean solved;
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            hs.setSudoku(s.copy(s));
            solved = hs.solve();
            if (solved) {
                solvedCount++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("solved: " + solvedCount);
        System.out.println("human: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            bts.setSudoku(s.copy(s));
            solved = bts.backtrack();
        }
        end = System.currentTimeMillis();
        System.out.println("bt: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            bts.setSudoku(s.copy(s));
            bts.btwc();
        }
        end = System.currentTimeMillis();
        System.out.println("btc: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            bts.setSudoku(s.copy(s));
            bts.btwe();
        }
        end = System.currentTimeMillis();
        System.out.println("btwe: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            hs.setSudoku(s.copy(s));
            hs.solveWbt();
        }
        end = System.currentTimeMillis();
        System.out.println("hwbt: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            dls.setSudoku(s.copy(s));
            dls.solve();
        }
        end = System.currentTimeMillis();
        System.out.println("dls: " + (end - start));        
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            rgs.setSudoku(s.copy(s));
            rgs.solve();
        }
        end = System.currentTimeMillis();
        System.out.println("rg: " + (end - start));
    }
    
    public static void comparePerformances() throws FileNotFoundException {
        FileReader fr = new FileReader();
        String filename = "sudoku16.txt";
        Sudoku sudoku = fr.readCommaSeparated(filename); //fr.readMultipleNumbersOnly(9, filename);
        System.out.println(sudoku);
        List<Sudoku> sudokus = new ArrayList();
        int count = 0;
        while (sudoku != null && count++ < 1) {
            sudokus.add(sudoku);
            sudoku = fr.readMultipleNumbersOnly(9, filename);
        }
        comparePerformances(sudokus);
    }
    
    private static void testBacktrack(Sudoku sudoku) {
        BacktrackSolver bts = new BacktrackSolver(sudoku);
        System.out.println(sudoku);
        bts.backtrack();
        System.out.println(sudoku);
    }
    
    private static void testReferenceGraph(Sudoku sudoku) {
        ReferenceGraphSolver rgs = new ReferenceGraphSolver(sudoku);
        System.out.println(sudoku);
        rgs.solve();
        System.out.println(sudoku);
    }
    
    private static void testHumanSolver(Sudoku sudoku) throws FileNotFoundException {
        HumanSolver solver = new HumanSolver(sudoku);
        System.out.println(sudoku);
        solver.solveWbt();
        System.out.println(sudoku);

    }
    
    public static void testDancingLinks(Sudoku sudoku) {
        System.out.println(sudoku);
        DancingLinksSolver dls = new DancingLinksSolver(sudoku);
        dls.solve();
        System.out.println(sudoku);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String filename ="16x16beginner.csv";
        FileReader fr = new FileReader();
        Sudoku sudoku = fr.readCommaSeparated(filename, ";");
        //fr.writeToAFile("16x16beginnerA.csv", sudoku, true);
        //comparePerformances();
        //testHumanSolver(sudoku);
        //testBacktrack(sudoku);
        //testReferenceGraph(sudoku);
        //testDancingLinks(sudoku);
        List<Sudoku> sudokus = new ArrayList();
        sudokus.add(sudoku);
        comparePerformances(sudokus);
    }

}
