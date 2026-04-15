package org.example;

/*
 * Κλάση που συνδέει ένα προϊόν με ένα e-shop.
 * Αποθηκεύει:
 * - το προϊόν
 * - το απόθεμα
 * - την τιμή
 */
public class StockItem {

    // Το προϊόν
    private Product product;

    // Πλήθος τεμαχίων
    private int stock;

    // Τιμή προϊόντος
    private double price;

    /*
     * Constructor
     */
    public StockItem(Product product, int stock, double price) {
        this.product = product;
        this.stock = stock;
        this.price = price;
    }

    // Getter προϊόντος
    public Product getProduct() {
        return product;
    }

    // Getter αποθέματος
    public int getStock() {
        return stock;
    }

    // Setter αποθέματος
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Setter τιμής
    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * Εμφάνιση πληροφοριών προϊόντος στο eshop
     */
    @Override
    public String toString() {
        return product.toString() +
                " | Stock: " + stock +
                " | Price: " + price;
    }
}