/*
 */
package ui;

import java.awt.HeadlessException;

public class SudokuOptionsFrame extends javax.swing.JFrame {
    private SudokuOptions options;
    private SudokuGrid grid;

    /**
     * Creates new form Options
     * @param options
     */
    public SudokuOptionsFrame(SudokuOptions options) {
        this();
        this.options = options;
    }

    public SudokuOptionsFrame() throws HeadlessException {
        initComponents();
    }
    
    public SudokuOptionsFrame(SudokuGrid grid) {
        this.grid = grid;
        initComponents();
        options = grid.getOptions();
    }

    public SudokuOptions getOptions() {
        return options;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSOLVER_GROUP = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbSOLVER = new javax.swing.JLabel();
        rbHUMAN = new javax.swing.JRadioButton();
        rbDANCING_LINKS = new javax.swing.JRadioButton();
        rbBACKTRACK = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lbIMPORT_TYPE = new javax.swing.JLabel();
        rbNUMBERS = new javax.swing.JRadioButton();
        rbALPHABET = new javax.swing.JRadioButton();
        lbSEPARATOR = new javax.swing.JLabel();
        tfSEPARATOR = new javax.swing.JTextField();
        lbCHARACTERS = new javax.swing.JLabel();
        tfCHARACTERS = new javax.swing.JTextField();
        btOK = new javax.swing.JButton();
        btCANCEL = new javax.swing.JButton();

        setAlwaysOnTop(true);
        setName("frOPTIONS"); // NOI18N

        lbSOLVER.setText("Solver");

        bgSOLVER_GROUP.add(rbHUMAN);
        rbHUMAN.setText("Human (fastest, cannot solve all sudokus)");

        bgSOLVER_GROUP.add(rbDANCING_LINKS);
        rbDANCING_LINKS.setText("Dancing Links (moderate, can solve most sudokus)");

        bgSOLVER_GROUP.add(rbBACKTRACK);
        rbBACKTRACK.setText("Backtrack (slowest, can solve all sudokus)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbBACKTRACK)
                    .addComponent(rbDANCING_LINKS)
                    .addComponent(rbHUMAN)
                    .addComponent(lbSOLVER))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSOLVER)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbDANCING_LINKS)
                .addGap(3, 3, 3)
                .addComponent(rbHUMAN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbBACKTRACK)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lbIMPORT_TYPE.setText("Import Type");

        rbNUMBERS.setText("Numbers");
        rbNUMBERS.setName("rbNUMBERS"); // NOI18N

        rbALPHABET.setText("Alphabet");
        rbALPHABET.setName("rbALPHABET"); // NOI18N

        lbSEPARATOR.setText("Separator");
        lbSEPARATOR.setName("lbSEPARATOR"); // NOI18N

        tfSEPARATOR.setName("tfSEPARATOR"); // NOI18N

        lbCHARACTERS.setText("Characters");
        lbCHARACTERS.setName("lbCHARACTERS"); // NOI18N

        tfCHARACTERS.setName("tfCHARACTERS"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rbNUMBERS)
                        .addComponent(lbIMPORT_TYPE))
                    .addComponent(rbALPHABET, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCHARACTERS)
                    .addComponent(lbSEPARATOR))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfSEPARATOR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 172, Short.MAX_VALUE))
                    .addComponent(tfCHARACTERS))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIMPORT_TYPE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNUMBERS)
                    .addComponent(lbSEPARATOR)
                    .addComponent(tfSEPARATOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbALPHABET)
                    .addComponent(lbCHARACTERS)
                    .addComponent(tfCHARACTERS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btOK.setText("OK");
        btOK.setName("btOK"); // NOI18N
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        btCANCEL.setText("Cancel");
        btCANCEL.setName("btCANCEL"); // NOI18N
        btCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCANCELActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCANCEL))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btOK)
                    .addComponent(btCANCEL))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        // TODO add your handling code here:
        if (rbHUMAN.isSelected()) {
            options.solver = SudokuOptions.SolverType.HUMAN;
        } else if  (rbDANCING_LINKS.isSelected()) {
            options.solver = SudokuOptions.SolverType.DL;
        } else {
            options.solver = SudokuOptions.SolverType.BT;
        }
        if (rbNUMBERS.isSelected()) {
            options.coding = SudokuOptions.Coding.NUMBERS;
        } else {
            options.coding = SudokuOptions.Coding.ALPHABET;
        }
        options.separator = tfSEPARATOR.getText();
        options.chars = tfCHARACTERS.getText();
        if (grid != null) {
            grid.setOptions(options);
        }
        dispose();
    }//GEN-LAST:event_btOKActionPerformed

    private void btCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCANCELActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btCANCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SudokuOptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SudokuOptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SudokuOptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SudokuOptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuOptionsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSOLVER_GROUP;
    private javax.swing.JButton btCANCEL;
    private javax.swing.JButton btOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCHARACTERS;
    private javax.swing.JLabel lbIMPORT_TYPE;
    private javax.swing.JLabel lbSEPARATOR;
    private javax.swing.JLabel lbSOLVER;
    private javax.swing.JRadioButton rbALPHABET;
    private javax.swing.JRadioButton rbBACKTRACK;
    private javax.swing.JRadioButton rbDANCING_LINKS;
    private javax.swing.JRadioButton rbHUMAN;
    private javax.swing.JRadioButton rbNUMBERS;
    private javax.swing.JTextField tfCHARACTERS;
    private javax.swing.JTextField tfSEPARATOR;
    // End of variables declaration//GEN-END:variables
}
