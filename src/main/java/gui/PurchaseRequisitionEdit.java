/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import component.ButtonEditor;
import component.ButtonRenderer;
import domain.Item;
import domain.PurchaseRequisition;
import domain.PurchaseRequisitionItem;
import domain.Supplier;
import function.IdGenerator;  // Import the IdGenerator class
import function.NavigationManager;
import repository.ItemRepo;
import repository.PurchaseRequisitionItemRepo;
import repository.PurchaseRequisitionRepo;
import repository.SupplierRepo;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import gui.table.PurchaseRequsitionTable;
import java.awt.Component;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Kang Wei
 */
public class PurchaseRequisitionEdit extends javax.swing.JFrame implements SelectionListener<PurchaseRequisitionItem>{
    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private PurchaseRequisition purchaseR;
    private List<PurchaseRequisitionItem> oriItemList = new ArrayList<>();
    private List<PurchaseRequisitionItem> itemList = new ArrayList<>();
    private Long viewId;
    
    public PurchaseRequisitionEdit(Long viewId) throws IOException {
        this.viewId =  viewId;
        oriItemList = new PurchaseRequisitionItemRepo().getItemsByRequisitionId(viewId);
        if (oriItemList == null) { // defensively handle null from repo
            oriItemList = new ArrayList<>();
        }
        itemList = new ArrayList<>(oriItemList);
        
        
        initComponents();
        tableModel = (DefaultTableModel) jTable2.getModel();
        this.setLocationRelativeTo(null); //this will center your frame
        setView();
        updateTable();
    }
    
    // Custom Methods
    private void setView() {
        PurchaseRequisitionRepo prr = new PurchaseRequisitionRepo();
        
        try {
            purchaseR = prr.getById(viewId);
            
            if (this.purchaseR == null) {
                return;
            }
            
            codeField.setText(purchaseR.getPurchaseRequisitionCode());
            requestDateField.setText(purchaseR.getRequestDate().toString());
            requiredDateField.setText(purchaseR.getRequestDate().toString());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(FinancialReportNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTable() throws IOException {
        removeAllRows();

        if(itemList != null){
            for (PurchaseRequisitionItem pri : itemList) {
                Item i = new ItemRepo().getById(pri.getItemId());

                
                tableModel.addRow(new Object[]{
                    i.getId(),
                    i.getItemCode(), 
                    i.getItemName(), 
                    pri.getQuantity(), 
                    "Delete"});
                
                System.out.println("table updated");
            }
        }
        
        TableColumn idColumn = jTable2.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
        idColumn.setResizable(false);
        
        TableColumn actionColumn = jTable2.getColumnModel().getColumn(4);
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
                    
                    for(PurchaseRequisitionItem pri : itemList){
                        if(Objects.equals(pri.getItemId(), id)){
                            itemList.remove(pri);
                            System.out.println("Removed Item Id: "+ pri.getItemId());
                            break;
                        }
                    }
                    
                    tableModel.removeRow(row);
                });
                return c;
            }
        });
    }
    
    private void removeAllRows() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cancelBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addItemBtn = new javax.swing.JButton();
        requestDateField = new javax.swing.JTextField();
        requiredDateField = new javax.swing.JTextField();
        codeField = new javax.swing.JTextField();

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
        jLabel1.setText("Edit Purchase Requisition");
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 100));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 100));

        inputPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        inputPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("PR Code");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "", "Item Code", "Name", "Qty", "Action"
            }
        ));
        jTable2.setShowGrid(true);
        jScrollPane3.setViewportView(jTable2);

        cancelBtn.setBackground(new java.awt.Color(255, 0, 51));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(51, 153, 255));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.setPreferredSize(new java.awt.Dimension(84, 32));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Request Date");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Required Date");

        addItemBtn.setText("+ Add");
        addItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        requestDateField.setEditable(false);
        requestDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        requiredDateField.setEditable(false);
        requiredDateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        codeField.setEditable(false);
        codeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(536, 536, 536)
                        .addComponent(cancelBtn)
                        .addGap(18, 18, 18)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addItemBtn)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(requestDateField)
                            .addComponent(requiredDateField))))
                .addGap(75, 75, 75))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requestDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(requiredDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(addItemBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        NavigationManager.getInstance().goBack();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (this.purchaseR == null) {
            JOptionPane.showMessageDialog(this, "No Purchase Requisition loaded to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (itemList == null) { 
            JOptionPane.showMessageDialog(this, "Item list is not initialized.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update PR Items
        PurchaseRequisitionItemRepo prItemRepo = new PurchaseRequisitionItemRepo();

        try {
            // DELETE:
         
            List<PurchaseRequisitionItem> itemsToDeleteInDB = new ArrayList<>();
            for (PurchaseRequisitionItem oriItem : oriItemList) {
                boolean stillExistsInCurrentList = false;
                if (oriItem.getId() != null) {
                    for (PurchaseRequisitionItem currentItem : itemList) {
                        if (oriItem.getId().equals(currentItem.getId())) {
                            stillExistsInCurrentList = true;
                            break;
                        }
                    }
                }
                if (!stillExistsInCurrentList && oriItem.getId() != null) {
                    itemsToDeleteInDB.add(oriItem);
                }
            }
            for (PurchaseRequisitionItem itemToDelete : itemsToDeleteInDB) {
                prItemRepo.delete(itemToDelete);
                System.out.println("Deleted PR Item ID: " + itemToDelete.getId() + " (Item ID: " + itemToDelete.getItemId() +")");
            }

            // ADD 
            for (PurchaseRequisitionItem currentItem : itemList) {
                if (currentItem.getId() == null) {
                    currentItem.setPurchaseRequisitionId(this.viewId);
                    prItemRepo.create(currentItem);
                    System.out.println("Created new PR Item for Item ID: " + currentItem.getItemId() + " with Qty: " + currentItem.getQuantity());
                }
            }

            // After all DB operations, refresh oriItemList to match current state for next edit cycle (if any)
            this.oriItemList = new ArrayList<>(prItemRepo.getItemsByRequisitionId(viewId));
            // And refresh the table
            updateTable();

            JOptionPane.showMessageDialog(this, "Purchase Requisition updated successfully!");
            NavigationManager.getInstance().goBack();

        } catch (IOException ex) {
            Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(Level.SEVERE, "Error updating purchase requisition items", ex);
            JOptionPane.showMessageDialog(this, "Error updating items: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void addItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemBtnActionPerformed
        // TODO add your handling code here:
        try {
            new RequisitionItem(this, this).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DailySaleNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addItemBtnActionPerformed

    @Override
    public void onSelected(PurchaseRequisitionItem item) {
        addToList(item);
        System.out.println(item.getQuantity());// Or any custom logic
    }
    
    public void addToList(PurchaseRequisitionItem prItem){
        
        // check for duplicate items
        boolean itemExists = false;
        for (PurchaseRequisitionItem existingItem : itemList) {
            if (existingItem.getItemId().equals(prItem.getItemId())) {
                itemExists = true;
                break;
            }
        }
        
         if (!itemExists) {

            itemList.add(prItem);
            
            System.out.println("Added to list: ");
            try {
                updateTable(); 
            } catch (IOException ex) {
                Logger.getLogger(SupplierNew.class.getName()).log(Level.SEVERE, "Error updating table after adding item", ex);
            }
        } else {
            System.out.println("Item is already in the list for this supplier.");
            JOptionPane.showMessageDialog(
                this,
                "Item is already added.",
                "Duplicate Item",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
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
            java.util.logging.Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PurchaseRequisitionEdit(12L).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PurchaseRequisitionEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField codeField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField requestDateField;
    private javax.swing.JTextField requiredDateField;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
