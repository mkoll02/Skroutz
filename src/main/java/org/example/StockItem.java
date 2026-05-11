package org.example;

/**
 * Συνδέει προϊόν με stock και τιμή.
 */
public class StockItem {

    private final Product product;

    private int stock;
    private double price;

    public StockItem(Product product,
                     int stock,
                     double price) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Το προϊόν δεν μπορεί να είναι null.");
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

    public void setStock(int stock) {

        if (stock < 0) {

            throw new IllegalArgumentException(
                    "Το stock δεν μπορεί να είναι αρνητικό.");
        }

        this.stock = stock;
    }

    public void setPrice(double price) {

        if (price <= 0) {

            throw new IllegalArgumentException(
                    "Η τιμή πρέπει να είναι θετική.");
        }

        this.price = price;
    }

    @Override
    public String toString() {

        return product.toString() +
                "\nStock    : " + stock +
                "\nPrice    : " + price;
    }
}