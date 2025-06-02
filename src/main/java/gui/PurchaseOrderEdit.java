/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import domain.Item;
import domain.PurchaseOrder;
import domain.PurchaseOrderItem;
import domain.PurchaseRequisition;
import domain.Supplier;
import domain.User;
import gui.table.PurchaseOrderTable;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.ItemRepo;
import repository.PurchaseOrderItemRepo;
import repository.PurchaseOrderRepo;
import repository.PurchaseRequisitionRepo;
import repository.SupplierRepo;
import repository.UserRepo;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderEdit extends javax.swing.JFrame {
    /**
     * Creates new form DashBoardSample
     */
    private PurchaseOrder purchaseOrder;
    private PurchaseOrderItem purchaseOrderItem;
    private DefaultTableModel tableModel;
    
    public PurchaseOrderEdit() {
        initComponents();
        this.setLocationRelativeTo(null); //this will center your frame
        
        initTableModel();
    }
    public PurchaseOrderEdit(PurchaseOrder po, PurchaseOrderItem poi) {
        this.purchaseOrder = po;
        this.purchaseOrderItem = poi;
        initComponents();
        this.setLocationRelativeTo(null);
        setView();
    }

    
    // Custom Methods
    private void setView() {
        PurchaseOrderRepo poRepo = new PurchaseOrderRepo();
        PurchaseOrderItemRepo poiRepo = new PurchaseOrderItemRepo();
        PurchaseRequisitionRepo prrRepo = new PurchaseRequisitionRepo();
        UserRepo userRepo = new UserRepo();
        SupplierRepo supRepo = new SupplierRepo();
        ItemRepo itemRepo = new ItemRepo();

        try {
            // Load main objects if not already loaded
            if (purchaseOrder == null) purchaseOrder = poRepo.getById(purchaseOrder.getId());
            if (purchaseOrderItem == null) {
                List<PurchaseOrderItem> poiList = poiRepo.getByPurchaseOrderId(purchaseOrder.getId());
                if (!poiList.isEmpty()) purchaseOrderItem = poiList.get(0);
            }

            // Load related data
            PurchaseRequisition prr = prrRepo.getPurchaseRequisitionById(purchaseOrder.getPurchaseRequisitionId());
            User createdUser = userRepo.getUserById(purchaseOrder.getCreatedById());
            User approvedUser = userRepo.getUserById(purchaseOrder.getApprovedById());
            Supplier supplier = supRepo.getSupplierById(purchaseOrder.getSupplierId());
            Item item = itemRepo.getById(purchaseOrderItem.getItemId());

            // Set fields
            purchaseOrderCodeField.setText(purchaseOrder.getPurchaseOrderCode());
            purchaseRequisitionCodeField.setText(prr != null ? prr.getPurchaseRequisitionCode() : "");
            approvedUserField.setText(approvedUser != null ? approvedUser.getFullName() : "");
            createdUserField.setText(createdUser != null ? createdUser.getFullName() : "");
            orderDateField.setText(purchaseOrder.getOrderDate().toString());
            expectedDateField.setText(purchaseOrder.getExpectedDeliveryDate().toString());
            statusField.setText(purchaseOrder.getStatus());
            quantityField.setText(String.valueOf(purchaseOrderItem.getQuantity()));
            uniCostField.setText(String.valueOf(purchaseOrderItem.getUnitCost()));
            totalAmountField.setText(String.valueOf(purchaseOrder.getTotalAmount()));
            supplierCodeField.setText(supplier != null ? supplier.getSupplierCode() : "");
            itemCodeField.setText(item != null ? item.getItemCode() : "");

            // Set editable state
            statusField.setEditable(true);
            quantityField.setEditable(true);
            totalAmountField.setEditable(true);

            // Other fields readonly
            purchaseOrderCodeField.setEditable(false);
            purchaseRequisitionCodeField.setEditable(false);
            approvedUserField.setEditable(false);
            createdUserField.setEditable(false);
            orderDateField.setEditable(false);
            expectedDateField.setEditable(false);
            uniCostField.setEditable(false);
            supplierCodeField.setEditable(false);
            itemCodeField.setEditable(false);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading purchase order: " + e.getMessage());
        }
    }
    
    
    private void initTableModel() {
        tableModel = (DefaultTableModel) jTable2.getModel();
        tableModel.setRowCount(0);
        
        tableModel.addRow(new Object[]{"", "", "", ""});
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        totalAmountField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        uniCostField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        orderDateField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        expectedDateField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        supplierCodeField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        createdUserField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        approvedUserField = new javax.swing.JTextField();
        itemCodeField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        purchaseRequisitionCodeField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        purchaseOrderCodeField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

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
        jLabel1.setText("Purchase Order Edit");
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 100));

        inputPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        inputPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO Code", "PR Code", "Item Code", "Supplier Code", "Order Date", "Expected Delivery Date", "Status", "Created By User", "Approve By User", "Quantity", "Unit Cost", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Long.class, java.lang.Long.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setShowGrid(true);
        jScrollPane3.setViewportView(jTable2);

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
                cancel(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Save");
        jButton2.setPreferredSize(new java.awt.Dimension(84, 32));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        totalAmountField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalAmountField.setText("...");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Approve By User");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Total Amount");

        uniCostField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uniCostField.setText("...");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Unit Cost ");

        quantityField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        quantityField.setText("...");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Quantity");

        statusField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusField.setText("...");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Status");

        orderDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderDateField.setText("...");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Order Date");

        expectedDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        expectedDateField.setText("...");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Expected Delivery Date");

        supplierCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        supplierCodeField.setText("...");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Supplierd Code");

        createdUserField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        createdUserField.setText("...");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Created By User");

        approvedUserField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        approvedUserField.setText("...");

        itemCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemCodeField.setText("...");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Item Code");

        purchaseRequisitionCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        purchaseRequisitionCodeField.setText("...");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Purchase Requisition Code");

        purchaseOrderCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        purchaseOrderCodeField.setText("...");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Purchase Order Code");

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(purchaseRequisitionCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(approvedUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18)
                            .addComponent(uniCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(50, 50, 50)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(supplierCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expectedDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))))
                .addGap(75, 75, 75))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchaseRequisitionCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(approvedUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(itemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createdUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expectedDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(uniCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            purchaseOrder.setStatus(statusField.getText());
            purchaseOrderItem.setQuantity(Integer.parseInt(quantityField.getText()));
            purchaseOrder.setTotalAmount(Double.parseDouble(totalAmountField.getText()));

            PurchaseOrderRepo poRepo = new PurchaseOrderRepo();
            PurchaseOrderItemRepo poiRepo = new PurchaseOrderItemRepo();

            poRepo.update(purchaseOrder);
            poiRepo.update(purchaseOrderItem);

            JOptionPane.showMessageDialog(this, "Purchase order updated successfully.");
            PurchaseOrderTable tablePage = new PurchaseOrderTable();
            tablePage.setVisible(true);
            this.dispose();
            // Optionally reopen PurchaseOrderView or list
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel
        PurchaseOrderTable second = new PurchaseOrderTable();
        second.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancel

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
            java.util.logging.Logger.getLogger(PurchaseOrderEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseOrderEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField approvedUserField;
    private javax.swing.JTextField createdUserField;
    private javax.swing.JTextField expectedDateField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JTextField itemCodeField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField orderDateField;
    private javax.swing.JTextField purchaseOrderCodeField;
    private javax.swing.JTextField purchaseRequisitionCodeField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTextField statusField;
    private javax.swing.JTextField supplierCodeField;
    private javax.swing.JTextField totalAmountField;
    private javax.swing.JTextField uniCostField;
    // End of variables declaration//GEN-END:variables
}
