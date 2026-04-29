package org.example;

public class Shoes extends Product {

    private int size;
    private String color;

    public Shoes(String barcode, String name, String brand, int size, String color) {

        super(barcode, name, "υποδήματα", brand);

        // Μέγεθος 37-45
        if (size >= 37 && size <= 45) {
            this.size = size;
        } else {
            this.size = 40;
        }

        this.color = (color == null || color.trim().isEmpty())
                ? "unknown" : color.trim();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Size: " + size +
                " | Color: " + color;
    }
}