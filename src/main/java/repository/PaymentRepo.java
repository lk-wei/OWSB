/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Payment;
import domain.Supplier;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 *
 * @author zuwei
 */
public class PaymentRepo extends MasterRepo<Payment>{
    public PaymentRepo() {
        super(Path.of("database/payment.txt"));
    }

    public Payment getPaymentById(Long paymentID) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            Payment p = stringToObject(line);

            if (p.getId().equals(paymentID)) {
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
    
    public List<Payment> getByDateRange(LocalDate fromDate, LocalDate toDate) throws IOException{
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("From date and to date cannot be null.");
        }
        if (fromDate.isAfter(toDate)) {
            System.out.println("Warning: fromDate is after toDate in getByDateRange. Returning empty list.");
            return new ArrayList<>();
        }

        List<Payment> allPayments = getAll();
        List<Payment> paymentsInRange = new ArrayList<>();

        for (Payment payment : allPayments) {
            LocalDate paymentDate = payment.getPaymentDate();
            if (paymentDate != null && !paymentDate.isBefore(fromDate) && !paymentDate.isAfter(toDate)) {
                paymentsInRange.add(payment);
            }
        }
        return paymentsInRange;
    }
    
    public List<Payment> getByMonth(int monthValue, int year) throws IOException{
        if (monthValue < 1 || monthValue > 12) {
            throw new IllegalArgumentException("Month value must be between 1 and 12.");
        }

        YearMonth targetYearMonth = YearMonth.of(year, monthValue);
        
        List<Payment> allPayments = getAll();
        List<Payment> paymentsInMonth = new ArrayList<>();

        for (Payment payment : allPayments) {
            LocalDate paymentDate = payment.getPaymentDate();
            
            if (paymentDate != null && YearMonth.from(paymentDate).equals(targetYearMonth)) {
                paymentsInMonth.add(payment);
            }
        }
        return paymentsInMonth;
    }
    
    public List<Payment> getByYear(int year) throws IOException{
        List<Payment> allPayments = getAll();
        List<Payment> paymentsInYear = new ArrayList<>();

        for (Payment payment : allPayments) {
            LocalDate paymentDate = payment.getPaymentDate();
            if (paymentDate != null && paymentDate.getYear() == year) {
                paymentsInYear.add(payment);
            }
        }
        return paymentsInYear;
    }
    

        //ui
    public DefaultTableModel getTableModel() throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Payment Code", "Supplier Name", "Date", "Amount", ""}
        );

        List<Payment> payments = getAll(); 
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
    
    public DefaultTableModel getReportTableModel(List<Payment> filteredPaymentList) throws IOException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            // These column names must match what's in your JFrame
            new String[]{"Payment Code", "Supplier Name", "Date", "Amount", ""}
        );

        if (filteredPaymentList != null) {
            SupplierRepo supplierRepo = new SupplierRepo(); // Instance to fetch supplier details
            for (Payment payment : filteredPaymentList) {    // Use the passed-in list
                Supplier supp = (payment.getSupplierId() != null) ? 
                                supplierRepo.getSupplierById(payment.getSupplierId()) : null;
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
    protected String objectToString(Payment payment) {
        return String.join("|",
                payment.getId().toString(),
                payment.getPaymentCode(),
                payment.getSupplierId().toString(),
                payment.getPaymentDate().toString(),
                Double.toString(payment.getPaymentAmount())
        );
    }

    // Converts pipe-delimited string back to User object
    @Override
    protected Payment stringToObject(String line) {
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
