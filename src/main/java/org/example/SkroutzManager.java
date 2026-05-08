package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class SkroutzManager {

    private ArrayList<Product> products;
    private ArrayList<Eshop> eshops;

    private Scanner sc;

    public SkroutzManager() {

        products = new ArrayList<>();
        eshops = new ArrayList<>();

        sc = new Scanner(System.in);
    }

    public ArrayList<Eshop> getEshops() {
        return eshops;
    }

    // Προσθήκη προϊόντος χωρίς duplicate barcode
    public void addProduct(Product p) {

        if (p == null) {
            return;
        }

        for (Product prod : products) {

            if (prod.getBarcode()
                    .equals(p.getBarcode())) {

                System.out.println(
                        "Το προϊόν υπάρχει ήδη.");

                return;
            }
        }

        products.add(p);
    }

    // Έλεγχος ύπαρξης barcode
    public boolean barcodeExists(String barcode) {

        for (Product p : products) {

            if (p.getBarcode().equals(barcode)) {
                return true;
            }
        }

        return false;
    }

    // Έλεγχος duplicate e-shop
    public boolean eshopExists(String afm,
                               String website) {

        for (Eshop e : eshops) {

            if (e.getAfm().equals(afm) ||
                    e.getWebsite()
                            .equalsIgnoreCase(website)) {

                return true;
            }
        }

        return false;
    }

    public void displayEshops() {

        if (eshops.isEmpty()) {
            System.out.println(
                    "Δεν υπάρχουν e-shops.");
            return;
        }

        System.out.println("\n===== E-SHOPS =====");

        for (Eshop e : eshops) {
            System.out.println(e);
        }
    }

    public Product findProductByBarcode() {

        System.out.print("Δώσε barcode: ");

        String barcode = sc.nextLine();

        for (Product p : products) {

            if (p.getBarcode().equals(barcode)) {
                return p;
            }
        }

        return null;
    }

    public Eshop findEshop() {

        System.out.print(
                "Αναζήτηση e-shop (AFM ή website): ");

        String input = sc.nextLine();

        for (Eshop e : eshops) {

            if (e.getAfm().equals(input) ||
                    e.getWebsite()
                            .equalsIgnoreCase(input)) {

                return e;
            }
        }

        return null;
    }

    public Product createProduct() {

        System.out.println("\n===== Νέο προϊόν =====");

        String barcode;

        while (true) {

            System.out.print("Barcode: ");
            barcode = sc.nextLine();

            // Μόνο αριθμοί
            if (!barcode.matches("\\d+")) {

                System.out.println(
                        "Το barcode πρέπει να έχει μόνο αριθμούς.");

                continue;
            }

            // Duplicate barcode
            if (barcodeExists(barcode)) {

                System.out.println(
                        "Το barcode υπάρχει ήδη.");

                continue;
            }

            break;
        }

        System.out.print("Name: ");
        String name = sc.nextLine();

        String category;

        while (true) {

            System.out.print(
                    "Category (ρούχα / υποδήματα / καλλυντικά): ");

            category =
                    sc.nextLine().trim().toLowerCase();

            if (category.equals("ρούχα") ||
                    category.equals("υποδήματα") ||
                    category.equals("καλλυντικά")) {

                break;
            }

            System.out.println(
                    "Μη αποδεκτή κατηγορία.");
        }

        System.out.print("Brand: ");
        String brand = sc.nextLine();

        // Ρούχα
        if (category.equals("ρούχα")) {

            System.out.print("Size (S/M/L): ");
            String size = sc.nextLine();

            System.out.print("Color: ");
            String color = sc.nextLine();

            return new Clothing(
                    barcode,
                    name,
                    brand,
                    size,
                    color
            );
        }

        // Υποδήματα
        if (category.equals("υποδήματα")) {

            int size;

            while (true) {

                try {

                    System.out.print("Size: ");

                    size = Integer.parseInt(
                            sc.nextLine());

                    break;

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Δώσε έγκυρο αριθμό.");
                }
            }

            System.out.print("Color: ");
            String color = sc.nextLine();

            return new Shoes(
                    barcode,
                    name,
                    brand,
                    size,
                    color
            );
        }

        // Καλλυντικά
        return new Product(
                barcode,
                name,
                category,
                brand
        );
    }

    // Έλεγχος stock
    public int getStock() {

        while (true) {

            try {

                System.out.print("Stock: ");

                int stock =
                        Integer.parseInt(sc.nextLine());

                if (stock >= 0) {
                    return stock;
                }

                System.out.println(
                        "Το stock δεν μπορεί να είναι αρνητικό.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Δώσε σωστό αριθμό.");
            }
        }
    }

    // Έλεγχος τιμής
    public double getPrice() {

        while (true) {

            try {

                System.out.print("Price: ");

                double price =
                        Double.parseDouble(sc.nextLine());

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