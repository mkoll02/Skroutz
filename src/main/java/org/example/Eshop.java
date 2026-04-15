package org.example;

import java.util.ArrayList;

public class Eshop {
    private String website;
    private String afm;
    private String email;
    private ArrayList<StockItem> products;

    public Eshop(String website, String afm, String email) {
        this.website = website;
        this.afm = afm;
        this.email = email;
        products = new ArrayList<>();
    }

    public String getAfm() { return afm; }
    public String getWebsite() { return website; }
    public ArrayList<StockItem> getProducts() { return products; }

    public void addProduct(Product p, int stock, double price) {
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
        for (StockItem item : products) {
            System.out.println(item);
        }
    }

    @Override
    public String toString() {
        return website + " - " + afm;
    }
}
