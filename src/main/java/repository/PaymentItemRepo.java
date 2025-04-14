/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PaymentItem;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zuwei
 */
public class PaymentItemRepo {

    // define the txt file that stores data
    final private Path filePath = Path.of("database/paymentItem.txt");

    public PaymentItemRepo() {

    }

    // create
    public void createPaymentItem(PaymentItem paymentItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(paymentItem));

        Files.write(filePath, lines);
    }

    // read
    public List<PaymentItem> getAllPaymentItem() throws IOException {
        List<PaymentItem> paymentItemList = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);
            paymentItemList.add(pi);
        }
        return paymentItemList;
    }

    public PaymentItem getPaymentItemById(Long paymentItemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);

            if (pi.getPaymentItemId().equals(paymentItemId)) {
                return pi;
            }
        }
        return null;
    }

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

    // update
    public void updatePaymentItem(PaymentItem paymentItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);

            if (pi.getPaymentItemId().equals(paymentItem.getPaymentItemId())) {
                updatedLines.add(objectToString(paymentItem));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // delete
    public void deletePaymentItem(PaymentItem paymentItem) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);

            if (!pi.getPaymentItemId().equals(paymentItem.getPaymentItemId())) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // others
    // Converts User object to pipe-delimited string
    private String objectToString(PaymentItem paymentItem) {
        return String.join("|",
                paymentItem.getPaymentItemId().toString(),
                
                paymentItem.getPaymentId().toString(),
                paymentItem.getPurchaseOrderId().toString(),
                Double.toString(paymentItem.getTotalAmount()),
                paymentItem.getProductOrderCode()
        );
    }

    // Converts pipe-delimited string back to User object
    private PaymentItem stringToObject(String line) {
        String[] parts = line.split("\\|", 5);

        return new PaymentItem(
                Long.valueOf(parts[0]), // PIId
                
                Long.valueOf(parts[1]),
                Long.valueOf(parts[2]),
                Double.parseDouble(parts[3]),
                parts[4]
        );
    }
}
