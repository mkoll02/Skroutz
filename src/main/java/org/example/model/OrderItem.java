package org.example.model;

// προιόν μέσα στην παραγγελία, μία γραμμή
public class OrderItem {

    private final String website;
    private final Product product;
    private final int quantity;
    private final double price;

    public OrderItem(String website, Product product, int quantity, double price) {

        this.website = Product.normalizeText(website, "Το website δεν μπορεί να είναι κενό.");

        if (product == null) {
            throw new IllegalArgumentException("Το προϊόν δεν μπορεί να είναι null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Το πλήθος πρέπει να είναι θετικό.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Η τιμή πρέπει να είναι θετική.");
        }

        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getWebsite() {
        return website;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getLineTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Website: " + website +
                " | Προϊόν: " + product.getName() +
                product.getExtraInfo() +
                " | Πλήθος: " + quantity +
                " | Τιμή: " + String.format("%.2f€", price) +
                " | Σύνολο: " + String.format("%.2f€", getLineTotal());
    }
}