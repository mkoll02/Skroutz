
package org.example;

import java.util.ArrayList;

/*
 * Κλάση που αναπαριστά ένα e-shop.
 */
public class Eshop {

    // Website του e-shop
    private String website;

    // ΑΦΜ επιχείρησης
    private String afm;

    // Email επικοινωνίας
    private String email;

    // Λίστα προϊόντων που πουλά το e-shop
    private ArrayList<StockItem> products;

    /*
     * Constructor
     */
    public Eshop(String website, String afm, String email) {
        this.website = website;
        this.afm = afm;
        this.email = email;
        products = new ArrayList<>();
    }

    public String getAfm() {
        return afm;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<StockItem> getProducts() {
        return products;
    }

    /*
     * Προσθήκη προϊόντος στο eshop
     * Αν υπάρχει ήδη, ενημερώνεται stock και τιμή
     */
    public void addProduct(Product p, int stock, double price) {

        for (StockItem item : products) {

            // Αν υπάρχει ήδη το προϊόν
            if (item.getProduct().getBarcode().equals(p.getBarcode())) {

                item.setStock(stock);
                item.setPrice(price);
                return;
            }
        }

        // Αν δεν υπάρχει δημιουργείται νέο
        products.add(new StockItem(p, stock, price));
    }

    /*
     * Εμφάνιση όλων των προϊόντων του eshop
     */
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
        return "Website: " + website + " | AFM: " + afm;
    }
}