package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * This class holds and manages the main frame of the program.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 27, 2016)
 * https://codereview.stackexchange.com/questions/121291/nice-gui-for-the-sudoku-solver-in-java
 */
public class SudokuFrame {

    private final JFrame frame = new JFrame("Sudoku solver");
    private SudokuGrid grid;

    public SudokuFrame() {
        frame.getContentPane().add(grid = new SudokuGrid(9));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMenus();
        frame.pack();
        frame.setResizable(false);
        centerView();
        frame.setVisible(true);
    }
    
    private void buildMenus() {
        JMenuBar bar = new JMenuBar();
        bar.add(buildFileMenu());        
        bar.add(buildOptionMenu());
        frame.setJMenuBar(bar);
    }
    
    private JMenu buildOptionMenu() {
        JMenu optionsMenu = new JMenu("Options");
        
        JMenuItem options = new JMenuItem("Options");
        
        optionsMenu.add(options);
        
        options.addActionListener((ActionEvent e) -> {
            SudokuOptionsFrame optionsFrame = new SudokuOptionsFrame(grid);
            optionsFrame.setVisible(true);
            //ComplexOptionPane optionPane = new ComplexOptionPane();
            //grid.setOptions(optionPane.getOp);
        });
        return optionsMenu;
    }

    private JMenu buildFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem open   = new JMenuItem("Open");
        JMenuItem grid4  = new JMenuItem("4 x 4");
        JMenuItem grid9  = new JMenuItem("9 x 9");
        JMenuItem grid16 = new JMenuItem("16 x 16");
        JMenuItem grid25 = new JMenuItem("25 x 25");

        JMenuItem about = new JMenuItem("About");

        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(grid4);
        fileMenu.add(grid9);
        fileMenu.add(grid16);
        fileMenu.add(grid25);
        fileMenu.addSeparator();
        fileMenu.add(about);

        open.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(frame);

            if (status == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                grid.openFile(file);
            }
        });

        grid4.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(4));
            frame.pack();
            centerView();
        });

        grid9.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(9));
            frame.pack();
            centerView();
        });

        grid16.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(16));
            frame.pack();
            centerView();
        });
        
        grid25.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(25));
            frame.pack();
            centerView();
        });


        about.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(
                    null,
                    "By Rodion \"rodde\" Efremov. Feb 27, 2016", 
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE);
        });
        
        return fileMenu;
    }

    private void centerView() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        frame.setLocation((screen.width - frameSize.width) >> 1,
                          (screen.height - frameSize.height) >> 1);
    }
}