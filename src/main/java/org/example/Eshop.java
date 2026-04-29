package org.example;

import java.util.ArrayList;

public class Eshop {

    private String website;
    private String afm;
    private String email;

    private ArrayList<StockItem> products;

    public Eshop(String website, String afm, String email) {

        if (website == null || website.trim().isEmpty()) {
            throw new IllegalArgumentException("Μη έγκυρο website");
        }
        this.website = website.trim();

        // ΑΦΜ: 9 ψηφία
        if (afm == null || !afm.matches("\\d{9}")) {
            throw new IllegalArgumentException("Μη έγκυρο ΑΦΜ");
        }
        this.afm = afm;

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Μη έγκυρο email");
        }
        this.email = email;

        products = new ArrayList<>();
    }

    public String getWebsite() { return website; }
    public String getAfm() { return afm; }
    public ArrayList<StockItem> getProducts() { return products; }

    // Προσθήκη ή ενημέρωση προϊόντος
    public void addProduct(Product p, int stock, double price) {

        if (p == null) {
            throw new IllegalArgumentException("Άκυρο προϊόν");
        }

        for (StockItem item : products) {
            if (item.getProduct().getBarcode().equals(p.getBarcode())) {
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

        for (StockItem item : products) {
            System.out.println(item);
        }
    }

    @Override
    public String toString() {
        return "Website: " + website +
                " | AFM: " + afm;
    }
}