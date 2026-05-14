package org.example;

// Κλάση που συνδέει ένα προϊόν με απόθεμα και τιμή μέσα σε συγκεκριμένο e-shop
public class StockItem {

    private final Product product;
    private int stock;
    private double price;

    public StockItem(Product product, int stock, double price) {

        if (product == null) {
            throw new IllegalArgumentException("Το προϊόν δεν μπορεί να είναι null.");
        }

        this.product = product;
        setStock(stock);
        setPrice(price);
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }
    public double getPrice() {
        return price;
    }

    // update
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
        }

        this.stock = stock;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Η τιμή πρέπει να είναι θετική.");
        }

        this.price = price;
    }

    public String toListString() {
        return product +
                " | Απόθεμα: " + stock +
                " | Τιμή: " + String.format("%.2f€", price);
    }
}