package org.example.functions;

import org.example.model.Clothing;
import org.example.model.Eshop;
import org.example.model.Product;
import org.example.model.Shoes;
import org.example.model.StockItem;
import org.example.service.SkroutzManager;

import java.util.Scanner;

//υποστήριξη λειτουργίας 1.
public class RegistrationFunctions {

    private final Scanner sc;

    public RegistrationFunctions(Scanner sc) {

        this.sc = sc;
    }

    //Υλοποιεί τη λειτουργία καταχώρησης προϊόντων και eshops
    public void insertProductsAndEshops(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 1: Καταχώρηση προϊόντων και e-shops =====");

        manager.displayEshops();

        System.out.println("\n1. Καταχώρηση νέου e-shop");
        System.out.println("2. Καταχώρηση προϊόντος σε e-shop/s");

        int choice = readInt("Επιλογή: ");

        if (choice == 1) {
            insertEshop(manager);
        } else if (choice == 2) {
            insertProductToEshops(manager);
        } else {
            System.out.println("Μη έγκυρη επιλογή. Επιστροφή στο βασικό μενού.");
        }
    }

    //Καταχωρεί νέο e-shop
    private void insertEshop(SkroutzManager manager) {

        System.out.println("\n===== Νέο e-shop =====");

        String website = readText("Website: ");

        if (manager.findEshop(website) != null) {
            System.out.println("Υπάρχει ήδη e-shop με αυτό το website.");
            return;
        }

        String afm = readAfm("ΑΦΜ 9 ψηφίων: ");

        if (manager.findEshop(afm) != null) {
            System.out.println("Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");
            return;
        }

        String email = readEmail("Email: ");

        try {
            Eshop eshop = new Eshop(website, afm, email);
            manager.addEshop(eshop);

            System.out.println("\nΤο e-shop καταχωρήθηκε επιτυχώς.");
            System.out.println(eshop);

        } catch (IllegalArgumentException e) {
            System.out.println("Σφάλμα: " + e.getMessage());
        }
    }

    //Καταχωρεί νέο ή υπάρχον προιόν
    private void insertProductToEshops(SkroutzManager manager) {

        if (manager.getEshops().size() < 2) {
            System.out.println("Πρέπει να υπάρχουν τουλάχιστον 2 e-shops.");
            return;
        }

        manager.displayEshops();
        manager.displayProducts();

        String barcode = readBarcode("Δώσε barcode προϊόντος 5 ψηφίων: ");

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

        int shopsToAdd = readIntInRange(
                "Σε πόσα e-shops θα καταχωρηθεί το προϊόν (" + min + "-" + max + "): ",
                min,
                max
        );

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

            int stock = readNonNegativeInt("Απόθεμα: ");
            double price = readPositiveDouble("Τιμή: ");

            shop.addOrUpdateProduct(product, stock, price);

            System.out.println("\n===== Καταχώρηση προϊόντος =====");
            System.out.println("Website : " + shop.getWebsite());
            System.out.println("Προϊόν  : " + product.getName() + product.getExtraInfo());
            System.out.println("Απόθεμα : " + stock);
            System.out.println("Τιμή    : " + String.format("%.2f€", price));
        }
    }

    //Δημιουργεί νέο προϊόν όταν το barcode δεν υπάρχει ήδη
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

            int category = readInt("Επιλογή: ");

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

    private String readClothingSize() {

        while (true) {
            String size = readText("Μέγεθος S/M/L: ").toUpperCase();

            if (Clothing.isValidSize(size)) {
                return size;
            }

            System.out.println("Το μέγεθος ρούχου πρέπει να είναι S, M ή L.");
        }
    }

    private int readShoeSize() {

        while (true) {
            int size = readInt("Μέγεθος 37-45: ");

            if (Shoes.isValidSize(size)) {
                return size;
            }

            System.out.println("Το μέγεθος πρέπει να είναι από 37 έως 45.");
        }
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

    private String readAfm(String message) {

        while (true) {
            String afm = readText(message);

            if (afm.matches("\\d{9}")) {
                return afm;
            }

            System.out.println("Το ΑΦΜ πρέπει να έχει ακριβώς 9 ψηφία.");
        }
    }

    private String readBarcode(String message) {

        while (true) {
            String barcode = readText(message);

            if (barcode.matches("\\d{5}")) {
                return barcode;
            }

            System.out.println("Το barcode πρέπει να έχει ακριβώς 5 ψηφία.");
        }
    }

    private String readEmail(String message) {

        while (true) {
            String email = readText(message);

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Μη έγκυρο email.");
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

    private int readIntInRange(String message, int min, int max) {

        while (true) {
            int number = readInt(message);

            if (number >= min && number <= max) {
                return number;
            }

            System.out.println("Πρέπει να δώσεις αριθμό από " + min + " έως " + max + ".");
        }
    }

    private int readNonNegativeInt(String message) {

        while (true) {
            int number = readInt(message);

            if (number >= 0) {
                return number;
            }

            System.out.println("Ο αριθμός δεν μπορεί να είναι αρνητικός.");
        }
    }

    private double readPositiveDouble(String message) {

        while (true) {
            try {
                System.out.print(message);

                String value = sc.nextLine().trim().replace(',', '.');
                double number = Double.parseDouble(value);

                if (number > 0) {
                    return number;
                }

                System.out.println("Η τιμή πρέπει να είναι θετική.");

            } catch (NumberFormatException e) {
                System.out.println("Δώσε σωστή αριθμητική τιμή.");
            }
        }
    }
}