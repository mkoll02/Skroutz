package org.example;

import java.util.Scanner;

public class FunctionsHelper {

    private Scanner sc = new Scanner(System.in);

    public void handleInsert(SkroutzManager manager) {

        manager.displayEshops();

        Product p = manager.findProductByBarcode();

        if (p == null) {
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

        System.out.println("Καταχωρήθηκε:");
        System.out.println("Shop: " + shop.getWebsite() +
                " | Product: " + p.getName() +
                " | Stock: " + stock +
                " | Price: " + price);
    }

    public void handleUpdateStock(SkroutzManager manager) {

        Eshop shop = manager.findEshop();

        if (shop == null) {
            System.out.println("Δεν βρέθηκε.");
            return;
        }

        shop.displayProducts();

        System.out.print("Δώσε barcode: ");
        String barcode = sc.next();

        for (StockItem item : shop.getProducts()) {

            if (item.getProduct().getBarcode().equals(barcode)) {

                int newStock = manager.getStock();
                item.setStock(newStock);

                System.out.println("Ενημερώθηκε.");
                shop.displayProducts();
                return;
            }
        }

        System.out.println("Δεν βρέθηκε προϊόν.");
    }
}