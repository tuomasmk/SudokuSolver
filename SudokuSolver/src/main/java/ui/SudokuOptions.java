/*
 */

package ui;


public class SudokuOptions {
    public enum SolverType {
        HUMAN,
        BT,
        DL
    }
    public enum Coding {
        NUMBERS,
        ALPHABET
    }
    public SolverType solver;
    public Coding coding;
    public String separator;
    public String chars;

    public SudokuOptions() {
        solver = SolverType.DL;
        coding = Coding.NUMBERS;
        separator = "";
        chars = "";
    }
    
    
}
