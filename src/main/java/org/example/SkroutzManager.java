package org.example;

import java.util.ArrayList;
import java.util.List;

//  Κεντρική κλάση διαχείρισης του συστήματος
//Κρατάει τις λίστες προϊόντων και e-shops

public class SkroutzManager {

    private final List<Product> products;
    private final List<Eshop> eshops;

    public SkroutzManager() {
        products = new ArrayList<>();
        eshops = new ArrayList<>();
    }

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Το προϊόν δεν μπορεί να είναι null.");
        }

        if (findProductByBarcode(product.getBarcode()) != null) {
            throw new IllegalArgumentException("Υπάρχει ήδη προϊόν με αυτό το barcode.");
        }

        products.add(product);
    }

    public void addEshop(Eshop eshop) {

        if (eshop == null) {
            throw new IllegalArgumentException("Το e-shop δεν μπορεί να είναι null.");
        }

        for (Eshop e : eshops) {
            if (e.getAfm().equals(eshop.getAfm())) {
                throw new IllegalArgumentException("Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");
            }

            if (e.getWebsite().equalsIgnoreCase(eshop.getWebsite())) {
                throw new IllegalArgumentException("Υπάρχει ήδη e-shop με αυτό το website.");
            }
        }

        eshops.add(eshop);
    }

    // αναζήτηση προιόντος
    public Product findProductByBarcode(String barcode) {

        if (barcode == null) {
            return null;
        }

        for (Product product : products) {
            if (product.getBarcode().equals(barcode.trim())) {
                return product;
            }
        }

        return null;
    }

    public Eshop findEshop(String input) {

        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        input = input.trim();

        for (Eshop eshop : eshops) {
            if (eshop.getAfm().equals(input)
                    || eshop.getWebsite().equalsIgnoreCase(input)) {
                return eshop;
            }
        }

        return null;
    }

    public int countShopsSellingProduct(Product product) {

        int count = 0;

        for (Eshop eshop : eshops) {
            if (eshop.findStockItemByBarcode(product.getBarcode()) != null) {
                count++;
            }
        }

        return count;
    }

    // Επιστρέφει αντίγραφο της λίστας e-shops
    public List<Eshop> getEshops() {
        return new ArrayList<>(eshops);
    }

    // Εμφανίζει διαθέσιμα e-shops
    public void displayEshops() {

        if (eshops.isEmpty()) {
            System.out.println("Δεν υπάρχουν e-shops.");
            return;
        }

        System.out.println("\n===== Διαθέσιμα e-shops =====");

        for (Eshop eshop : eshops) {
            System.out.println("Website: " + eshop.getWebsite()
                    + " | ΑΦΜ: " + eshop.getAfm());
        }
    }

    // Εμφανίζει υπάρχοντα προϊόντα
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Δεν υπάρχουν προϊόντα.");
            return;
        }

        System.out.println("\n===== Υπάρχοντα προϊόντα =====");

        for (Product product : products) {
            System.out.println(product
                    + " | Πωλείται σε shops: "
                    + countShopsSellingProduct(product));
        }
    }
}