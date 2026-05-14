package org.example;

import java.util.Scanner;

/*
 * Κλάση λειτουργιών καταχώρησης
 * προϊόντων και e-shops.
 */
public class RegistrationFunctions {

    /* Scanner εισόδου χρήστη */
    private final Scanner sc;

    /*
     * Constructor κλάσης.
     */
    public RegistrationFunctions(Scanner sc) {
        this.sc = sc;
    }

    /*
     * Λειτουργία 1:
     * Καταχώρηση προϊόντων και e-shops.
     */
    public void insertProductsAndEshops(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 1: Καταχώρηση προϊόντων και e-shops =====");

        /* Εμφάνιση διαθέσιμων e-shops */
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

            System.out.println(
                    "Μη έγκυρη επιλογή. Επιστροφή στο βασικό μενού.");
        }
    }

    /*
     * Καταχώρηση νέου e-shop.
     */
    private void insertEshop(SkroutzManager manager) {

        System.out.println("\n===== Νέο e-shop =====");

        String website = readText("Website: ");

        /* Έλεγχος υπάρχοντος website */
        if (manager.findEshop(website) != null) {

            System.out.println(
                    "Υπάρχει ήδη e-shop με αυτό το website.");

            return;
        }

        String afm = readText("ΑΦΜ 9 ψηφίων: ");

        /* Έλεγχος υπάρχοντος ΑΦΜ */
        if (manager.findEshop(afm) != null) {

            System.out.println(
                    "Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");

            return;
        }

        String email = readText("Email: ");

        try {

            Eshop eshop =
                    new Eshop(website, afm, email);

            manager.addEshop(eshop);

            System.out.println(
                    "\nΤο e-shop καταχωρήθηκε επιτυχώς.");

            System.out.println(eshop);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Σφάλμα: " + e.getMessage());
        }
    }

    /*
     * Καταχώρηση προϊόντος σε e-shop/s.
     */
    private void insertProductToEshops(SkroutzManager manager) {

        /* Απαιτούνται τουλάχιστον 2 e-shops */
        if (manager.getEshops().size() < 2) {

            System.out.println(
                    "Πρέπει να υπάρχουν τουλάχιστον 2 e-shops.");

            return;
        }

        /* Εμφάνιση e-shops και προϊόντων */
        manager.displayEshops();
        manager.displayProducts();

        String barcode =
                readText("\nΔώσε barcode προϊόντος 5 ψηφίων: ");

        Product product =
                manager.findProductByBarcode(barcode);

        /*
         * Αν το προϊόν δεν υπάρχει,
         * δημιουργείται νέο προϊόν.
         */
        if (product == null) {

            product =
                    createProductWithBarcode(
                            manager,
                            barcode);

            if (product == null) {

                System.out.println(
                        "Επιστροφή στο βασικό μενού.");

                return;
            }

        } else {

            /*
             * Αν υπάρχει ήδη,
             * δίνεται μόνο stock + price.
             */
            System.out.println(
                    "\nΤο προϊόν υπάρχει ήδη στο σύστημα.");

            System.out.println(
                    "Θα δοθούν μόνο απόθεμα και τιμή ανά e-shop.");

            System.out.println(product);
        }

        int currentShops =
                manager.countShopsSellingProduct(product);

        /* Μέγιστο 4 e-shops */
        if (currentShops >= 4) {

            System.out.println(
                    "Το προϊόν υπάρχει ήδη σε 4 e-shops.");

            return;
        }

        int min = currentShops == 0 ? 2 : 1;
        int max = 4 - currentShops;

        int shopsToAdd =
                readShopsToAdd(min, max);

        for (int i = 1; i <= shopsToAdd; i++) {

            manager.displayEshops();

            String shopInput =
                    readText(
                            "\nΔώσε website ή ΑΦΜ e-shop για καταχώρηση: ");

            Eshop shop =
                    manager.findEshop(shopInput);

            /* Έλεγχος ύπαρξης e-shop */
            if (shop == null) {

                System.out.println(
                        "Δεν βρέθηκε e-shop με αυτά τα στοιχεία.");

                i--;
                continue;
            }

            /*
             * Έλεγχος αν το προϊόν υπάρχει ήδη
             * στο συγκεκριμένο e-shop.
             */
            if (shop.findStockItemByBarcode(
                    product.getBarcode()) != null) {

                System.out.println(
                        "Το προϊόν υπάρχει ήδη σε αυτό το e-shop.");

                System.out.println(
                        "Πρέπει να επιλέξεις διαφορετικό e-shop.");

                i--;
                continue;
            }

            int stock = readStock();
            double price = readPrice();

            /* Καταχώρηση προϊόντος */
            shop.addOrUpdateProduct(
                    product,
                    stock,
                    price);

            /*
             * Εμφάνιση στοιχείων καταχώρησης
             * όπως ζητά η εκφώνηση.
             */
            System.out.println(
                    "\n===== Καταχώρηση προϊόντος =====");

            System.out.println(
                    "Website : " + shop.getWebsite());

            System.out.println(
                    "Προϊόν  : " + product.getName());

            System.out.println(
                    "Απόθεμα : " + stock);

            System.out.println(
                    "Τιμή    : " +
                            String.format("%.2f€", price));
        }
    }

    /*
     * Δημιουργία νέου προϊόντος.
     */
    private Product createProductWithBarcode(
            SkroutzManager manager,
            String barcode) {

        try {

            /* Έλεγχος barcode */
            Product.validateBarcode(barcode);

            System.out.println(
                    "\nΤο προϊόν δεν υπάρχει. Καταχώρηση νέου προϊόντος.");

            String name =
                    readText("Όνομα προϊόντος: ");

            String brand =
                    readText("Brand: ");

            System.out.println("\nΚατηγορία:");
            System.out.println("1. Ρούχα");
            System.out.println("2. Υποδήματα");
            System.out.println("3. Προϊόντα φαρμακείου");
            System.out.print("Επιλογή: ");

            int category = readInt();

            Product product;

            if (category == 1) {

                String size =
                        readText("Μέγεθος S/M/L: ");

                String color =
                        readText("Χρώμα: ");

                product =
                        new Clothing(
                                barcode,
                                name,
                                brand,
                                size,
                                color);

            } else if (category == 2) {

                int size = readShoeSize();

                String color =
                        readText("Χρώμα: ");

                product =
                        new Shoes(
                                barcode,
                                name,
                                brand,
                                size,
                                color);

            } else if (category == 3) {

                product =
                        new Product(
                                barcode,
                                name,
                                Product.CATEGORY_PHARMACY,
                                brand);

            } else {

                System.out.println(
                        "Μη έγκυρη κατηγορία.");

                return null;
            }

            manager.addProduct(product);

            return product;

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Σφάλμα: " + e.getMessage());

            return null;
        }
    }

    /*
     * Ανάγνωση πλήθους e-shops.
     */
    private int readShopsToAdd(int min, int max) {

        int number;

        do {

            System.out.print(
                    "Σε πόσα e-shops θα καταχωρηθεί το προϊόν (" +
                            min + "-" + max + "): ");

            number = readInt();

            if (number < min || number > max) {

                System.out.println(
                        "Πρέπει να επιλέξεις από " +
                                min + " έως " + max + " e-shops.");
            }

        } while (number < min || number > max);

        return number;
    }

    /*
     * Ανάγνωση μεγέθους παπουτσιού.
     */
    private int readShoeSize() {

        int size;

        do {

            System.out.print("Μέγεθος 37-45: ");

            size = readInt();

            if (!Shoes.isValidSize(size)) {

                System.out.println(
                        "Το μέγεθος πρέπει να είναι από 37 έως 45.");
            }

        } while (!Shoes.isValidSize(size));

        return size;
    }

    /*
     * Ανάγνωση κειμένου.
     */
    private String readText(String message) {

        while (true) {

            System.out.print(message);

            String text =
                    sc.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println(
                    "Το πεδίο δεν μπορεί να είναι κενό.");
        }
    }

    /*
     * Ανάγνωση ακεραίου αριθμού.
     */
    private int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                        sc.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.print(
                        "Δώσε σωστό αριθμό: ");
            }
        }
    }

    /*
     * Ανάγνωση stock προϊόντος.
     */
    private int readStock() {

        int stock;

        do {

            System.out.print("Απόθεμα: ");

            stock = readInt();

            if (stock < 0) {

                System.out.println(
                        "Το απόθεμα δεν μπορεί να είναι αρνητικό.");
            }

        } while (stock < 0);

        return stock;
    }

    /*
     * Ανάγνωση τιμής προϊόντος.
     */
    private double readPrice() {

        while (true) {

            try {

                System.out.print("Τιμή: ");

                double price =
                        Double.parseDouble(
                                sc.nextLine().trim());

                if (price > 0) {
                    return price;
                }

                System.out.println(
                        "Η τιμή πρέπει να είναι θετική.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Δώσε σωστή τιμή.");
            }
        }
    }
}