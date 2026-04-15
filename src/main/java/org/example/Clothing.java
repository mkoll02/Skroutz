package org.example;

public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String barcode, String name, String brand, String size, String color) {
        super(barcode, name, "ρούχα", brand);
        this.size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + " - Size: " + size + " - Color: " + color;
    }
}