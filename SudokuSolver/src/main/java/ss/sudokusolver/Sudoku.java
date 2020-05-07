package ss.sudokusolver;

public class Sudoku {

    int squareSize;
    int length;
    int[][] numbers;
    int[] empty;
    int[][] preEmptiveSets;

    public Sudoku() {
    }

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

    public void initializeEmpty() {
        empty = new int[length * length];
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (numbers[i][j] == 0) {
                    empty[count++] = i * length + j;
                }
            }
        }
    }

    /**
     * Finds next empty cell in a sudoku. Order is from left to right then top
     * to bottom.
     *
     * @return coordinates of the next empty cell [x, y].
     */
    public int[] nextFreeCell() {
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
        return nextFreeCell()[0] == -1;
    }

    public int getNumber(int row, int col) {
        return numbers[row][col];
    }

    public void setNumber(int num, int row, int col) {
        numbers[row][col] = num;
    }

    public boolean canPlace(int num, int row, int col) {
        return !isInBox(num, row, col) && !isInRow(num, row, col) && !isInCol(num, row, col);
    }

    public boolean isInBox(int num, int row, int col) {
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

    public boolean isInRow(int num, int row, int col) {
        for (int j = 0; j < length; j++) {
            if (j != col && numbers[row][j] == num) {
                return true;
            }
        }
        return false;
    }

    public boolean isInCol(int num, int row, int col) {
        for (int i = 0; i < length; i++) {
            if (i != row && numbers[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null) {
            return null;
        }
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    public Sudoku copy(Sudoku from) {
        Sudoku s = new Sudoku();
        s.length = from.length;
        s.squareSize = from.squareSize;
        s.numbers = deepCopyIntMatrix(from.numbers);
        return s;
    }

    /**
     * To pretty print sudoku. TODO: fix charset problem, universalize.
     *
     * @param a number of number in a sudoku.
     * @return Textual representation of the sudoku.
     */
    String f(int a) {
        String p5 = "0121212121213121212121213121212121213121212121213121212121213121212121214";
        String p4 = "012121212131212121213121212121312121212131212121214";
        String p3 = "012121213121212131212121312121214";
        String p2 = "012131214";
        String p1 = "0121213121213121214";
        String pX;
        switch (a) {
            case 36:
                pX = p5;
                break;
            case 25:
                pX = p4;
                break;
            case 4:
                pX = p2;
                break;
            case 16:
                pX = p3;
                break;
            default:
                pX = p1;
        }

        String p = pX, // Both lines and rows are repeated according to this pattern.
                r1[] = {"╔═╤╦╗", "║ │║║x", "╟─┼╫╢", "╠═╪╬╣", "╚═╧╩╝"}, // Characters found on each line.
                //   (note the 'x')
                r = "";                                            // The string under construction
        for (int x1 : p.getBytes()) {                             // For each line,
            for (int x
                    : //  For each character in the pattern,
                    p.replace("1", r1[x1 -= 48].length() > 5 ? "151" : "111") //    *but* with a cell width of 3,
                    //    and with an optional character ('x')
                    .getBytes()) {
                r += r1[x1].charAt(x - 48);                               //   append the real mapped character
            }
            r += "\n";                                              //  then append a new line
        }
        // For each number in the input
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int temp = numbers[i][j];
                if (a > 25) {
                    r = r.replaceFirst("x", temp > 0 ? "" + chars.charAt(temp - 1) : " ");
                } else if (a > 9) {
                    r = r.replaceFirst("x", temp > 0 ? "" + alphabets.charAt(temp - 1) : " ");
                } else {
                    r = r.replaceFirst("x", temp > 0 ? "" + temp : " ");                 //  replace the first 'x' with that number.
                    //    (or space if zero)    
                }
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

    /**
     *
     * @return number of characters used in the sudoku.
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @return Number of cells in one side of a square. It is equal to square
     * root if length.
     */
    public int getSquareSize() {
        return squareSize;
    }

    public int[] getEmpty() {
        return empty;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }
}
