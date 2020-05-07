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
    static long start;
    static long end;
    
    public static void compareEfficient(List<Sudoku> sudokus) {
        HumanSolver hs = new HumanSolver();
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
            dls.setSudoku(s.copy(s));
            dls.solve();
        }
        end = System.currentTimeMillis();
        System.out.println("dls: " + (end - start));
/*        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            System.out.println(s);
            hs.setSudoku(s.copy(s));
            hs.solveWbt();
        }
        end = System.currentTimeMillis();
        System.out.println("hwbt: " + (end - start));*/
    }
    
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
    
    private static void backtrackPerformance(List<Sudoku> sudokus, int times) {
        BacktrackSolver solver = new BacktrackSolver();
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.backtrack();
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with bcaktrack");
        }
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.backtrack(0, 0);
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with bcaktrack");
        }
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.btwc();
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with bcaktrack");
        }
    }
    
    private static void humanPerformance(List<Sudoku> sudokus, int times) {
        HumanSolver solver = new HumanSolver();
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.solveWdl();
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with human solver");
        }
    }
    
    private static void dancingLinksPerformance(List<Sudoku> sudokus, int times) {
        DancingLinksSolver solver = new DancingLinksSolver();
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.solve();
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with dancing links");
        }
    }
    
    private static void referenceGraphPerformance(List<Sudoku> sudokus, int times) {
        ReferenceGraphSolver solver = new ReferenceGraphSolver();
        for (Sudoku sudoku : sudokus) {
            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                solver.setSudoku(sudoku.copy(sudoku));
                solver.solve();
            }
            end = System.currentTimeMillis();
            System.out.println("" + times + " sudokus solved in " + (end - start) + " ms with reference graph");
        }
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
        bts.backtrack(0, 0);
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
    
    private static void testPerformances() throws FileNotFoundException {
        List<Sudoku> sudokus = new ArrayList();
        FileReader fr = new FileReader();
        List<String> filenames = new ArrayList();
        filenames.add("Alphabet sudokus/9x9beginnerA.csv");
        filenames.add("Alphabet sudokus/16x16beginnerA.csv");
        filenames.add("Alphabet sudokus/25x25beginnerA.csv");
        filenames.add("Alphabet sudokus/9x9confirmedA.csv");
        filenames.add("Alphabet sudokus/16x16confirmedA.csv");
        filenames.add("Alphabet sudokus/25x25advancedA.csv");
        //filenames.add("Alphabet sudokus/25x25expertA.csv");
        for (String file : filenames) {
            sudokus.add(fr.readCommaSeparatedAlphabet(file, ";"));
        }
        humanPerformance(sudokus, 100);
        //dancingLinksPerformance(sudokus, 100);
        //backtrackPerformance(sudokus, 100);
        //referenceGraphPerformance(sudokus, 100);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String filename ="Alphabet sudokus/25x25expertA.csv";
        FileReader fr = new FileReader();
        Sudoku sudoku = fr.readCommaSeparatedAlphabet(filename, ";");
        //Sudoku sudoku = fr.readByChar(36, filename, ".0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //fr.writeToAFile("9x9hardestA.csv", sudoku, true);
        //testHumanSolver(sudoku);
        //testBacktrack(sudoku);
        //testReferenceGraph(sudoku);
        //testDancingLinks(sudoku);
        List<Sudoku> sudokus = new ArrayList();
        sudokus.add(sudoku);
        //compareEfficient(sudokus);
        testPerformances();
    }

}
