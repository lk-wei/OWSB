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
public class SupplierNew extends javax.swing.JFrame implements SelectionListener<Item>{
    /**
     * Creates new form DashBoardSample
     */
    private DefaultTableModel tableModel;
    private List<Item> itemList = new ArrayList<>();
    private User currentUser;
    
    public SupplierNew() throws IOException {
        // get lgged in user
        currentUser = UserSession.getInstance().getCurrentUser();
        
        initComponents();
        tableModel = (DefaultTableModel) itemTable.getModel();
        this.setLocationRelativeTo(null); //this will center your frame
        
        updateTable();
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
        
        TableColumn idColumn = itemTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setPreferredWidth(0);
        idColumn.setResizable(false);
        
        TableColumn actionColumn = itemTable.getColumnModel().getColumn(3);
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
        supplierNameLabel = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        addItemBtn = new javax.swing.JButton();
        phoneField = new javax.swing.JTextField();
        phoneLabel1 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add New Supplier");
        jLabel1.setToolTipText("");

        supplierNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        supplierNameLabel.setText("Supplier Code");

        codeField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneLabel.setText("Name");

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailLabel.setText("Email");

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Item Code", "Item Name", "Action"
            }
        ));
        itemTable.setShowGrid(true);
        jScrollPane1.setViewportView(itemTable);

        addItemBtn.setText("+ Add");
        addItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        phoneField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phoneLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneLabel1.setText("Phone");

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(supplierNameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codeField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneLabel)
                                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneLabel1))))
                        .addGap(75, 75, 75))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addItemBtn)
                            .addComponent(emailLabel)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(supplierNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(phoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(phoneLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addItemBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        cancelButton.setBackground(new java.awt.Color(255, 0, 51));
        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(84, 32));
        cancelButton.setMinimumSize(new java.awt.Dimension(84, 32));
        cancelButton.setPreferredSize(new java.awt.Dimension(84, 32));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        createButton.setBackground(new java.awt.Color(102, 204, 0));
        createButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setText("Create");
        createButton.setMaximumSize(new java.awt.Dimension(83, 32));
        createButton.setMinimumSize(new java.awt.Dimension(83, 32));
        createButton.setPreferredSize(new java.awt.Dimension(83, 32));
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
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
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        if (codeField.getText().trim().isEmpty() ||
            nameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the field.");
            return;
        }
        
        SupplierRepo supplierRepo = new SupplierRepo();
        
        try {
            // Fetch all suppliers and check if the code already exists
            List<Supplier> existingSuppliers = supplierRepo.getAll();
            for (Supplier existingSupplier : existingSuppliers) {
                if (existingSupplier.getSupplierCode().equals(codeField.getText().trim())) {
                    // If the supplier code exists, show an error message
                    JOptionPane.showMessageDialog(null, "Supplier code already exists! Please choose another code.");
                    return;
                }
            }
            
            Supplier newSupplier = new Supplier(
                null,                          
                codeField.getText(),
                nameField.getText(),             
                emailField.getText(),                    
                phoneField.getText(),
                null                           
            );
            
            new SupplierRepo().create(newSupplier);
                
            // get new created fr ID
            Long newlyCreatedSupplierId = newSupplier.getId();

            
            
            if(itemList != null){
                for (Item i : itemList) {
                    new ItemSupplierRepo().create(new ItemSupplier(
                        null,
                        i.getId(),
                        newlyCreatedSupplierId
                    ));
                }
            }

            JOptionPane.showMessageDialog(null, "New Supplier added successfully!");
            NavigationManager.getInstance().goBack();
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        NavigationManager.getInstance().goBack();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemBtnActionPerformed
        // TODO add your handling code here:
        try {
            new SupplierItem(this, this).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(SupplierNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addItemBtnActionPerformed

    @Override
    public void onSelected(Item item) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemBtn;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField codeField;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel phoneLabel1;
    private javax.swing.JLabel supplierNameLabel;
    // End of variables declaration//GEN-END:variables
}
