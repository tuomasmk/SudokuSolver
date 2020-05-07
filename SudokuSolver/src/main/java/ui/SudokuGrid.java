package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import logics.BacktrackSolver;
import logics.DancingLinksSolver;
import logics.HumanSolver;
import logics.Solver;
import ss.sudokusolver.Sudoku;
import utilities.FileReader;

/**
 * This class implements a visual component representing a sudoku board.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 26, 2016)
 * https://codereview.stackexchange.com/questions/121291/nice-gui-for-the-sudoku-solver-in-javas
 */
final class SudokuGrid extends JPanel {

    private static final Font FONT = new Font("Verdana", 
                                              Font.CENTER_BASELINE, 
                                              20);

    private final JTextField[][] grid;
    private final Map<JTextField, Point> mapFieldToCoordinates = 
            new HashMap<>();

    private final int dimension;
    private final JPanel gridPanel;
    private final JPanel buttonPanel;
    private final JButton solveButton;
    private final JButton visualSolveButton;
    private final JButton clearButton;
    private final JPanel[][] minisquarePanels;
    private SudokuOptions options;
    private Sudoku sudoku;

    SudokuGrid(int dimension) {
        this.grid = new JTextField[dimension][dimension];
        this.dimension = dimension;
        this.options = new SudokuOptions();

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                JTextField field = new JTextField();
                field.addKeyListener(new SudokuCellKeyListener(this));
                mapFieldToCoordinates.put(field, new Point(x, y));
                grid[y][x] = field;
            }
        }

        this.gridPanel   = new JPanel();
        this.buttonPanel = new JPanel();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension fieldDimension = new Dimension(30, 30);

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                JTextField field = grid[y][x];
                field.setBorder(border);
                field.setFont(FONT);
                field.setPreferredSize(fieldDimension);
            }
        }

        int minisquareDimension = (int) Math.sqrt(dimension);
        this.gridPanel.setLayout(new GridLayout(minisquareDimension,
                                                minisquareDimension));

        this.minisquarePanels = new JPanel[minisquareDimension]
                                          [minisquareDimension];

        Border minisquareBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        for (int y = 0; y < minisquareDimension; ++y) {
            for (int x = 0; x < minisquareDimension; ++x) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(minisquareDimension,
                                               minisquareDimension));
                panel.setBorder(minisquareBorder);
                minisquarePanels[y][x] = panel;
                gridPanel.add(panel);
            }
        }

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                int minisquareX = x / minisquareDimension;
                int minisquareY = y / minisquareDimension;

                minisquarePanels[minisquareY][minisquareX].add(grid[y][x]);
            }
        }

        this.gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                                2));
        this.clearButton = new JButton("Clear");
        this.solveButton = new JButton("Solve");
        this.visualSolveButton = new JButton("Visualize");

        this.buttonPanel.setLayout(new BorderLayout());
        this.buttonPanel.add(clearButton, BorderLayout.WEST);
        this.buttonPanel.add(visualSolveButton, BorderLayout.CENTER);
        this.buttonPanel.add(solveButton, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(gridPanel,   BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);  

        clearButton.addActionListener((ActionEvent e) -> {
            clearAll();
        });

        solveButton.addActionListener((ActionEvent e) -> {
            solve();
        });
        
        visualSolveButton.addActionListener((ActionEvent e) -> {
            visualizeSolving();
        });
    }

    public void openFile(File file) {
        
        try {
            FileReader fr = new FileReader();
            Sudoku sudoku = fr.readCommaSeparatedAlphabet(file.getPath(), ";");
            drawSudoku(sudoku);
        } catch (FileNotFoundException ex) {

        }
    }

    private void addSpace(JTextField field) {
        if (field.getText().isEmpty()) {
            field.setText(" ");
        }
    }

    void moveCursor(JTextField field, char c) {
        Point coordinates = mapFieldToCoordinates.get(field);
        field.setBackground(Color.WHITE);

        switch (c) {
            case 'w':
            case 'W':

                if (coordinates.y > 0) {
                    grid[coordinates.y - 1][coordinates.x].requestFocus();
                    addSpace(grid[coordinates.y - 1][coordinates.x]);
                }

                break;

            case 'd':
            case 'D':

                if (coordinates.x < dimension - 1) {
                    grid[coordinates.y][coordinates.x + 1].requestFocus();
                    addSpace(grid[coordinates.y][coordinates.x + 1]);
                }

                break;

            case 's':
            case 'S':

                if (coordinates.y < dimension - 1) {
                    grid[coordinates.y + 1][coordinates.x].requestFocus();
                    addSpace(grid[coordinates.y + 1][coordinates.x]);
                }

                break;

            case 'a':
            case 'A':

                if (coordinates.x > 0) {
                    grid[coordinates.y][coordinates.x - 1].requestFocus();
                    addSpace(grid[coordinates.y][coordinates.x - 1]);
                }

                break;
        }
    }

    void clearAll() {
        for (JTextField[] row : grid) {
            for (JTextField field : row) {
                field.setText("");
            }
        }
    }
    
    Sudoku sudokuFromGrid() {
        Sudoku sudoku = new Sudoku(dimension);

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                String text = grid[y][x].getText();

                int number = 0;

                try {
                    number = Integer.parseInt(text.trim());
                } catch (NumberFormatException ex) {

                }

                sudoku.setNumber(number, x, y);
            }
        }
        return sudoku;
    }
    
    Solver getSolver() {
        if (options == null) {
            return new DancingLinksSolver();
        }
        switch (options.solver) {
            case HUMAN:
                return new HumanSolver();
            case BT:
                return new BacktrackSolver();
            case DL:
                return new DancingLinksSolver();
                
        }
        return new DancingLinksSolver();
    }       

    void solve() {
        Sudoku sudoku = sudokuFromGrid();
        Solver solver = getSolver(); //DancingLinksSolver dls = new DancingLinksSolver(sudoku);
        solver.setSudoku(sudoku);
        try {
            Thread t = new Thread() {
                @Override
                public void run() {
                    solver.solve();
                    drawSudoku(sudoku); 

                    if (!sudoku.isSolved()) {
                        throw new RuntimeException("Could not solve sudoku,"
                                                    + "try another algorithm.");
                    }
                }
            };
            t.run();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                                          ex.getMessage(),
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void drawSudoku(Sudoku sudoku) {
        String skip = dimension < 10 ? " " : "";

        for (int y = 0; y < dimension; ++y) {
            for (int x = 0; x < dimension; ++x) {
                int number = sudoku.getNumber(x, y);
                if (number != 0) {
                    grid[y][x].setText(skip + number);
                } else {
                    grid[y][x].setText(skip + "");
                }
                
            }
        }  
        gridPanel.repaint();
        gridPanel.invalidate();
        gridPanel.paintImmediately(gridPanel.getBounds());
    }
    
    boolean backtrackAndVisualize(int x, int y) throws InterruptedException {
        drawSudoku(sudoku);
        //Thread.sleep(20);
        if (x == sudoku.getLength()-1 && y == sudoku.getLength()) {
            return true;
        } else {
            if (y == sudoku.getLength()) {
                x++;
                y = 0;
            }
            if (sudoku.getNumber(x, y) != 0) {
                return backtrackAndVisualize(x, y + 1);
            } else {
                for (int num = 1; num <= sudoku.getLength(); num++) {
                    if (sudoku.canPlace(num, x, y)) {
                        sudoku.setNumber(num, x, y);
                        if (backtrackAndVisualize(x, y + 1)) {
                            return true;
                        } else {
                            sudoku.setNumber(0, x, y);
                        }
                    }
                }
                return false;
            }
        }
    }
    
    void visualizeSolving() {
        sudoku = sudokuFromGrid();

        try {
            backtrackAndVisualize(0, 0);
            if (!sudoku.isSolved()) {
                throw new RuntimeException("Something gone wrong.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                                          ex.getMessage(),
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public void setOptions(SudokuOptions options) {
        this.options = options;
    }

    public SudokuOptions getOptions() {
        return options;
    }
}