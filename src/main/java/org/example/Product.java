package org.example;

public class Product {
    protected String barcode;
    protected String name;
    protected String category;
    protected String brand;

    public Product(String barcode, String name, String category, String brand) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public String getBarcode() { return barcode; }
    public String getName() { return name; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return barcode + " - " + name + " - " + category + " - " + brand;
    }
}