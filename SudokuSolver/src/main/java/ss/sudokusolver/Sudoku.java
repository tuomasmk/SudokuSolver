package ss.sudokusolver;

import java.util.ArrayList;


public class Sudoku {
    int squareSize;
    int length;
    int[][] numbers;

    public Sudoku(int length) {
        if (length > 0) {
            this.length = length;
            squareSize = (int) Math.sqrt(length);
            numbers = new int[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    numbers[i][j] = 0;
                }
            }
        }
    }

    public Sudoku(int[][] sudoku) {
        length = sudoku.length;
        squareSize = (int) Math.sqrt(length);
        numbers = sudoku;
    }
    
    public ArrayList<Integer> candidates(int row, int col) {
        ArrayList<Integer> nums = new ArrayList();
        for (int i = 1; i <= length; i++) {
            if (canPlace(i, row, col)) {
                nums.add(i);
            }
        }
        return nums;
    }
    
    public int[] nextFreeSlot() {
        int[] slot = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (numbers[i][j] == 0) {
                    slot[0] = i;
                    slot[1] = j;
                    return slot;
                }
            }
        }
        slot[0] = -1;
        slot[1] = -1;
        return slot;
    }
    
    public boolean isSolved() {
        return nextFreeSlot()[0] == -1;
    }
    
    public int getNumber(int row, int col) {
        return numbers[row][col];
    }
    
    public void setNumber(int num, int row, int col) {
        numbers[row][col] = num;
    }
    
    public boolean canPlace(int num, int row, int col) {
        return !isInBox(num, col, row) && !isInRow(num, col, row) && !isInCol(num, col, row);
    }
    
    public boolean isInBox(int num, int col, int row) {
        int startCol = col / squareSize * squareSize;
        int startRow = row / squareSize * squareSize;
        for (int i = startRow; i < startRow + squareSize; i++) {
            for (int j = startCol; j < startCol + squareSize; j++) {
                if (!(i == row && j == col) && numbers[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isInRow(int num, int col, int row) {
        for (int j = 0; j < length; j++) {
            if (j != col && numbers[row][j] == num) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isInCol(int num, int col, int row) {
        for (int i = 0; i < length; i++) {
            if (i != row && numbers[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    String f(int a) {
        String p3 = "012121213121212131212121312121214";
        String p2 = "012131214";
        String p1 = "0121213121213121214";
        String pX;
        if (a == 4) {
            pX = p2;
        } else if (a == 16) {
            pX = p3;
        } else {
            pX = p1;
        }

        String p = pX,                         // Both lines and rows are repeated according to this pattern.
               r1[] = {"╔═╤╦╗" , "║ │║║x" , "╟─┼╫╢" , "╠═╪╬╣" , "╚═╧╩╝"},  // Characters found on each line.
                                                                //   (note the 'x')
               r = "";                                            // The string under construction
        for (int x1: p.getBytes()) {                             // For each line,
            for (int x:                                           //  For each character in the pattern,
                p.replace("1" , r1[x1 -= 48].length() > 5 ? "151" : "111")   //    *but* with a cell width of 3,
                                                                //    and with an optional character ('x')
                .getBytes())
                r += r1[x1].charAt(x - 48);                               //   append the real mapped character
            r += "\n";                                              //  then append a new line
        }
                                                                // For each number in the input
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int temp = numbers[i][j];
                r = r.replaceFirst("x" , temp > 0 ? "" + temp : " ");                 //  replace the first 'x' with that number.
                                                              //    (or space if zero)
            }
        }

        return r;                                               // Return the constructed string.
    }
    
/*
╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗
║ 8 │ 5 │   ║   │   │ 2 ║ 4 │   │   ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║ 7 │ 2 │   ║   │   │   ║   │   │ 9 ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║   │   │ 4 ║   │   │   ║   │   │   ║
╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣
║   │   │   ║ 1 │   │ 7 ║   │   │ 2 ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║ 3 │   │ 5 ║   │   │   ║ 9 │   │   ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║   │ 4 │   ║   │   │   ║   │   │   ║
╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣
║   │   │   ║   │ 8 │   ║   │ 7 │   ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║   │ 1 │ 7 ║   │   │   ║   │   │   ║
╟───┼───┼───╫───┼───┼───╫───┼───┼───╢
║   │   │   ║   │ 3 │ 6 ║   │ 4 │   ║
╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝
*/
    
    @Override
    public String toString() {
        return f(length);
    }

    public int getLength() {
        return length;
    }

    public int getSquareSize() {
        return squareSize;
    }
}


