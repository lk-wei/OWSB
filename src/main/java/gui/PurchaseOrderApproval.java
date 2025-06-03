/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import component.ButtonEditor;
import component.ButtonRenderer;
import domain.FinancialApproval;
import domain.Item;
import domain.PurchaseOrder;
import domain.PurchaseOrderItem;
import domain.PurchaseRequisition;
import domain.Supplier;
import domain.User;
import function.NavigationManager;
import java.awt.Component;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import repository.FinancialApprovalRepo;
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
public class PurchaseOrderApproval extends javax.swing.JFrame implements SelectionListener<PurchaseOrderItem>{
    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private List<PurchaseOrderItem> itemList = new ArrayList<>();
    private List<PurchaseOrderItem> oriItemList = new ArrayList<>();
    private Supplier SelectedSupplier;
    private PurchaseRequisition SelectedPR;
    private double totalAmount;
    private Long viewId;
    private PurchaseOrder toApprove;
    private User currentUser;
    
    public PurchaseOrderApproval(Long viewId) throws IOException {
        this.viewId = viewId;
        
        currentUser = new User();
        currentUser.setId(1L);
        
        
        PurchaseOrderItemRepo poiRepo = new PurchaseOrderItemRepo();
        itemList = poiRepo.getByPurchaseOrderId(viewId);
        oriItemList = itemList;
        
        initComponents();
        tableModel = (DefaultTableModel) jTable3.getModel();
        this.setLocationRelativeTo(null); //this will center your frame
        
        setView();
        updateTable();
        recalculateTotalAmount();
    }
    private void setView() {
        PurchaseOrderRepo por = new PurchaseOrderRepo();
        PurchaseRequisitionRepo prr = new PurchaseRequisitionRepo();
        
        try {
            toApprove = por.getById(viewId);
            PurchaseRequisition pr = prr.getById(toApprove.getPurchaseRequisitionId());
            User u = new UserRepo().getById(pr.getRequestedById());
            Supplier s = new SupplierRepo().getById(toApprove.getSupplierId());
            
            if (this.toApprove == null) {
                System.out.println("po not found");
                return;
            }
            purchaseOrderCodeField.setText(toApprove.getPurchaseOrderCode());
            expDevDateField.setText(toApprove.getExpectedDeliveryDate().toString());
            orderDateField.setText(toApprove.getOrderDate().toString());
            totalAmountField.setValue(toApprove.getTotalAmount());
            
            if (pr == null) {
                System.out.println("pr not found");
                return;
            }
            prCodeField.setText(pr.getPurchaseRequisitionCode());
            
            if (u == null) {
                System.out.println("pr creator not found");
                return;
            }
            reqByField.setText(u.getUserName());
            
            if (s == null) {
                System.out.println("Supplier not found");
                return;
            }
            supplierCodeField.setText(s.getSupplierCode());
            supplierNameField.setText(s.getSuppliername());
            
            
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(FinancialReportNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateTable() throws IOException {
        removeAllRows();
        
        if(itemList != null){
            for (PurchaseOrderItem poi : itemList) {
                Item i = new ItemRepo().getById(poi.getItemId());
                
                double totalCost = i.getUnitCost() * poi.getQuantity();
                
                tableModel.addRow(new Object[]{
                    poi.getId(),
                    i.getItemCode(), 
                    i.getItemName(), 
                    i.getUnitCost(),
                    poi.getQuantity(),
                    totalCost,
                    "Edit"});
                
                System.out.println("table updated");
            }
        }
        
        TableColumn idColumn = jTable3.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
        idColumn.setResizable(false);
        
        TableColumn actionColumn = jTable3.getColumnModel().getColumn(6);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);

                button.addActionListener(e -> {
                    fireEditingStopped();
                    
                    // add button function here
                    Object rawId = table.getModel().getValueAt(row, 0);
                    Long id = Long.valueOf(rawId.toString());
                    System.out.println(id);
                    
                    try{ 
                        new ApprovalItem(PurchaseOrderApproval.this, PurchaseOrderApproval.this, id).setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                    try {
                        updateTable();
                    } catch (IOException ex) {
                        Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                return c;
            }
        });
        
        totalAmountField.setValue(totalAmount);
    }
    
    private void removeAllRows() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    
    // Inside PurchaseOrderNew.java
    private void recalculateTotalAmount() {
        this.totalAmount = 0.0; // Reset class member totalAmount
        if (itemList != null) {
            for (PurchaseOrderItem poi : itemList) {
                this.totalAmount += poi.getUnitCost() * poi.getQuantity(); // Make sure POItem has unitCost
            }
        }
        totalAmountField.setValue(this.totalAmount); // Update the JSpinner
        System.out.println("Recalculated total PO Amount: " + this.totalAmount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        reqByField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        supplierNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        approveBtn = new javax.swing.JButton();
        totalAmountField = new javax.swing.JSpinner();
        cancelButton1 = new javax.swing.JButton();
        supplierCodeField = new javax.swing.JTextField();
        prCodeField = new javax.swing.JTextField();
        purchaseOrderCodeField = new javax.swing.JTextField();
        expDevDateField = new javax.swing.JTextField();
        orderDateField = new javax.swing.JTextField();

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "PO Code", "Created Date", "Created By", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Order Approval");
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 100));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        inputPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        inputPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Purchase Requsition Code");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Requested By User");

        reqByField.setEditable(false);
        reqByField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Supplier Name");

        supplierNameField.setEditable(false);
        supplierNameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Purchase Order Code");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Expected Delivery Date ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Order Date");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Supplier Code");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Item Code", "Name", "Unit Cost", "Qty", "Total", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        approveBtn.setBackground(new java.awt.Color(102, 204, 0));
        approveBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        approveBtn.setText("Approve");
        approveBtn.setPreferredSize(new java.awt.Dimension(84, 32));
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });

        totalAmountField.setEnabled(false);

        cancelButton1.setBackground(new java.awt.Color(255, 102, 102));
        cancelButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelButton1.setText("Reject");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });

        supplierCodeField.setEditable(false);
        supplierCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        prCodeField.setEditable(false);
        prCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        purchaseOrderCodeField.setEditable(false);
        purchaseOrderCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        purchaseOrderCodeField.setEnabled(false);

        expDevDateField.setEditable(false);
        expDevDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        expDevDateField.setEnabled(false);

        orderDateField.setEditable(false);
        orderDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderDateField.setEnabled(false);

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel13)
                        .addGap(261, 261, 261)
                        .addComponent(jLabel7))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel11))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel2)
                                .addGap(187, 187, 187))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(prCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(reqByField, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(supplierCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel10)
                        .addGap(214, 214, 214)
                        .addComponent(jLabel12))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addComponent(cancelButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancelButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addGap(299, 299, 299)
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(expDevDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(48, 48, 48))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(6, 6, 6)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reqByField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expDevDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jPanel1.add(inputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        NavigationManager.getInstance().goBack();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        // TODO add your handling code here:
        FinancialApprovalRepo far = new FinancialApprovalRepo();
        PurchaseOrderRepo por = new PurchaseOrderRepo();
        PurchaseOrderItemRepo poir = new PurchaseOrderItemRepo();
        
        if(itemList == null){
            System.out.println("Empty item list");
            return;
        }
        
        try {
             // update status to PO to approved
            toApprove.setTotalAmount(totalAmount);
            toApprove.setStatus("Approved");
            por.update(toApprove);
            
            
            // Modified PO items
            for(PurchaseOrderItem i : itemList){
                poir.update(i);
            }
            
            // create the Financial Approval
            far.create(new FinancialApproval(
                    null,
                    toApprove.getId(),
                    currentUser.getId(),
                    LocalDate.now()
            ));
            JOptionPane.showMessageDialog(null, "PO Approved. Any Modification will be reflected on the PO!");
            NavigationManager.getInstance().goBack();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_approveBtnActionPerformed

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        // TODO add your handling code here:
        FinancialApprovalRepo far = new FinancialApprovalRepo();
        PurchaseOrderRepo por = new PurchaseOrderRepo();
        PurchaseOrderItemRepo poir = new PurchaseOrderItemRepo();
        
        if(itemList == null){
            System.out.println("Empty item list");
            return;
        }
        
        try {
             // update status to PO to approved
            toApprove.setStatus("Rejected");
            por.update(toApprove);
            
            // create the Financial Approval
            far.create(new FinancialApproval(
                    null,
                    toApprove.getId(),
                    currentUser.getId(),
                    LocalDate.now()
            ));
            
            JOptionPane.showMessageDialog(null, "PO Rejected!");
            NavigationManager.getInstance().goBack();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelButton1ActionPerformed

    @Override
    public void onSelected(PurchaseOrderItem item) {
        try {
            updateList(item);
        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(item.getQuantity());// Or any custom logic
    }
    
    public void updateList(PurchaseOrderItem poItem) throws IOException{
        for(PurchaseOrderItem i : itemList){
            if(Objects.equals(i.getId(), poItem.getId())){
                i.setQuantity(poItem.getQuantity());
            }
        }
            
        recalculateTotalAmount();
        updateTable();
    }
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
            java.util.logging.Logger.getLogger(PurchaseOrderApproval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderApproval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderApproval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderApproval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new PurchaseOrderApproval(10L).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PurchaseOrderApproval.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveBtn;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JTextField expDevDateField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField orderDateField;
    private javax.swing.JTextField prCodeField;
    private javax.swing.JTextField purchaseOrderCodeField;
    private javax.swing.JTextField reqByField;
    private javax.swing.JTextField supplierCodeField;
    private javax.swing.JTextField supplierNameField;
    private javax.swing.JSpinner totalAmountField;
    // End of variables declaration//GEN-END:variables
}
