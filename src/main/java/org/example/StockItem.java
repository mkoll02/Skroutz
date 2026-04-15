package org.example;


public class StockItem {
    private Product product;
    private int stock;
    private double price;

    public StockItem(Product product, int stock, double price) {
        this.product = product;
        this.stock = stock;
        this.price = price;
    }

    public Product getProduct() { return product; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return product.toString() + " | Stock: " + stock + " | Price: " + price;
    }
}