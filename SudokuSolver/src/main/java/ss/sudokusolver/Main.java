package ss.sudokusolver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import logics.Solver;
import utilities.FileReader;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader();
        
/*        
        Sudoku sudoku = fr.readCommaSeparated("sudoku2.txt");
        //Sudoku sudoku = fr.readNumbersOnly(9, "sudoku.csv");
        Solver solver = new Solver(sudoku);
        System.out.println(sudoku);
        solver.backtractWithCandidates();
        System.out.println(sudoku);
*/
        // for performance testing
        String filename = "sudoku.csv";
        Sudoku sudoku = fr.readMultipleNumbersOnly(9, filename);
        Solver solver = new Solver(sudoku);
        List<Sudoku> sudokus = new ArrayList();
        int count = 0;
        while (sudoku != null && count++ < 100000) {
            sudokus.add(sudoku);
            sudoku = fr.readMultipleNumbersOnly(9, filename);
        }
        long start, end;
        int solvedCount = 0;
        boolean solved;
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            solver.setSudoku(s.copy(s));
            solved = solver.humanSolver();
            if (solved) {
                solvedCount++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("solved: " + solvedCount);
        System.out.println("human: " + (end - start));
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            solver.setSudoku(s.copy(s));
            solved = solver.backtrack();
        }
        end = System.currentTimeMillis();
        System.out.println("bt: " + (end - start));
/*        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            solver.setSudoku(s.copy(s));
            solved = solver.backtractWithCandidates();
        }
        end = System.currentTimeMillis();
        System.out.println("btc: " + (end - start));*/
        start = System.currentTimeMillis();
        for (Sudoku s:sudokus) {
            solver.setSudoku(s.copy(s));
            solved = solver.referenceGraph();
        }
        end = System.currentTimeMillis();
        System.out.println("rg: " + (end - start));
    }

}
