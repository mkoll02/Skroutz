package org.example.functions;

import org.example.model.Eshop;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Product;
import org.example.model.StockItem;
import org.example.service.SkroutzManager;

import java.util.List;
import java.util.Scanner;

//παρέχει αναφορές για παραγγελίες πελατών, eshops και προιόντων
public class ReportFunctions {

    private final Scanner sc;

    public ReportFunctions(Scanner sc) {

        this.sc = sc;
    }

    //μενού αναφορών
    public void showReports(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 4: Αναζήτηση πληροφοριών - Αναφορές =====");
        System.out.println("1. Ιστορικό παραγγελιών πελάτη");
        System.out.println("2. Συνολική αναφορά e-shops");
        System.out.println("3. Συνολική αναφορά προϊόντων");

        int choice = readInt("Επιλογή: ");

        if (choice == 1) {
            showCustomerOrders(manager);
        } else if (choice == 2) {
            showEshopReport(manager);
        } else if (choice == 3) {
            showProductReport(manager);
        } else {
            System.out.println("Μη έγκυρη επιλογή.");
        }
    }

    //ιστορικό παραγγελιών πελάτη
    private void showCustomerOrders(SkroutzManager manager) {

        System.out.print("\nΔώσε username πελάτη: ");
        String username = sc.nextLine().trim();
        boolean found = false;

        for (Order order : manager.getOrders()) {
            if (order.getCustomer().getUsername().equalsIgnoreCase(username)) {
                found = true;

                System.out.println("\n" + order);

                for (OrderItem item : order.getItems()) {
                    System.out.println("   " + item);
                }
            }
        }

        if (!found) {
            System.out.println("Δεν βρέθηκαν παραγγελίες για αυτόν τον πελάτη.");
        }
    }

    //αναφορά eshops
    private void showEshopReport(SkroutzManager manager) {

        System.out.println("\n===== Συνολική Αναφορά e-shops =====");

        for (Eshop eshop : manager.getEshops()) {
            System.out.println("\nE-shop: " + eshop.getWebsite()
                    + " | ΑΦΜ: " + eshop.getAfm()
                    + " | Πλήθος παραγγελιών: " + manager.countOrdersForEshop(eshop)
                    + " | Εισπράξεις: " + String.format("%.2f€", manager.getRevenueForEshop(eshop)));

            List<StockItem> items = eshop.getStockItems();

            if (items.isEmpty()) {
                System.out.println("Δεν υπάρχουν προϊόντα.");
            } else {
                for (StockItem item : items) {
                    System.out.println("   Προϊόν: " + item.getProduct().getName()
                            + item.getProduct().getExtraInfo()
                            + " | Απόθεμα: " + item.getStock()
                            + " | Τιμή: " + String.format("%.2f€", item.getPrice()));
                }
            }
        }
    }

    //αναφορά για τα προιόντα
    private void showProductReport(SkroutzManager manager) {

        System.out.println("\n===== Συνολική Αναφορά προϊόντων =====");

        for (Product product : manager.getProducts()) {
            double lowestPrice = manager.findLowestPrice(product);

            System.out.println("Barcode: " + product.getBarcode()
                    + " | Όνομα: " + product.getName()
                    + " | Πλήθος e-shops: " + manager.countShopsSellingProduct(product)
                    + " | Χαμηλότερη τιμή: "
                    + (lowestPrice == -1 ? "Μη διαθέσιμο" : String.format("%.2f€", lowestPrice))
                    + " | Πλήθος παραγγελιών: " + manager.countOrdersContainingProduct(product));
        }
    }

    private int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Δώσε σωστό αριθμό.");
            }
        }
    }
}