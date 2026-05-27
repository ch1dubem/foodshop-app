package com.pluralsight.managers;



import com.pluralsight.models.Order;
import com.pluralsight.models.interfaces.Priceable;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public void saveReceipt(Order order) {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) {
                folder.mkdir();
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = "receipts/" + timestamp + ".txt";

            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("==========================================");
            bw.newLine();
            bw.write("        DUBEM'S NAIJA KITCHEN");
            bw.newLine();
            bw.write("   \"Taste of Home, One Bowl at a Time\"");
            bw.newLine();
            bw.write("==========================================");
            bw.newLine();
            bw.newLine();

            int count = 1;
            for (Priceable item : order.getItems()) {
                bw.write(count + ") " + item);
                bw.newLine();
                bw.write(String.format("   Subtotal: $%.2f", item.getPrice()));
                bw.newLine();
                bw.write("------------------------------------------");
                bw.newLine();
                count++;
            }

            bw.newLine();
            bw.write(String.format("ORDER TOTAL: $%.2f", order.getTotal()));
            bw.newLine();
            bw.write("==========================================");
            bw.newLine();
            bw.write("Receipt: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")));
            bw.newLine();
            bw.write("Thank you for eating at Dubem's Naija Kitchen!");
            bw.newLine();
            bw.close();

            System.out.println("Receipt saved to: " + fileName);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
