/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.PaymentItem;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 *
 * @author zuwei
 */
public class PaymentItemRepo extends MasterRepo<PaymentItem>{
    public PaymentItemRepo() {
        super(Path.of("database/paymentItem.txt"));
    }
    
    // custom method
    public PaymentItem getPaymentItemById(Long paymentItemId) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            PaymentItem pi = stringToObject(line);

            if (pi.getId().equals(paymentItemId)) {
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

    // Converts User object to pipe-delimited string
    @Override
    protected String objectToString(PaymentItem paymentItem) {
        return String.join("|",
                paymentItem.getId().toString(),
                paymentItem.getPaymentId().toString(),
                paymentItem.getPaymentCode(),
                paymentItem.getPurchaseOrderId().toString(),
                Double.toString(paymentItem.getTotalAmount()),
                paymentItem.getProductOrderCode()
        );
    }

    // Converts pipe-delimited string back to User object
    @Override
    protected PaymentItem stringToObject(String line) {
        String[] parts = line.split("\\|", 5);

        return new PaymentItem(
                Long.valueOf(parts[0]), // PIId                
                Long.valueOf(parts[1]),
                parts[2],
                Long.valueOf(parts[3]),
                Double.parseDouble(parts[4]),
                parts[5]
        );
    }
}
