package org.example;

import java.util.Scanner;

/**
 * Κλάση λειτουργιών εφαρμογής.
 */
public class Functions {

    private final Scanner sc;

    public Functions(Scanner sc) {
        this.sc = sc;
    }

    public void insertProduct(SkroutzManager manager) {

        manager.displayEshops();

        String barcode = readBarcode();

        Product product =
                manager.findProductByBarcode(barcode);

        if (product == null) {

            System.out.println(
                    "Το προϊόν δεν υπάρχει.");

            product = createProduct(barcode);

            manager.addProduct(product);
        }

        Eshop shop = selectEshop(manager);

        if (shop == null) {

            System.out.println(
                    "Δεν βρέθηκε e-shop.");
            return;
        }

        int stock = readStock();
        double price = readPrice();

        shop.addOrUpdateProduct(product, stock, price);

        System.out.println(
                "\n===== Επιτυχής καταχώρηση =====");

        System.out.println(
                "Website : " + shop.getWebsite());

        System.out.println(
                "Product : " + product.getName());

        System.out.println(
                "Stock   : " + stock);

        System.out.println(
                "Price   : " + price);
    }

    public void updateStock(SkroutzManager manager) {

        Eshop shop = selectEshop(manager);

        if (shop == null) {

            System.out.println(
                    "Δεν βρέθηκε e-shop.");
            return;
        }

        shop.displayProducts();

        String barcode = readBarcode();

        StockItem item =
                shop.findStockItem(barcode);

        if (item == null) {

            System.out.println(
                    "Δεν βρέθηκε προϊόν.");
            return;
        }

        int stock = readStock();

        item.setStock(stock);

        System.out.println(
                "\nΤο stock ενημερώθηκε.");

        shop.displayProducts();
    }

    private Eshop selectEshop(SkroutzManager manager) {

        System.out.print(
                "\nΑναζήτηση e-shop (AFM ή website): ");

        String input = sc.nextLine().trim();

        return manager.findEshop(input);
    }

    private Product createProduct(String barcode) {

        System.out.println(
                "\n===== Νέο προϊόν =====");

        String name = readText("Name: ");

        String category;

        while (true) {

            System.out.print(
                    "Category (ρούχα / υποδήματα / προϊόντα φαρμακείου): ");

            category = sc.nextLine()
                    .trim()
                    .toLowerCase();

            if (Product.isValidCategory(category)) {
                break;
            }

            System.out.println(
                    "Μη αποδεκτή κατηγορία.");
        }

        String brand = readText("Brand: ");

        if (category.equals(
                Product.CATEGORY_CLOTHING)) {

            String size;

            while (true) {

                System.out.print(
                        "Size (S/M/L): ");

                size = sc.nextLine()
                        .trim()
                        .toUpperCase();

                if (size.equals("S")
                        || size.equals("M")
                        || size.equals("L")) {

                    break;
                }

                System.out.println(
                        "Επιτρεπτά μεγέθη: S, M, L.");
            }

            String color = readText("Color: ");

            return new Clothing(
                    barcode,
                    name,
                    brand,
                    size,
                    color
            );
        }

        if (category.equals(
                Product.CATEGORY_SHOES)) {

            int size;

            while (true) {

                try {

                    System.out.print(
                            "Size (37-45): ");

                    size = Integer.parseInt(
                            sc.nextLine());

                    if (size >= 37 && size <= 45) {
                        break;
                    }

                    System.out.println(
                            "Το μέγεθος πρέπει να είναι 37-45.");

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Δώσε έγκυρο αριθμό.");
                }
            }

            String color = readText("Color: ");

            return new Shoes(
                    barcode,
                    name,
                    brand,
                    size,
                    color
            );
        }

        return new Product(
                barcode,
                name,
                category,
                brand
        );
    }

    private String readBarcode() {

        while (true) {

            System.out.print(
                    "\nΔώσε barcode: ");

            String barcode = sc.nextLine().trim();

            try {

                Product.validateBarcode(barcode);

                return barcode;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }

    private String readText(String message) {

        while (true) {

            System.out.print(message);

            String text = sc.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println(
                    "Το πεδίο δεν μπορεί να είναι κενό.");
        }
    }

    private int readStock() {

        while (true) {

            try {

                System.out.print("Stock: ");

                int stock = Integer.parseInt(
                        sc.nextLine());

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

    private double readPrice() {

        while (true) {

            try {

                System.out.print("Price: ");

                double price = Double.parseDouble(
                        sc.nextLine());

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