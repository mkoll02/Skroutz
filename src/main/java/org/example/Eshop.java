package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Κλάση ηλεκτρονικού καταστήματος.
 */
public class Eshop {

    private final String website;
    private final String afm;
    private final String email;

    private final ArrayList<StockItem> products;

    public Eshop(String website,
                 String afm,
                 String email) {

        validateWebsite(website);
        validateAfm(afm);
        validateEmail(email);

        this.website = website.trim();
        this.afm = afm.trim();
        this.email = email.trim();

        products = new ArrayList<>();
    }

    private void validateWebsite(String website) {

        if (website == null
                || website.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Μη έγκυρο website.");
        }
    }

    private void validateAfm(String afm) {

        if (afm == null
                || !afm.trim().matches("\\d{9}")) {

            throw new IllegalArgumentException(
                    "Μη έγκυρο ΑΦΜ.");
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

    public List<StockItem> getProducts() {

        return new ArrayList<>(products);
    }

    public void addOrUpdateProduct(Product product,
                                   int stock,
                                   double price) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Το προϊόν δεν μπορεί να είναι null.");
        }

        for (StockItem item : products) {

            if (item.getProduct()
                    .getBarcode()
                    .equals(product.getBarcode())) {

                item.setStock(stock);
                item.setPrice(price);

                return;
            }
        }

        products.add(
                new StockItem(product, stock, price));
    }

    public StockItem findStockItem(String barcode) {

        if (barcode == null
                || barcode.trim().isEmpty()) {

            return null;
        }

        for (StockItem item : products) {

            if (item.getProduct()
                    .getBarcode()
                    .equals(barcode.trim())) {

                return item;
            }
        }

        return null;
    }

    public void displayProducts() {

        if (products.isEmpty()) {

            System.out.println(
                    "Δεν υπάρχουν προϊόντα.");
            return;
        }

        System.out.println(
                "\n===== Προϊόντα e-shop =====");

        for (StockItem item : products) {

            Product p = item.getProduct();

            System.out.println(
                    "Barcode : " + p.getBarcode());

            System.out.println(
                    "Name     : " + p.getName());

            System.out.println(
                    "Category : " + p.getCategory());

            if (p instanceof Clothing clothing) {

                System.out.println(
                        "Size     : " + clothing.getSize());

                System.out.println(
                        "Color    : " + clothing.getColor());
            }

            if (p instanceof Shoes shoes) {

                System.out.println(
                        "Size     : " + shoes.getSize());

                System.out.println(
                        "Color    : " + shoes.getColor());
            }

            System.out.println(
                    "Stock    : " + item.getStock());

            System.out.println(
                    "Price    : " + item.getPrice());

            System.out.println(
                    "---------------------------");
        }
    }

    @Override
    public String toString() {

        return "Website: " + website +
                " | AFM: " + afm;
    }
}