package org.example;

/**
 * Συνδέει προϊόν με stock και τιμή.
 */
public class StockItem {

    /**
     * Προϊόν.
     */
    private final Product product;

    /**
     * Διαθέσιμο απόθεμα.
     */
    private int stock;

    /**
     * Τιμή προϊόντος.
     */
    private double price;

    /**
     * Constructor StockItem.
     */
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

    /**
     * Getter προϊόντος.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Getter stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Getter τιμής.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter stock.
     */
    public void setStock(int stock) {

        if (stock < 0) {

            throw new IllegalArgumentException(
                    "Το stock δεν μπορεί να είναι αρνητικό.");
        }

        this.stock = stock;
    }

    /**
     * Setter τιμής.
     */
    public void setPrice(double price) {

        if (price <= 0 || price > 100000) {

            throw new IllegalArgumentException(
                    "Μη αποδεκτή τιμή προϊόντος.");
        }

        this.price = price;
    }

    @Override
    public String toString() {

        return product.toString() +
                "\nStock    : " + stock +
                "\nPrice    : " +
                String.format("%.2f", price) + "€";
    }
}