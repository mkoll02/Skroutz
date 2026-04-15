package org.example;

/*
 * Κλάση βοηθητικών λειτουργιών για το σύστημα Skroutz.
 * Υλοποιεί τις λειτουργίες:
 * 1. Καταχώρηση προϊόντος σε e-shop
 * 2. Ανανέωση αποθέματος προϊόντος
 */

public class FunctionsHelper {

    /*
     * ΛΕΙΤΟΥΡΓΙΑ 1
     * Καταχώρηση προϊόντος σε e-shop
     */
    public void handleInsert(SkroutzManager manager) {

        // Αναζήτηση προϊόντος με barcode
        Product p = manager.findProductByBarcode();

        // Αν δεν υπάρχει στο σύστημα δημιουργείται νέο
        if (p == null) {
            p = manager.createProduct();
            manager.addProduct(p);
        }

        // Αναζήτηση e-shop
        Eshop shop = manager.findEshop();

        // Έλεγχος αν το e-shop βρέθηκε
        if (shop == null) {
            System.out.println("Το e-shop δεν βρέθηκε!");
            return;
        }

        // Ζήτηση αποθέματος από τον χρήστη
        int stock = manager.getStockFromUser();

        // Ζήτηση τιμής
        double price = manager.getPriceFromUser();

        // Προσθήκη προϊόντος στο e-shop
        shop.addProduct(p, stock, price);

        // Εμφάνιση των προϊόντων του e-shop
        System.out.println("\n--- Επιτυχής Καταχώρηση! ---");
        shop.displayProducts();
    }

    /*
     * ΛΕΙΤΟΥΡΓΙΑ 2
     * Ανανέωση αποθέματος προϊόντος
     */
    public void handleUpdateStock(SkroutzManager manager) {

        // Αναζήτηση e-shop
        Eshop shop = manager.findEshop();

        // Έλεγχος αν υπάρχει
        if (shop == null) {
            System.out.println("Το e-shop δεν βρέθηκε!");
            return;
        }

        // Έλεγχος αν υπάρχουν προϊόντα στο e-shop
        if (shop.getProducts().isEmpty()) {
            System.out.println("Το e-shop δεν έχει προϊόντα.");
            return;
        }

        // Εμφάνιση προϊόντων
        System.out.println("\n--- Προϊόντα e-shop ---");
        shop.displayProducts();

        // Ζήτηση barcode προϊόντος
        String barcode = manager.getBarcodeFromUser();

        // Αναζήτηση προϊόντος στο e-shop
        StockItem item = manager.findStockItem(shop, barcode);

        // Αν δεν βρεθεί το προϊόν
        if (item == null) {
            System.out.println("Το προϊόν δεν βρέθηκε στο e-shop.");
            return;
        }

        // Νέο απόθεμα
        int newStock = manager.getStockFromUser();

        // Ενημέρωση αποθέματος
        item.setStock(newStock);

        // Εμφάνιση ενημερωμένης λίστας
        System.out.println("\n--- Ενημερωμένη Λίστα Προϊόντων ---");
        shop.displayProducts();
    }
}