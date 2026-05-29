package com.pluralsight.managers;

import com.pluralsight.models.Order;
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

            // Original loop:
            // int count = 1;
            // for (var item : order.getItems()) {
            //     bw.write(count + ") " + item);
            //     bw.newLine();
            //     bw.write(String.format("   Subtotal: $%.2f", item.getPrice()));
            //     bw.newLine();
            //     bw.write("------------------------------------------");
            //     bw.newLine();
            //     count++;
            // }
            final int[] count = {1};
            order.getItems().forEach(item -> {
                try {
                    bw.write(count[0] + ") " + item);
                    bw.newLine();
                    bw.write(String.format("   Subtotal: $%.2f", item.getPrice()));
                    bw.newLine();
                    bw.write("------------------------------------------");
                    bw.newLine();
                    count[0]++;
                } catch (IOException e) {
                    System.out.println("Error writing item: " + e.getMessage());
                }
            });

            bw.newLine();
            bw.write(String.format("ORDER TOTAL: $%.2f", order.getTotal()));
            bw.newLine();
            bw.write("==========================================");
            bw.newLine();
            bw.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            bw.newLine();
            bw.write("Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
            bw.newLine();
            bw.write("==========================================");
            bw.newLine();
            bw.write("Thank you for eating at Dubem's Naija Kitchen!");
            bw.newLine();
            bw.close();

            System.out.println("\033[2m  Receipt saved to: " + fileName + "\033[0m");

        } catch (IOException e) {
            System.out.println("Error saving receipt: ");
        }
    }
}