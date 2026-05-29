package com.pluralsight.managers;

import com.pluralsight.models.Order;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Handles saving order receipts to text files
// Creates a "receipts" folder and generates timestamped filenames

public class ReceiptManager {

    // ANSI codes for console output only (not written to the file)
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[32m";
    private static final String BOLD = "\033[1m";
    private static final String RED = "\033[31m";
    private static final String DIM = "\033[2m";

    // Saves the order to a receipt file in the receipts folder
    // Filename format: yyyyMMdd-HHmmss.txt (e.g. 20260528-105841.txt)
    public void saveReceipt(Order order) {
        try {
            // Create receipts folder if it doesn't exist
            File folder = new File("receipts");
            if (!folder.exists()) {
                folder.mkdir();
            }

            // Generate filename from current date/time
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = "receipts/" + timestamp + ".txt";

            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            // Write receipt header
            bw.write("==========================================");
            bw.newLine();
            bw.write("        DUBEM'S NAIJA KITCHEN");
            bw.newLine();
            bw.write("   \"Taste of Home, One Bowl at a Time\"");
            bw.newLine();
            bw.write("==========================================");
            bw.newLine();
            bw.newLine();

            // Write each item with its subtotal

            // Original loop:
            // int count = 1;
            // for (var item : order.getItems()) {  count++; }

           int[] count = {1};
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
                    System.out.println(RED + "  ✘ Error writing item." + RESET);
                }
            });

            // Write total and formatted date/time
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

            // Console confirmation with ANSI styling
            System.out.println(GREEN + BOLD + "  ✔ Receipt saved to: " + DIM + fileName + RESET);

        } catch (IOException e) {
            System.out.println(RED + "  ✘ Error saving receipt." + RESET);
        }
    }
}