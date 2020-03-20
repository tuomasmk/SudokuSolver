package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import ss.sudokusolver.Sudoku;


public class FileReader {

    public FileReader() {
    }
    
    /**
     * Reads sudoku from a file containing string of numbers in the first column.
     * @param size      sudoku size.
     * @param filename  to import from.
     * @return  imported sudoku.
     * @throws FileNotFoundException 
     */
    public Sudoku readNumbersOnly(int size, String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String line = "";
        boolean resume = true;
        while (resume) {
            resume = false;
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
            if (line.length() < size * size) {
                if (scanner.hasNextLine()) {
                    resume = true;
                } else {
                    return null;
                }
            }
        }
        Sudoku sudoku = new Sudoku(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = line.charAt(i * size + j);
                int num = c - '0';
                if (num >= 0 && num <= 9) {
                    sudoku.setNumber(num, i, j);
                } else {
                    sudoku.setNumber(0, i, j);
                }
            }
        }
        return sudoku;
    }
    
    /**
     * Reads sudoku from comma separated file.
     * Each row is on its own row separated with commas.
     * Size of the sudoku is interpreted from the file.
     * @param filename  sudoku is read from.
     * @return  imported sudoku.
     * @throws FileNotFoundException 
     */
    public Sudoku readCommaSeparated(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String current = scanner.nextLine();
        String[] line = current.split(",");
        int[][] numbers = new int[line.length][line.length];
        int j = 0;
        for (int i = 0; i < line.length; i++) {
            try {
                numbers[j][i] = Integer.parseInt(line[i].trim());
            } catch (NumberFormatException e) {
                numbers[j][i] = 0;
            }
        }
        while (scanner.hasNextLine()) {
            j++;
            current = scanner.nextLine();
            line = current.split(",");
            for (int i = 0; i < line.length; i++) {
                try {
                    numbers[j][i] = Integer.parseInt(line[i].trim());
                } catch (NumberFormatException e) {
                    numbers[j][i] = 0;
                }
            }
        }
        return new Sudoku(numbers);
    }
}
