package org.example;

public class StockItem {

    private Product product;
    private int stock;
    private double price;

    public StockItem(Product product, int stock, double price) {

        if (product == null) {
            throw new IllegalArgumentException("Product null");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Μη έγκυρο stock");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Μη έγκυρη τιμή");
        }

        this.product = product;
        this.stock = stock;
        this.price = price;
    }

    public Product getProduct() { return product; }
    public int getStock() { return stock; }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Μη έγκυρο stock");
        }
        this.stock = stock;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Μη έγκυρη τιμή");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return product.toString() +
                " | Stock: " + stock +
                " | Price: " + price;
    }
}