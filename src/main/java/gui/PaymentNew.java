/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import component.ButtonEditor;
import component.ButtonRenderer;
import domain.Payment;
import domain.PaymentItem;
import domain.PurchaseOrder;
import domain.Supplier;
import function.NavigationManager;
import java.awt.Component;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import repository.PaymentItemRepo;
import repository.PaymentRepo;
import repository.PurchaseOrderItemRepo;
import repository.PurchaseOrderRepo;
import repository.SupplierRepo;

/**
 *
 * @author zuwei
 */
public class PaymentNew extends javax.swing.JFrame {

    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private List<PurchaseOrder> selectedPO = new ArrayList<>();

    public PaymentNew() throws IOException {
        initComponents();
        initTable();
        this.setLocationRelativeTo(null); //this will center your frame

        tableModel = (DefaultTableModel) jTable2.getModel();

        // adds items to the combobox 
        List<Supplier> supplierList = new SupplierRepo().getAll();
        for (Supplier item : supplierList) {
            supplierField.addItem(item);
        }
    }

    // Custom Methods
    private void removeAllRows() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public void initTable() {
        // Inside initComponents() in your PaymentNew.java (or similar class)
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{ // Data will be populated by updateTable method
                },
                new String[]{
                    "PO ID", "PO Code", "PO Date", "PO Amount", "Select for Payment?" // 5 Columns
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, // PO ID (will be hidden)
                java.lang.String.class, // PO Code
                java.lang.Object.class, // PO Date (use Object for LocalDate, or String if formatted)
                java.lang.Double.class, // PO Amount
                java.lang.Boolean.class // Select for Payment? (renders as checkbox)
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false,
                true // Only "Select for Payment?" checkbox is editable
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        // Optionally hide the PO ID column (column 0) after model is set
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            TableColumn idColumn = jTable2.getColumnModel().getColumn(0);
            idColumn.setMinWidth(0);
            idColumn.setMaxWidth(0);
            idColumn.setPreferredWidth(0);
            idColumn.setResizable(false);
        }

    }

    public void updateTable(Long supplierId) throws IOException {
        if (this.tableModel == null) { // Safety check, though should be set in constructor
            this.tableModel = (DefaultTableModel) jTable2.getModel();
        }
        this.tableModel.setRowCount(0); // Clear existing rows from the instance model
        // if (this.displayedPurchaseOrders != null) this.displayedPurchaseOrders.clear();

        if (supplierId == null) {
            System.out.println("Supplier ID is null, cannot fetch purchase orders.");
            return;
        }

        PurchaseOrderRepo poRepo = new PurchaseOrderRepo(); // Assuming you have this
        List<PurchaseOrder> purchaseOrderList = poRepo.getBySupplierId(supplierId); // This method needs to exist in PurchaseOrderRepo

        if (purchaseOrderList != null) {
            // this.displayedPurchaseOrders.addAll(purchaseOrderList); // Store fetched POs if needed later
            for (PurchaseOrder po : purchaseOrderList) {
                // Add data matching the 5 columns: "PO ID", "PO Code", "PO Date", "PO Amount", "Select for Payment?"
                this.tableModel.addRow(new Object[]{
                    po.getId(),
                    po.getPurchaseOrderCode(),
                    po.getOrderDate(),
                    po.getTotalAmount(),
                    Boolean.FALSE
                });
            }
            System.out.println("Table updated with " + purchaseOrderList.size() + " POs for supplier ID: " + supplierId);
        } else {
            System.out.println("No purchase orders found for supplier ID: " + supplierId);
        }

        // Add a TableModelListener to recalculate total when "Select for Payment?" changes
        this.tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Check if the change was in the "Select for Payment?" column (index 4)
                // or if rows were inserted/deleted which might also affect total.
                if (e.getColumn() == 4 || e.getColumn() == TableModelEvent.ALL_COLUMNS
                        || e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                    if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != 4) {
                        return; // Only care about updates to the checkbox column for this specific recalculation
                    }
                    calculateTotalAmount();
                }
            }
        });
        calculateTotalAmount(); // Calculate initial total (will be 0)
    }

    private void calculateTotalAmount() {
        // empty total amount first
        double totalAmount = 0.0;

        // Iterate through all rows in the table model
        for (int modelRow = 0; modelRow < tableModel.getRowCount(); modelRow++) {

            // Check the "Include?" column (assuming it's at index 4 and stores Boolean values)
            Object includeValue = tableModel.getValueAt(modelRow, 4);
            boolean isIncluded = false;
            if (includeValue instanceof Boolean) {
                isIncluded = (Boolean) includeValue;
            }

            if (isIncluded) {
                // If included, get the amount from the "Amount" column (index 3)
                Object amountObj = tableModel.getValueAt(modelRow, 3);
                if (amountObj != null) {
                    try {
                        // The model should already store this as a Double if getColumnClass is correct
                        if (amountObj instanceof Number number) { // More robust check
                            totalAmount += number.doubleValue();
                        } else {
                            // Fallback if it's a String that needs parsing (less ideal if column class is Double)
                            double amount = Double.parseDouble(amountObj.toString());
                            totalAmount += amount;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Could not parse amount in row " + modelRow + ": " + amountObj);
                        // Optionally, show an error or log, but for a sum, often best to ignore unparseable
                    }
                }
            }
        }

        totalAmiuntField.setValue(totalAmount);
        System.out.println("Total amount of included payments: " + totalAmount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        supplierField = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dateField = new com.toedter.calendar.JDateChooser();
        codeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        supplierCodeField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        paymentAmountField = new javax.swing.JSpinner();
        totalAmiuntField = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("New Payment");
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 100));

        inputPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        inputPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Supplier");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Date");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Total Amount");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Purchase Order Code", "Date", "Amount", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setShowGrid(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable2PropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        supplierField.setPreferredSize(new java.awt.Dimension(64, 23));
        supplierField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Payment Amount");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Payment Code");

        supplierCodeField.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Supplier Code");

        totalAmiuntField.setEnabled(false);

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(paymentAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalAmiuntField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(supplierField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(dateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierCodeField)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(75, 75, 75))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codeField, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(dateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(supplierCodeField)
                    .addComponent(supplierField, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalAmiuntField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Create");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 583, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // add pyment
            Supplier selectedSupplier = (Supplier) supplierField.getSelectedItem();

            Date selectedDate = dateField.getDate();
            // When merge, this field will automatically get the current user id

            // Check for null to avoid NullPointerException
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Please select a date.");
                return;
            }
            
            double totalAmt = ((Number) totalAmiuntField.getValue()).doubleValue();
            double payAmt = ((Number) paymentAmountField.getValue()).doubleValue();
            
            if (payAmt > totalAmt) {
                JOptionPane.showMessageDialog(null, "Payment Amount cannot be More than Totak Amount");
                return;
            }

            // Convert Date to LocalDate
            LocalDate saleDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Payment newPayment = new Payment(
                    null,
                    codeField.getText(), 
                    selectedSupplier.getId(), 
                    selectedSupplier.getSuppliername(), 
                    saleDate, 
                    totalAmt, 
                    payAmt
            );
            new PaymentRepo().create(newPayment);
                
                // get new created fr ID
            Long newlyCreatedPaymentId = newPayment.getId();
   
            // add payment item
            // Iterate through all rows in the table model
            for (int modelRow = 0; modelRow < tableModel.getRowCount(); modelRow++) {

                // Check the "Include?" column (assuming it's at index 4 and stores Boolean values)
                Object includeValue = tableModel.getValueAt(modelRow, 4);
                boolean isIncluded = false;
                if (includeValue instanceof Boolean aBoolean) {
                    isIncluded = aBoolean;
                }

                if (isIncluded) {
                    Object rawId = tableModel.getValueAt(modelRow, 0);
                    Long id = Long.valueOf(rawId.toString());

                    PurchaseOrder poi = new PurchaseOrderRepo().getById(id);

                    new PaymentItemRepo().create(new PaymentItem(
                            null,
                            newlyCreatedPaymentId,
                            codeField.getText(),
                            id,
                            poi.getTotalAmount(),
                            poi.getPurchaseOrderCode()
                    ));

                }
            }
            
            JOptionPane.showMessageDialog(null, "Payment Record Created successfully!");
            NavigationManager.getInstance().goBack();
        } catch (IOException ex) {
            Logger.getLogger(PaymentNew.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        calculateTotalAmount();
    }//GEN-LAST:event_jTable2MouseClicked

    private void supplierFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierFieldActionPerformed
        // TODO add your handling code here:
        Supplier selectedSupplier = (Supplier) supplierField.getSelectedItem();
        if (selectedSupplier != null) {
            supplierCodeField.setText(selectedSupplier.getSupplierCode());
        }

        try {
            updateTable(selectedSupplier.getId());
        } catch (IOException ex) {
            Logger.getLogger(PaymentNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_supplierFieldActionPerformed

    private void jTable2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable2PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2PropertyChange

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
            java.util.logging.Logger.getLogger(PaymentNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PaymentNew().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PaymentNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codeField;
    private com.toedter.calendar.JDateChooser dateField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JSpinner paymentAmountField;
    private javax.swing.JTextField supplierCodeField;
    private javax.swing.JComboBox<Supplier> supplierField;
    private javax.swing.JSpinner totalAmiuntField;
    // End of variables declaration//GEN-END:variables
}
