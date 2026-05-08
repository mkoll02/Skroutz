package org.example;

import java.util.ArrayList;
import java.util.List;

public class Eshop {

    private String website;
    private String afm;
    private String email;

    private ArrayList<StockItem> products;

    public Eshop(String website, String afm, String email) {

        // Έλεγχος website
        if (website == null || website.trim().isEmpty()) {
            throw new IllegalArgumentException("Μη έγκυρο website.");
        }

        // Έλεγχος ΑΦΜ (9 ψηφία)
        if (afm == null || !afm.matches("\\d{9}")) {
            throw new IllegalArgumentException("Μη έγκυρο ΑΦΜ.");
        }

        // Έλεγχος email
        if (email == null ||
                !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

            throw new IllegalArgumentException("Μη έγκυρο email.");
        }

        this.website = website.trim();
        this.afm = afm.trim();
        this.email = email.trim();

        products = new ArrayList<>();
    }

    public String getWebsite() {
        return website;
    }

    public String getAfm() {
        return afm;
    }

    public String getEmail() {
        return email;
    }

    public List<StockItem> getProducts() {
        return products;
    }

    // Προσθήκη ή ενημέρωση προϊόντος
    public void addProduct(Product p, int stock, double price) {

        if (p == null) {
            throw new IllegalArgumentException("Άκυρο προϊόν.");
        }

        for (StockItem item : products) {

            // Αν υπάρχει ήδη το προϊόν, ενημερώνεται
            if (item.getProduct().getBarcode()
                    .equals(p.getBarcode())) {

                item.setStock(stock);
                item.setPrice(price);

                return;
            }
        }

        products.add(new StockItem(p, stock, price));
    }

    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Δεν υπάρχουν προϊόντα.");
            return;
        }

        System.out.println("\n===== Προϊόντα e-shop =====");

        for (StockItem item : products) {
            System.out.println(item);
            System.out.println("---------------------------");
        }
    }

    @Override
    public String toString() {

        return "Website: " + website +
                " | AFM: " + afm;
    }
}