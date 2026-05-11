package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Διαχειριστής συστήματος.
 */
public class SkroutzManager {

    private final ArrayList<Product> products;
    private final ArrayList<Eshop> eshops;

    public SkroutzManager() {

        products = new ArrayList<>();
        eshops = new ArrayList<>();
    }

    public void addProduct(Product product) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Το προϊόν είναι null.");
        }

        if (findProductByBarcode(
                product.getBarcode()) != null) {

            throw new IllegalArgumentException(
                    "Υπάρχει ήδη προϊόν με αυτό το barcode.");
        }

        products.add(product);
    }

    public void addEshop(Eshop eshop) {

        if (eshop == null) {

            throw new IllegalArgumentException(
                    "Το e-shop είναι null.");
        }

        for (Eshop e : eshops) {

            if (e.getAfm().equals(eshop.getAfm())) {

                throw new IllegalArgumentException(
                        "Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");
            }

            if (e.getWebsite().equalsIgnoreCase(
                    eshop.getWebsite())) {

                throw new IllegalArgumentException(
                        "Υπάρχει ήδη e-shop με αυτό το website.");
            }
        }

        eshops.add(eshop);
    }

    public Product findProductByBarcode(String barcode) {

        if (barcode == null
                || barcode.trim().isEmpty()) {

            return null;
        }

        for (Product p : products) {

            if (p.getBarcode()
                    .equals(barcode.trim())) {

                return p;
            }
        }

        return null;
    }

    public Eshop findEshop(String input) {

        if (input == null
                || input.trim().isEmpty()) {

            return null;
        }

        for (Eshop e : eshops) {

            if (e.getAfm().equals(input.trim())
                    || e.getWebsite()
                    .equalsIgnoreCase(input.trim())) {

                return e;
            }
        }

        return null;
    }

    public List<Eshop> getEshops() {

        return new ArrayList<>(eshops);
    }

    public void displayEshops() {

        if (eshops.isEmpty()) {

            System.out.println(
                    "Δεν υπάρχουν e-shops.");
            return;
        }

        System.out.println(
                "\n===== E-SHOPS =====");

        for (Eshop e : eshops) {
            System.out.println(e);
        }
    }
}