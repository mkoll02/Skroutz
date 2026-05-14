package org.example;

import java.util.Scanner;

// 2η λειτουργία
public class StockFunctions {

    private final Scanner sc;

    public StockFunctions(Scanner sc) {
        this.sc = sc;
    }

    public void updateStock(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 2: Ανανέωση αποθέματος =====");

        // Εμφάνιση διαθέσιμων e-shops
        manager.displayEshops();

        System.out.print("\nΑναζήτηση e-shop με ΑΦΜ ή website: ");
        Eshop shop = manager.findEshop(sc.nextLine());

        if (shop == null) {
            System.out.println("Δεν βρέθηκε e-shop.");
            return;
        }

        if (shop.getProductCount() == 0) {
            System.out.println("Το e-shop δεν έχει προϊόντα.");
            return;
        }

        String answer;

        do {
            // Εμφάνιση προϊόντων
            shop.displayProductsForStockUpdate();

            System.out.print("\nΔώσε barcode προϊόντος για αλλαγή αποθέματος: ");
            StockItem item = shop.findStockItemByBarcode(sc.nextLine());

            if (item == null) {
                System.out.println("Δεν βρέθηκε προϊόν με αυτό το barcode στο συγκεκριμένο e-shop.");
            } else {
                item.setStock(readStock());
                System.out.println("Το απόθεμα ενημερώθηκε.");
            }

            System.out.print("Θέλεις να ενημερώσεις άλλο προϊόν; Ν/Ο: ");
            answer = sc.nextLine().trim();

        } while (answer.equalsIgnoreCase("Ν")
                || answer.equalsIgnoreCase("ΝΑΙ"));

        System.out.println("\n===== Ανανεωμένη λίστα προϊόντων =====");
        shop.displayProductsForStockUpdate();
    }

    // Ανάγνωση νέου αποθέματος
    private int readStock() {

        int stock;

        do {
            System.out.print("Νέο απόθεμα: ");
            stock = readInt();

            if (stock < 0) {
                System.out.println("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
            }

        } while (stock < 0);

        return stock;
    }

    //Ανάγνωση ακέραιου
    private int readInt() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Δώσε σωστό αριθμό: ");
            }
        }
    }
}