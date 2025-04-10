/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Payment;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // others
    // Converts User object to pipe-delimited string
    private String objectToString(Payment payment) {
        return String.join("|",
                payment.getPaymentId().toString(),
                payment.getSupplierId().toString(),
                payment.getPaymentDate().toString(),
                Double.toString(payment.getPaymentAmount())
        );
    }

    // Converts pipe-delimited string back to User object
    private Payment stringToObject(String line) {
        String[] parts = line.split("\\|", 4);

        return new Payment(
                Long.valueOf(parts[0]), // userId
                Long.valueOf(parts[1]), // suppliername
                LocalDate.parse(parts[2]), // date
                Double.parseDouble(parts[3])
        );
    }
}
