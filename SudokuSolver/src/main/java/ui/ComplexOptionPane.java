/*
 */
package ui;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ComplexOptionPane extends JPanel {

    private SudokuOptionsFrame optionsFramse = new SudokuOptionsFrame();
    private JTextArea textArea = new JTextArea(20, 40);

    public ComplexOptionPane() {
        add(new JScrollPane(textArea));
        add(new JButton(new AbstractAction("Options") {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int result = JOptionPane.showConfirmDialog(null, optionsFramse,
                        "Options", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {

                }
            }
        }));
    }

    private static void createAndShowGui() {
        ComplexOptionPane mainPanel = new ComplexOptionPane();

        JFrame frame = new JFrame("ComplexOptionPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
