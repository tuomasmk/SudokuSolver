package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import ss.sudokusolver.Sudoku;


public class FileReader {
    Scanner scanner;

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
    
    public Sudoku readMultipleNumbersOnly(int size, String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (scanner == null) {
            scanner = new Scanner(file);
        }
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
    
    public Sudoku readCommaSeparated(String filename) throws FileNotFoundException {
        return (readCommaSeparated(filename, ","));
    }
    
    /**
     * Reads sudoku from comma separated file.
     * Each row is on its own row separated with commas.
     * Size of the sudoku is interpreted from the file.
     * @param filename  sudoku is read from.
     * @param separator value separator in the file.
     * @return  imported sudoku.
     * @throws FileNotFoundException 
     */
    public Sudoku readCommaSeparated(String filename, String separator) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String current = scanner.nextLine();
        String[] line = current.split(separator, -1);
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
            line = current.split(separator, -1);
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
    
    public Sudoku readCommaSeparatedAlphabet(String filename) throws FileNotFoundException {
        return readCommaSeparatedAlphabet(filename, ",");
    }
    
     /**
     * Reads sudoku from comma separated file.
     * Each row is on its own row separated with commas.
     * Size of the sudoku is interpreted from the file.
     * @param filename  sudoku is read from.
     * @return  imported sudoku.
     * @throws FileNotFoundException 
     */
    public Sudoku readCommaSeparatedAlphabet(String filename, String separator) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String current = scanner.nextLine();
        String[] line = current.split(separator, -1);
        int[][] numbers = new int[line.length][line.length];
        int j = 0;
        for (int i = 0; i < line.length; i++) {
            try {
                if (line[i].length() == 0) {
                    numbers[j][i] = 0;
                } else {
                    numbers[j][i] = line[i].charAt(0) - 'A' + 1; //Integer.parseInt(line[i].trim());
                }
            } catch (NumberFormatException e) {
                numbers[j][i] = 0;
            }
        }
        while (scanner.hasNextLine()) {
            j++;
            current = scanner.nextLine();
            line = current.split(separator, -1);
            for (int i = 0; i < line.length; i++) {
                try {
                    if (line[i].length() == 0) {
                        numbers[j][i] = 0;
                    } else {
                        numbers[j][i] = line[i].charAt(0) - 'A' + 1; //Integer.parseInt(line[i].trim());
                    }
                } catch (NumberFormatException e) {
                    numbers[j][i] = 0;
                }
            }
        }
        return new Sudoku(numbers);
    }
    
    private String addChar(String string, Integer number, boolean alphabet) {
        String alphabets = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (number > 0) {
            if (alphabet) {
                string += alphabets.charAt(number);
            } else {
                string += number;
            }
        }
        return string;
    }
    
    public void writeToAFile(String filename, Sudoku sudoku, boolean alphabet) {
        try {
            String line = "";
            int num;
            FileWriter myWriter = new FileWriter(filename);
            for (int r = 0; r < sudoku.getLength(); r++) {
                for (int c = 0; c < sudoku.getLength() - 1; c++) {
                    num = sudoku.getNumber(r, c);
                    line = addChar(line, num, alphabet);
                    line += ";";
                }
                line = addChar(line, sudoku.getNumber(r, sudoku.getLength() - 1), alphabet);
                line += "\n";
            }
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
