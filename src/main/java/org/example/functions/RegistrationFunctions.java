package org.example.functions;

import org.example.model.Clothing;
import org.example.model.Eshop;
import org.example.model.Product;
import org.example.model.Shoes;
import org.example.model.StockItem;
import org.example.service.SkroutzManager;

import java.util.Scanner;

public class RegistrationFunctions {

    private final Scanner sc;

    public RegistrationFunctions(Scanner sc) {
        this.sc = sc;
    }

    public void insertProductsAndEshops(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 1: Καταχώρηση προϊόντων και e-shops =====");

        manager.displayEshops();

        System.out.println("\n1. Καταχώρηση νέου e-shop");
        System.out.println("2. Καταχώρηση προϊόντος σε e-shop/s");
        System.out.print("Επιλογή: ");

        int choice = readInt();

        if (choice == 1) {
            insertEshop(manager);
        } else if (choice == 2) {
            insertProductToEshops(manager);
        } else {
            System.out.println("Μη έγκυρη επιλογή. Επιστροφή στο βασικό μενού.");
        }
    }

    private void insertEshop(SkroutzManager manager) {

        System.out.println("\n===== Νέο e-shop =====");

        String website = readText("Website: ");

        if (manager.findEshop(website) != null) {
            System.out.println("Υπάρχει ήδη e-shop με αυτό το website.");
            return;
        }

        String afm = readAfm();

        if (manager.findEshop(afm) != null) {
            System.out.println("Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");
            return;
        }

        String email = readEmail();

        try {
            Eshop eshop = new Eshop(website, afm, email);
            manager.addEshop(eshop);

            System.out.println("\nΤο e-shop καταχωρήθηκε επιτυχώς.");
            System.out.println(eshop);

        } catch (IllegalArgumentException e) {
            System.out.println("Σφάλμα: " + e.getMessage());
        }
    }

    private void insertProductToEshops(SkroutzManager manager) {

        if (manager.getEshops().size() < 2) {
            System.out.println("Πρέπει να υπάρχουν τουλάχιστον 2 e-shops.");
            return;
        }

        manager.displayEshops();
        manager.displayProducts();

        String barcode = readBarcode();

        Product product = manager.findProductByBarcode(barcode);

        if (product == null) {
            product = createProductWithBarcode(manager, barcode);

            if (product == null) {
                System.out.println("Επιστροφή στο βασικό μενού.");
                return;
            }

        } else {
            System.out.println("\nΤο προϊόν υπάρχει ήδη στο σύστημα.");
            System.out.println("Θα δοθούν μόνο απόθεμα και τιμή ανά e-shop.");
            System.out.println(product);
        }

        int currentShops = manager.countShopsSellingProduct(product);

        if (currentShops >= 4) {
            System.out.println("Το προϊόν υπάρχει ήδη σε 4 e-shops.");
            return;
        }

        int min = currentShops == 0 ? 2 : 1;
        int max = 4 - currentShops;

        int shopsToAdd = readShopsToAdd(min, max);

        for (int i = 1; i <= shopsToAdd; i++) {

            manager.displayEshops();

            String shopInput = readText("\nΔώσε website ή ΑΦΜ e-shop για καταχώρηση: ");
            Eshop shop = manager.findEshop(shopInput);

            if (shop == null) {
                System.out.println("Δεν βρέθηκε e-shop με αυτά τα στοιχεία.");
                i--;
                continue;
            }

            StockItem existingItem = shop.findStockItemByBarcode(product.getBarcode());

            if (existingItem != null) {
                System.out.println("Το προϊόν υπάρχει ήδη σε αυτό το e-shop.");
                System.out.println("Πρέπει να επιλέξεις διαφορετικό e-shop.");
                i--;
                continue;
            }

            int stock = readStock();
            double price = readPrice();

            shop.addOrUpdateProduct(product, stock, price);

            System.out.println("\n===== Καταχώρηση προϊόντος =====");
            System.out.println("Website : " + shop.getWebsite());
            System.out.println("Προϊόν  : " + product.getName() + product.getExtraInfo());
            System.out.println("Απόθεμα : " + stock);
            System.out.println("Τιμή    : " + String.format("%.2f€", price));
        }
    }

    private Product createProductWithBarcode(SkroutzManager manager, String barcode) {

        try {
            Product.validateBarcode(barcode);

            System.out.println("\nΤο προϊόν δεν υπάρχει. Καταχώρηση νέου προϊόντος.");

            String name = readText("Όνομα προϊόντος: ");
            String brand = readText("Brand: ");

            System.out.println("\nΚατηγορία:");
            System.out.println("1. Ρούχα");
            System.out.println("2. Υποδήματα");
            System.out.println("3. Προϊόντα φαρμακείου");
            System.out.print("Επιλογή: ");

            int category = readInt();

            Product product;

            if (category == 1) {
                String size = readClothingSize();
                String color = readText("Χρώμα: ");

                product = new Clothing(barcode, name, brand, size, color);

            } else if (category == 2) {
                int size = readShoeSize();
                String color = readText("Χρώμα: ");

                product = new Shoes(barcode, name, brand, size, color);

            } else if (category == 3) {
                product = new Product(barcode, name, Product.CATEGORY_PHARMACY, brand);

            } else {
                System.out.println("Μη έγκυρη κατηγορία.");
                return null;
            }

            manager.addProduct(product);
            return product;

        } catch (IllegalArgumentException e) {
            System.out.println("Σφάλμα: " + e.getMessage());
            return null;
        }
    }

    private String readAfm() {

        while (true) {
            System.out.print("ΑΦΜ 9 ψηφίων: ");

            String afm = sc.nextLine().trim();

            if (afm.matches("\\d{9}")) {
                return afm;
            }

            System.out.println("Το ΑΦΜ πρέπει να έχει ακριβώς 9 ψηφία.");
        }
    }

    private String readEmail() {

        while (true) {
            System.out.print("Email: ");

            String email = sc.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.println("Μη έγκυρο email.");
        }
    }

    private String readBarcode() {

        while (true) {
            System.out.print("\nΔώσε barcode προϊόντος 5 ψηφίων: ");

            String barcode = sc.nextLine().trim();

            if (barcode.matches("\\d{5}")) {
                return barcode;
            }

            System.out.println("Το barcode πρέπει να έχει ακριβώς 5 ψηφία.");
        }
    }

    private String readClothingSize() {

        while (true) {
            System.out.print("Μέγεθος S/M/L: ");

            String size = sc.nextLine().trim().toUpperCase();

            if (Clothing.isValidSize(size)) {
                return size;
            }

            System.out.println("Το μέγεθος ρούχου πρέπει να είναι S, M ή L.");
        }
    }

    private int readShopsToAdd(int min, int max) {

        int number;

        do {
            System.out.print("Σε πόσα e-shops θα καταχωρηθεί το προϊόν (" + min + "-" + max + "): ");
            number = readInt();

            if (number < min || number > max) {
                System.out.println("Πρέπει να επιλέξεις από " + min + " έως " + max + " e-shops.");
            }

        } while (number < min || number > max);

        return number;
    }

    private int readShoeSize() {

        int size;

        do {
            System.out.print("Μέγεθος 37-45: ");
            size = readInt();

            if (!Shoes.isValidSize(size)) {
                System.out.println("Το μέγεθος πρέπει να είναι από 37 έως 45.");
            }

        } while (!Shoes.isValidSize(size));

        return size;
    }

    private String readText(String message) {

        while (true) {
            System.out.print(message);

            String text = sc.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println("Το πεδίο δεν μπορεί να είναι κενό.");
        }
    }

    private int readInt() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Δώσε σωστό αριθμό: ");
            }
        }
    }

    private int readStock() {

        int stock;

        do {
            System.out.print("Απόθεμα: ");
            stock = readInt();

            if (stock < 0) {
                System.out.println("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
            }

        } while (stock < 0);

        return stock;
    }

    private double readPrice() {

        while (true) {
            try {
                System.out.print("Τιμή: ");

                double price = Double.parseDouble(sc.nextLine().trim());

                if (price > 0) {
                    return price;
                }

                System.out.println("Η τιμή πρέπει να είναι θετική.");

            } catch (NumberFormatException e) {
                System.out.println("Δώσε σωστή τιμή.");
            }
        }
    }
}