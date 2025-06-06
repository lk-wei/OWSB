/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.table;

import component.ButtonEditor;
import component.ButtonRenderer;
import domain.User;
import function.FrontendPermissionManager;
import function.NavigationManager;
import function.UserSession;
import gui.FinancialReportView;
import gui.PaymentNew;
import gui.PaymentView;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import repository.PaymentRepo;

/**
 *
 * @author Kang Wei
 */
public class PaymentTable extends javax.swing.JFrame {

    /**
     * Creates new form DashBoardSample
     */
    private User currentUser;
    
    public PaymentTable() {
        this.currentUser = UserSession.getInstance().getCurrentUser();

        initComponents();
        this.setLocationRelativeTo(null); //this will center your frame
        FrontendPermissionManager.applyButtonPermissions(
                currentUser,
                "pay",
                newBtn,
                null,
                null
        );
        
        updateTable();
    }

    // Custom Methods
    private void updateTable() {
        PaymentRepo repo = new PaymentRepo();
        try {
            jTable1.setModel(repo.getTableModel());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        // Hide the ID column
        TableColumn idColumn = jTable1.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
        idColumn.setResizable(false);

        int lastColumnIndex = jTable1.getColumnModel().getColumnCount() - 1;
        TableColumn actionColumn = jTable1.getColumnModel().getColumn(lastColumnIndex);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);

                for (java.awt.event.ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }

                button.addActionListener(e -> {
                    int modelRow = table.convertRowIndexToModel(row);
                    Object rawId = table.getModel().getValueAt(modelRow, 0);
                    Long id = Long.valueOf(rawId.toString());
                    System.out.println("ID: " + id);

                    try {
                        NavigationManager.getInstance().openFrame(new PaymentView(id), PaymentTable.this);
                    } catch (IOException ex) {
                        Logger.getLogger(PaymentTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                return c;
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navBarSample2 = new component.NavBarSample();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        newBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().add(navBarSample2, java.awt.BorderLayout.NORTH);

        jPanel2.setMinimumSize(new java.awt.Dimension(1000, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Payment Table");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Payment Code", "Supplier Name", "Date", "Total Amount", "Payment Amount", ""
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        newBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        newBtn.setText("+ New");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(newBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        updateTable();
    }//GEN-LAST:event_formWindowActivated

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        try {
            // TODO add your handling code here:
            NavigationManager.getInstance().openFrame(new PaymentNew(), this);
        } catch (IOException ex) {
            Logger.getLogger(PaymentTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_newBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private component.NavBarSample navBarSample2;
    private javax.swing.JButton newBtn;
    // End of variables declaration//GEN-END:variables
}
