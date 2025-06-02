package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */



import domain.Item;
import domain.PurchaseOrder;
import domain.PurchaseOrderItem;
import domain.PurchaseRequisition;
import domain.Supplier;
import domain.User;
import function.NavigationManager;
import gui.table.PurchaseOrderTable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.ItemRepo;
import repository.PurchaseOrderItemRepo;
import repository.PurchaseOrderRepo;
import repository.PurchaseRequisitionRepo;
import repository.UserRepo;
import repository.SupplierRepo;

/**
 *
 * @author Kang Wei
 */
public class PurchaseOrderView extends javax.swing.JFrame {
    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private Long viewId;
    private PurchaseOrder report;
    private PurchaseOrderItem poi;
    private PurchaseRequisition prr;
    private User user;
    private Item item;
    private Supplier sup;
    
    public PurchaseOrderView() {
        initComponents();
        this.setLocationRelativeTo(null); //this will center your frame
    }

    public PurchaseOrderView(Long id) {
        this.viewId = id;
        initComponents();
        this.setLocationRelativeTo(null);
        setView();
    }
    
    // Custom Methods

//      private void setView() {
//        PurchaseOrderRepo por = new PurchaseOrderRepo();
//        PurchaseOrderItemRepo poir = new PurchaseOrderItemRepo();
//        
//        try {
//            report = por.getById(viewId);
//            
//            if (this.report == null) {
//                return;
//            }
//            
//            purchaseOrderCodeField.setText(report.getPurchaseOrderCode());
//            purchaseRequisitionCodeField.setText(prr.getPurchaseRequisitionCode());
//            approveUserField.setText(user.getFullName());
//            itemCodeField.setText(item.getItemCode());
//            createdUserField.setText(user.getFullName());
//            supplierCodeField.setText(sup.getSupplierCode());
//            orderDateField.setText(String.valueOf(report.getOrderDate()));
//            expectedDateField.setText(String.valueOf(report.getExpectedDeliveryDate()));
//            statusField.setText(report.getStatus());
//            quantityField.setText(String.valueOf(poi.getQuantity()));
//            unitCostField.setText(String.valueOf(poi.getUnitCost()));
//            totalAmountField.setText(String.valueOf(report.getTotalAmount()));
//            
////            
////            LocalDate localDate = report.getCreationDate(); // example date
////            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
////
////            reportDateField.setDate(date);
//            
//            
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
//        } catch (IOException ex) {
//            Logger.getLogger(FinancialReportNew.class.getName()).log(Level.SEVERE, null, ex);
//        }
        private void setView() {
            PurchaseOrderRepo por = new PurchaseOrderRepo();
            PurchaseOrderItemRepo poir = new PurchaseOrderItemRepo();
            PurchaseRequisitionRepo prrRepo = new PurchaseRequisitionRepo();
            UserRepo userRepo = new UserRepo();
            ItemRepo itemRepo = new ItemRepo();
            SupplierRepo supRepo = new SupplierRepo();

            try {
                // Load the main PurchaseOrder object
                report = por.getById(viewId);
                if (report == null) return;

                // Load related objects using their IDs
                prr = prrRepo.getPurchaseRequisitionById(report.getPurchaseRequisitionId());
                sup = supRepo.getSupplierById(report.getSupplierId());
                User createdUser = userRepo.getUserById(report.getCreatedById());
                User approvedUser = userRepo.getUserById(report.getApprovedById());
                
                // Load PurchaseOrderItems list, then get first item's item object as example
                List<PurchaseOrderItem> items = poir.getByPurchaseOrderId(report.getId());
                if (!items.isEmpty()) {
                    poi = items.get(0);
                    item = itemRepo.getById(poi.getItemId());// or get by ID if available
               }

                // Now safely set UI fields (check for nulls)
                if (approvedUser != null) {
                    approveUserField.setText(approvedUser.getFullName());
                }

                if (createdUser != null) {
                    createdUserField.setText(createdUser.getFullName());
                }
                purchaseOrderCodeField.setText(report.getPurchaseOrderCode());
                if (prr != null) purchaseRequisitionCodeField.setText(prr.getPurchaseRequisitionCode());
                
                if (item != null) itemCodeField.setText(item.getItemCode());
                if (sup != null) supplierCodeField.setText(sup.getSupplierCode());
                orderDateField.setText(String.valueOf(report.getOrderDate()));
                expectedDateField.setText(String.valueOf(report.getExpectedDeliveryDate()));
                statusField.setText(report.getStatus());
                if (poi != null) {
                    quantityField.setText(String.valueOf(poi.getQuantity()));
                    unitCostField.setText(String.valueOf(poi.getUnitCost()));
                }
                totalAmountField.setText(String.valueOf(report.getTotalAmount()));

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading purchase order: " + e.getMessage());
            
        }
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        purchaseOrderCodeField = new javax.swing.JTextField();
        purchaseRequisitionCodeField = new javax.swing.JTextField();
        approveUserField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        itemCodeField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        createdUserField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        supplierCodeField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        orderDateField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        expectedDateField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        unitCostField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        totalAmountField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField6.setText("Input");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Label");

        jTextField10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField10.setText("Input");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Label");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Order Details");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Purchase Order Code");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Purchase Requisition Code");

        purchaseOrderCodeField.setEditable(false);
        purchaseOrderCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        purchaseOrderCodeField.setText("...");
        purchaseOrderCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderCodeFieldActionPerformed(evt);
            }
        });

        purchaseRequisitionCodeField.setEditable(false);
        purchaseRequisitionCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        purchaseRequisitionCodeField.setText("...");

        approveUserField.setEditable(false);
        approveUserField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        approveUserField.setText("...");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Approve By User");

        itemCodeField.setEditable(false);
        itemCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemCodeField.setText("...");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Item Code");

        createdUserField.setEditable(false);
        createdUserField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        createdUserField.setText("...");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Created By User ");

        supplierCodeField.setEditable(false);
        supplierCodeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        supplierCodeField.setText("...");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Supplierd Code");

        orderDateField.setEditable(false);
        orderDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderDateField.setText("...");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Order Date");

        expectedDateField.setEditable(false);
        expectedDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        expectedDateField.setText("...");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Expected Delivery Date");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusField.setText("...");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Status");

        quantityField.setEditable(false);
        quantityField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        quantityField.setText("...");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Quantity");

        unitCostField.setEditable(false);
        unitCostField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unitCostField.setText("...");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Unit Cost ");

        totalAmountField.setEditable(false);
        totalAmountField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalAmountField.setText("...");
        totalAmountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountFieldActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Total Amount");

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(purchaseRequisitionCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(75, 75, 75))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unitCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(approveUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createdUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(50, 50, 50)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(supplierCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(expectedDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseOrderCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchaseRequisitionCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(approveUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        deleteButton.setBackground(new java.awt.Color(255, 0, 51));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(153, 153, 153));
        backButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(102, 204, 0));
        editButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(18, 18, 18)
                .addComponent(editButton)
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        NavigationManager.getInstance().goBack();
    }//GEN-LAST:event_backButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
            PurchaseOrderRepo poRepo = new PurchaseOrderRepo();
            PurchaseOrderItemRepo poiRepo = new PurchaseOrderItemRepo();

            PurchaseOrder po = poRepo.getById(viewId);
            List<PurchaseOrderItem> poiList = poiRepo.getByPurchaseOrderId(viewId);

            if (po == null || poiList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Purchase order or items not found.");
                return;
            }

            PurchaseOrderItem poi = poiList.get(0); // Or handle multiple items as needed

            PurchaseOrderEdit editFrame = new PurchaseOrderEdit(po, poi);
            editFrame.setVisible(true);
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void purchaseOrderCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseOrderCodeFieldActionPerformed

    private void totalAmountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountFieldActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this purchase order and all its related items? This action cannot be undone.",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (report == null) {
                    JOptionPane.showMessageDialog(this, "Purchase order not found or already deleted.", "Not Found", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    PurchaseOrderRepo poRepo = new PurchaseOrderRepo();
                    PurchaseOrderItemRepo poiRepo = new PurchaseOrderItemRepo();

                    // Delete all purchase order items related to this purchase order
                    List<PurchaseOrderItem> itemsToDelete = poiRepo.getByPurchaseOrderId(report.getId());
                    for (PurchaseOrderItem item : itemsToDelete) {
                        poiRepo.delete(item);
                    }

                    // Delete the purchase order
                    poRepo.delete(report);

                    JOptionPane.showMessageDialog(this, "Purchase order and related items deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Navigate back to the previous screen or table view
                    PurchaseOrderTable tablePage = new PurchaseOrderTable();
                    tablePage.setVisible(true);
                    this.dispose();

                } catch (IOException ex) {
                    Logger.getLogger(PurchaseOrderView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Failed to delete purchase order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_deleteButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new PurchaseOrderView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField approveUserField;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField createdUserField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField expectedDateField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JTextField itemCodeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField orderDateField;
    private javax.swing.JTextField purchaseOrderCodeField;
    private javax.swing.JTextField purchaseRequisitionCodeField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTextField statusField;
    private javax.swing.JTextField supplierCodeField;
    private javax.swing.JTextField totalAmountField;
    private javax.swing.JTextField unitCostField;
    // End of variables declaration//GEN-END:variables
}
