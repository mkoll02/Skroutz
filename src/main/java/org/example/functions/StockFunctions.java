package org.example.functions;

import org.example.model.Eshop;
import org.example.model.StockItem;
import org.example.service.SkroutzManager;

import java.util.Scanner;

public class StockFunctions {

    private final Scanner sc;

    public StockFunctions(Scanner sc) {
        this.sc = sc;
    }

    public void updateStock(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 2: Ανανέωση αποθέματος =====");

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

        boolean continueUpdate;

        do {
            shop.displayProductsForStockUpdate();

            System.out.print("\nΔώσε barcode προϊόντος για αλλαγή αποθέματος: ");
            StockItem item = shop.findStockItemByBarcode(sc.nextLine());

            if (item == null) {
                System.out.println("Δεν βρέθηκε προϊόν με αυτό το barcode στο συγκεκριμένο e-shop.");
            } else {
                item.setStock(readStock());
                System.out.println("Το απόθεμα ενημερώθηκε.");
            }

            continueUpdate = readYesNo("Θέλεις να ενημερώσεις άλλο προϊόν; Ν/Ο: ");

        } while (continueUpdate);

        System.out.println("\n===== Ανανεωμένη λίστα προϊόντων =====");
        shop.displayProductsForStockUpdate();
    }

    private int readStock() {
        return readNonNegativeInt("Νέο απόθεμα: ");
    }

    private int readNonNegativeInt(String message) {

        int number;

        do {
            number = readInt(message);

            if (number < 0) {
                System.out.println("Ο αριθμός δεν μπορεί να είναι αρνητικός.");
            }

        } while (number < 0);

        return number;
    }

    private boolean readYesNo(String message) {

        while (true) {
            System.out.print(message);
            String answer = sc.nextLine().trim();

            if (answer.equalsIgnoreCase("Ν") || answer.equalsIgnoreCase("ΝΑΙ")) {
                return true;
            }

            if (answer.equalsIgnoreCase("Ο") || answer.equalsIgnoreCase("ΟΧΙ")) {
                return false;
            }

            System.out.println("Δώσε Ν ή Ο.");
        }
    }

    private int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Δώσε σωστό ακέραιο αριθμό.");
            }
        }
    }
}