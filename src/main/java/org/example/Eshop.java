package org.example;

import java.util.ArrayList;
import java.util.List;

// Κλάση eshop

public class Eshop {

    private final String website;
    private final String afm;
    private final String email;

    // Λίστα προϊόντων του e-shop
    private final List<StockItem> stockItems;

    // Constructor  e-shop
    public Eshop(String website, String afm, String email) {

        // έλεγχος
        this.website = Product.normalizeText(
                website,
                "Το website δεν μπορεί να είναι κενό.");

        validateAfm(afm);
        validateEmail(email);

        this.afm = afm.trim();
        this.email = email.trim();

        // Αρχικοποίηση λίστας
        this.stockItems = new ArrayList<>();
    }

    // Έλεγχος ΑΦΜ
    private void validateAfm(String afm) {

        if (afm == null
                || !afm.trim().matches("\\d{9}")) {

            throw new IllegalArgumentException(
                    "Το ΑΦΜ πρέπει να έχει ακριβώς 9 ψηφία.");
        }
    }

    private void validateEmail(String email) {

        if (email == null
                || !email.trim().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                    "Μη έγκυρο email.");
        }
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

    // πλήθος
    public int getProductCount() {
        return stockItems.size();
    }

    // Αναζήτηση προϊόντος με barcode
    public StockItem findStockItemByBarcode(String barcode) {

        if (barcode == null) {
            return null;
        }

        for (StockItem item : stockItems) {

            if (item.getProduct()
                    .getBarcode()
                    .equals(barcode.trim())) {

                return item;
            }
        }

        return null;
    }

    // Προσθήκη ή ενημέρωση προϊόντος
    public void addOrUpdateProduct(Product product,
                                   int stock,
                                   double price) {

        StockItem item =
                findStockItemByBarcode(
                        product.getBarcode());

        if (item == null) {

            stockItems.add(
                    new StockItem(product, stock, price));

        } else {

            item.setStock(stock);
            item.setPrice(price);
        }
    }

    // Εμφάνιση προϊόντων e-shop
    public void displayProductsForStockUpdate() {

        if (stockItems.isEmpty()) {

            System.out.println(
                    "Δεν υπάρχουν προϊόντα στο e-shop.");

            return;
        }

        System.out.println(
                "\n===== Προϊόντα e-shop: "
                        + website + " =====");

        for (StockItem item : stockItems) {

            System.out.println(item.toListString());
        }
    }

    @Override
    public String toString() {

        return "Website: " + website +
                " | ΑΦΜ: " + afm;
    }
}