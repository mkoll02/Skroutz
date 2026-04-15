package org.example;

import org.example.Product;

public class Shoes extends Product {
    private String size;
    private String color;

    public Shoes(String barcode, String name, String brand, String size, String color) {
        super(barcode, name, "υποδήματα", brand);
        this.size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + " - Size: " + size + " - Color: " + color;
    }
}