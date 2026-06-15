package org.example.model;

public class CartItem {

    private final Eshop eshop;
    private final StockItem stockItem;
    private int quantity;

    //καλάθι
    public CartItem(Eshop eshop, StockItem stockItem, int quantity) {

        if (eshop == null) {
            throw new IllegalArgumentException("Το e-shop δεν μπορεί να είναι null.");
        }

        if (stockItem == null) {
            throw new IllegalArgumentException("Το προϊόν δεν μπορεί να είναι null.");
        }

        this.eshop = eshop;
        this.stockItem = stockItem;
        setQuantity(quantity);
    }

    public Eshop getEshop() {

        return eshop;
    }

    public StockItem getStockItem() {

        return stockItem;
    }

    public Product getProduct() {

        return stockItem.getProduct();
    }

    public int getQuantity() {

        return quantity;
    }

    public double getPrice() {

        return stockItem.getPrice();
    }

    //σύνολο καλαθιού
    public double getLineTotal() {

        return quantity * getPrice();
    }

    //έλεγχος
    public boolean isSameProductAndShop(Eshop eshop, Product product) {
        return this.eshop.getWebsite().equalsIgnoreCase(eshop.getWebsite())
                && this.getProduct().equals(product);
    }

    //ενημέρωση ποσότητας
    public void setQuantity(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Το πλήθος πρέπει να είναι θετικό.");
        }

        if (quantity > stockItem.getStock()) {
            throw new IllegalArgumentException("Δεν υπάρχει αρκετό απόθεμα.");
        }

        this.quantity = quantity;
    }

    public String toCartString() {
        return "E-shop: " + eshop.getWebsite() +
                " | Προϊόν: " + getProduct().getName() +
                getProduct().getExtraInfo() +
                " | Τιμή: " + String.format("%.2f€", getPrice()) +
                " | Πλήθος: " + quantity +
                " | Σύνολο: " + String.format("%.2f€", getLineTotal());
    }
}