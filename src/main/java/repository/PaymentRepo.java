/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.FinancialReport;
import domain.Payment;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 *
 * @author zuwei
 */
public class PaymentRepo {

    // define the txt file that stores data
    final private Path filePath = Path.of("database/payment.txt");

    public PaymentRepo() {

    }

    // create
    public void createPayment(Payment payment) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(payment));

        Files.write(filePath, lines);
    }

    // read
    public List<Payment> getAllPayment() throws IOException {
        List<Payment> paymentList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Payment p = stringToObject(line);
            paymentList.add(p);
        }
        return paymentList;
    }

    public Payment getPaymentById(Long paymentID) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (p.getPaymentId().equals(paymentID)) {
                return p;
            }
        }
        return null;
    }

    public Payment getSupplierId(Long supplierID) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (p.getSupplierId().equals(supplierID)) {
                return p;
            }
        }
        return null;
    }

    // update
    public void updatePayment(Payment payment) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (p.getPaymentId().equals(payment.getPaymentId())) {
                updatedLines.add(objectToString(payment));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // delete
    public void deletePayment(Payment payment) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (!p.getPaymentId().equals(payment.getPaymentId())) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }
    
        public Payment getBySupplierId(Long supplierID) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (p.getSupplierId().equals(supplierID)) {
                return p;
            }
        }
        return null;
    }
        //ui
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Payment Code", "Supplier Name", "Date", "Amount", ""}
        );

        List<Payment> payments = getAllPayment(); 
        SupplierRepo supplierRepo = new SupplierRepo();

        for (Payment payment : payments) {
            Supplier supp = supplierRepo.getSupplierById(payment.getSupplierId());
            model.addRow(new Object[]{
                payment.getPaymentCode(),
                supp.getSuppliername(),
                payment.getPaymentDate(),
                payment.getPaymentAmount(),
                ""
            });
        }
        return model;
    }
    
    // others
    // Converts User object to pipe-delimited string
    private String objectToString(Payment payment) {
        return String.join("|",
                payment.getPaymentId().toString(),
                payment.getPaymentCode(),
                payment.getSupplierId().toString(),
                payment.getPaymentDate().toString(),
                Double.toString(payment.getPaymentAmount())
        );
    }

    // Converts pipe-delimited string back to User object
    private Payment stringToObject(String line) {
        String[] parts = line.split("\\|");

        return new Payment(
                Long.valueOf(parts[0]), // 
                parts[1],
                Long.valueOf(parts[2]), // suppliername
                LocalDate.parse(parts[3]), // date
                Double.parseDouble(parts[4])
        );
    }
}
