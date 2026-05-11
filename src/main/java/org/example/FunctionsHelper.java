package org.example;

import java.util.Scanner;

/**
 * Βοηθητική κλάση λειτουργιών.
 */
public class FunctionsHelper {

    private Scanner sc;

    public FunctionsHelper(Scanner sc) {
        this.sc = sc;
    }

    public void handleInsert(SkroutzManager manager) {

        manager.displayEshops();

        Product p = manager.findProductByBarcode();

        if (p == null) {

            System.out.println("Το προϊόν δεν υπάρχει.");
            System.out.println("Δημιουργία νέου προϊόντος...");

            p = manager.createProduct();
            manager.addProduct(p);
        }

        Eshop shop = manager.findEshop();

        if (shop == null) {
            System.out.println("Δεν βρέθηκε e-shop.");
            return;
        }

        int stock = manager.getStock();
        double price = manager.getPrice();

        shop.addProduct(p, stock, price);

        System.out.println("\n===== Επιτυχής καταχώρηση =====");
        System.out.println("Website : " + shop.getWebsite());
        System.out.println("Product : " + p.getName());
        System.out.println("Stock   : " + stock);
        System.out.println("Price   : " + price);
    }

    public void handleUpdateStock(SkroutzManager manager) {

        Eshop shop = manager.findEshop();

        if (shop == null) {
            System.out.println("Δεν βρέθηκε e-shop.");
            return;
        }

        shop.displayProducts();

        System.out.print("Δώσε barcode προϊόντος: ");
        String barcode = sc.nextLine().trim();

        for (StockItem item : shop.getProducts()) {

            if (item.getProduct()
                    .getBarcode()
                    .equals(barcode)) {

                int newStock = manager.getStock();

                item.setStock(newStock);

                System.out.println("\nΤο stock ενημερώθηκε.");
                shop.displayProducts();

                return;
            }
        }

        System.out.println("Δεν βρέθηκε προϊόν.");
    }
}