/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import component.ButtonEditor;
import component.ButtonRenderer;
import domain.Item;
import domain.ItemSupplier;
import domain.Supplier;
import domain.User;
import function.NavigationManager;
import function.UserSession;
import java.awt.Component;
import java.io.IOException;
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
import repository.ItemSupplierRepo;
import repository.SupplierRepo;

/**
 *
 * @author jacks
 */
public class SupplierEdit extends javax.swing.JFrame implements ItemSelectionListener{
    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private Long viewId;
    private Supplier supplier;
    private List<Item> oriItemList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private User currentUser;
    
    public SupplierEdit(Long viewId) throws IOException {
        // get lgged in user
        currentUser = UserSession.getInstance().getCurrentUser();
        
        this.viewId = viewId;
        oriItemList = new SupplierRepo().getItemsForSupplier(viewId);
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
        SupplierRepo sr = new SupplierRepo();
        ItemSupplierRepo isr = new ItemSupplierRepo();
        
        try {
            supplier = sr.getById(viewId);
            
            if (this.supplier == null) {
                return;
            }
            
            codeField.setText(supplier.getSupplierCode());
            nameField.setText(supplier.getSuppliername());
            emailField.setText(supplier.getEmail());
            phoneField.setText(supplier.getPhone());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(FinancialReportNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Custom Methods
    public void updateTable() throws IOException {
        removeAllRows();

        if(itemList != null){
            for (Item i : itemList) {
                tableModel.addRow(new Object[]{i.getId(),i.getItemCode(), i.getItemName(), "Delete"});            
                System.out.println("table updated");
            }
        }
        
        TableColumn idColumn = jTable2.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
        idColumn.setResizable(false);
        
        int lastColumnIndex = jTable2.getColumnModel().getColumnCount() - 1;
        TableColumn actionColumn = jTable2.getColumnModel().getColumn(lastColumnIndex);
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
                    
                    for(Item i : itemList){
                        if(Objects.equals(i.getId(), id)){
                            itemList.remove(i);
                            System.out.println("Removed id: "+ id );
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        supplierNameLabel = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        addItemBtn = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Edit Supplier");
        jLabel1.setToolTipText("");

        codeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        codeLabel.setText("Code");

        supplierNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        supplierNameLabel.setText("Name");

        codeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        codeField.setEnabled(false);

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneLabel.setText("Email");

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailLabel.setText("Phone");

        phoneField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Item Code", "Item Name", ""
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        addItemBtn.setText("+ Add");
        addItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(addItemBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(codeField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, inputPanelLayout.createSequentialGroup()
                                            .addComponent(codeLabel)
                                            .addGap(262, 262, 262)))
                                    .addComponent(phoneLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel)
                                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(phoneField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(supplierNameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))))
                        .addGap(75, 75, 75))))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel)
                    .addComponent(supplierNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(phoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(emailLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(addItemBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        backButton.setBackground(new java.awt.Color(255, 0, 51));
        backButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.setMaximumSize(new java.awt.Dimension(84, 32));
        backButton.setMinimumSize(new java.awt.Dimension(84, 32));
        backButton.setPreferredSize(new java.awt.Dimension(84, 32));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(51, 153, 255));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.setMaximumSize(new java.awt.Dimension(83, 32));
        updateBtn.setMinimumSize(new java.awt.Dimension(83, 32));
        updateBtn.setPreferredSize(new java.awt.Dimension(83, 32));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (nameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the field.");
            return;
        }
        
        try {
            // TODO add your handling code here:
            // update supplier
            SupplierRepo sr = new SupplierRepo();
            supplier.setSuppliername(nameField.getText());
            supplier.setPhone(phoneField.getText());
            supplier.setEmail(emailField.getText());
            sr.update(supplier);
            
            
            // update supplier item
            ItemSupplierRepo isr = new ItemSupplierRepo();
            List<ItemSupplier> isList = isr.getBySupplierId(viewId);
            
            
            System.out.println("New List Here:");
            for (Item i : itemList) {
                System.out.println(i.getId());
            }
            System.out.println("");
            System.out.println("Old List Here:");
            for (Item io : oriItemList) {
                System.out.println(io.getId());
            }
            System.out.println("");
            
            // item to delete
            for (Item i : oriItemList) {
                boolean stillExists = itemList.stream().anyMatch(o -> Objects.equals(o.getId(), i.getId()));

                if (!stillExists) {
                    for (ItemSupplier is : isList) {
                        if (Objects.equals(is.getItemId(), i.getId())) {
                            isr.delete(is);
                        }
                    }
                }
            }
            
            // items to add
            for (Item i : itemList) {
                boolean exists = oriItemList.stream().anyMatch(o -> Objects.equals(o.getId(), i.getId()));

                if (!exists) {
                    isr.create(new ItemSupplier(null, i.getId(), viewId));
                }
            }
            
            JOptionPane.showMessageDialog(null, "Update Record Updated successfully!");
            NavigationManager.getInstance().goBack();
        } catch (IOException ex) {
            Logger.getLogger(SupplierEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        NavigationManager.getInstance().goBack();
    }//GEN-LAST:event_backButtonActionPerformed

    private void addItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemBtnActionPerformed
        // TODO add your handling code here:
        try {
            new SupplierItem(this, this).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DailySaleNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addItemBtnActionPerformed

    @Override
    public void onItemSelected(Item item) {
        addToList(item); // Or any custom logic
    }
    
    public void addToList(Item item){
        if (item == null || item.getId() == null) {
            System.out.println("Attempted to add a null item or item with null ID.");
            return;
        }
        
        // check for duplicate items
        boolean itemExists = false;
        for (Item existingItem : itemList) {
            if (existingItem.getId().equals(item.getId())) {
                itemExists = true;
                break;
            }
        }
        
         if (!itemExists) {
            itemList.add(item);
            System.out.println("Added to list: " + item.getItemCode());
            try {
                updateTable(); 
            } catch (IOException ex) {
                Logger.getLogger(SupplierNew.class.getName()).log(Level.SEVERE, "Error updating table after adding item", ex);
            }
        } else {
            System.out.println("Item " + item.getItemCode() + " is already in the list for this supplier.");
            JOptionPane.showMessageDialog(
                this,
                "Item '" + item.getItemCode()+ "' is already added for this supplier.",
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
            java.util.logging.Logger.getLogger(SupplierEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SupplierEdit(12L).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SupplierEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemBtn;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel supplierNameLabel;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
