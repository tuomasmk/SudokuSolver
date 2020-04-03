package ss.sudokusolver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import logics.BacktrackSolver;
import logics.HumanSolver;
import logics.ReferenceGraphSolver;
import utilities.FileReader;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader();
        
//        Sudoku sudoku = fr.readCommaSeparated("sudoku1.txt");
        //Sudoku sudoku = fr.readNumbersOnly(9, "sudoku.csv");
/*
        humanSolver solver = new humanSolver(sudoku);
        System.out.println(sudoku);
        solver.fillUsingPlaceFinding();
        System.out.println(sudoku);
*/
        // for performance testing        
        String filename = "sudoku.csv";
        Sudoku sudoku = fr.readMultipleNumbersOnly(9, filename);
        HumanSolver hs = new HumanSolver(sudoku);
        BacktrackSolver bts = new BacktrackSolver(sudoku);
        ReferenceGraphSolver rgs = new ReferenceGraphSolver(sudoku);
        List<Sudoku> sudokus = new ArrayList();
        int count = 0;
        while (sudoku != null && count++ < 10000) {
            sudokus.add(sudoku);
            sudoku = fr.readMultipleNumbersOnly(9, filename);
        }
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
            solved = bts.btwc();
        }
        end = System.currentTimeMillis();
        System.out.println("btc: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            rgs.setSudoku(s.copy(s));
            solved = rgs.solve();
        }
        end = System.currentTimeMillis();
        System.out.println("rg: " + (end - start));
    }

}