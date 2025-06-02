/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.FinancialReportItem;
import domain.Payment;
import domain.PaymentItem;
import domain.PurchaseOrder;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zuwei
 */
public class PaymentItemRepo extends MasterRepo<PaymentItem> {

    private Iterable<Payment> payments;

    public PaymentItemRepo() {
        super(Path.of("database/paymentItem.txt"));
    }
    // Custom methods

    public List<PaymentItem> getByPaymentId(Long id) throws IOException {
        List<PaymentItem> itemList = new ArrayList<>();
        System.out.println("1");
        for (PaymentItem item : getAll()) {
            System.out.println(item.getPaymentCode());
            if (item.getPaymentId().equals(id)) {
                itemList.add(item);
            }
        }

        return itemList;
    }
    // custom method

    public PaymentItem getPaymentId(String paymentItemPO) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);

            if (pi.getPaymentId().equals(paymentItemPO)) {
                return pi;
            }
        }
        return null;
    }


    public DefaultTableModel getTableModel(Long id) throws IOException {
        String[] columnNames = {"","Payment Code", "Supplier Name", "Date", "Total Amount","Payment Amount", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (PaymentItem item : getByPaymentId(id)) {
            Payment py = new PaymentRepo().getPaymentById(item.getPaymentId());
            PurchaseOrder po = new PurchaseOrderRepo().getOrderById(item.getPurchaseOrderId());
            Supplier s = new SupplierRepo().getSupplierById(po.getSupplierId());

            model.addRow(new Object[]{
                "",
                py.getPaymentCode(),
                s.getSuppliername(),
                py.getPaymentDate().toString(),
                py.getTotalAmount(),
                py.getPaymentAmount(),
                "" // Action 
            });
        }
        return model;
    }

    public DefaultTableModel getTableModel2(List<Payment> filteredPaymentList) throws IOException {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                // These column names must match what's in your JFrame
                new String[]{"Payment Code", "Supplier Name", "Date", "Amount", ""}
        );

        if (filteredPaymentList != null) {
            SupplierRepo supplierRepo = new SupplierRepo(); // Instance to fetch supplier details
            for (Payment payment : filteredPaymentList) {    // Use the passed-in list
                Supplier supp = (payment.getSupplierId() != null)
                        ? supplierRepo.getSupplierById(payment.getSupplierId()) : null;
                String supplierName = (supp != null) ? supp.getSuppliername() : "N/A";

                model.addRow(new Object[]{
                    payment.getPaymentCode(),
                    supplierName,
                    payment.getPaymentDate().toString(), // Or format the date as desired
                    payment.getPaymentAmount(),
                    Boolean.TRUE // Default "Include?" to true
                });
            }
        }
        return model;
    }

    // Converts User object to pipe-delimited string
    @Override
    protected String objectToString(PaymentItem pi) {

        return String.join(",",
                String.valueOf(pi.getId()),
                String.valueOf(pi.getId()),
                pi.getPaymentCode(),
                String.valueOf(pi.getId()),
                String.valueOf(pi.getTotalAmount()),
                pi.getPurchaseOrderCode()
        );
    }

    // Converts pipe-delimited string back to User object
    @Override
    protected PaymentItem stringToObject(String line) {
        String[] parts = line.split("\\|", -1);
        
        PaymentItem pi = new PaymentItem();
        PurchaseOrder po = new PurchaseOrder();

        pi.setId(Long.parseLong(parts[0]));
        pi.setId(Long.parseLong(parts[1]));
        pi.setPaymentCode(String.valueOf(parts[2]));
        pi.setId(Long.parseLong(parts[3]));
        pi.setTotalAmount(Double.valueOf(parts[4]));
        pi.setPurchaseOrderCode(String.valueOf(parts[5]));
        return pi;

    }
}
