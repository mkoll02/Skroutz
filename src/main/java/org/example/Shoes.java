package org.example;

public class Shoes extends Product {

    private static final int MIN_SIZE = 37;
    private static final int MAX_SIZE = 45;
    private static final int DEFAULT_SIZE = 40;

    private int size;
    private String color;

    public Shoes(String barcode,
                 String name,
                 String brand,
                 int size,
                 String color) {

        super(barcode, name, "υποδήματα", brand);

        // Μέγεθος παπουτσιού
        if (size >= MIN_SIZE && size <= MAX_SIZE) {
            this.size = size;
        } else {
            this.size = DEFAULT_SIZE;
        }

        // Έλεγχος χρώματος
        if (color == null || color.trim().isEmpty()) {
            this.color = "Unknown";
        } else {
            this.color = color.trim();
        }
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {

        return super.toString() +
                "\nSize     : " + size +
                "\nColor    : " + color;
    }
}