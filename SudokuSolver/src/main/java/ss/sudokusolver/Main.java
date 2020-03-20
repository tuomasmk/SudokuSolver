package ss.sudokusolver;

import java.io.FileNotFoundException;
import logics.Solver;
import utilities.FileReader;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader();
        //Sudoku sudoku = fr.readCommaSeparated("sudoku1.txt");
        Sudoku sudoku = fr.readNumbersOnly(9, "sudoku.csv");
        System.out.println(sudoku);
        Solver solver = new Solver(sudoku);
        //solver.backtrack();
        //solver.candidateCheck();
        //solver.fillUsingCandidateCheck();
        //solver.fillUsingPlaceFinding();
        solver.humanSolver();
        System.out.println(solver.getSudoku());
    }

}
